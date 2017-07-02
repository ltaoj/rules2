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
            tab("tab_t", "li", "tab_c", "div", "onmouseover")
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
                        <li><i class="fa fa-book"></i>&nbsp;&nbsp;打卡学习</li>
                        <li><i class="fa fa-user-o"></i>&nbsp;&nbsp;管理员</li>
                    </ul>
                </div>
            </div>
            <div class="col_main">
                <div class="col_main_inner" id="tab_c">
                    <h1 id="main_title"><i class="fa fa-user-circle "></i>&nbsp;&nbsp;学生用户</h1>
                    <div><input type="file" name="file1" size="30"/>
                        <input type="submit" name="submit" value="提交">
                        <input type="reset" name="reset" value="重置"></div>
                    <div class="hide">2</div>
                    <div class="hide">3</div>
                    <div class="hide">4</div>
                    <div class="hide">5</div>
                    <div class="hide">6</div>
                    <div class="hide">7</div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
