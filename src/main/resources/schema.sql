DROP TABLE IF EXISTS TBL_PRODUCT;
DROP TABLE IF EXISTS TBL_PRODUCT_NEW;
DROP TABLE IF EXISTS TBL_GRP_PRODUCT;
DROP TABLE IF EXISTS TBL_PRICE;
DROP TABLE IF EXISTS TBL_PRICE_TYPE;



CREATE TABLE TBL_PRODUCT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    quantity INT NOT NULL
);





CREATE TABLE TBL_GRP_PRODUCT (
                                 sid INT AUTO_INCREMENT PRIMARY KEY,
                                 pid INT,
                                 hc boolean,
                                 name VARCHAR(250) NOT NULL
);

CREATE TABLE TBL_PRODUCT_NEW (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(250) NOT NULL,
                             sid INT NOT NULL
);

CREATE TABLE TBL_PRICE (
                           p_id INT AUTO_INCREMENT PRIMARY KEY,
                           id INT NOT NULL,
                           p_t_id INT NOT NULL,
                           price INT NOT NULL
);

CREATE TABLE TBL_PRICE_TYPE (
                                p_t_id INT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(250) NOT NULL
);

commit;




