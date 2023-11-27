CREATE TABLE IF NOT EXISTS client(
    id SERIAL PRIMARY KEY,
    nui VARCHAR (10) UNIQUE,
    fullname VARCHAR (50),
    address   VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS product(
    id SERIAL PRIMARY KEY,
    description VARCHAR (50) ,
    brand VARCHAR (50),
    price   DECIMAL (10, 2),
    stock   INT
);

CREATE TABLE IF NOT EXISTS invoice(
    id SERIAL PRIMARY KEY,
    code VARCHAR (50) UNIQUE,
    create_at DATE,
    total   DECIMAL (12, 2),
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS detail(
    id SERIAL PRIMARY KEY,
    quantity VARCHAR (50),
    price   DECIMAL (10, 2),
    invoice_id INT,
    product_id INT,
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);