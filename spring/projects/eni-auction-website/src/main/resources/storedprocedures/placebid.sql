SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE PLACE_BID
(
    -- Add the parameters for the stored procedure here
    @user_id                INTEGER = NULL,
    @user_username          VARCHAR(30) = NULL,
    @user_lastname          VARCHAR(30) = NULL,
    @user_firstname         VARCHAR(30) = NULL,
    @user_email             VARCHAR(50) = NULL,
    @user_phone             VARCHAR(15) = NULL,
    @user_address_id        INTEGER = NULL,
    @user_password          VARCHAR(150) = NULL,
    @user_credit_points     INTEGER = NULL,
    @user_is_admin          bit = NULL,

    @address_id             INTEGER = NULL,
    @address_street         VARCHAR(150) = NULL,
    @address_zipcode        VARCHAR(5) = NULL,
    @address_city           VARCHAR(30) = NULL

    @item_id                INTEGER = NULL,
    @item_name              VARCHAR(30) = NULL,
    @item_description       VARCHAR(300) = NULL,
    @item_auction_start_date DATETIME2 = NULL,
    @item_auction_end_date  DATETIME2 = NULL,
    @item_starting_price    INTEGER = NULL,
    @item_price_sold        INTEGER = NULL,
    @item_current_price     INTEGER = NULL,
    @item_user_id           INTEGER = NULL,
    @item_item_category_id  INTEGER = NULL,
    @item_retrieval_address_id INTEGER = NULL,
    @item_is_sold           bit = NULL,
    @item_is_retrieved      bit = NULL
    )
AS
BEGIN
    update Users SET
                     username = @user_username,
                     lastname = @user_lastname,
                     firstname = @user_firstname,
                     email = @user_email,
                     phone = @user_phone,
                     address_id = @user_address_id,
                     password= @user_password,
                     credit_points= @user_credit_points,
                     is_admin= @user_is_admin
                WHERE id = @user_id

    update Address SET
                     street = @address_street,
                     zipcode = @address_zipcode,
                     city = @address_city
                WHERE id = @address_id

    UPDATE Items SET
                     name = @item_name,
                     description = @item_description,
                     auction_start_date = @item_auction_start_date,
                     auction_end_date = @item_auction_end_date,
                     starting_price = @item_starting_price,
                     price_sold = @item_price_sold,
                     current_price = @item_current_price,
                     user_id = @item_user_id,
                     item_category_id = @item_item_category_id,
                     retrieval_address_id = @item_retrieval_address_id,
                     is_sold = @item_is_sold,
                     is_retrieved = @item_is_retrieved
                WHERE id = @item_id
END
GO