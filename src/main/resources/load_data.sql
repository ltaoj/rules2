### userinfo ###
INSERT INTO userinfo VALUES(3903150332, '葛凡', 1, '工程试验班1503', 2015, '软件工程', '软件学院');
INSERT INTO userinfo VALUES(3903150326, '李涛江', 1, '工程试验班1503', 2015, '软件工程', '软件学院');
INSERT INTO userinfo VALUES(3903150327, '陈铭明', 1,'工程试验班1503', 2015, '软件工程', '软件学院');

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

### choice ###
INSERT INTO choice(title_id, content, checked) VALUES(1, '1', 0);
INSERT INTO choice(title_id, content, checked) VALUES(1, '2', 1);
INSERT INTO choice(title_id, content, checked) VALUES(1, '3', 0);
INSERT INTO choice(title_id, content, checked) VALUES(1, '4', 0);
INSERT INTO choice(title_id, content, checked) VALUES(2, '1', 0);
INSERT INTO choice(title_id, content, checked) VALUES(2, '2', 0);
INSERT INTO choice(title_id, content, checked) VALUES(2, '3', 0);
INSERT INTO choice(title_id, content, checked) VALUES(2, '4', 1);

### wrongtitle ###
INSERT INTO wrongtitle VALUES(1, 3903150326);

### college ###
INSERT INTO college VALUES(1, '软件学院');
INSERT INTO college VALUES(2, '信息与工程学院');

### major ###
INSERT INTO major VALUES(100, 1, '软件工程');
INSERT INTO major VALUES(101, 2,'电气工程');
INSERT INTO major VALUES(102, 2,'计算机科学与技术');
INSERT INTO major VALUES(103, 2,'网络工程');

### clazz ###
INSERT INTO clazz VALUES(200, 100, '工程试验班1501');
INSERT INTO clazz VALUES(201, 100, '工程试验班1502');
INSERT INTO clazz VALUES(202, 100, '工程试验班1503');
INSERT INTO clazz VALUES(203, 101, '电气1501');
INSERT INTO clazz VALUES(204, 102, '计科1501');
INSERT INTO clazz VALUES(205, 103, '网络1501');


### additiontitle ###
#填空题 type=1
INSERT INTO additiontitle(name, type, answer) VALUES('中华人民共和国是#领导的、以#为基础的人民民主专政的社会主义国家。#制度是中华人民共和国的根本制度。', 1, '工人阶级#工农联盟#社会主义')
INSERT INTO additiontitle(name, type, answer) VALUES('中华人民共和国的国家机构实行#原则。', 1, '民主集中制')
INSERT INTO additiontitle(name, type, answer) VALUES('中华人民共和国的一切权力属于#。人民行使国家权力的机关是#和#。', 1, '人民#全国人民代表大会#地方各级人民代表大会')
#简答题 type=3
INSERT INTO additiontitle(name, type, answer) VALUES('', 3, '')
#案例分析题 type=4

#论述题 type=5
