CREATE TABLE IF NOT EXISTS users (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(100),
    password varchar(150),
    name varchar(100),
    access_key varchar(100),
    provider_id varchar(100),
    provider varchar(50) NOT NULL,
    email varchar(50),
    image_url varchar(250),
    PRIMARY KEY (id)
);