-- CREATE BUYER
INSERT INTO member (
`name`, `email`, `password`, `phoneNumber`, `homeAddress`, `memberType`
) VALUES ('john', 'test@gmail.com', 'test123', '5100021234', '1000 paterson rd
richardson TX', 'buyer');
INSERT INTO buyer (`memberID`, `shippingAddress`)
VALUES (LAST_INSERT_ID(), '6734 Buell Alley richardson TX');

-- CREATE SELLER
INSERT INTO member (
`name`, `email`, `password`, `phoneNumber`, `homeAddress`, `memberType`
) VALUES ('mike', 'test123@gmail.com', 'test123', '5100020034', '1234 rd richardson TX',
'seller');
INSERT INTO seller(`memberID`, `bankAccount`, `routingNumber`)
VALUES (LAST_INSERT_ID(), '1234567890123', '123456789');

-- CREATE ADMIN
INSERT INTO member (
`name`, `email`, `password`, `phoneNumber`, `homeAddress`, `memberType`
) VALUES ('boss', 'root@gmail.com', 'root123', '4083221234', '2345 river pt richardson
TX', 'admin');
INSERT INTO admin(`memberID`, `approved`)
VALUES (LAST_INSERT_ID(), TRUE);

-- CREATE CATEGORY
INSERT INTO category(`name`) VALUES ('shirts'), ('t-shirts'), ('blouses'), ("pants");

-- CREATE ITEM
-- Insert into item(
--     `sellerID`, `itemName`, `description`, `startingBid`,
--     `bidStartDate`, `bidEndDate`, `categoryID`, `size`
-- ) VALUES (
--     2, 'T-shirt', 'new T-shirt ', 12,
--     '2020-01-01 10:10:10', '2020-01-01 10:25:10', 1, '15'
-- );

-- CREATE BID
-- INSERT INTO bid(itemID, buyerID, bidPrice, bidTime)
-- values(1, 1, 14, '2020-01-01 10:14:10');

-- CREATE FEEDBACK
-- INSERT INTO feedback(`senderID`, `receiverID`, `itemID`, `rating`, `comment`)
-- values(1, 2, 1, 5, 'perfect experience');

