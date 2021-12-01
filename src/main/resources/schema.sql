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
    ON DELETE RESTRICT
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
    ON DELETE RESTRICT,
    FOREIGN KEY( buyerID ) REFERENCES buyer(memberID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
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
    ON DELETE RESTRICT,
    FOREIGN KEY( receiverID ) REFERENCES member(memberID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY( itemID ) REFERENCES item(itemid)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);
------------------------ END TABLES ------------------------

------------------------ START VIEWS -----------------------
-- TOP AUCTIONS VIEW
CREATE OR REPLACE VIEW top_auctions AS
SELECT item.*, category.name AS category, member.name, count(*) AS totalBids
FROM bid, item, category, member
WHERE bid.itemID = item.itemID
AND member.memberID = item.sellerID
AND NOW() < item.bidEndDate
GROUP BY (bid.itemID)
ORDER BY COUNT(bid.itemID) DESC
LIMIT 10;

-- TOP CATEGORIES VIEW
CREATE OR REPLACE VIEW top_categories AS
SELECT category.name AS category, COUNT(item.itemID) AS totalItems
FROM category LEFT OUTER JOIN item
ON category.categoryID = item.categoryID 
GROUP BY(category.categoryID)
ORDER BY COUNT(item.itemID) DESC
LIMIT 10;
------------------------- END VIEWS ------------------------