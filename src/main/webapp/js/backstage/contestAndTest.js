/**
 * Created by GF on 2017/7/3.
 */
//模态框居中
function contestOperateModal() {
    $('#contestOperateModal').on('show.bs.modal', function () {
        var $this = $(this);
        var $modal_dialog = $this.find('.modal-dialog');
        var m_top = ($(window).height() - $modal_dialog.height()) / 3;
        $modal_dialog.css({
            'margin': m_top + 'px auto'
        });
    });
}
function testOperateModal() {
    $('#testOperateModal').on('show.bs.modal', function () {
        var $this = $(this);
        var $modal_dialog = $this.find('.modal-dialog');
        var m_top = ($(window).height() - $modal_dialog.height()) / 3;
        $modal_dialog.css({
            'margin': m_top + 'px auto'
        });
    });
}
var contestOperateInfo;
function onContestSearchBT(obj) {
    document.getElementById("contest_update").className='btn btn-default';
    document.getElementById("contest_add").className='btn btn-default';
    document.getElementById("contest_search").className='btn btn-primary';
    document.getElementById("contest_delete").className='btn btn-default';
    getBackContestInfo();
    contestOperateInfo=obj.innerHTML;
}
function onContestDeleteBT(obj) {
    document.getElementById("contest_update").className='btn btn-default';
    document.getElementById("contest_add").className='btn btn-default';
    document.getElementById("contest_search").className='btn btn-default';
    document.getElementById("contest_delete").className='btn btn-primary';
    getBackContestInfo();
    contestOperateInfo=obj.innerHTML;
}
function onContestAddBT(obj) {
    document.getElementById("contest_update").className='btn btn-default';
    document.getElementById("contest_add").className='btn btn-primary';
    document.getElementById("contest_search").className='btn btn-default';
    document.getElementById("contest_delete").className='btn btn-default';
    $('#contest_info_name').val("");
    $('#contest_grade').val(new Date().format("yyyy"));
    $('#contest_start_year').val("");
    $('#contest_start_month').val("");
    $('#contest_start_day').val("");
    $('#contest_start_hour').val("");
    $('#contest_info_time').val("");
    $('#contest_end_year').val("");
    $('#contest_end_month').val("");
    $('#contest_end_day').val("");
    $('#contest_end_hour').val("");
    contestOperateInfo=obj.innerHTML;
}
function onContestUpdateBT(obj) {
    document.getElementById("contest_update").className='btn btn-primary';
    document.getElementById("contest_add").className='btn btn-default';
    document.getElementById("contest_search").className='btn btn-default';
    document.getElementById("contest_delete").className='btn btn-default';
    getBackContestInfo();
    contestOperateInfo=obj.innerHTML;
}
//点击操作 弹出模态框
function operateContestMessageBox() {
    if (contestOperateInfo == "删除") {
        if( $('#contest_info_name').val()==""||$('#contest_grade').val()==""||$('#contest_start_year').val()==""||$('#contest_start_month').val()==""||
            $('#contest_start_day').val()==""||$('#contest_start_hour').val()==""||$('#contest_info_time').val()==""||$('#contest_end_year').val()==""||
            $('#contest_end_month').val()==""||$('#contest_end_day').val()==""||$('#contest_end_hour').val()==""){
            $('#contest_operate_all_message').html("暂无竞赛");
            $('#contest_operateBT').attr("data-toggle", '');
            $('#contest_operateBT').attr("data-target", '');
        }else {
            $('#contest_operate_all_message').html("");
            $('#contest_operate_header').html("删除");
            $('#contest_operate_message').html("确认删除此次竞赛！");
            $('#contest_operateBT').attr("data-toggle", 'modal');
            $('#contest_operateBT').attr("data-target", '#contestOperateModal');
            contestOperateModal();
        }
    }if(contestOperateInfo == "查询"){
    }if(contestOperateInfo == "添加"){
        if( $('#contest_info_name').val()==""||$('#contest_grade').val()==""||$('#contest_start_year').val()==""||$('#contest_start_month').val()==""||
            $('#contest_start_day').val()==""||$('#contest_start_hour').val()==""||$('#contest_info_time').val()==""||$('#contest_end_year').val()==""||
            $('#contest_end_month').val()==""||$('#contest_end_day').val()==""||$('#contest_end_hour').val()==""){
            $('#contest_operate_all_message').html("竞赛添加信息不能为空");
            $('#contest_operateBT').attr("data-toggle", '');
            $('#contest_operateBT').attr("data-target", '');
        }else {
            $('#contest_operate_all_message').html("");
            $('#contest_operate_header').html("添加");
            $('#contest_operate_message').html("确认添加此次竞赛！");
            $('#contest_operateBT').attr("data-toggle", 'modal');
            $('#contest_operateBT').attr("data-target", '#contestOperateModal');
            contestOperateModal();
        }
    }if(contestOperateInfo == "修改"){
        if( $('#contest_info_name').val()==""||$('#contest_grade').val()==""||$('#contest_start_year').val()==""||$('#contest_start_month').val()==""||
            $('#contest_start_day').val()==""||$('#contest_start_hour').val()==""||$('#contest_info_time').val()==""||$('#contest_end_year').val()==""||
            $('#contest_end_month').val()==""||$('#contest_end_day').val()==""||$('#contest_end_hour').val()==""){
            $('#contest_operate_all_message').html("竞赛修改信息不能为空");
            $('#contest_operateBT').attr("data-toggle", '');
            $('#contest_operateBT').attr("data-target", '');
        }else {
            $('#contest_operate_all_message').html("");
            $('#contest_operate_header').html("修改");
            $('#contest_operate_message').html("确认修改此次竞赛！");
            $('#contest_operateBT').attr("data-toggle", 'modal');
            $('#contest_operateBT').attr("data-target", '#contestOperateModal');
            contestOperateModal();
        }
    }
}
function operateContest() {
    if (contestOperateInfo == "删除") {
        deleteContest();
    }if(contestOperateInfo == "查询"){
        getBackContestInfo();
    }if(contestOperateInfo == "添加"){
        insertContest();
    }if(contestOperateInfo == "修改"){
        updateContest();
    }
}
var testOperateInfo;
function onTestSearchBT(obj) {
    document.getElementById("test_update").className='btn btn-default';
    document.getElementById("test_add").className='btn btn-default';
    document.getElementById("test_search").className='btn btn-primary';
    document.getElementById("test_delete").className='btn btn-default';
    getBackTestInfo();
    testOperateInfo=obj.innerHTML;
}
function onTestDeleteBT(obj) {
    document.getElementById("test_update").className='btn btn-default';
    document.getElementById("test_add").className='btn btn-default';
    document.getElementById("test_search").className='btn btn-default';
    document.getElementById("test_delete").className='btn btn-primary';
    getBackTestInfo();
    testOperateInfo=obj.innerHTML;
}
function onTestAddBT(obj) {
    document.getElementById("test_update").className='btn btn-default';
    document.getElementById("test_add").className='btn btn-primary';
    document.getElementById("test_search").className='btn btn-default';
    document.getElementById("test_delete").className='btn btn-default';
    $('#test_info_name').val("");
    $('#test_grade').val(new Date().format("yyyy"));
    $('#test_start_year').val("");
    $('#test_start_month').val("");
    $('#test_start_day').val("");
    $('#test_start_hour').val("");
    $('#test_info_time').val("");
    $('#test_end_year').val("");
    $('#test_end_month').val("");
    $('#test_end_day').val("");
    $('#test_end_hour').val("");
    testOperateInfo=obj.innerHTML;
}
function onTestUpdateBT(obj) {
    document.getElementById("test_update").className='btn btn-primary';
    document.getElementById("test_add").className='btn btn-default';
    document.getElementById("test_search").className='btn btn-default';
    document.getElementById("test_delete").className='btn btn-default';
    getBackTestInfo();
    testOperateInfo=obj.innerHTML;
}
function operateTestMessageBox() {
    if (testOperateInfo == "删除") {
        if( $('#test_info_name').val()==""||$('#test_grade').val()==""||$('#test_start_year').val()==""||$('#test_start_month').val()==""||
            $('#test_start_day').val()==""||$('#test_start_hour').val()==""||$('#test_info_time').val()==""||$('#test_end_year').val()==""||
            $('#test_end_month').val()==""||$('#test_end_day').val()==""||$('#test_end_hour').val()==""){
            $('#test_operate_all_message').html("暂无考试");
            $('#test_operateBT').attr("data-toggle", '');
            $('#test_operateBT').attr("data-target", '');
        }else {
            $('#test_operate_all_message').html("");
            $('#test_operate_header').html("删除");
            $('#test_operate_message').html("确认删除此次考试！");
            $('#test_operateBT').attr("data-toggle", 'modal');
            $('#test_operateBT').attr("data-target", '#testOperateModal');
            testOperateModal();
        }
    }if(testOperateInfo == "查询"){
    }if(testOperateInfo == "添加"){
        if( $('#test_info_name').val()==""||$('#test_grade').val()==""||$('#test_start_year').val()==""||$('#test_start_month').val()==""||
            $('#test_start_day').val()==""||$('#test_start_hour').val()==""||$('#test_info_time').val()==""||$('#test_end_year').val()==""||
            $('#test_end_month').val()==""||$('#test_end_day').val()==""||$('#test_end_hour').val()==""){
            $('#test_operate_all_message').html("考试添加信息不能为空");
            $('#test_operateBT').attr("data-toggle", '');
            $('#test_operateBT').attr("data-target", '');
        }else {
            $('#test_operate_all_message').html("");
            $('#test_operate_header').html("添加");
            $('#test_operate_message').html("确认添加此次考试！");
            $('#test_operateBT').attr("data-toggle", 'modal');
            $('#test_operateBT').attr("data-target", '#testOperateModal');
            testOperateModal();
        }
    }if(testOperateInfo == "修改"){
        if( $('#test_info_name').val()==""||$('#test_grade').val()==""||$('#test_start_year').val()==""||$('#test_start_month').val()==""||
            $('#test_start_day').val()==""||$('#test_start_hour').val()==""||$('#test_info_time').val()==""||$('#test_end_year').val()==""||
            $('#test_end_month').val()==""||$('#test_end_day').val()==""||$('#test_end_hour').val()==""){
            $('#test_operate_all_message').html("考试修改信息不能为空");
            $('#test_operateBT').attr("data-toggle", '');
            $('#test_operateBT').attr("data-target", '');
        }else {
            $('#test_operate_all_message').html("");
            $('#test_operate_header').html("修改");
            $('#test_operate_message').html("确认修改此次考试！");
            $('#test_operateBT').attr("data-toggle", 'modal');
            $('#test_operateBT').attr("data-target", '#testOperateModal');
            testOperateModal();
        }
    }
}
function operateTest() {
    if (testOperateInfo == "删除") {
        deleteTest();
    }if(testOperateInfo == "查询"){
        getBackTestInfo();
    }if(testOperateInfo == "添加"){
        insertTest();
    }if(testOperateInfo == "修改"){
        updateTest();
    }
}
function deleteContest() {
    var testId = getContest_id();
    var testInfoString = {testId: testId};
    var testinfo = JSON.stringify(testInfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/deleteTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                alert("删除成功");
                $('#contest_info_name').val("");
                $('#contest_grade').val("");
                $('#contest_start_year').val("");
                $('#contest_start_month').val("");
                $('#contest_start_day').val("");
                $('#contest_start_hour').val("");
                $('#contest_info_time').val("");
                $('#contest_end_year').val("");
                $('#contest_end_month').val("");
                $('#contest_end_day').val("");
                $('#contest_end_hour').val("");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function insertContest() {
    var name=$('#contest_info_name').val();
    var type=1;
    var grade=$('#contest_grade').val();
    var startTimeString=$('#contest_start_year').val()+"/"+$('#contest_start_month').val()+"/"+$('#contest_start_day').val()+" "+$('#contest_start_hour').val()+":00:00";
    var endTimeString=$('#contest_end_year').val()+"/"+$('#contest_end_month').val()+"/"+$('#contest_end_day').val()+" "+$('#contest_end_hour').val()+":00:00";
    var startTime=new Date(startTimeString).getTime();
    var endTime=new Date(endTimeString).getTime();
    var duration=$('#contest_info_time').val();
    var testinfoString={name:name,type:type,grade:grade,startTime:startTime,endTime:endTime,duration:duration};
    var testinfo = JSON.stringify(testinfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/insertTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                alert("添加成功");
                $('#contest_info_name').val("");
                $('#contest_grade').val("");
                $('#contest_start_year').val("");
                $('#contest_start_month').val("");
                $('#contest_start_day').val("");
                $('#contest_start_hour').val("");
                $('#contest_info_time').val("");
                $('#contest_end_year').val("");
                $('#contest_end_month').val("");
                $('#contest_end_day').val("");
                $('#contest_end_hour').val("");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function updateContest() {
    var testId=getContest_id();
    var name=$('#contest_info_name').val();
    var type=1;
    var grade=$('#contest_grade').val();
    var startTimeString=$('#contest_start_year').val()+"/"+$('#contest_start_month').val()+"/"+$('#contest_start_day').val()+" "+$('#contest_start_hour').val()+":00:00";
    var endTimeString=$('#contest_end_year').val()+"/"+$('#contest_end_month').val()+"/"+$('#contest_end_day').val()+" "+$('#contest_end_hour').val()+":00:00";
    var startTime=new Date(startTimeString).getTime();
    var endTime=new Date(endTimeString).getTime();
    var duration=$('#contest_info_time').val();
    var testinfoString={testId:testId,name:name,type:type,grade:grade,startTime:startTime,endTime:endTime,duration:duration};
    var testinfo = JSON.stringify(testinfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/updateTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                alert("修改成功");
                $('#contest_info_name').val("");
                $('#contest_grade').val("");
                $('#contest_start_year').val("");
                $('#contest_start_month').val("");
                $('#contest_start_day').val("");
                $('#contest_start_hour').val("");
                $('#contest_info_time').val("");
                $('#contest_end_year').val("");
                $('#contest_end_month').val("");
                $('#contest_end_day').val("");
                $('#contest_end_hour').val("");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function deleteTest() {
    var testId=getTest_id();
    var testInfoString = {testId: testId};
    var testinfo = JSON.stringify(testInfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/deleteTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                $('#test_info_name').val("");
                $('#test_grade').val("");
                $('#test_start_year').val("");
                $('#test_start_month').val("");
                $('#test_start_day').val("");
                $('#test_start_hour').val("");
                $('#test_info_time').val("");
                $('#test_end_year').val("");
                $('#test_end_month').val("");
                $('#test_end_day').val("");
                $('#test_end_hour').val("");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function insertTest() {
    var name=$('#test_info_name').val();
    var type=0;
    var grade=$('#test_grade').val();
    var startTimeString=$('#test_start_year').val()+"/"+$('#test_start_month').val()+"/"+$('#test_start_day').val()+" "+$('#test_start_hour').val()+":00:00";
    var endTimeString=$('#test_end_year').val()+"/"+$('#test_end_month').val()+"/"+$('#test_end_day').val()+" "+$('#test_end_hour').val()+":00:00";
    var startTime=new Date(startTimeString).getTime();
    var endTime=new Date(endTimeString).getTime();
    var duration=$('#test_info_time').val();
    var testinfoString={name:name,type:type,grade:grade,startTime:startTime,endTime:endTime,duration:duration};
    var testinfo = JSON.stringify(testinfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/insertTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                alert("添加成功");
                $('#test_info_name').val("");
                $('#test_grade').val("");
                $('#test_start_year').val("");
                $('#test_start_month').val("");
                $('#test_start_day').val("");
                $('#test_start_hour').val("");
                $('#test_info_time').val("");
                $('#test_end_year').val("");
                $('#test_end_month').val("");
                $('#test_end_day').val("");
                $('#test_end_hour').val("");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function updateTest() {
    var testId=getTest_id();
    var name=$('#test_info_name').val();
    var type=0;
    var grade=$('#test_grade').val();
    var startTimeString=$('#test_start_year').val()+"/"+$('#test_start_month').val()+"/"+$('#test_start_day').val()+" "+$('#test_start_hour').val()+":00:00";
    var endTimeString=$('#test_end_year').val()+"/"+$('#test_end_month').val()+"/"+$('#test_end_day').val()+" "+$('#test_end_hour').val()+":00:00";
    var startTime=new Date(startTimeString).getTime();
    var endTime=new Date(endTimeString).getTime();
    var duration=$('#test_info_time').val();
    var testinfoString={testId:testId,name:name,type:type,grade:grade,startTime:startTime,endTime:endTime,duration:duration};
    var testinfo = JSON.stringify(testinfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/updateTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                alert("修改成功");
                $('#test_info_name').val("");
                $('#test_grade').val("");
                $('#test_start_year').val("");
                $('#test_start_month').val("");
                $('#test_start_day').val("");
                $('#test_start_hour').val("");
                $('#test_info_time').val("");
                $('#test_end_year').val("");
                $('#test_end_month').val("");
                $('#test_end_day').val("");
                $('#test_end_hour').val("");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function getContestRandomTitle() {
    $('#contest_title').html("");
    var counts=$('#contest_title_num').find("option:selected").text();
    var countString=counts;
    var count=JSON.stringify(countString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/getContestRandomTitle',
        dataType: 'json',
        method: 'POST',
        async: false,
        data: count,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $('#contest_title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + data[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < 4; j++) {
                    var str = "A";
                    $('#contest_title').append("<tr><td><input name=\"" + data[i].titleId + "\" type=\"radio\" />" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + data[i].options[j].content + "&nbsp;</td></tr>");
                }
                $('#contest_title').append("</table>");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function exitRandomGenerate() {
    var testId=getContest_id();
    var testinfoJson={testId:testId};
    var testinfo=JSON.stringify(testinfoJson);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/deleteContestTitle',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if(data.result=="success"){
                alert("删除成功");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function getContesttitle() {
    var testId=getContest_id();
    var testinfoJson={testId:testId};
    var testinfo=JSON.stringify(testinfoJson);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/getContesttitle',
        dataType: 'json',
        method: 'POST',
        async: false,
        data: testinfo,
        success: function (data) {
            if(data.result=="success"){
                alert("已有竞赛试题");
                $('#random_generate_title').attr("data-toggle",'');
                $('#random_generate_title').attr("data-target",'');
            }else{
                getContestRandomTitle();
                $('#random_generate_title').attr("data-toggle",'modal');
                $('#random_generate_title').attr("data-target",'#contestTitleModal');
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}