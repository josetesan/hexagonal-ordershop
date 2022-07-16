create table "ORDER" (
    id uuid primary key ,
    create_date timestamp not null default now(),
    closed_date timestamp,
    status int not null default 0
);


create table PRODUCT (
    id uuid primary key ,
    amount int not null default 0,
    price double not null default 0.0,
    name varchar(32) not null
);

create table ORDER_ITEMS (
    create_date timestamp not null default now(),
    order_id uuid not null,
    product_id uuid not null,
    amount int not null default 0,
    total_price double not null default 0.0,
    PRIMARY KEY (order_id, product_id)
);

