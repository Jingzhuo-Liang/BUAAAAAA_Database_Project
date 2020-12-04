create table admin(
ad_name char(10) not null,
ad_id int not null,
ad_email char(32) not null unique,
ad_pwd char(32) not null,
primary key(ad_id));

insert into admin values
('eloise',001,'eloise.zhang@outlook.com','123456'),
('LJZ',002,'xxx@buaa.edu.cn','123456'),
('YHB',003,'3038295671@qq.com','123456');

create table users(
user_name char(10) not null,
user_id int not null,
primary key(user_id),
user_email char(32) not null unique,
user_pwd char(32) not null,
user_stu enum('0','1'),
user_mem char(10),
user_vip enum('0','1') not null,
user_data date);

insert into users VALUES
('海绵宝宝',001,'haimianbaobao@buaa.edu.cn','654321','0','0001','0','2020-10-26'),
('派大星',002,'paidaxing@buaa.edu.cn','654321','1','0011','1','2020-10-25'),
('蟹老板',003,'xielaoban@buaa.edu.cn','654321','0','0111','0','2020-11-15'),
('皮老板',004,'pilaoban@buaa.edu.cn','654321','1','0111','1','2020-10-16');

CREATE TABLE platform(
plat_name char(32) not null,
plat_web char(32) not null,
plat_id int not null,
primary key(plat_id));

INSERT INTO platform VALUES
('京东','https://www.jd.com/',1),
('淘宝','https://ai.taobao.com/',2),
('苏宁易购','https://www.suning.com/',3),
('苹果官网','https://www.apple.com.cn/',4);

create table products(
prod_name varchar(128) not null,
prod_id int not null,
primary key(prod_id),
prod_plat_id int not null,
prod_store char(32),
prod_gift char(128),
prod_eval float,
check (prod_eval >=0 and prod_eval<=1),
prod_sales int,
check (prod_sales>=0),
prod_web varchar(1024) unique not null,
foreign key(prod_plat_id) REFERENCES platform(plat_id));

insert into products VALUES
('【当天发/送快充】Apple/苹果 iPhone 12 Pro 5G手机国行正品官方旗舰店iPhone11promax新品苹果125g/12pro',1,2,'博盛景数码专营店','无限充电头,钢化膜',0.96,1235,'https://detail.tmall.com/item.htm?id=629116136933&ali_trackid=2:mm_12238993_19794510_110773600209:1606034219_215_1706159517&union_lens=lensId:OPT@1606034164@0ba928d0_536b_175ef188a1a_befb@01;recoveryid:201_11.10.227.244_5156955_1606034158142;prepvid:201_11.11.94.152_7070745_1606034163173&spm=a2e1u.19484427.29996460.2&pvid=100_11.14.193.29_114772_1971606034164036308&scm=null&bxsign=tbkOU5IiqeQlaiawiq6Fo2BFcvFdbOwDir/Y8%20V%20Axkf2A3RbI7q25Yc4tJYL/MVJyBQtePWouyLfdEl2QfSb7fyLNJsJfzdjCF0wBeLJ3zCus=&sku_properties=10004:709990523;5919063:6536025'),
('【值享焕新版】Apple iPhone 12 Pro (A2408) 128GB 银色 支持移动联通电信5G 双卡双待手机',2,1,'Apple 产品京东自营旗舰店','【值享焕新】Apple 适用于iPhone 12 Pro 的 AppleCare+全方位服务计划',0.98,220000,'https://item.jd.com/100016437564.html#crumb-wrap'),
('Apple iPhone 12 Pro 128G 金色 移动联通电信5G全网通手机',3,3,'Apple产品苏宁自营旗舰店','Apple专享使用无忧',0.99,13000,'https://product.suning.com/0000000000/12122934581.html#?safp=d488778a.SFS_search.0.bb35e8bddf&safc=prd.0.0&safpn=10010&ch=cu'),
('iPhone 12 Pro',4,4,null,null,null,null,'https://www.apple.com.cn/cn-k12/shop/buy-iphone/iphone-12-pro'),
('macbook pro Apple M1 芯片，配备 8 核中央处理器和 8 核图形处理器 256GB 存储容量',5,4,null,null,null,null,'https://www.apple.com.cn/cn-k12/shop/buy-mac/macbook-pro/MYD82CH/A#');

create table collection(
coll_user_id int not null,
constraint fk_coll_user_id foreign key(coll_user_id) REFERENCES users(user_id),
coll_prod_id int not null,
coll_num int not null,
primary key(coll_user_id,coll_prod_id),
CHECK(coll_num >= 0 and coll_num <= 99),
constraint fk_coll_prod_id foreign key(coll_prod_id) REFERENCES products(prod_id));

insert into collection VALUES
(1,1,2),
(2,1,1),
(1,3,1),
(1,4,1),
(2,4,1);

create table price(
price_prod_id int not null,
foreign key(price_prod_id) REFERENCES products(prod_id),
price_date date not null,
PRIMARY key (price_prod_id,price_date),
price_ori int not null,
check(price_ori >0),
price_disc int,
check(price_disc>=0),
price_plat_disc int,
check(price_plat_disc>=0),
price_stu_disc int,
check(price_stu_disc>=0),
price_mem_disc int,
check(price_men_disc>=0));

insert into price VALUES
('1','2020-11-22',9288,null,null,null,null),
('1','2020-11-21',9288,null,null,null,null),
('1','2020-11-20',9288,null,null,null,null),
('2','2020-11-22',10297.00,null,null,null,null),
('2','2020-11-21',10100.00,null,null,null,null),
('2','2020-11-20',10137.00,null,null,null,null),
('3','2020-11-22',8499,null,null,null,null),
('3','2020-11-21',9299,null,null,null,null),
('3','2020-11-20',8799,null,null,null,null),
('4','2020-11-22',8499,null,null,null,null),
('4','2020-11-21',8499,null,null,null,null),
('4','2020-11-20',8499,null,null,null,null),
('4','2020-11-19',8499,null,null,null,null),
('5','2020-11-22',9999,null,null,800,null),
('5','2020-11-21',9999,null,null,800,null),
('5','2020-11-20',9999,null,null,800,null),
('5','2020-11-19',9999,null,null,800,null);

CREATE TABLE platdisc(
platdisc_plat_id int not null,
FOREIGN KEY(platdisc_plat_id) REFERENCES platform(plat_id),
platdisc_date date not null,
PRIMARY KEY(platdisc_plat_id,platdisc_date),
platdisc_disc char(32),
platdisc_mem_disc char(32));

insert into platdisc VALUES
(1,'2020-11-19','500-40','60-3'),
(2,'2020-11-19','300-30','-2');

create table prodisc(
prodisc_prod_id int not null,
FOREIGN KEY(prodisc_prod_id) REFERENCES products(prod_id),
prodisc_date date not null,
PRIMARY KEY (prodisc_prod_id,prodisc_date),
prodisc_disc char(32));

insert into prodisc VALUES
(1,'2020-11-20','5000-10');

create table message(
msg_prod_id int not null,
FOREIGN KEY(msg_prod_id) REFERENCES products(prod_id),
msg_user_id int not null,
FOREIGN KEY(msg_user_id) REFERENCES users(user_id),
msg_id int not null,
PRIMARY KEY(msg_id),
msg_date date not null,
msg_msg char(255) not null);

insert into message VALUES
(1,4,1,'2020-11-20','好贵呀'),
(2,3,2,'2020-11-21','这个真贵，什么时候便宜到 2 块钱我就买'),
(4,2,3,'2020-11-19','已经买了，就差个女朋友了'),
(5,1,4,'2020-11-22','M1 芯片真香');

create table msgreply(
msgr_id int not null,
PRIMARY KEY(msgr_id),
msgr_date date not null,
msgr_user_id int not null,
FOREIGN KEY(msgr_user_id) REFERENCES users(user_id),
msgr_msg_id int not null,
FOREIGN KEY(msgr_msg_id) REFERENCES message(msg_id),
msgr_msg char(255) not null);

insert into msgreply VALUES
(1,'2020-11-22',2,4,'Intel: 姓 A 的都不是好东西');

create table history(
hist_user_id int not null,
FOREIGN KEY(hist_user_id) REFERENCES users(user_id),
hist_prod_id int not null,
FOREIGN KEY(hist_prod_id) REFERENCES products(prod_id),
hist_date date not null,
PRIMARY KEY(hist_user_id,hist_prod_id,hist_date));

insert into history VALUES
(4,1,'2020-11-20'),
(3,2,'2020-11-21'),
(2,4,'2020-11-19'),
(1,5,'2020-11-22'),
(2,5,'2020-11-22');

create TABLE popularity(
pop_prod_id int not null,
PRIMARY KEY(pop_prod_id),
pop_pop int not null);








