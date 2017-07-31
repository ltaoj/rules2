<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html
        class=" js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg no-smil svgclippaths js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg no-smil svgclippaths"
        lang="en" style="height: auto;">
<!--<![endif]-->
<head>
    中南大学-校规校纪系统
    <!-- Meta -->
    <meta charset="utf-8">
    <%--<!-- Web Fonts -->--%>
    <%--<link--%>
    <%--href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=cyrillic,latin"--%>
    <%--rel="stylesheet" type="text/css">--%>
    <!-- CSS Global Compulsory -->
    <link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <link href="css/blocks.css" rel="stylesheet">
    <link href="css/one.style.css" rel="stylesheet">
    <!-- CSS Footer -->
    <link href="css/footers/footer-v7.css" rel="stylesheet">
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
    <!-- CSS Theme -->
    <link href="css/app.style.css" rel="stylesheet">
    <!-- CSS Customization -->
    <link href="css/custom.css" rel="stylesheet">
    <!-- 全局JS -->
    <script src="plugins/jquery/jquery.min.js"></script>
    <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="js/forms/login.js"></script>
    <script src="plugins/json/jquery.json.min.js"></script>
    <!-- JS Implementing Plugins -->
    <!-- 平滑滚动 -->
    <script src="plugins/smoothScroll/jquery.smooth-scroll.js"></script>
    <script src="js/smoothScroll/smoothScroll.js"></script>
    <!-- 平滑滚动结束 -->
    <!--登录-->
    <script src="js/modalBox/loginModal.js"></script>
    <!--登录结束-->
    <!-- 弹出框 -->
    <script src="js/popup/personalPopup.js"></script>
    <!-- 弹出框结束 -->
    <!--通知-->
    <script src="js/notice/getPicture.js"></script>
    <script src="js/notice/getTextNotice.js"></script>
    <script src="js/modalBox/noticeModal.js"></script>
    <!--通知结束-->
    <!--题库-->
    <script src="js/modalBox/questionBankModal.js"></script>
    <!--题库结束-->
    <!--获取考试信息-->
    <script src="js/test/getTestInfo.js"></script>
    <script src="js/test/getTestRecord.js"></script>
    <script src="js/test/startTest.js"></script>
    <script src="js/test/getContestInfo.js"></script>
    <script src="js/test/isRegistedContest.js"></script>
    <script src="js/test/changeContestStatus.js"></script>
    <script src="js/test/getContestRecord.js"></script>
    <script src="js/test/registContest.js"></script>
    <script src="js/test/eventOnCloseWeb.js"></script>
    <script src="js/test/startContest.js"></script>
    <!--获取考试信息结束-->
    <!--Cookie-->
    <script src="js/cookie/cookie.js"></script>
    <script src="js/init/init.js"></script>
    <!--Cookie结束-->
    <!--feedback-->
    <script src="js/feedback/feedback.js"></script>
    <!--feedback结束-->
</head>
<body class="demo-lightbox-gallery  pace-done" id="body"
      data-target=".one-page-header" data-spy="scroll">

<!-- 登录按钮模态框 -->
<div class="modal fade" id="loginModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">x
                </button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="bs-example bs-example-form">
                    <div style="padding: 5px 160px 10px;">
                        <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-user-o"></i>
								</span> <input type="text" class="form-control" placeholder="学号" id="studentId">

                        </div>
                        <br>
                        <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-key"></i>
								</span> <input type="password" class="form-control" placeholder="密码" id="password">
                        </div>
                        <br>
                        <span id="loginMessage" class="g-color-red"></span>
                        <br>
                        <button type="button" class="btn btn-primary "
                                style="width: 250px" id="loginBT" onclick="login()">登录
                        </button>
                    </div>
                    <hr>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- 做题模态框 -->
<div class="modal fade" id="titleModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true" onclick="exitPractice()">x
                </button>
                <label class="modal-title" id="subjectTitle"></label>
            </div>
            <div class="modal-body" style="overflow-y: scroll; height: 800px;">
                <form class="bs-example bs-example-form" method="post">
                    <div id="title"></div>
                    <div style="text-align: center;">
                        <button type="button" class="btn btn-primary"
                                style="width: 100px; text-align: center" onclick="submitPractice()" id="submitTitleBT">提交
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- 考试模态框 -->

<div class="modal fade" id="testModal" data-backdrop="static">
    <div class="modal-dialog" style="width:1900px;height:1300px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-toggle="modal" data-target="#exitModal" onclick="exitModal()">x
                </button>
                <label class="modal-title" id="testId" style="display: none"></label>
                <div id="testCountMsg" class="HotDate">
                    <span id="test_t_h">00时</span> <span id="test_t_m">00分</span>
                    <span id="test_t_s">00秒</span>
                </div>
            </div>
            <div class="modal-body" >
                <form class="bs-example bs-example-form" method="post">
                    <div id="testTitle"></div>
                    <div style="text-align: center;">
                        <button type="button" class="btn btn-primary"
                                style="width: 100px; text-align: center" onclick="submitAll()">提交
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- 退出按钮模态框 -->
<div class="modal fade" id="exitModal" data-backdrop="static">
    <div class="modal-dialog" style="width:450px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="">
                    退出
                </h4>
            </div>
            <div class="modal-body">
                确认退出本次考试?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal" onclick="exitAll()" aria-hidden="true">退出
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true">
                    取消
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!--通知模态框-->
<div class="modal fade" id="noticeModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">x
                </button>
                <label class="modal-title" id="noticeTitle"></label>
            </div>
            <div class="modal-body" style="overflow-y: scroll; height: 400px;">
                <div id="noticeContent"></div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!--/.modal-->

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
                    <li class="page-scroll"><a href="#notice">通知公告</a></li>
                    <li class="page-scroll"><a href="#questionsbank">在线题库</a></li>
                    <li class="page-scroll"><a href="#examination">报名考试</a></li>
                    <li class="page-scroll"><a href="#ranking">考试排名</a></li>
                    <li class="page-scroll"><a href="#integral">积分兑换</a></li>
                    <li class="page-scroll"><a href="#contact">联系我们</a></li>
                    <li><a href="#help">考试须知</a></li>
                    <li><a href="Backstage.jsp" target="_blank">后台管理</a></li>
                    <li id="login-li"><a
                            class="btn-u btn-u-lg btn-u-green btn-u-upper rounded-2x"
                            data-toggle="modal"
                            data-target="#loginModal">登录</a></li>
                    <li style="display:none" id="personal-li"><a type="button"
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
    <div class="interactive-slider-v2">
        <div class="container">
            <div class="row">
                <div class="col-md-5 col-sm-12 col-xs-12 col-md-offset-1 promo-info">
                    <h1>
                        <strong>校规校纪</strong>系统
                    </h1>
                    <p>
                        <strong>校规校纪系统</strong>
                        使同学们参与考试更加的方便，省去了同学们由于参加考试寻找考场浪费的时间，让学生得到更充裕的时间做其他的事情
                    </p>
                    <p class="popover-options">
                        <a type="button" class="btn btn-primary btn-lg"><img
                                src="img/phone.png" width="25" height="22"> 手机App端</a>
                    </p>
                    <script>
                        $(function () {
                            $(".popover-options a")
                                .popover(
                                    {
                                        trigger: 'hover',
                                        html: true,
                                        content: "<img src='img/erWeiMa.jpg' width='120' height='120'>"
                                    });
                        });
                    </script>
                </div>
                <div class="col-md-6 col-sm-12 col-xs-12 banner-img overflow-h">
                    <img class="img-responsive animated slideInUp wow animated"
                         style="visibility: visible; animation-name: slideInUp;" alt="校规校纪"
                         src="img/schoolrules.png">
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 公告模块结束 -->
<!-- 通知栏 -->
<section id="notice">
    <div class="container-fluid with-bg-2">
        <div class="container margin-bottom-60">
            <div class="how-it-works">
                <div class="g-heading-v10 g-mb-65 text-center">
                    <h2 class="g-color-white">
                        <em class="block-name"> 通知公告</em>
                    </h2>
                    <p>这里将提供最新的消息，包括考试通知，考试结果，一系列你想要的答案</p>
                </div>
                <div class="col-md-5">
                    <div class="carousel slide carousel-v1" id="myCarousel">
                        <div class="carousel-inner" id="pictureNotice">
                            <div class="item active">
                                <img alt="" src="img/2.jpg">
                                <div class="carousel-caption">
                                    <p>1234</p>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-arrow">
                            <a class="left carousel-control" href="#myCarousel"
                               data-slide="prev"> <i class="fa fa-angle-left"></i>
                            </a> <a class="right carousel-control" href="#myCarousel"
                                    data-slide="next"> <i class="fa fa-angle-right"></i>
                        </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <table style="font-size: 16px">
                        <tbody id="textNotice">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 通知栏结束 -->
<!-- 题库考试  -->
<section id="questionsbank">
    <div class="container">
        <div class="g-pt-90 g-pb-70">
            <div class="g-heading-v10 g-mb-65 text-center">
                <h2>
                    <em class="block-name">题库</em>
                </h2>
                <p>选择下方的模拟考试题库进行在线练习</p>
            </div>
            <div class="row equal-height-columns">
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s;"
                     data-wow-duration="1.5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>校规校纪模拟考试</h3>
                        <br>
                        <ul class="list-unstyled">
                            <li id="isSimulationLogin" class="g-color-red">&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-footer">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                               data-toggle="" data-target=""
                               id="enterSimulation"
                              onclick="enterTitle()"
                            >选择练习</a>
                            </div>
                    </div>
                </div>
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s;"
                     data-wow-duration="1.5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>校规校纪模拟考试</h3>
                        <br>
                        <ul class="list-unstyled">
                            <li id="isSimulationBlankLogin" class="g-color-red">&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-footer">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                               data-toggle="" data-target=""
                               id="enterSimulationBlank"
                               onclick="enterBlankTitle()"
                            >填空练习</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s;"
                     data-wow-duration="1.5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>校规校纪模拟考试</h3>
                        <br>
                        <ul class="list-unstyled">
                            <li id="isSimulationJudgeLogin" class="g-color-red">&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-footer">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                               data-toggle="" data-target=""
                               id="enterSimulationJudge"
                               onclick="enterJudgeTitle()"
                            >判断练习</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s;"
                     data-wow-duration="1.5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>校规校纪模拟考试</h3>
                        <br>
                        <ul class="list-unstyled">
                            <li id="isSimulationShortLogin" class="g-color-red">&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-footer">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                               data-toggle="" data-target=""
                               id="enterSimulationShort"
                               onclick="enterShortTitle()"
                            >简答练习</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s;"
                     data-wow-duration="1.5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>校规校纪模拟考试</h3>
                        <br>
                        <ul class="list-unstyled">
                            <li id="isSimulationCaseLogin" class="g-color-red">&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-footer">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                               data-toggle="" data-target=""
                               id="enterSimulationCase"
                               onclick="enterCaseTitle()"
                            >案例分析练习</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 benefit-box animated fadeIn wow animated"
                     style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s;"
                     data-wow-duration="1.5s">
                    <div class="benefit equal-height-column"
                         style="height: 300px; width: 310px;">
                        <i class="fa  fa-database fa-3x"></i>
                        <h3>校规校纪模拟考试</h3>
                        <br>
                        <ul class="list-unstyled">
                            <li id="isSimulationDiscussLogin" class="g-color-red">&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-footer">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                               data-toggle="" data-target=""
                               id="enterSimulationDiscuss"
                               onclick="enterDiscussTitle()"
                            >论述练习</a>
                        </div>
                    </div>
                </div>
                <%--<div class="col-md-4 benefit-box animated fadeIn wow animated"--%>
                     <%--style="visibility: visible; animation-name: fadeIn; animation-duration: 1.5s; animation-delay: 0.5s;"--%>
                     <%--data-wow-duration="1.5s" data-wow-delay=".5s">--%>
                    <%--<div class="benefit equal-height-column"--%>
                         <%--style="height: 300px; width: 310px;">--%>
                        <%--<i class="fa  fa-database fa-3x"></i>--%>
                        <%--<h3>错题重做</h3>--%>
                        <%--<br>--%>
                        <%--<ul class="list-unstyled">--%>
                            <%--<li id="isWrongTitleLogin" class="g-color-red">&nbsp;</li>--%>
                        <%--</ul>--%>
                        <%--<div class="pricing-v9-footer">--%>
                            <%--<a--%>
                                    <%--class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"--%>
                                    <%--id="enterWrongTitle"--%>
                                    <%--data-toggle="" data-target="" onclick="enterWrongTitle()"--%>
                            <%-->进入</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>

        </div>
    </div>
    </div>
</section>
<!-- 题库结束  -->

<!-- 考试竞赛-->
<section id="examination">
    <div class="container-fluid">
        <div class="subscribe">
            <div class="g-heading-v10 g-mb-45">
                <h2>
                    <em class="block-name">考试和竞赛</em>
                </h2>
            </div>
            <div class="container">
                <div class="g-heading-v7 g-mb-40">
                    <p>请在下方的竞赛报名处选择你需要参加的考试，浏览必须的考试信息并点击报名按钮完成竞赛报名</p>
                    <p>请在下方的考试处选择你需要参加的考试，浏览必须的考试信息在规定时间内完成考试</p>
                </div>
                <div class="col-md-2 col-xs-6 col-2xs-12 g-mb-30"></div>
                <!-- /考试项目 -->
                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default" id="testInfo">校规校纪考试</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li id="test_name">&nbsp;</li>
                            <li id="testTime">&nbsp;</li>
                            <li id="testGrade">&nbsp;</li>
                            <li id="testDuration">&nbsp;</li>
                            <li>参考书目：《中南大学本科生手册》</li>
                            <li>注意：使用本人校内门户学号和密码登录</li>
                            <li class="g-color-red" id="isLogin">&nbsp;</li>
                            <li>&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            是否考试：<span class="g-color-default" id="isTested">未登录</span>
                        </div>
                        <div class="pricing-v9-footer" id="yesToTest">
                            <a
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                                    data-toggle="" data-target="" id="startTestModal"
                                    onclick="enterTest()">进入</a>
                        </div>
                        <div class="pricing-v9-footer" id="noToTest" style="display: none;">
                            <a      id="tested"
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                            >已考试</a>
                        </div>
                    </div>
                </div>

                <!-- /考试项目 -->
                <!-- 竞赛项目 -->

                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default" id="contestInfo">校规校纪竞赛</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li id="contest_name">&nbsp;</li>
                            <li id="contestTime">&nbsp;</li>
                            <li id="contestGrade">&nbsp;</li>
                            <li id="contestDuration">&nbsp;</li>
                            <li>参考书目：《中南大学本科生手册》</li>
                            <li>注意：使用本人校内门户学号和密码登录</li>
                            <li id="isContestLogin" class="g-color-red">&nbsp;</li>
                            <li id="isContested" class="g-color-red">&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            是否报名：<span class="g-color-default" id="isRegisted">未登录</span>
                        </div>
                        <div class="pricing-v9-footer" id="regist">
                            <a
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                                    onclick="regist()">报名</a>
                        </div>
                        <div class="pricing-v9-footer" id="enter" style="display:none">
                            <a
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                                    id="startContestModal"
                                    data-toggle="" data-target=""
                                    onclick="enterContest()">进入</a>
                        </div>
                        <div class="pricing-v9-footer" id="notBegin" style="display:none">
                            <a
                                    class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                                    disabled="true">未开始</a>
                        </div>
                        <div class="pricing-v9-footer" id="isEnd" style="display:none">
                            <a type="button"
                               class="btn-u btn-u-lg btn-u-light-green btn-u-upper rounded-2x"
                            >已结束</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 考试报名结束 -->
<!-- 考试排名 -->
<section id="ranking">
    <div class="container-fluid">
        <div class="subscribe">
            <div class="g-heading-v10 g-mb-45">
                <h2>
                    <em class="block-name">考试成绩</em>
                </h2>
            </div>
            <div class="container">
                <div class="g-heading-v7 g-mb-40">
                    <p>请在下方了解已参加过的考试的成绩信息</p>
                </div>
                <div class="col-md-2 col-xs-6 col-2xs-12 g-mb-30"></div>
                <!-- 考试成绩项目 -->
                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default">校规校纪</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li id="testName"></li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            你的成绩：<span class="g-color-default" id="testRecord">--</span>
                        </div>
                    </div>
                </div>
                <!-- 考试排名项目结束 -->
                <!-- 考试成绩项目 -->
                <div class="col-md-4 col-xs-6 col-2xs-12 g-mb-30">
                    <div class="pricing-v9 rounded-2x hover-effect">
                        <div class="pricing-v9-head">
                            <h3 class="h3">
                                <span class="g-color-default">校规校纪</span>
                            </h3>
                        </div>
                        <ul class="list-unstyled">
                            <li id="contestName"></li>
                            <li id="rank">排名:--</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                            <li>&nbsp;</li>
                        </ul>
                        <div class="pricing-v9-price">
                            你的成绩：<span class="g-color-default" id="contestRecord">--</span>
                        </div>
                    </div>
                </div>
                <!-- 考试排名项目结束 -->
            </div>
        </div>
    </div>
</section>
<!-- 考试排名结束 -->


<!-- 积分兑换 -->
<section id="integral">
    <div class="container">
        <div class="g-pt-90">
            <div class="g-heading-v10 g-mb-30 text-center">
                <h2>
                    <em class="block-name">积分兑换</em>
                </h2>
                <p>通过学习获得的积分可以兑换以下物品，物品会进行间断的更新。</p>
            </div>
            <div class="row equal-height-columns">
                <div class="col-md-3 short-info short-info-bg-1 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods1" src="img/exchangegoods1.jpg">
                    <div class="short-info-text">
                        <h2>物品1</h2>
                        <p>物品1</p>
                    </div>
                </div>
                <div class="col-md-3 short-info short-info-bg-2 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods2" src="img/exchangegoods3.jpg">
                    <div class="short-info-text">
                        <h2>物品2</h2>
                        <p>物品2</p>
                    </div>
                </div>
                <div class="col-md-3 short-info short-info-bg-3 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods3" src="img/exchangegoods1.jpg">
                    <div class="short-info-text">
                        <h2>物品3</h2>
                        <p>物品3</p>
                    </div>
                </div>
                <div class="col-md-3 short-info short-info-bg-4 equal-height-column"
                     style="height: 420px;">
                    <img alt="Goods4" src="img/exchangegoods2.jpg">
                    <div class="short-info-text">
                        <h2>物品4</h2>
                        <p>物品4</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 积分兑换结束 -->
<hr>
<!-- 页脚 -->
<section id="contact">
    <div class="container-fluid with-bg-2 no-side-padding">
        <div class="container">
            <div class="footer">
                <div class="row">
                    <div class="col-md-5">
                        <div class="g-heading-v10 g-mb-65">
                            <h2 class="g-color-white">
                                <em class="block-name"> 联系我们</em>问题和调查
                            </h2>
                            <p>您可以通过填写该表格向我们发送问题或评论，我们很想听听你的意见。我们会根据意见进行完善。</p>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <div class="sky-form contact-style" id="sky-form3">
                            <fieldset id="feedback">
                                <div class="row">
                                    <div class="col-md-6 col-md-offset-0">
                                        <div class="margin-bottom-30">
                                            <input name="name" class="form-control" id="name" type="text"
                                                   placeholder="姓名">
                                        </div>
                                        <div class="margin-bottom-30">
                                            <input name="email" class="form-control" id="email"
                                                   type="text" placeholder="邮箱*">
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-md-offset-0">
                                        <div class="margin-bottom-30">
											<textarea name="message"
                                                      class="form-control g-textarea-noresize" id="message"
                                                      placeholder="消息内容" rows="4"></textarea>
                                        </div>
                                        <label id="submitMessage"></label>
                                        <p>
                                            <button class="btn-u btn-u-lg btn-u-upper rounded-2x"
                                                     onclick="feedbackClick()">提交
                                            </button>
                                        </p>
                                    </div>
                                </div>
                            </fieldset>
                            <div class="message">
                                <i class="rounded-x fa fa-check"></i>
                                <p>提交成功</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <div class="cr-left">
                        <p>
                            <strong>微信小程序</strong>：中南大学校规校纪考试
                        </p>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <div class="cr-right">
                        <ul class="list-unstyled list-inline cr-list">
                            <li class="page-scroll"><a class="rounded" href="#notice"><span
                                    class="glyphicon glyphicon-pushpin"></span></a></li>
                            <li><a class="rounded" href="#"><span
                                    class="glyphicon glyphicon-link"></span></a></li>
                            <li class="popover-options"><a class="rounded"
                                                           data-placement="top"><span
                                    class="glyphicon glyphicon-qrcode"></span></a>
                                <script>
                                    $(function () {
                                        $(".popover-options a")
                                            .popover(
                                                {
                                                    trigger: 'hover',
                                                    html: true,
                                                    content: "<img src='img/erWeiMa.jpg' width='120' height='120'>"
                                                });
                                    });
                                </script>
                            </li>
                            <li><a class="rounded" href="mailto:????@????.com"><span
                                    class="glyphicon glyphicon-envelope"></span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 页脚结束 -->

<!-- JS Implementing Plugins -->
<script src="plugins/owl-carousel2/owl.carousel.min.js"></script>
</body>
</html>