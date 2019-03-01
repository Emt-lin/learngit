/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : cloudmusic

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 01/03/2019 22:18:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album`  (
  `aid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `aname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `albumtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  CONSTRAINT `album_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `singer` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('1', 'Here With You', '1', '2012-08-29', 'image/2788361488086447.jpg');
INSERT INTO `album` VALUES ('10', '热门华语281', '10', '2014-01-01', 'image/album/109951163825045428.jpg');
INSERT INTO `album` VALUES ('11', '自定义', '13', '2009-01-10', 'image/album/18590542604286213.jpg');
INSERT INTO `album` VALUES ('12', '青年晚报', '13', '2016-06-27', 'image/album/3431575794705764.jpg');
INSERT INTO `album` VALUES ('13', '不如吃茶去', '13', '2014-08-23', 'image/album/6642149743396577.jpg');
INSERT INTO `album` VALUES ('14', '素颜', '13', '2010-08-18', 'image/album/111050674419060.jpg');
INSERT INTO `album` VALUES ('15', '梦游计', '13', '2012-07-11', 'image/album/123145302311773.jpg');
INSERT INTO `album` VALUES ('16', '断桥残雪', '13', '2007-12-28', 'image/album/58274116284456.jpg');
INSERT INTO `album` VALUES ('17', '寻雾启示', '13', '2010-01-06', 'image/album/34084860473122.jpg');
INSERT INTO `album` VALUES ('18', '寻宝游戏', '13', '2018-07-12', 'image/album/109951163402069754.jpg');
INSERT INTO `album` VALUES ('2', '弦上春雪', '2', '2013-09-02', 'image/7799935488436943.jpg');
INSERT INTO `album` VALUES ('3', 'Dream It Possible', '3', '2015-09-01', 'image/109951163088676710.jpg');
INSERT INTO `album` VALUES ('4', 'Nothing To Fear', '4', '2013-04-23', 'image/2447512883490682.jpg');
INSERT INTO `album` VALUES ('5', '光年之外', '5', '2016-12-30', 'image/18587244069235039.jpg');
INSERT INTO `album` VALUES ('6', 'LOVE AND WAR', '6', '2014-09-24', 'image/album/6624557557981797.jpg');
INSERT INTO `album` VALUES ('7', 'Angel With a Shotgun - Single', '7', '2013-06-20', 'image/album/7851612533948579.jpg');
INSERT INTO `album` VALUES ('8', 'xx', '8', '2009-08-16', 'image/album/825733232504415.jpg');
INSERT INTO `album` VALUES ('9', '最美情侣', '9', '2017-04-04', 'image/album/18940187300130282.jpg');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `mid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `aid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  INDEX `aid`(`aid`) USING BTREE,
  CONSTRAINT `music_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `singer` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `music_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `album` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES ('1', 'Here With You', '1', '1', '03:45', 'music/Here With You.mp3', NULL);
INSERT INTO `music` VALUES ('10', '东京不太热', '10', '10', '03:45', 'music/东京不太热.mp3', NULL);
INSERT INTO `music` VALUES ('11', '有何不可', '13', '11', '04:01', 'image/album/18590542604286213.jpg', NULL);
INSERT INTO `music` VALUES ('12', '雅俗共赏', '13', '12', '04:09', 'image/album/3431575794705764.jpg', NULL);
INSERT INTO `music` VALUES ('13', '惊鸿一面', '13', '13', '04:16', 'image/album/6642149743396577.jpg', NULL);
INSERT INTO `music` VALUES ('14', '素颜', '13', '14', '03:58', 'image/album/111050674419060.jpg', NULL);
INSERT INTO `music` VALUES ('15', '幻听', '13', '15', '04:33', 'image/album/123145302311773.jpg', NULL);
INSERT INTO `music` VALUES ('16', '如果当时', '13', '11', '05:16', 'image/album/18590542604286213.jpg', NULL);
INSERT INTO `music` VALUES ('17', '断桥残雪', '13', '16', '03:47', 'image/album/58274116284456.jpg', NULL);
INSERT INTO `music` VALUES ('18', '庐州月', '13', '17', '04:15', 'image/album/34084860473122.jpg', NULL);
INSERT INTO `music` VALUES ('19', '如约而至', '13', '18', '04:15', 'image/album/109951163402069754.jpg', NULL);
INSERT INTO `music` VALUES ('2', '轮回之境', '2', '2', '04:11', 'music/轮回之境.mp3', NULL);
INSERT INTO `music` VALUES ('20', '大千世界', '13', '18', '04:15', 'image/album/109951163402069754.jpg', NULL);
INSERT INTO `music` VALUES ('3', 'Dream It Possible', '3', '3', '03:24', 'music/Dream It Possible.mp3', NULL);
INSERT INTO `music` VALUES ('4', 'Nothing To Fear', '4', '4', '02:56', 'music/Nothing To Fear.mp3', NULL);
INSERT INTO `music` VALUES ('5', '光年之外', '5', '5', '03:55', 'music/光年之外.mp3', NULL);
INSERT INTO `music` VALUES ('6', 'Color Blind', '6', '6', '03:32', 'music/Color Blind.mp3', NULL);
INSERT INTO `music` VALUES ('7', 'Angel With A Shotgun', '7', '7', '03:13', 'music/Angel With A Shotgun.mp3', NULL);
INSERT INTO `music` VALUES ('9', '最美情侣', '9', '9', '04:01', 'music/最美情侣.mp3', NULL);

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `sid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `text` varchar(20000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES ('1', 'Asher Book', 'image/109951163542951713.jpg', '\"name\":\"Asher Monroe简介\",\"text\":\"Asher Book（1988年9月18日-）美国歌手、舞蹈演员，男孩乐队VFactory（V工厂）的主唱，米高梅电影旗下演员。出生于弗吉尼亚州的阿灵顿，在搬到洛杉矶之前，Book就读于著名的纽约表演艺术学校。 Book取得事业上的巨大突破是在7岁，当时他在舞台剧《美女与野兽》中扮演chip。2006年，Book成为男孩乐队VFactory的成员。2009年1月27日发行《 Love Struck》专辑。随后他们与华纳兄弟签约。他在2009年扮演《Fame》（中文名为《名扬四海》）中的Marco--一个有抱负的歌手。2009版《Fame》音乐电影是翻拍自八十年代同名电影。该剧导演是Kevin Tancharoen，编剧 阿丽娜布罗什克纳和阿利森伯内特。该片于2009年9月25日上映。2010年主演电视剧《Parenthood》（为人父母）中的Steve 。\"');
INSERT INTO `singer` VALUES ('10', '封茗囧菌', 'image/singer/109951163369198647.jpg', '\"name\":\"封茗囧菌简介\",\"text\":\"@封茗囧菌 【合作请戳微博私信：http://weibo.com/mondai】请勿搬运或是将我的音频上传到别的平台（比如全民K歌）谢谢合作=x=~\"');
INSERT INTO `singer` VALUES ('11', '林俊杰', 'image/singer/3389794350704969.jpg', '\"name\":\"林俊杰简介\",\"text\":\"著名男歌手，作曲人、作词人、音乐制作人，偶像与实力兼具。林俊杰出生于新加坡的一个音乐世家。在父母的引导下，4岁就开始学习古典钢琴，不善言辞的他由此发现了另一种与人沟通的语言。小时候的林俊杰把哥哥林俊峰当作偶像，跟随哥哥的步伐做任何事，直到接触流行音乐后，便爱上创作这一条路。2003年以专辑《乐行者》出道，2004年一曲《江南》红遍两岸三地，凭借其健康的形象，迷人的声线，出众的唱功，卓越的才华，迅速成为华语流行乐坛的领军人物之一，迄今为止共创作数百首音乐作品，唱片销量在全亚洲逾1000万张。2007年成立个人音乐制作公司JFJ，2008创立潮流品牌SMG。2011年被媒体封为“新四大天王”之一，同年8月8日正式加盟华纳音乐，开启事业新乐章。2012年发行故事影像书《记得》，成功跻身畅销书作家行列。获得多项奖项：第15届台湾金曲奖最佳新人奖，6届新加坡金曲奖大奖，6届新加坡词曲版权协会大奖，8届全球华语歌曲排行榜大奖，5届MusicRadio中国TOP排行榜大奖。');
INSERT INTO `singer` VALUES ('12', '周杰伦', 'image/singer/109951163111196186.jpg', '\"name\":\"周杰伦简介\",\"text\":\"著名歌手，音乐人，词曲创作人，编曲及制作人，MV及电影导演。新世纪华语歌坛领军人物，中国风歌曲始祖，被时代周刊誉为“亚洲猫王”，是2000年后亚洲流行乐坛最具革命性与指标性的创作歌手，亚洲销量超过3100万张，有“亚洲流行天王”之称，开启华语乐坛“R&B时代”与“流行乐中国风”的先河，周杰伦的出现打破了亚洲流行乐坛长年停滞不前的局面，为亚洲流行乐坛翻开了新的一页，是华语乐坛真正把R&B提升到主流高度的人物，引领华语乐坛革命整十年，改写了华语乐坛的流行方向。\"');
INSERT INTO `singer` VALUES ('13', '许嵩', 'image/singer/109951163536274581.jpg', '\"name\":\"许嵩简介\",\"text\":\"著名作词人、作曲人、唱片制作人、歌手。内地独立音乐之标杆人物，有音乐鬼才之称。2009年独立出版首张词曲全创作专辑《自定义》，2010年独立出版第二张词曲全创作专辑《寻雾启示》，两度掀起讨论热潮，一跃成为内地互联网讨论度最高的独立音乐人。2011年加盟海蝶音乐，推出第三张词曲全创作专辑《苏格拉没有底》，发行首月即在大陆地区摘下唱片销量榜冠军，轰动全国媒体，并拉开全国巡回签售及歌迷见面会。2012年7月发表第四张全创作专辑《梦游计》，秉承了一贯的创新理念，天马行空的创作题材令乐迷耳目一新，引起强烈反响，进一步奠定了其华语乐坛青年一代领军人物的地位。主要成就：音乐风云榜最受欢迎唱作新人，第9届亚太音乐榜最受欢迎唱作人，全球华语榜最受欢迎新人奖。\"');
INSERT INTO `singer` VALUES ('2', 'CRITTY', 'image/109951162939901937.jpg', '\"name\":\"CRITTY简介\",\"text\":\"CRITTY，古风歌手、百度音乐人，5sing原创音乐基地推荐音乐人，其作品多为古风、中国风歌曲，嗓音甜美大气。 微博：@熙影CRITTY 熙影CRITTY官方群：543885651 CC果冻①群：84323275；CC果冻②群：181884897\"');
INSERT INTO `singer` VALUES ('3', 'Delacey', 'image/18693896697231910.jpg', NULL);
INSERT INTO `singer` VALUES ('4', 'Dexter Britain', 'image/19055636021042180.jpg', '\"name\":\"Biography\",\"text\":\"Dexter Britain (born July 13, 1989) is a self-taught pianist, composer, producer and songwriter. Best known for his standout piece, \"The Time To Run (Finale)\". He has fast become a name in soundtrack composition.<br>His work has been used more than 11 000 times and features in projects from commercials, and feature films, to charity videos, and weddings. Among his clients are G-Tech, GoPro, World Health Organisation, NASA, O2, Jason Silva (Shots of Awe, Future Of Us), TEDx, Kelloggs, Nike, BVLGARI, McLaren, Bentley, National Geographic, UNICEF, Ralph Lauren, Myers Department Store, Nationwide Insurance, Google, O2 Telefonica, LG.\"');
INSERT INTO `singer` VALUES ('5', 'G.E.M.邓紫棋', 'image/18646617697368402.jpg', '\"name\":\"G.E.M.邓紫棋简介\",\"text\":\"邓紫棋成长于一个音乐世家，其母亲为上海音乐学院声乐系毕业生，外婆教唱歌，舅父拉小提琴，外公在乐团吹色士风。在家人的熏陶下，自小便热爱音乐，喜爱唱歌，与音乐结下不解之缘。G.E.M.在5岁的时候已经开始尝试作曲及填词，13岁完成了8级钢琴。G.E.M.在小学时期就读位于太子道西的中华基督教会协和小学上午校，为2003年的毕业生，同时亦为校内诗歌班成员。其英文名G.E.M.是Get Everybody Moving的缩写，象徵著她希望透过音乐让大家动起来的梦想。2008年出道，2009年在叱咤乐坛流行榜颁奖典礼夺得女新人奖金奖，亦是该奖项历年来最年轻，以及第一位未成年的得奖者。因其广阔的音域，得到不少前辈歌手点名公开赞扬。2014年她参加湖南卫视《我是歌手》第二季名声大震。\"');
INSERT INTO `singer` VALUES ('6', 'Matt B', 'image/1389782698816821.jpg', NULL);
INSERT INTO `singer` VALUES ('7', 'Nightcore', 'image/3431575796024824.jpg', NULL);
INSERT INTO `singer` VALUES ('8', 'The xx', 'image/3427177782351215.jpg', '\"name\":\"The xx简介\",\"text\":\"The xx是一支来自于英国伦敦西南的乐队，成立于2005年。四位创始成员在Elliott学院相遇，这所学院因培养了包括Hot Chip, Burial和Four Tet等校友而著称。乐队名列NME评选的2009年未来50名单（The Future 50 list）第六位。2009年11月11日，键盘手Baria Qureshi在错过了几场预定的演出后确定离队，声称已疲惫不堪。她将不会被替代，The xx将继续作为三人乐队继续维持下去。\"');
INSERT INTO `singer` VALUES ('9', '白小白', 'image/109951163008377235.jpg', '\"name\":\"白小白简介\",\"text\":\"艺名：白小白 原名：白杨 生日：01.10 代表作：《最美情侣》《过去的照片》\"');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_music
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_music`;
CREATE TABLE `tb_user_music`  (
  `uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uid`, `mid`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  CONSTRAINT `tb_user_music_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_user_music_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `music` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
