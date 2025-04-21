INSERT INTO TBL_PRODUCT (name, quantity) VALUES
    ('ASUS', 10),
    ('APLE', 11),
    ('SAMSUNG', 12),
    ('MAC', 1121);



INSERT INTO TBL_PRODUCT_NEW (name, sid) VALUES
                                        ('Desktop1', 3),
                                        ('Desktop2', 3),
                                        ('Monitor1',4),
                                        ('Monitor2', 4);

INSERT INTO TBL_GRP_PRODUCT (pid,name,hc) VALUES
                                              (-1,'IT products',true),
                                              (1,'Computers',true),
                                              (2,'Desktop Computers',false),
                                              (1,'Monitors',false);

INSERT INTO TBL_PRICE (id,p_t_id,price) VALUES
                                            (1,1,300),
                                            (2,1,300),
                                            (3,1,300),
                                            (4,1,300);

INSERT INTO TBL_PRICE_TYPE (name) VALUES
                                      ('Normal'),
                                      ('Discont');

commit;






SELECT * from TBL_GRP_PRODUCT;





SELECT * from TBL_PRODUCT;


