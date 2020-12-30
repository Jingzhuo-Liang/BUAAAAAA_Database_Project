/*
 Navicat Premium Data Transfer

 Source Server         : hiahia
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : huobisanjia

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 25/12/2020 13:59:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `ad_name` char(10) NOT NULL,
  `ad_id` int(11) NOT NULL,
  `ad_email` char(32) NOT NULL,
  `ad_pwd` char(32) NOT NULL,
  `enabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`ad_id`),
  UNIQUE KEY `unique_ad_email` (`ad_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
BEGIN;
INSERT INTO `admins` VALUES ('eloise', 1, 'eloise.zhang@outlook.com', '123456', 1);
INSERT INTO `admins` VALUES ('LJZ', 2, 'xxx@buaa.edu.cn', '123456', 1);
INSERT INTO `admins` VALUES ('YHB', 3, '3038295671@qq.com', '123456', 1);
INSERT INTO `admins` VALUES ('test1', 4, 'test1@buaa.edu.cn', '123', 0);
INSERT INTO `admins` VALUES ('test2', 5, 'test2@buaa.edu.cn', '123', 0);
COMMIT;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `comid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`userid`,`comid`,`time`),
  KEY `fk_comid` (`comid`),
  CONSTRAINT `fk_comid` FOREIGN KEY (`comid`) REFERENCES `commodity` (`id`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------
BEGIN;
INSERT INTO `collection` VALUES (1, 1, '2020-12-07');
INSERT INTO `collection` VALUES (1, 2, '2020-11-22');
INSERT INTO `collection` VALUES (2, 2, '2020-11-22');
INSERT INTO `collection` VALUES (10, 1, '2020-12-17');
INSERT INTO `collection` VALUES (12, 1, '2020-12-17');
INSERT INTO `collection` VALUES (16, 1, '2020-12-17');
INSERT INTO `collection` VALUES (18, 1, '2020-12-03');
COMMIT;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `cid` int(11) NOT NULL,
  `comname` varchar(128) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform` int(11) NOT NULL,
  `storename` char(32) DEFAULT NULL,
  `gift` char(128) DEFAULT NULL,
  `favorrate` float DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `url` varchar(1024) NOT NULL,
  `piclink` varchar(512) DEFAULT NULL,
  `newestprice` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`cid`),
  UNIQUE KEY `url` (`url`),
  KEY `platform` (`platform`),
  CONSTRAINT `commodity_ibfk_1` FOREIGN KEY (`platform`) REFERENCES `platform` (`plat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
BEGIN;
INSERT INTO `commodity` VALUES (1, '【当天发/送快充】Apple/苹果 iPhone 12 Pro 5G手机国行正品官方旗舰店iPhone11promax新品苹果125g/12pro', 1, 1, '博盛景数码专营店', '无限充电头,钢化膜', 0.96, 1235, 'https://detail.tmall.com/item.htm?id=629116136933&ali_trackid=2:mm_12238993_19794510_110773600209:1606034219_215_1706159517&union_lens=lensId:OPT@1606034164@0ba928d0_536b_175ef188a1a_befb@01;recoveryid:201_11.10.227.244_5156955_1606034158142;prepvid:201_11.11.94.152_7070745_1606034163173&spm=a2e1u.19484427.29996460.2&pvid=100_11.14.193.29_114772_1971606034164036308&scm=null&bxsign=tbkOU5IiqeQlaiawiq6Fo2BFcvFdbOwDir/Y8%20V%20Axkf2A3RbI7q25Yc4tJYL/MVJyBQtePWouyLfdEl2QfSb7fyLNJsJfzdjCF0wBeLJ3zCus=&sku_properties=10004:709990523;5919063:6536025', 'https://i.loli.net/2020/12/06/hX9TxvglfICOjY3.png', 9288);
INSERT INTO `commodity` VALUES (1, '【值享焕新版】Apple iPhone 12 Pro (A2408) 128GB 银色 支持移动联通电信5G 双卡双待手机', 2, 1, 'Apple 产品京东自营旗舰店', '【值享焕新】Apple 适用于iPhone 12 Pro 的 AppleCare+全方位服务计划', 0.98, 220000, 'https://item.jd.com/100016437564.html#crumb-wrap', 'https://i.loli.net/2020/12/06/uDEUlh7GNRqxwZb.png', 10297);
INSERT INTO `commodity` VALUES (1, 'Apple iPhone 12 Pro 128G 金色 移动联通电信5G全网通手机', 3, 3, 'Apple产品苏宁自营旗舰店', 'Apple专享使用无忧', 0.99, 13000, 'https://product.suning.com/0000000000/12122934581.html#?safp=d488778a.SFS_search.0.bb35e8bddf&safc=prd.0.0&safpn=10010&ch=cu', 'https://i.loli.net/2020/12/06/yRaWg17xPdjDcsq.png', 8499);
INSERT INTO `commodity` VALUES (1, 'iPhone 12 Pro', 4, 2, 'Apple 产品京东自营旗舰店', '无限充电头,钢化膜', 0, 0, 'https://www.apple.com.cn/cn-k12/shop/buy-iphone/iphone-12-pro', 'https://i.loli.net/2020/11/22/sEkhmxv9YBctOjL.jpg', 8499);
INSERT INTO `commodity` VALUES (2, 'macbook pro Apple M1 芯片，配备 8 核中央处理器和 8 核图形处理器 256GB 存储容量', 5, 3, 'Apple 产品京东自营旗舰店', '无限充电头,钢化膜', 0, 0, 'https://www.apple.com.cn/cn-k12/shop/buy-mac/macbook-pro/MYD82CH/A#', 'https://i.loli.net/2020/11/22/rbw8oujy7TxOzVk.jpg', 9999);
INSERT INTO `commodity` VALUES (1, '华为P40 Pro 5G手机 亮黑色 全网通(8G+256G)', 6, 1, '掌视界数码旗舰店', '通用适配钢化膜/软膜 精美钢化膜', 0, 0, 'https://item.jd.com/67119376996.html', 'https://img11.360buyimg.com/n7/jfs/t1/134701/34/16987/157532/5fbcc859Eed6aca07/5ef100f49576ba6c.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '荣耀Play4T Pro 麒麟810芯片 OLED屏幕指纹 4800万高感光夜拍三摄 22.5W超级快充 全网通8GB+128GB 冰岛幻境', 7, 1, '荣耀京东自营旗舰店', '通用适配钢化膜/软膜 精美钢化膜', 0, 0, 'https://item.jd.com/100006788665.html', 'https://img10.360buyimg.com/n7/jfs/t1/109383/15/11613/242507/5e8c4c3dE901f4367/723da17587f13bb7.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI 畅享 10S 炫彩OLED屏幕指纹4800万超广角AI三摄4000mAh大电池6GB+128GB幻夜黑全网通双4G', 8, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜', 0, 0, 'https://item.jd.com/100010422114.html', 'https://img14.360buyimg.com/n7/jfs/t1/100715/14/3476/76465/5de0d76eE1a8e56d4/48126192beaafccb.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI Mate 30E Pro 5G麒麟990E SoC芯片 双4000万徕卡电影影像 8GB+128GB亮黑色全网通', 9, 1, '华为京东自营官方旗舰店', '华为P40Pro 液态硅胶手机壳', 0, 0, 'https://item.jd.com/100016170950.html', 'https://img14.360buyimg.com/n0/jfs/t1/130534/19/13282/138385/5f917b4fE0a8f9aca/44578c39df00079c.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '京品手机\n荣耀20青春版 AMOLED屏幕指纹 4000mAh大电池 20W快充 4800万 手机 6GB+64GB 冰岛幻境', 10, 1, '荣耀京东自营旗舰店', '【核桃编程】零基础编程（低年级6-9岁/高年级10-12岁）', 0, 0, 'https://item.jd.com/100005207363.html', 'https://img14.360buyimg.com/n0/jfs/t1/76587/14/12877/497285/5da51d7cEbb0bf38c/0f96fe7f6a8596a0.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '京品手机\n华为 HUAWEI 畅享Z 5G 天玑800 5G SoC 芯片4800万高感光夜拍6.5英寸90HZ畅滑屏8GB+128GB幻夜黑5G双模全网通', 11, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜', 0, 0, 'https://item.jd.com/100013325266.html', 'https://img14.360buyimg.com/n0/jfs/t1/120816/13/7203/365545/5f0d1f05E30e5edc8/8408126cdbc59075.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI P40 Pro 麒麟990 5G SoC芯片 5000万超感知徕卡四摄 50倍数字变焦 8GB+128GB亮黑色全网通5G', 12, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100012015134.html', 'https://img14.360buyimg.com/n0/jfs/t1/102355/27/16507/458075/5e7cb7b7E3071625c/a90c1d0514532adc.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI P40 麒麟990 5G SoC芯片 5000万超感知徕卡三摄 30倍数字变焦 8GB+128GB亮黑色全网通5G', 13, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100012014970.html', 'https://img14.360buyimg.com/n0/jfs/t1/90275/22/16448/88997/5e7cbc95E216daec4/9316593be50948f8.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI 畅享10 Plus 超高清全视屏前置悬浮式镜头4800万超广角AI三摄 8GB+128GB幻夜黑全网通双4G', 14, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100009093988.html', 'https://img14.360buyimg.com/n0/jfs/t1/79632/11/13100/407944/5da84996E9719f615/46eecf98fef315ed.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI 畅享20 5G AI三摄闪拍6.6英寸影音大屏 5000mAh大电池4GB+64GB亮黑色5G双模全网通', 15, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100016254632.html', 'https://img14.360buyimg.com/n0/jfs/t1/135052/37/13983/56069/5f9a3cfeE9e06c613/0c48cd669d3171e7.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '荣耀Play4 5G双模 6400万锐力四摄 4300mAh大电池 VC液冷散热 6GB+128GB 幻夜黑', 16, 1, '荣耀京东自营旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100013525120.html', 'https://img14.360buyimg.com/n0/jfs/t1/149285/24/1422/210372/5ef464a9E56630594/dc7e6e1b5a42ca7b.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '荣耀20青春版 华为手机 幻夜黑 全网通(6GB+64GB)', 17, 1, '一佳手机专营店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/10020353760568.html', 'https://img14.360buyimg.com/n0/jfs/t1/125938/8/9658/496217/5f350659Eab638867/983b82a624dd0d97.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为mate40pro 5G手机 亮黑色 8+256G全网通（碎屏险套餐）', 18, 1, '炜东电商旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/10023108638660.html', 'https://img14.360buyimg.com/n0/jfs/t1/155208/5/6141/202415/5fb28e12E056cf212/3435e93dc090985e.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '荣耀30 50倍远摄 麒麟985 5G 4000万超广角AI四摄 3200W美颜自拍 全网通版8GB+128GB 幻夜黑 全面屏', 19, 1, '荣耀京东自营旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100012597526.html', 'https://img14.360buyimg.com/n0/jfs/t1/107594/6/12496/138399/5e95dddfE1b7d6ef7/52dd0f4d83460485.jpg', 2000);
INSERT INTO `commodity` VALUES (1, '华为nova5i 手机 苏音蓝 （4GB+128GB）', 20, 1, '聚捷联盛手机旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/30191153091.html', NULL, 2000);
INSERT INTO `commodity` VALUES (1, '京品手机\n荣耀V30 PRO DXO122分 5G双模 麒麟990 5GSOC芯片 双超级快充 游戏8GB+128GB幻夜星河 双卡双待', 21, 1, '荣耀京东自营旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100010260256.html', NULL, 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI nova 7 Pro 3200万追焦双摄 50倍潜望式变焦四摄 5G SoC芯片 8GB+128GB 仲夏紫全网通5G', 22, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100006987227.html', NULL, 2000);
INSERT INTO `commodity` VALUES (1, '荣耀20青春版手机全网通 幻夜黑 全网通 （6G+64GB）', 23, 1, '聚捷联盛手机旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/65899769450.html', NULL, 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI Mate40 Pro 全网通5G手机 亮黑色 全网通(8+256G)无线充套装', 24, 1, '聚捷联盛手机旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/10023386974728.html', NULL, 2000);
INSERT INTO `commodity` VALUES (1, '华为 HUAWEI 畅享20 Plus 5G 6.63英寸90Hz高刷真全屏4800万高感光夜拍8GB+128GB幻夜黑5G双模全网通', 25, 1, '华为京东自营官方旗舰店', '通用适配钢化膜/软膜 精美钢化膜 入仓', 0, 0, 'https://item.jd.com/100015097462.html', NULL, 2000);
COMMIT;

-- ----------------------------
-- Table structure for commoditydiscount
-- ----------------------------
DROP TABLE IF EXISTS `commoditydiscount`;
CREATE TABLE `commoditydiscount` (
  `comid` int(11) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL,
  `discount` char(32) DEFAULT NULL,
  PRIMARY KEY (`comid`,`time`),
  CONSTRAINT `commoditydiscount_ibfk_1` FOREIGN KEY (`comid`) REFERENCES `commodity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commoditydiscount
-- ----------------------------
BEGIN;
INSERT INTO `commoditydiscount` VALUES (1, '2020-12-06', '800-10');
INSERT INTO `commoditydiscount` VALUES (1, '2020-12-07', '1000-20');
COMMIT;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `comid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`userid`,`comid`,`time`),
  KEY `comid` (`comid`),
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `history_ibfk_2` FOREIGN KEY (`comid`) REFERENCES `commodity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history
-- ----------------------------
BEGIN;
INSERT INTO `history` VALUES (1, 1, '2020-12-17');
INSERT INTO `history` VALUES (1, 2, '2020-11-22');
INSERT INTO `history` VALUES (2, 1, '2020-12-16');
INSERT INTO `history` VALUES (3, 1, '2020-12-06');
INSERT INTO `history` VALUES (4, 1, '2020-12-17');
INSERT INTO `history` VALUES (5, 1, '2020-12-07');
INSERT INTO `history` VALUES (6, 1, '2020-12-09');
INSERT INTO `history` VALUES (7, 1, '2020-12-06');
INSERT INTO `history` VALUES (8, 1, '2020-12-17');
INSERT INTO `history` VALUES (9, 1, '2020-12-07');
INSERT INTO `history` VALUES (10, 1, '2020-12-17');
INSERT INTO `history` VALUES (11, 1, '2020-12-07');
INSERT INTO `history` VALUES (12, 1, '2020-12-17');
INSERT INTO `history` VALUES (13, 1, '2020-12-04');
INSERT INTO `history` VALUES (15, 1, '2020-12-07');
INSERT INTO `history` VALUES (16, 1, '2020-12-17');
INSERT INTO `history` VALUES (17, 1, '2020-12-06');
INSERT INTO `history` VALUES (19, 1, '2020-12-04');
COMMIT;

-- ----------------------------
-- Table structure for leavemessage
-- ----------------------------
DROP TABLE IF EXISTS `leavemessage`;
CREATE TABLE `leavemessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `comid` int(11) NOT NULL,
  `time` date NOT NULL,
  `message` char(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comid` (`comid`),
  KEY `userid` (`userid`),
  CONSTRAINT `leavemessage_ibfk_1` FOREIGN KEY (`comid`) REFERENCES `commodity` (`id`),
  CONSTRAINT `leavemessage_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leavemessage
-- ----------------------------
BEGIN;
INSERT INTO `leavemessage` VALUES (1, 1, 1, '2020-11-21', '1 号 认为iphone1 可真是个好东西');
INSERT INTO `leavemessage` VALUES (2, 1, 2, '2020-11-21', '1 号 认为iphone2 可真是个好东西');
INSERT INTO `leavemessage` VALUES (3, 1, 3, '2020-11-21', '1 号 认为iphone3 可真是个好东西');
INSERT INTO `leavemessage` VALUES (4, 1, 4, '2020-11-21', '1 号 认为iphone4 可真是个好东西');
INSERT INTO `leavemessage` VALUES (5, 2, 1, '2020-11-21', '2 号 认为iphone1 用户体验不是很好');
INSERT INTO `leavemessage` VALUES (6, 2, 2, '2020-11-21', '2 号 认为iphone2 用户体验不是很好');
INSERT INTO `leavemessage` VALUES (7, 2, 3, '2020-11-21', '2 号 认为iphone3 用户体验不是很好');
INSERT INTO `leavemessage` VALUES (8, 2, 4, '2020-11-21', '2 号 认为iphone4 用户体验不是很好');
INSERT INTO `leavemessage` VALUES (12, 1, 2, '2020-11-21', '真不错');
INSERT INTO `leavemessage` VALUES (13, 1, 1, '2020-11-21', '？？？');
INSERT INTO `leavemessage` VALUES (14, 1, 2, '2020-11-21', '还行');
INSERT INTO `leavemessage` VALUES (15, 1, 2, '2020-11-21', '真的是这样吗');
INSERT INTO `leavemessage` VALUES (16, 1, 2, '2020-11-21', '不是吧');
INSERT INTO `leavemessage` VALUES (17, 1, 2, '2020-11-21', '不会吧');
INSERT INTO `leavemessage` VALUES (18, 1, 2, '2020-11-21', '别啊');
INSERT INTO `leavemessage` VALUES (19, 1, 2, '2020-11-21', '啊啊啊');
INSERT INTO `leavemessage` VALUES (20, 1, 2, '2020-11-21', '太好了');
INSERT INTO `leavemessage` VALUES (21, 1, 1, '2020-11-21', '好极了');
INSERT INTO `leavemessage` VALUES (22, 1, 1, '2020-11-21', '真不错');
INSERT INTO `leavemessage` VALUES (23, 1, 4, '2020-11-21', '还可以吧');
INSERT INTO `leavemessage` VALUES (24, 1, 4, '2020-11-21', '一半一半');
INSERT INTO `leavemessage` VALUES (25, 1, 3, '2020-11-21', '哈好');
INSERT INTO `leavemessage` VALUES (57, 1, 1, '2020-11-23', '好东西');
INSERT INTO `leavemessage` VALUES (58, 1, 3, '2020-11-24', '你好');
INSERT INTO `leavemessage` VALUES (59, 1, 1, '2020-11-25', 'dfghjkl');
INSERT INTO `leavemessage` VALUES (60, 1, 1, '2020-12-07', '可以可以');
INSERT INTO `leavemessage` VALUES (61, 1, 1, '2020-12-07', '一般一般');
COMMIT;

-- ----------------------------
-- Table structure for platform
-- ----------------------------
DROP TABLE IF EXISTS `platform`;
CREATE TABLE `platform` (
  `plat_name` char(32) NOT NULL,
  `plat_web` char(32) NOT NULL,
  `plat_id` int(11) NOT NULL,
  PRIMARY KEY (`plat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform
-- ----------------------------
BEGIN;
INSERT INTO `platform` VALUES ('京东', 'https://www.jd.com/', 1);
INSERT INTO `platform` VALUES ('淘宝', 'https://ai.taobao.com/', 2);
INSERT INTO `platform` VALUES ('苏宁易购', 'https://www.suning.com/', 3);
INSERT INTO `platform` VALUES ('苹果官网', 'https://www.apple.com.cn/', 4);
COMMIT;

-- ----------------------------
-- Table structure for platformdiscount
-- ----------------------------
DROP TABLE IF EXISTS `platformdiscount`;
CREATE TABLE `platformdiscount` (
  `platformId` int(11) NOT NULL,
  `time` date NOT NULL,
  `discount` char(32) DEFAULT NULL,
  `memberDiscount` char(32) DEFAULT NULL,
  PRIMARY KEY (`platformId`,`time`),
  CONSTRAINT `platformdiscount_ibfk_1` FOREIGN KEY (`platformId`) REFERENCES `platform` (`plat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platformdiscount
-- ----------------------------
BEGIN;
INSERT INTO `platformdiscount` VALUES (1, '2020-12-07', '500-100', '0-60');
COMMIT;

-- ----------------------------
-- Table structure for popular
-- ----------------------------
DROP TABLE IF EXISTS `popular`;
CREATE TABLE `popular` (
  `id` int(11) NOT NULL,
  `fovorable` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of popular
-- ----------------------------
BEGIN;
INSERT INTO `popular` VALUES (1, 200);
INSERT INTO `popular` VALUES (2, 100);
INSERT INTO `popular` VALUES (10, 100);
INSERT INTO `popular` VALUES (12, 100);
INSERT INTO `popular` VALUES (18, 100);
COMMIT;

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
  `comid` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` int(11) NOT NULL,
  `comDisc` int(11) DEFAULT NULL,
  `platDisc` int(11) DEFAULT NULL,
  `stuDisc` int(11) DEFAULT NULL,
  `memDisc` int(11) DEFAULT NULL,
  PRIMARY KEY (`comid`,`date`),
  CONSTRAINT `price_ibfk_1` FOREIGN KEY (`comid`) REFERENCES `commodity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price
-- ----------------------------
BEGIN;
INSERT INTO `price` VALUES (1, '2020-11-20', 9288, 0, 0, 0, 0);
INSERT INTO `price` VALUES (1, '2020-11-21', 9288, 0, 0, 0, 0);
INSERT INTO `price` VALUES (1, '2020-11-22', 9288, 0, 0, 0, 0);
INSERT INTO `price` VALUES (2, '2020-11-20', 10137, 0, 0, 0, 0);
INSERT INTO `price` VALUES (2, '2020-11-21', 10100, 0, 0, 0, 0);
INSERT INTO `price` VALUES (2, '2020-11-22', 10297, 0, 0, 0, 0);
INSERT INTO `price` VALUES (3, '2020-11-20', 8799, 0, 0, 0, 0);
INSERT INTO `price` VALUES (3, '2020-11-21', 9299, 0, 0, 0, 0);
INSERT INTO `price` VALUES (3, '2020-11-22', 8499, 0, 0, 0, 0);
INSERT INTO `price` VALUES (4, '2020-11-19', 8499, 0, 0, 0, 0);
INSERT INTO `price` VALUES (4, '2020-11-20', 8499, 0, 0, 0, 0);
INSERT INTO `price` VALUES (4, '2020-11-21', 8499, 0, 0, 0, 0);
INSERT INTO `price` VALUES (4, '2020-11-22', 8499, 0, 0, 0, 0);
INSERT INTO `price` VALUES (5, '2020-11-19', 9999, 0, 0, 800, 0);
INSERT INTO `price` VALUES (5, '2020-11-20', 9999, 0, 0, 800, 0);
INSERT INTO `price` VALUES (5, '2020-11-21', 9999, 0, 0, 800, 0);
INSERT INTO `price` VALUES (5, '2020-11-22', 9999, 0, 0, 800, 0);
COMMIT;

-- ----------------------------
-- Table structure for replymessage
-- ----------------------------
DROP TABLE IF EXISTS `replymessage`;
CREATE TABLE `replymessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `leavemessageid` int(11) NOT NULL,
  `time` date NOT NULL,
  `message` char(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `leavemessageid` (`leavemessageid`),
  CONSTRAINT `replymessage_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `replymessage_ibfk_2` FOREIGN KEY (`leavemessageid`) REFERENCES `leavemessage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of replymessage
-- ----------------------------
BEGIN;
INSERT INTO `replymessage` VALUES (1, 1, 1, '2020-11-21', '1 号 认为你说的对');
INSERT INTO `replymessage` VALUES (2, 1, 2, '2020-11-21', '1 号 认为你说的对');
INSERT INTO `replymessage` VALUES (3, 1, 3, '2020-11-21', '1 号 认为你说的对');
INSERT INTO `replymessage` VALUES (4, 1, 4, '2020-11-21', '1 号 认为你说的对');
INSERT INTO `replymessage` VALUES (5, 2, 5, '2020-11-21', '2 号 认为你是再放P');
INSERT INTO `replymessage` VALUES (6, 2, 6, '2020-11-21', '2 号 认为你是再放P');
INSERT INTO `replymessage` VALUES (7, 2, 7, '2020-11-21', '2 号 认为你是再放P');
INSERT INTO `replymessage` VALUES (8, 2, 8, '2020-11-21', '2 号 认为你是再放P');
INSERT INTO `replymessage` VALUES (9, 1, 1, '2020-11-21', '1 号 认为你说的真是太对了');
INSERT INTO `replymessage` VALUES (10, 1, 1, '2020-11-21', '我觉得你是在放P');
INSERT INTO `replymessage` VALUES (11, 1, 1, '2020-11-21', '还行');
INSERT INTO `replymessage` VALUES (12, 1, 5, '2020-11-21', '你说的对');
INSERT INTO `replymessage` VALUES (13, 1, 13, '2020-11-21', '！！！');
INSERT INTO `replymessage` VALUES (14, 1, 2, '2020-11-21', '是否');
INSERT INTO `replymessage` VALUES (15, 1, 3, '2020-11-21', '23456');
INSERT INTO `replymessage` VALUES (16, 1, 16, '2020-11-21', '是啊');
INSERT INTO `replymessage` VALUES (17, 1, 57, '2020-11-24', '好东西');
INSERT INTO `replymessage` VALUES (18, 1, 58, '2020-11-24', '我很好');
INSERT INTO `replymessage` VALUES (19, 1, 59, '2020-11-25', '234567890-');
INSERT INTO `replymessage` VALUES (20, 1, 22, '2020-12-04', '是不错');
INSERT INTO `replymessage` VALUES (21, 1, 7, '2020-12-04', '别吵');
INSERT INTO `replymessage` VALUES (22, 1, 5, '2020-12-07', '说的真好');
INSERT INTO `replymessage` VALUES (23, 1, 61, '2020-12-07', '二般');
INSERT INTO `replymessage` VALUES (24, 1, 61, '2020-12-16', '三般');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` char(32) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` char(32) NOT NULL,
  `password` char(32) NOT NULL,
  `isStudent` enum('0','1') DEFAULT NULL,
  `isMember` char(10) DEFAULT NULL,
  `isVip` enum('0','1') NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('海绵宝宝', 1, 'haimianbaobao@buaa.edu.cn', '654321', '0', '001', '0', '2020-10-26');
INSERT INTO `users` VALUES ('派大星', 2, 'paidaxing@buaa.edu.cn', '654321', '1', '001', '1', '2020-10-25');
INSERT INTO `users` VALUES ('蟹老板', 3, 'xielaoban@buaa.edu.cn', '654321', '0', '011', '0', '2020-11-15');
INSERT INTO `users` VALUES ('皮老板', 4, 'pilaoban@buaa.edu.cn', '654321', '1', '011', '1', '2020-10-16');
INSERT INTO `users` VALUES ('admin', 5, '2081645852@qq.com', '123', '1', '111', '1', '2020-12-07');
INSERT INTO `users` VALUES ('ljz', 6, '18373489@buaa.edu.cn', '123', '1', '101', '1', '2020-11-21');
INSERT INTO `users` VALUES ('littleLiang', 7, '183734899999@buaa.edu.cn', '123', '1', '101', '1', '2020-12-03');
COMMIT;

-- ----------------------------
-- Table structure for visits
-- ----------------------------
DROP TABLE IF EXISTS `visits`;
CREATE TABLE `visits` (
  `day` date NOT NULL,
  `userid` int(11) NOT NULL,
  `time` timestamp NOT NULL,
  PRIMARY KEY (`time`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visits
-- ----------------------------
BEGIN;
INSERT INTO `visits` VALUES ('2020-12-20', 2, '2020-12-20 20:10:30');
INSERT INTO `visits` VALUES ('2020-12-20', 2, '2020-12-20 20:15:30');
INSERT INTO `visits` VALUES ('2020-12-20', 2, '2020-12-20 20:15:32');
INSERT INTO `visits` VALUES ('2020-12-21', 4, '2020-12-21 10:15:30');
INSERT INTO `visits` VALUES ('2020-12-21', 1, '2020-12-21 12:15:30');
INSERT INTO `visits` VALUES ('2020-12-22', 3, '2020-12-22 12:15:30');
INSERT INTO `visits` VALUES ('2020-12-23', 1, '2020-12-23 07:19:59');
INSERT INTO `visits` VALUES ('2020-12-23', 1, '2020-12-23 07:33:13');
INSERT INTO `visits` VALUES ('2020-12-23', 1, '2020-12-23 07:47:16');
INSERT INTO `visits` VALUES ('2020-12-23', 1, '2020-12-23 07:50:57');
INSERT INTO `visits` VALUES ('2020-12-23', 1, '2020-12-23 08:14:43');
INSERT INTO `visits` VALUES ('2020-12-23', 1, '2020-12-23 08:16:11');
INSERT INTO `visits` VALUES ('2020-12-23', 2, '2020-12-23 21:50:56');
INSERT INTO `visits` VALUES ('2020-12-23', 1, '2020-12-23 22:34:53');
INSERT INTO `visits` VALUES ('2020-12-24', 1, '2020-12-24 16:00:06');
INSERT INTO `visits` VALUES ('2020-12-25', 2, '2020-12-25 21:50:56');
INSERT INTO `visits` VALUES ('2020-12-25', 2, '2020-12-25 21:50:57');
INSERT INTO `visits` VALUES ('2020-12-25', 2, '2020-12-25 21:50:58');
COMMIT;

-- ----------------------------
-- Procedure structure for checkadmin
-- ----------------------------
DROP PROCEDURE IF EXISTS `checkadmin`;
delimiter ;;
CREATE PROCEDURE `checkadmin`(IN adname VARCHAR(10),IN adpwd VARCHAR(32))
Begin
	if exists (select * from admins where ad_name = adname and ad_pwd = adpwd) 
	then select enabled from admins where ad_name = adname and ad_pwd = adpwd;

	ELSE SELECT -1;
	
	end if;
End
;;
delimiter ;

-- ----------------------------
-- Function structure for deleteCommodity
-- ----------------------------
DROP FUNCTION IF EXISTS `deleteCommodity`;
delimiter ;;
CREATE FUNCTION `deleteCommodity`(comid int)
 RETURNS int(11)
begin
	delete  from commodity where commodity.id = comid;
	delete  from price where price.comid = comid;
	delete  from collection where collection.comid = comid;
	delete  from commoditydiscount where commoditydiscount.comid = comid;
	delete  from history where history.comid = comid;
	delete  from replymessage where replymessage.leavemessageid in (select id from leavemessage where leavemessage.comid = comid);
	delete from leavemessage where leavemessage.comid = comid;
	delete from popular where popular.id = comid;
	return 0;
end
;;
delimiter ;

-- ----------------------------
-- Function structure for deleteLeaveMessage
-- ----------------------------
DROP FUNCTION IF EXISTS `deleteLeaveMessage`;
delimiter ;;
CREATE FUNCTION `deleteLeaveMessage`(id int)
 RETURNS int(11)
begin
	delete from leavemessage where leavemessage.id = id;
	delete from replymessage where replymessage.leavemessageid = id;
	return 0;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for vv
-- ----------------------------
DROP PROCEDURE IF EXISTS `vv`;
delimiter ;;
CREATE PROCEDURE `vv`(in begin_date date)
BEGIN
	select day,count(day) as cntday from visits where day>begin_date group by day;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table collection
-- ----------------------------
DROP TRIGGER IF EXISTS `updatePopularAuto`;
delimiter ;;
CREATE TRIGGER `updatePopularAuto` AFTER INSERT ON `collection` FOR EACH ROW begin
	delete  from popular;
	insert into popular (select comid, count(comid) * 100 from collection group by comid LIMIT 5);
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
