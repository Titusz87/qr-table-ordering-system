CREATE TABLE IF NOT EXISTS user_data(
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS menu_data(
    id SERIAL PRIMARY KEY,
    dish_name VARCHAR(100) NOT NULL,
    ingredients VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    dietary VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_data(

    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES user_data(id),
    placed_at TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    payment_type VARCHAR(20) NOT NULL,
    comment TEXT,
    total NUMERIC(10,2) NOT NULL
);

CREATE TABLE  IF NOT EXISTS order_items (
   id SERIAL PRIMARY KEY,
   order_id UUID REFERENCES order_data(id),
   menu_id INT REFERENCES menu_data(id),
   quantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS payment_data(
    id UUID PRIMARY KEY,
    payment_type VARCHAR(20) NOT NULL,
    amount INT NOT NULL,
    reference VARCHAR(100) NOT NULL
);

