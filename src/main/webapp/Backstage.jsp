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
    <script src="plugins/json/jquery.json.min.js"></script>
    <!-- 平滑滚动 -->
    <script src="plugins/smoothScroll/jquery.smooth-scroll.js"></script>
    <script src="js/smoothScroll/smoothScroll.js"></script>
    <!-- 平滑滚动结束 -->
    <!--Excel导入导出-->
    <script src="plugins/js-xlsx/shim.js"></script>
    <script src="plugins/js-xlsx/xlsx.full.min.js"></script>
    <script src="js/backstage/readExcel.js"></script>
    <!--Excel导入导出结束-->
    <!-- 后台管理js -->
    <script src="js/backstage/initBackstage.js"></script>
    <script src="js/backstage/contestAndTest.js"></script>
    <script src="js/backstage/achievement.js"></script>
    <script src="js/backstage/notice.js"></script>
    <!-- 后台管理js结束 -->
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
                        initBackstage(tab_t_li[this.index].id);
                        $('#main_title').html(tab_t_li[this.index].innerHTML);
                    }
                }
            }
        }
    </script>
</head>
<!-- 竞赛试题模态框 -->
<div class="modal fade" id="contestTitleModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal"
                        aria-hidden="true" onclick="exitRandomGenerate()">X
                </button>
            </div>
            <div class="modal-body" style="overflow-y: scroll; height: 800px;">
                <form class="bs-example bs-example-form" method="post">
                    <div id="contest_title"></div>
                    <div style="text-align: center;">
                        <button type="button" class="btn btn-primary"
                                style="width: 100px; text-align: center" onclick="" data-dismiss="modal"
                                aria-hidden="true">确认
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
<!-- 竞赛操作按钮模态框 -->
<div class="modal fade" id="contestOperateModal" data-backdrop="static">
    <div class="modal-dialog" style="width:450px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="contest_operate_header">

                </h4>
            </div>
            <div class="modal-body" id="contest_operate_message">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal" onclick="operateContest()" aria-hidden="true">确定
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
<!-- 考试操作按钮模态框 -->
<div class="modal fade" id="testOperateModal" data-backdrop="static">
    <div class="modal-dialog" style="width:450px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="test_operate_header">

                </h4>
            </div>
            <div class="modal-body" id="test_operate_message">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal" onclick="operateTest()" aria-hidden="true">确定
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
                        <li class="to_red" id="0"><i class="fa fa-user-circle "></i>&nbsp;&nbsp;学生用户</li>
                        <li id="1"><i class="fa fa-newspaper-o"></i>&nbsp;&nbsp;考试试题</li>
                        <li id="2"><i class="fa fa-file-o"></i>&nbsp;&nbsp;考试竞赛</li>
                        <li id="3"><i class="fa fa-gg"></i>&nbsp;&nbsp;学生成绩</li>
                        <li id="4"><i class="fa fa-bullhorn"></i>&nbsp;&nbsp;考试通知</li>
                        <li id="5"><i class="fa fa-user-o"></i>&nbsp;&nbsp;管理员</li>
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
                                <input type="file" name="file1" size="30" onchange="setObj(this)"/>
                                <br>
                                <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x"
                                        onclick="importf(1)">上传
                                </button>
                                <div id="demo"></div>
                            </div>
                        </div>
                    </li>
                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <h3>导入试题</h3>
                                <input type="file" name="file1" size="30" onchange="setObj(this)"/>
                                <br>
                                <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x"
                                        onclick="importf(0)">上传
                                </button>
                            </div>
                        </div>
                    </li>
                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <div class="row">
                                    <div class="col-md-5">
                                        <div style="width:80%">
                                            <h3>竞赛信息</h3>
                                            <div class="btn-group">
                                                <a class="btn btn-primary" onclick="onContestSearchBT(this)" id="contest_search">查询</a>
                                                <a class="btn btn-default" onclick="onContestUpdateBT(this)" id="contest_update">修改</a>
                                                <a class="btn btn-default" onclick="onContestDeleteBT(this)" id="contest_delete">删除</a>
                                                <a class="btn btn-default" onclick="onContestAddBT(this)" id="contest_add">添加</a>
                                            </div>
                                            <br>
                                            <br>
                                            <div class="input-group input-group">
								        <span class="input-group-addon"><i class="fa fa-edit"></i>
								        </span> <input type="text" class="form-control" placeholder="竞赛名称"
                                                       id="contest_info_name">
                                            </div>
                                            <br>
                                            <label>考试年级：</label>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="contest_grade" value="">
                                                <span class="input-group-addon">级</span>
                                            </div>
                                            <br>
                                            <label>开始时间：</label>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="contest_start_year">
                                                <span class="input-group-addon">年</span>
                                                <input type="text" class="form-control" id="contest_start_month">
                                                <span class="input-group-addon">月</span>
                                                <input type="text" class="form-control" id="contest_start_day">
                                                <span class="input-group-addon">日</span>
                                            </div>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="contest_start_hour">
                                                <span class="input-group-addon">时</span>
                                                <input type="text" class="form-control" id="contest_start_minute"
                                                       value="00">
                                                <span class="input-group-addon">分</span>
                                                <input type="text" class="form-control" id="contest_start_seconds"
                                                       value="00">
                                                <span class="input-group-addon">秒</span>
                                            </div>
                                            <br>
                                            <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-tripadvisor"></i>
								</span> <input type="text" class="form-control" placeholder="竞赛时长(分钟)"
                                               id="contest_info_time">
                                            </div>
                                            <br>
                                            <label>结束时间：</label>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="contest_end_year">
                                                <span class="input-group-addon">年</span>
                                                <input type="text" class="form-control" id="contest_end_month">
                                                <span class="input-group-addon">月</span>
                                                <input type="text" class="form-control" id="contest_end_day">
                                                <span class="input-group-addon">日</span>
                                            </div>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="contest_end_hour">
                                                <span class="input-group-addon">时</span>
                                                <input type="text" class="form-control" id="contest_end_minute"
                                                       value="00">
                                                <span class="input-group-addon">分</span>
                                                <input type="text" class="form-control" id="contest_end_seconds"
                                                       value="00">
                                                <span class="input-group-addon">秒</span>
                                            </div>
                                            <br>
                                            <span id="operate_all_message"></span>
                                            <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x"
                                                    data-toggle="" data-target=""
                                                    id="contest_operateBT" onclick="operateContestMessageBox()">操作
                                            </button>
                                        </div>
                                    </div>
                                    <div class="col-md-5" style="border-left: 1px solid #e7e7eb;padding-left:40px ">
                                        <div style="width:80%">
                                            <h3>考试信息</h3>
                                            <div class="btn-group">
<<<<<<< HEAD
                                                <a class="btn btn-primary">查询</a>
                                                <a class="btn btn-default" id="test_isClick">修改</a>
                                                <a class="btn btn-default">删除</a>
                                                <a class="btn btn-default">添加</a>
=======
                                                <a class="btn btn-primary" onclick="onTestSearchBT(this)" id="test_search">查询</a>
                                                <a class="btn btn-default" onclick="onTestUpdateBT(this)" id="test_update">修改</a>
                                                <a class="btn btn-default" onclick="onTestDeleteBT(this)" id="test_delete">删除</a>
                                                <a class="btn btn-default" onclick="onTestAddBT(this)" id="test_add">添加</a>
>>>>>>> 4151c46851f967f9c5f5d85d5dbd50d354341350
                                            </div>
                                            <br>
                                            <br>
                                            <div class="input-group input-group">
								        <span class="input-group-addon"><i class="fa fa-edit"></i>
								        </span> <input type="text" class="form-control" placeholder="考试名称"
                                                       id="test_info_name">
                                            </div>
                                            <br>
                                            <label>考试年级：</label>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="test_grade" value="">
                                                <span class="input-group-addon">级</span>
                                            </div>
                                            <br>
                                            <label>开始时间：</label>
                                            <br>

                                            <div class="input-group">
                                                <input type="text" class="form-control" id="test_start_year">
                                                <span class="input-group-addon">年</span>
                                                <input type="text" class="form-control" id="test_start_month">
                                                <span class="input-group-addon">月</span>
                                                <input type="text" class="form-control" id="test_start_day">
                                                <span class="input-group-addon">日</span>
                                            </div>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="test_start_hour">
                                                <span class="input-group-addon">时</span>
                                                <input type="text" class="form-control" id="test_start_minute"
                                                       value="00">
                                                <span class="input-group-addon">分</span>
                                                <input type="text" class="form-control" id="test_start_seconds"
                                                       value="00">
                                                <span class="input-group-addon">秒</span>
                                            </div>
                                            <br>
                                            <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-tripadvisor"></i>
								</span> <input type="text" class="form-control" placeholder="考试时长(分钟)"
                                               id="test_info_time">
                                            </div>
                                            <br>
                                            <label>结束时间：</label>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="test_end_year">
                                                <span class="input-group-addon">年</span>
                                                <input type="text" class="form-control" id="test_end_month">
                                                <span class="input-group-addon">月</span>
                                                <input type="text" class="form-control" id="test_end_day">
                                                <span class="input-group-addon">日</span>
                                            </div>
                                            <br>
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="test_end_hour">
                                                <span class="input-group-addon">时</span>
                                                <input type="text" class="form-control" id="test_end_minute" value="00">
                                                <span class="input-group-addon">分</span>
                                                <input type="text" class="form-control" id="test_end_seconds"
                                                       value="00">
                                                <span class="input-group-addon">秒</span>
                                            </div>
                                            <br>
                                            <span id="test_operate_all_message"></span>
                                            <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x"
                                                    data-toggle="" data-target=""
                                                    id="test_operateBT" onclick="operateTestMessageBox()">操作
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box">
                                <h3>指定竞赛题目</h3>
                                <br>
                                <span>题库中含有：</span><label>1000</label><span>道题</span>&nbsp;<button
                                    class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x"
                                    id="random_generate_title" data-toggle="" data-target=""
                                    onclick="getContesttitle()">随机生成
                            </button>
                                <select id="contest_title_num">
                                    <option value="0">10</option>
                                    <option value="1">20</option>
                                    <option value="2" selected>50</option>
                                    <option value="3">100</option>
                                    <option value="4">200</option>
                                </select><span>题</span>

                            </div>
                            <div class="box">
                                <h3>查看竞赛报名学生</h3>
                                <br>
                                <span>当前报名学生人数为：</span><label id="contestregistionNum">1000</label><span>人</span>
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
                                        class="fa fa-refresh fa-spin fa-2x"></i>
                                    <label></label>
                                    <br>
                                    <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">查询</button>
                                </div>
                            </div>
                        </div>
                        <div class="main_bd">
                            <div class="box">
                                <h3>查询年纪,学院，学校成绩(判断权限，改变内容)</h3>
                                <br>
                                <div style="width:100%;font-size: 15px;">
                                    <span>学院:</span>
                                    <select id="college" onchange="getMajor(this.value)">
                                        <option value="">请选择学院(默认全校)</option>
                                    </select>
                                    <span>专业:</span>
                                    <select id="major" onchange="getClazz(this.value)">
                                        <option value=""></option>
                                    </select>
                                    <span>年级:</span>
                                    <select id="grade">
                                        <option value="2015">2015</option>
                                        <option value="2016">2016</option>
                                        <option value="2017">2017</option>
                                    </select>
                                    <span>班级:</span>
                                    <select id="clazz">
                                        <option value=""></option>
                                    </select>
                                </div>
                                <br>
                                <div style="width:100%">
                                    <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x" onclick="query()">查询</button>
                                    <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x"
                                            style="margin-left: 20px">导出
                                    </button>
                                </div>

                                <div id="achieve_result" style="width:100%">
                                    <span>共查询出记录数目<span id="achieve_num"></span>个</span>
                                    <table class="table table-hover">
                                        <thead>
                                            <th>学号</th>
                                            <th>姓名</th>
                                            <th>学院</th>
                                            <th>专业</th>
                                            <th>年级</th>
                                            <th>班级</th>
                                            <th>分数</th>
                                        </thead>
                                        <tbody id="achieve_tbody"></tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </li>

                    <li class="hide">
                        <div class="main_bd">
                            <div class="box">
                                <h3>发布通知</h3>
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
                                    <br>
                                    <div class="input-group input-group">
								<span class="input-group-addon"><i class="fa fa-pencil"></i>
								</span> <textarea class="form-control" placeholder="内容"
                                                  id="publish_notice_content" rows="3"></textarea>
                                    </div>

                                </div>
                                <br>
                                <button class="btn-u btn-u-primary btn-u-green btn-u-upper rounded-2x">发布</button>
                            </div>
                            <div class="box">
                                <h3>修改/删除通知</h3>
                                <span style="font-size: 20px;">图片通知：</span>

                                <div id="backstage_notice_picture" style="width:40%;line-height:2.0;font-size:18px">
                                </div>
                                <span style="font-size: 20px;"
                                      >文字通知：</span>
                                <div style="width:40%;line-height:2.0;font-size:18px" id="backstage_notice_text"></div>
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
