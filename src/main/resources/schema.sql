----------------------- START TABLES -----------------------
-- MEMBER
CREATE TABLE member (
    memberID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15),
    homeAddress VARCHAR(255),
    memberType ENUM('admin', 'buyer', 'seller') NOT NULL,
    PRIMARY KEY ( memberID )
);

-- MEMBER/SELLER
CREATE TABLE seller (
    memberID INT NOT NULL,
    bankAccount VARCHAR(20) NOT NULL,
    routingNumber VARCHAR(20) NOT NULL,
    PRIMARY KEY ( memberID ),
    FOREIGN KEY( memberID ) REFERENCES member(memberID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

-- MEMBER/BUYER
CREATE TABLE buyer (
    memberID INT NOT NULL,
    shippingAddress VARCHAR(200),
    PRIMARY KEY ( memberID ),
    FOREIGN KEY( memberID ) REFERENCES member(memberID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

-- MEMBER/ADMIN
CREATE TABLE admin (
    memberID INT NOT NULL,
    approved BOOLEAN NOT NULL DEFAULT 0,
    PRIMARY KEY ( memberID ),
    FOREIGN KEY( memberID ) REFERENCES member(memberID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

-- CATEGORY
CREATE TABLE category (
    categoryID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL UNIQUE,
    PRIMARY KEY ( categoryID )
);

-- ITEM
CREATE TABLE item (
    itemID INT NOT NULL AUTO_INCREMENT,
    sellerID INT NOT NULL,
    itemName VARCHAR(40) NOT NULL,
    description VARCHAR(200) NOT NULL,
    image VARCHAR(200),
    startingBid DECIMAL(10, 2) NOT NULL,
    bidStartDate DATETIME NOT NULL,
    bidEndDate DATETIME NOT NULL,
    categoryID INT NOT NULL,
    size VARCHAR(10) NOT NULL,
    PRIMARY KEY ( itemID, sellerID ),
    FOREIGN KEY( sellerID ) REFERENCES seller(memberID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

-- BID
CREATE TABLE bid (
    itemID INT NOT NULL,
    buyerID INT NOT NULL,
    bidPrice DECIMAL(10, 2) NOT NULL,
    bidTime DATETIME NOT NULL,
    isWinner BOOLEAN NOT NULL DEFAULT 0,
    PRIMARY KEY( itemID, buyerID, bidPrice ),
    FOREIGN KEY( itemID ) REFERENCES item(itemID)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY( buyerID ) REFERENCES buyer(memberID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

-- FEEDBACK
CREATE TABLE feedback (
    senderID INT NOT NULL,
    receiverID INT NOT NULL,
    itemID INT NOT NULL,
    rating DECIMAL(2, 1) UNSIGNED NOT NULL DEFAULT 5 CHECK (rating <= 5),
    comment VARCHAR(200) NOT NULL,
    PRIMARY KEY ( senderID, receiverID, itemID ),
    FOREIGN KEY( senderID ) REFERENCES member(memberID)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY( receiverID ) REFERENCES member(memberID)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY( itemID ) REFERENCES item(itemid)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
------------------------ END TABLES ------------------------

------------------------ START VIEWS -----------------------
-- TOP AUCTIONS VIEW
CREATE OR REPLACE VIEW top_auctions AS
SELECT item.*, sorted_items.totalBids totalBids
FROM item NATURAL JOIN (
    SELECT item.itemID, COUNT(bid.itemID) totalBids
    FROM item LEFT OUTER JOIN bid
    ON item.itemID = bid.itemID
    WHERE NOW() < item.bidEndDate
    GROUP BY (item.itemID)
    ORDER BY COUNT(bid.itemID) DESC
    LIMIT 10
) sorted_items;

-- TOP CATEGORIES VIEW
CREATE OR REPLACE VIEW top_categories AS
SELECT category.name AS category, COUNT(item.itemID) AS totalItems
FROM category LEFT OUTER JOIN item
ON category.categoryID = item.categoryID 
WHERE NOW() < item.bidEndDate
GROUP BY(category.categoryID)
ORDER BY COUNT(item.itemID) DESC
LIMIT 10;

-- ITEM DETAIL VIEW
CREATE OR REPLACE VIEW item_detail AS
SELECT item.*, category.name category, member.name sellerName,
IFNULL(bid.current, item.startingBid) currentBid, bid.count totalBids
FROM (item NATURAL JOIN (
    SELECT item.itemID, MAX(bid.bidPrice) current,
    COUNT(bid.itemID) count
    FROM item LEFT OUTER JOIN bid
    ON item.itemID = bid.itemID
    GROUP BY item.itemID
) as bid NATURAL JOIN category)
JOIN member ON item.sellerID = member.memberID;
------------------------- END VIEWS ------------------------