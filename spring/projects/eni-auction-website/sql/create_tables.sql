-- Script de création de la base de données AUCTIONS_DB
--   type :      SQL Server 2012
--

CREATE TABLE ItemCategories (
    id               INTEGER IDENTITY(1,1) NOT NULL,
    description      VARCHAR(30) NOT NULL,
)

ALTER TABLE ItemCategories ADD constraint itemCategories_PK PRIMARY KEY (id)

CREATE TABLE Users (
    id               INTEGER IDENTITY(1,1) NOT NULL,
    username         VARCHAR(30) NOT NULL,
    lastname         VARCHAR(30) NOT NULL,
    firstname        VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    phone            VARCHAR(15),
    address_id       INTEGER,            -- foreign key --> OK
    password         VARCHAR(150) NOT NULL,
    credit_points    INTEGER NOT NULL,
    is_admin         bit NOT NULL,
)

ALTER TABLE Users ADD constraint users_pk PRIMARY KEY (id)
ALTER TABLE users ADD CONSTRAINT users_username_uk UNIQUE (username);
ALTER TABLE users ADD CONSTRAINT users_email_uk UNIQUE (email);

CREATE TABLE Address (
    id                           INTEGER IDENTITY(1, 1) NOT NULL,
    street                       VARCHAR(150),
    zipcode                      VARCHAR(5),
    city                         VARCHAR(30)
)

ALTER TABLE address add constraint address_pk primary key (id)


CREATE TABLE Items (
    id                            INTEGER IDENTITY(1,1) NOT NULL,
    name                          VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	auction_start_date            DATETIME2 NOT NULL,
    auction_end_date              DATETIME2 NOT NULL,
    starting_price                INTEGER NOT NULL,
    price_sold                    INTEGER,
    current_price                 INTEGER,
    user_id                       INTEGER NOT NULL, -- foreign key --> OK
    item_category_id              INTEGER NOT NULL, -- foreign key --> OK
    retrieval_address_id          INTEGER NOT NULL, -- foreign key --> OK
    is_sold                       bit NOT NULL,
    is_retrieved                  bit NOT NULL,
    image_path                    VARCHAR(255)
)

ALTER TABLE Items ADD constraint items_pk PRIMARY KEY (id)



CREATE TABLE Bids(
	id                      INTEGER IDENTITY(1,1) NOT NULL,
	bid_date                DATETIME2 NOT NULL,
	bid_amount              INTEGER NOT NULL,
	item_id                 INTEGER NOT NULL,     -- foreign key --> OK
	user_id                 INTEGER NOT NULL      -- foreign key --> OK
 )

ALTER TABLE Bids ADD constraint bids_pk PRIMARY KEY (id)



-- FOREIGN KEYS
ALTER TABLE Users
    ADD CONSTRAINT address_fk FOREIGN KEY ( address_id )
                    REFERENCES  address (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION


ALTER TABLE Items
    ADD CONSTRAINT items_users_fk FOREIGN KEY ( user_id )
                    REFERENCES Users ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION

ALTER TABLE Items
    ADD CONSTRAINT items_categories_fk FOREIGN KEY (item_category_id )
                    REFERENCES ItemCategories ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION

ALTER TABLE Items
    ADD CONSTRAINT items_address_fk FOREIGN KEY (retrieval_address_id )
                    REFERENCES ADDRESS ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION


ALTER TABLE Bids
    ADD CONSTRAINT bids_items_fk FOREIGN KEY ( item_id )
        REFERENCES Items ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION

ALTER TABLE Bids
    ADD CONSTRAINT bids_users_fk FOREIGN KEY ( user_id )
        REFERENCES Users ( id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION



