-- Insert 20 sample sandwich shops
INSERT INTO sandwichshop (name, rating) VALUES
('The Classic Deli', 4.5),
('Sublime Subs', 4.2),
('Bready Winners', 4.8),
('Roll With It', 3.9),
('The Sandwich Spot', 4.6),
('Stacked High', 4.3),
('Crusty Buns', 4.1),
('The Daily Grind', 3.7),
('Lunchbox Legends', 4.9),
('The Gourmet Grab', 4.4),
('Bread & Beyond', 4.0),
('The Filling Station', 3.8),
('Slice of Heaven', 4.7),
('The Munch Hut', 4.5),
('Wrap It Up', 4.2),
('The Panini Press', 4.3),
('Bagel Bonanza', 4.1),
('The Deli Den', 3.9),
('Sandwich Sensations', 4.8),
('The Sub Shack', 4.6);

-- Insert sample ratings for each shop
INSERT INTO ratings (sandwichshop_id, rating, review) VALUES
(1, 4.5, 'Great pastrami sandwich!'),
(1, 3.0, 'A little too salty.'),
(1, 5.0, null),
(1, 4.0, 'Good but pricy'),
(2, 5.0, 'Best veggie sandwich ever!'),
(2, 4.0, null),
(2, 3.5, 'Ok'),
(2, 4.8, 'Very good!'),
(2, 4.9, 'Awesome!'),
(3, 4.0, 'Nice'),
(3, 4.2, 'I like it'),
(3, 4.8, null),
(3, 3.8, 'I had better'),
(3, 4.7, 'The best'),
(3, 5.0, 'Excellent'),
(4, 3.9, 'Fine'),
(4, 4.0, null),
(4, 4.5, 'Not bad'),
(4, 3.8, 'Not a fan'),
(5, 4.6, 'Delicious'),
(5, 4.8, null),
(5, 4.9, 'Excellent'),
(5, 4.7, 'Great'),
(5, 4.5, 'Good'),
(5, 4.4, 'I loved it'),
(6, 4.3, 'Nice'),
(6, 4.5, null),
(6, 4.6, 'Not bad'),
(6, 4.7, 'Good'),
(6, 4.8, 'Great'),
(6, 4.9, 'Excellent'),
(6, 5.0, 'The best'),
(7, 4.1, 'Tasty'),
(7, 4.3, null),
(7, 4.5, 'I liked it'),
(7, 4.6, 'Very good'),
(7, 4.7, 'Excellent'),
(7, 4.8, 'The best'),
(8, 3.7, 'Fine'),
(8, 3.9, null),
(8, 4.1, 'Ok'),
(8, 4.3, 'Good'),
(8, 4.5, 'Great'),
(8, 4.7, 'Excellent'),
(8, 4.9, 'The best'),
(8, 5.0, 'Amazing'),
(9, 4.9, 'Delicious'),
(9, 4.8, null),
(9, 4.7, 'Good'),
(9, 4.6, 'Great'),
(9, 4.5, 'Excellent'),
(9, 4.4, 'The best'),
(9, 4.3, 'Tasty'),
(10, 4.4, 'Nice'),
(10, 4.6, null),
(10, 4.8, 'Good'),
(10, 5.0, 'Great'),
(11, 4.0, 'Fine'),
(11, 4.2, null),
(11, 4.4, 'Ok'),
(11, 4.6, 'Good'),
(11, 4.8, 'Great'),
(11, 5.0, 'Excellent'),
(12, 3.8, 'Nice'),
(12, 4.0, null),
(12, 4.2, 'Ok'),
(12, 4.4, 'Good'),
(12, 4.6, 'Great'),
(12, 4.8, 'Excellent'),
(13, 4.7, 'Delicious'),
(13, 4.9, null),
(13, 4.5, 'Good'),
(13, 4.3, 'Great'),
(13, 4.1, 'Excellent'),
(13, 3.9, 'The best'),
(14, 4.5, 'Nice'),
(14, 4.7, null),
(14, 4.9, 'Good'),
(14, 4.3, 'Great'),
(14, 4.1, 'Excellent'),
(15, 4.2, 'Tasty'),
(15, 4.4, null),
(15, 4.6, 'Ok'),
(15, 4.8, 'Good'),
(15, 5.0, 'Great'),
(16, 4.3, 'Delicious'),
(16, 4.5, null),
(16, 4.7, 'Good'),
(16, 4.9, 'Great'),
(16, 4.1, 'Excellent'),
(17, 4.1, 'Nice'),
(17, 4.3, null),
(17, 4.5, 'Ok'),
(17, 4.7, 'Good'),
(17, 4.9, 'Great'),
(18, 3.9, 'Tasty'),
(18, 4.1, null),
(18, 4.3, 'Ok'),
(18, 4.5, 'Good'),
(18, 4.7, 'Great'),
(19, 4.8, 'Delicious'),
(19, 5.0, null),
(19, 4.6, 'Good'),
(19, 4.4, 'Great'),
(20, 4.6, 'Nice'),
(20, 4.8, null),
(20, 5.0, 'Good'),
(20, 4.4, 'Great');


UPDATE sandwichshop s
SET s.rating = (
    SELECT AVG(r.rating)
    FROM ratings r
    WHERE r.sandwichshop_id = s.id
);
