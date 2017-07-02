<%--
  Created by IntelliJ IDEA.
  User: CMM
  Date: 2017/7/2
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>中南大学-校规校纪系统-后台管理系统</title>
    <!-- Meta -->
    <meta charset="utf-8">
    <!-- CSS Global Compulsory -->
    <link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <link href="css/blocks.css" rel="stylesheet">
    <link href="css/one.style.css" rel="stylesheet">
    <!-- CSS Implementing Plugins -->
    <link href="plugins/animate.css" rel="stylesheet">
    <link href="plugins/line-icons/line-icons.css" rel="stylesheet">
    <link href="plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet">
    <link href="plugins/owl-carousel2/owl-carousel.css" rel="stylesheet">
    <link
            href="plugins/cube-portfolio/cubeportfolio/css/cubeportfolio.min.css"
            rel="stylesheet">
    <link
            href="plugins/cube-portfolio/cubeportfolio/custom/custom-cubeportfolio.css"
            rel="stylesheet">
    <link href="plugins/shhos/shhos.css" rel="stylesheet">
    <link href="css/app.style.css" rel="stylesheet">

    <link href="css/backstage.css" rel="stylesheet">
    <script src="plugins/jquery/jquery.min.js"></script>
    <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- 平滑滚动 -->
    <script src="plugins/smoothScroll/jquery.smooth-scroll.js"></script>
    <script src="js/smoothScroll/smoothScroll.js"></script>
    <!-- 平滑滚动结束 -->
    <script>
        window.onload = function () {
            tab("tab_t", "li", "tab_c", "li", "onmousedown")
            function tab(tab_t, tab_t_tag, tab_c, tag_c_tag, evt) {
                var tab_t = document.getElementById(tab_t);
                var tab_t_li = tab_t.getElementsByTagName(tab_t_tag);
                var tab_c = document.getElementById(tab_c);
                var tab_c_li = tab_c.getElementsByTagName(tag_c_tag);
                var len = tab_t_li.length;
                var i = 0;
                for (i = 0; i < len; i++) {
                    tab_t_li[i].index = i;
                    tab_t_li[i][evt] = function () {
                        for (i = 0; i < len; i++) {
                            tab_c_li[i].className = 'hide';
                            tab_t_li[i].className = '';
                        }
                        tab_c_li[this.index].className = '';
                        tab_t_li[this.index].className = 'to_red';
                        $('#main_title').html(tab_t_li[this.index].innerHTML);
                    }
                }
            }
        }
    </script>
</head>
<body>
<!-- 顶栏 -->
<nav
        class="one-page-header navbar navbar-default navbar-fixed-top homeapp-header"
        data-role="navigation">
    <div class="container">
        <div class="menu-container page-scroll">
            <a class="navbar-brand" href="#body"> <img alt="中南大学"
                                                       src="img/logo.png">
            </a>
        </div>
        <!-- 导航栏 -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <div class="menu-container">
                <ul class="nav navbar-nav">
                    <li><a type="button"
                           class="btn-u btn-u-lg btn-u-green btn-u-upper rounded-2x"
                           data-toggle="personal" id="personal"
                           onclick="personalPopover()">个人</a></li>
                </ul>
            </div>
        </div>
        <!-- 导航栏结束 -->
    </div>
    <!-- /.container --> </nav>
<!-- 顶栏结束 -->
<!-- 公告模块 -->
<section class="promo-section" id="intro">
    <div class="interactive-slider-v2" style="background:#FFFFFF"></div>
</section>

<section>
    <div>
        <div id="js_container_box" class="container_box side_l ">
            <div class="col_side">
                <div class="menu_box" id="menuBar">
                    <ul style="list-style:none;" id="tab_t">
                        <li class="to_red"><i class="fa fa-user-circle "></i>&nbsp;&nbsp;学生用户</li>
                        <li><i class="fa fa-newspaper-o"></i>&nbsp;&nbsp;考试试题</li>
                        <li><i class="fa fa-file-o"></i>&nbsp;&nbsp;考试竞赛</li>
                        <li><i class="fa fa-gg"></i>&nbsp;&nbsp;学生成绩</li>
                        <li><i class="fa fa-bullhorn"></i>&nbsp;&nbsp;考试通知</li>
                        <li><i class="fa fa-user-o"></i>&nbsp;&nbsp;管理员</li>
                    </ul>
                </div>
            </div>
            <div class="col_main">
                <div class="col_main_inner" id="tab_c">
                    <h1 id="main_title"><i class="fa fa-user-circle "></i>&nbsp;&nbsp;学生用户</h1>
                    <li>
                        <div class="main_bd">
                            <div class="box">
                                <h3>添加学生信息</h3>
                                <input type="file" name="file1" size="30"/>
                                <br>
                                <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">上传</button>
                            </div>
                        </div>
                    </li>
                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <h3>导入试题</h3>
                                <input type="file" name="file1" size="30"/>
                                <br>
                                <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">上传</button>
                            </div>
                        </div>
                    </li>
                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <h3>考试竞赛信息</h3>
                                <div style="width:30%">
                                    <div class="btn-group">
                                        <a class="btn btn-primary">添加</a>
                                        <a class="btn btn-default">修改</a>
                                        <a class="btn btn-default">删除</a>
                                        <a class="btn btn-default">查询</a>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-edit"></i>
								</span> <input type="text" class="form-control" placeholder="竞赛名称"
                                               id="contest_info_name">
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <input type="text" class="form-control">
                                        <span class="input-group-addon">年</span>
                                        <input type="text" class="form-control">
                                        <span class="input-group-addon">月</span>
                                        <input type="text" class="form-control">
                                        <span class="input-group-addon">日</span>
                                    </div>
                                    <br>
                                    <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-tripadvisor"></i>
								</span> <input type="text" class="form-control" placeholder="竞赛时长"
                                               id="contest_info_time">
                                    </div>
                                </div>
                                <br>
                                <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">操作</button>
                            </div>
                            <div class="box">
                                <h3>指定竞赛题目</h3>
                                <br>
                            </div>
                            <div class="box">
                                <h3>查看竞赛报名学生</h3>
                                <br>
                            </div>
                        </div>
                    </li>
                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <h3>查询个人成绩</h3>
                                <br>
                                <div style="width:30%">
                                    <div class="input-group input-group">
                                        <span class="input-group-addon"><i class="fa fa-address-card-o"></i></span>
                                        <input type="password" class="form-control" placeholder="学号"
                                               id="search_one_record"/>
                                    </div>

                                    <label style="font-size: medium;margin-top: 15px">考试成绩: </label> &nbsp;<i
                                        class="fa fa-refresh fa-spin fa-2x" style="margin-top: 10px"></i>
                                    <label></label>
                                    <br>
                                    <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">查询
                                    </button>
                                </div>

                            </div>
                        </div>
                        <div class="main_bd">
                            <div class="box">
                                <h3>查询年纪,学院，学校成绩(判断权限，改变内容)</h3>
                                <br>
                                <div style="width:30%">
                                    <div class="input-group input-group">
                                        <span class="input-group-addon"><i class="fa fa-search"></i></span>
                                        <input type="text" class="form-control" placeholder="年级(判断权限，改变内容)"
                                               id="search_record"/>
                                    </div>
                                    <br>
                                    <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">查询</button>

                                    <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x"
                                            style="margin-left: 20px">导出
                                    </button>
                                </div>

                            </div>
                        </div>
                    </li>

                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <h3>发布图片通知</h3>
                                <input type="file" name="file1" size="50"/>
                                <br>
                                <div style="width:30%">
                                    <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-themeisle"></i>
								</span> <input type="text" class="form-control" placeholder="主题"
                                               id="publish_notice_title">
                                    </div>
                                    <br>
                                    <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-tripadvisor"></i>
								</span> <input type="text" class="form-control" placeholder="来源（WHO）"
                                               id="publish_notice_source">
                                    </div>
                                </div>
                                <br>
                                <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">发布</button>
                            </div>

                            <div class="box">
                                <h3>发布文字通知</h3>
                            </div>

                            <div class="box">
                                <h3>删除通知</h3>
                            </div>

                            <div class="box">
                                <h3>修改通知</h3>
                            </div>
                        </div>
                    </li>
                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <h3>添加管理员</h3>
                                <br>
                                <div style="width: 40%">
                                    <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-user-o"></i>
								</span> <input type="text" class="form-control" placeholder="用户名"
                                               id="admin_username">
                                    </div>
                                    <br>
                                    <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-key"></i>
								</span> <input type="text" class="form-control" placeholder="密码" id="admin_password">
                                    </div>
                                    <br>
                                    <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">添加</button>
                                </div>
                            </div>
                        </div>
                    </li>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
