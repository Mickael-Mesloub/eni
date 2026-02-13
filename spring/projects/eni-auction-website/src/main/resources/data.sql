
-- ADDING ITEM CATEGORIES
INSERT INTO ItemCategories(description)
VALUES ('Informatique')
INSERT INTO ItemCategories(description)
VALUES ('Ameublement')
INSERT INTO ItemCategories(description)
VALUES ('Vêtements')
INSERT INTO ItemCategories(description)
VALUES ('Sports & Loisirs')
INSERT INTO ItemCategories(description)
VALUES ('Multimédia')
INSERT INTO ItemCategories(description)
VALUES ('Divers')

-- ADD INTO ADDRESS
INSERT INTO Address(street, zipcode, city) VALUES ('12 boulevard de la Plage', 44730, 'Ecaraille')
INSERT INTO Address(street, zipcode, city) VALUES ('6 boulevard Carnot', 92340, 'Bourg-La-Reine')
INSERT INTO Address(street, zipcode, city) VALUES ('11 rue du Tour de la Vieille Ville', 46800, 'Montcuq')

-- ADD INTO USERS
INSERT INTO Users(username, lastname, firstname, email, phone, address_id, password, credit_points, is_admin)
VALUES ('LineMartin', 'MARTIN', 'Line', 'line.martin2025@campus-eni.fr', 0606060606, 1, '{bcrypt}$2a$10$iwHW1ojDQhQlIp4B4i7T1OL.73dhaZgMu6uSpta.tCA3yV1IiS4B2', 100, 1);
INSERT INTO Users(username, lastname, firstname, email, phone, address_id, password, credit_points, is_admin)
VALUES ('SebastiaanMoedt', 'MOEDT', 'Sabastiaan', 'sebastiaan.moedt2025@campus-eni.fr', 0606060608, 2, '{bcrypt}$2a$10$nbmpWySs4Ofg0wVMwmP.PuP3Dg1cRkdjTxRv6xMH9r2lLI0dNl21W', 100, 0);
INSERT INTO Users(username, lastname, firstname, email, phone, address_id, password, credit_points, is_admin)
VALUES ('MickaelMesloub', 'MESLOUB', 'Mickaël', 'mickael.mesloub2025@campus-eni.fr', 0606060607, 3, '{bcrypt}$2a$10$qDqqvHOKsqlRj88nEdjMye68yZZupTpIpnDe6dCGbz6qmshp4z55y', 100, 0);

-- ADD INTO ITEMS
INSERT INTO Items(
            name, 
            description, 
            auction_start_date, 
            auction_end_date, 
            starting_price, 
            current_price, 
            price_sold, 
            user_id, 
            item_category_id, 
            retrieval_address_id, 
            is_sold, 
            is_retrieved, 
            image_path)
    VALUES(
                'DVD Beowulf (1999)', --name--
                'Le meilleur film techno-médiéval futuriste', --description--
                GETDATE(), --start date==
                GETDATE() + 7, --end date--
                15, --starting price--
                15, --current price--
                null, --price sold--
                3, --user_id--
                5, --item_category--
                3, --retrieval address--
                0, --is sold--
                0, --is retrieved--
                '/uploads/images/beowulf.jpg') --image path

INSERT INTO Items(
            name, 
            description, 
            auction_start_date, 
            auction_end_date, 
            starting_price, 
            current_price, 
            price_sold, 
            user_id, 
            item_category_id, 
            retrieval_address_id, 
            is_sold, 
            is_retrieved, 
            image_path)
    VALUES(
                'LEGO Plongeur', --name--
                'collector, quasi introuvable. excellent état', --description--
                GETDATE(), --start date==
                GETDATE() + 7, --end date--
                75, --starting price--
                75, --current price--
                null, --price sold--
                3, --user_id--
                6, --item_category--
                3, --retrieval address--
                0, --is sold--
                0, --is retrieved--
                '/uploads/images/plongeur.jpg') --image path

INSERT INTO Items(
            name, 
            description, 
            auction_start_date, 
            auction_end_date, 
            starting_price, 
            current_price, 
            price_sold, 
            user_id, 
            item_category_id, 
            retrieval_address_id, 
            is_sold, 
            is_retrieved, 
            image_path)
    VALUES(
                'Ballon de foot', 
                'Il est crevé, parfait pour un chien', 
                GETDATE(), --start date--
                GETDATE() + 45, --end date--
                2, --starting price--
                2, --current price--
                null, --price sold--
                1, --user_id--
                3, --item_category--
                1, --retrieval address--
                0, --is sold--
                0, --is retrieved--
                '/images/default_image.png') --image path

INSERT INTO Items(
            name, 
            description, 
            auction_start_date, 
            auction_end_date, 
            starting_price, 
            current_price, 
            price_sold, 
            user_id, 
            item_category_id, 
            retrieval_address_id, 
            is_sold, 
            is_retrieved, 
            image_path)
    VALUES(
                'Clavier Cassé', 
                'Un clavier cassé, ayant appartenu à un joueur de LoL', 
                GETDATE(), --start date--
                GETDATE() + 6, --end date--
                1, --starting price--
                1, --current price--
                null, --price sold--
                2, --user_id--
                1, --item_category--
                2, --retrieval address--
                0, --is sold--
                0, --is retrieved--
                '/uploads/images/clavier.jpg') --image path

INSERT INTO Items(
            name, 
            description, 
            auction_start_date, 
            auction_end_date, 
            starting_price, 
            current_price, 
            price_sold, 
            user_id, 
            item_category_id, 
            retrieval_address_id, 
            is_sold, 
            is_retrieved, 
            image_path)
    VALUES(
                'Chat', 
                'Un chat suspect', 
                GETDATE(), --start date--
                GETDATE() + 6, --end date--
                46, --starting price--
                46, --current price--
                null, --price sold--
                1, --user_id--
                2, --item_category--
                1, --retrieval address--
                0, --is sold--
                0, --is retrieved--
                '/uploads/images/chat.png') --image path
INSERT INTO Items(
            name, 
            description, 
            auction_start_date, 
            auction_end_date, 
            starting_price, 
            current_price, 
            price_sold, 
            user_id, 
            item_category_id, 
            retrieval_address_id, 
            is_sold, 
            is_retrieved, 
            image_path)
    VALUES(
                'Un lingot', 
                'Un VRAI lingot, en VRAI or.', 
                GETDATE(), --start date--
                GETDATE() + 8, --end date--
                100000, --starting price--
                100000, --current price--
                null, --price sold--
                3, --user_id--
                2, --item_category--
                3, --retrieval address--
                0, --is sold--
                0, --is retrieved--
                '/uploads/images/lingot.jpg') --image path

-- ADD INTO BIDS
-- INSERT INTO Bids(bid_date, bid_amount, item_id, user_id)
-- VALUES (GETDATE(), 20, 1, 1)
-- INSERT INTO Bids(bid_date, bid_amount, item_id, user_id)
-- VALUES (GETDATE(), 20, 2, 3)
-- INSERT INTO Bids(bid_date, bid_amount, item_id, user_id)
-- VALUES (GETDATE(), 25, 1, 2)
