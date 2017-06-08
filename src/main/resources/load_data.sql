### userinfo ###
INSERT INTO userinfo VALUES(3903150328, '葛凡', 1, '工程试验班1503', 2015, '软件工程', '中南大学');
INSERT INTO userinfo VALUES(3903150326, '李涛江', 1, '工程试验班1503', 2015, '软件工程', '中南大学');
INSERT INTO userinfo VALUES(3903150327, '陈铭明', 1,'工程试验班1503', 2015, '软件工程', '中南大学');

### signon ###
INSERT INTO signon VALUES(3903150326, 3903150326);

### integral ###
INSERT INTO integral VALUES(3903150326, 10);

### clockin ###
INSERT INTO clockin(student_id, clock_day, duration, title_num, comment) VALUES(3903150326, '2017-06-07 15:56:00', 15, 20, '第一天打卡测试数据库');

### testinfo ###
INSERT INTO testinfo(name, type, grade, start_time, end_time, duration) VALUES('中南大学2015级校规校纪补考', 0, 2015, '2017-09-01 14:00:00', '2017-10-01 14:00:00', 120);
INSERT INTO testinfo(name, type, grade, start_time, end_time, duration) VALUES('中南大学2017级校规校纪考试', 0, 2017, '2017-09-01 14:00:00', '2017-10-01 14:00:00', 120);
INSERT INTO testinfo(name, type, grade, start_time, end_time, duration) VALUES('中南大学第一届校规校纪竞赛', 0, 2015, '2017-06-15 14:00:00', '2017-06-15 16:00:00', 120);

### contestregistion ###
INSERT INTO contestregistion VALUES(3903150326, 3, 0);

### testrecord ###
INSERT INTO testrecord(student_id, test_id, start_time, submit_time, score) VALUES(3903150326, 1, '2017-06-07 14:00:00', '2017-06-07 14:05:00', 99);

### titlediff ###
INSERT INTO titlediff VALUES(1, '易');
INSERT INTO titlediff VALUES(2, '中');
INSERT INTO titlediff VALUES(3, '难');

### title ###
INSERT INTO title(name, diff_id, score) VALUES('1+1等于多少？', 1, 5);
INSERT INTO title(name, diff_id, score) VALUES('2*2等于多少？', 1, 5);

### option ###
INSERT INTO `option`(title_id, content, checked) VALUES(1, '1', 0);
INSERT INTO `option`(title_id, content, checked) VALUES(1, '2', 1);
INSERT INTO `option`(title_id, content, checked) VALUES(1, '3', 0);
INSERT INTO `option`(title_id, content, checked) VALUES(1, '4', 0);
INSERT INTO `option`(title_id, content, checked) VALUES(2, '1', 0);
INSERT INTO `option`(title_id, content, checked) VALUES(2, '2', 0);
INSERT INTO `option`(title_id, content, checked) VALUES(2, '3', 0);
INSERT INTO `option`(title_id, content, checked) VALUES(2, '4', 1);

### wrongtitle ###
INSERT INTO wrongtitle VALUES(1, 3903150326);