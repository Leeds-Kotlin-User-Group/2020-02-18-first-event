create sequence demos_seq start 1;
create table demo.demos (
    id int not null default nextval('demos_seq'::regclass),
    name varchar(255),
    primary key(id)
);