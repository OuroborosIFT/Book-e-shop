create sequence author_seq start with 1 increment by 1
create sequence book_seq start with 1 increment by 1
create sequence bucket_seq start with 1 increment by 1
create sequence genre_seq start with 1 increment by 1
create sequence order_details_seq start with 1 increment by 1
create sequence order_seq start with 1 increment by 1
create sequence user_seq start with 1 increment by 1
create table author_book (author_id bigint not null, book_id bigint not null)
create table authors (id bigint not null, seq_name varchar(255), about varchar(2147483647), last_name varchar(255), name varchar(255), patronymic varchar(255), portrait varchar(2147483647), primary key (id))
create table book_genre (book_id bigint not null, genre_id bigint not null)
create table books (id bigint not null, seq_name varchar(255), description varchar(2147483647), image varchar(2147483647), price double, title varchar(255), primary key (id))
create table bucket_books (bucket_id bigint not null, book_id bigint not null)
create table buckets (id bigint not null, seq_name varchar(255), user_id bigint, primary key (id))
create table genres (id bigint not null, seq_name varchar(255), title varchar(255), primary key (id))
create table orders (id bigint not null, seq_name varchar(255), address varchar(255), changed timestamp, created timestamp, status varchar(255), sum double, user_id bigint, primary key (id))
create table orders_details (id bigint not null, seq_name varchar(255), amount bigint, price double, book_id bigint, order_id bigint, primary key (id))
create table users (id bigint not null, seq_name varchar(255), email varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id))
alter table author_book add constraint FKmeehr164a2cpxegeiawuv40a3 foreign key (book_id) references books
alter table author_book add constraint FK7cqs8nb7l859jcwwqoawcokqf foreign key (author_id) references authors
alter table book_genre add constraint FKnkh6m50njl8771p0ll3lylqp2 foreign key (genre_id) references genres
alter table book_genre add constraint FKq0f88ptislu8lv230mvgonssl foreign key (book_id) references books
alter table bucket_books add constraint FKa3u4um6sav5eaougwft3vyk8f foreign key (book_id) references books
alter table bucket_books add constraint FKa23agv9jycs4nclqbgm7hoq0o foreign key (bucket_id) references buckets
alter table buckets add constraint FKnl0ltaj67xhydcrfbq8401nvj foreign key (user_id) references users
alter table orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users
alter table orders_details add constraint FK52sbsnqnmw0ehs0lihtwy83tp foreign key (book_id) references books
alter table orders_details add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders