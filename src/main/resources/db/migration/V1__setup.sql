create table orders (
    id bigint auto_increment primary key ,
    create_date timestamp not null default now(),
    closed_date timestamp,
    status int not null default 0
);


create table products (
    id bigint auto_increment primary key ,
    create_date timestamp not null default now(),
    amount int not null default 0,
    price double not null default 0.0,
    name varchar(32) not null
);

create table order_products (
    create_date timestamp not null default now(),
    order_id bigint not null,
    product_id bigint not null,
    amount int not null default 0,
    total_price double not null default 0.0,
    PRIMARY KEY (order_id, product_id)
);

