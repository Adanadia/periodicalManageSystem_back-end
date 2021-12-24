create table user_table(
    user_num char(10) primary key,
    user_name varchar(20) not null,
    user_email varchar(50) not null check ( user_email like '%_@_._%' ),
    password varchar(50) not null,
    balance double(10,2) default 0.00 check ( balance >= 0 ),
    user_profile varchar(100) null
);

create table borrow_table(
    volume int not null,
    year int not null,
    stage int not null,
    user_num char(10) not null,
    periodical_name varchar(20) not null,
    borrow_date char(10) not null,
    due_date char(10) not null,
    return_date char(10) default null
);

create table periodical_register(
    periodical_name varchar(20) not null,
    volume int not null,
    year int not null,
    stage int not null,
    deposit double default 0.00,
    primary key (periodical_name,volume,year,stage),
    periodical_cover varchar(100) null
);

create table periodical_content(
    periodical_name varchar(20) not null,
    volume int not null,
    year int not null,
    stage int not null,
    paper_title varchar(100) ,
    paper_author varchar(50) default null,
    paper_keyword_1 varchar(200) default null,
    paper_keyword_2 varchar(200) default null,
    paper_keyword_3 varchar(200) default null,
    paper_keyword_4 varchar(200) default null,
    paper_keyword_5 varchar(200) default null,
    periodical_page varchar(10) default null,
    primary key (periodical_name,paper_title)
);

create table periodical_catalogue(
    ISSN char(9) not null unique,
    CN varchar(15) not null unique,
    mailing_code varchar(10) not null unique,
    public_cycle char(4) not null check ( public_cycle in ('月刊','年刊') ),
    periodical_name varchar(20) not null,
    primary key (periodical_name)
);

create table administrator(
    id varchar(20) primary key,
    password varchar(20) not null default '123456'
);
create table periodical_subscription(
    mailing_code varchar(10) not null unique,
    periodical_name varchar(20) not null,
    subscription_year int(4) not null,
    primary key (mailing_code,subscription_year)
);

create table periodical_reserve(
    user_num char(10),
    periodical_name varchar(20) not null,
    volume int not null,
    year int not null,
    stage int not null,
    reserve_date char(10) default null
)
