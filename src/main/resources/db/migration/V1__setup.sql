create table ORDERS (
    id uuid  NOT NULL DEFAULT gen_random_uuid(),
    create_date timestamp not null default now(),
    closed_date timestamp,
    status varchar(12) not null default 0,
    PRIMARY KEY (ID)
);


create table PRODUCTS (
    id uuid  NOT NULL DEFAULT gen_random_uuid(),
    amount int not null default 0,
    price numeric(6,2)  not null default 0.0,
    name varchar(32) not null,
    PRIMARY KEY (ID)
);

create table ORDER_ITEM (
    order_id uuid not null,
    product_id uuid not null,
    create_date timestamp not null default now(),
    amount int not null default 0,
    price numeric(8,2) not null default 0.0,
    PRIMARY KEY (order_id, product_id)
);

