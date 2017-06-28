/**
 * Created by GF on 2017/6/24.
 */
var testTitleList;
function startTest(testId) {
    $('#testTitle').html("");
    var studentId = getStudentId();
    var testRecordString = {studentId: studentId, testId: testId};
    var testRecord = $.toJSON(testRecordString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/startTest',
        dataType: 'json',
        method: 'post',
        data: testRecord,
        success: function (data) {
            testTitleList=data;
            $('#testId').html(testId);
            for (var i = 0; i < data.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + data[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < 4; j++) {
                    var str = "A";
                    $('#testTitle').append("<tr><td><input name=\"" + data[i].titleId + "\" type=\"radio\" onclick=\"addTitleToTestList(" + i + "," + j + ")\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + data[i].options[j].content + "&nbsp;</td></tr>");
                }
                $('#testTitle').append("</table>");
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('Error:' + JSON.stringify(xhr));
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
function enterTest() {
    if (getStudentId() != null) {
        setTime();
        $('#startTestModal').attr("data-toggle", 'modal');
        $('#startTestModal').attr("data-target", '#testModal');
        startTest(getTestId());
    } else {
        $("#isLogin").html("请先登录");
    }
}
function enterContest() {
    if(getScore()==null) {
        setContestTime();
        $('#startContestModal').attr("data-toggle", 'modal');
        $('#startContestModal').attr("data-target", '#testModal');
        startTest(getContestId());
    }else{
        $('#startContestModal').attr("data-toggle", '');
        $('#startContestModal').attr("data-target", '');
        $('#isContested').html("已考试");
    }
}
function exitAll() {
    var id=document.getElementById("testId").innerText;
    if(id==getTestId()){
        exitByX(getTestId());
    }if(id==getContestId()){
        exitByX(getContestId());
    }
}
function exitByX(testId) {
    var studentId = getStudentId();
    var testRecordString = {studentId: studentId, testId: testId};
    var testRecord = $.toJSON(testRecordString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/insertSubmitTime',
        dataType: 'json',
        method: 'post',
        data: testRecord,
        success: function (data) {
            if (data.result == "success") {
                clearInterval(int);
                clearInterval(int1);
            }
        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('Error:' + JSON.stringify(xhr));
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
var int;
function setTime() {
    var time;
    getTestRecord(getStudentId());
    if(getSubmitTime()==null){
        time=getDuration()*60;
    }else {
        var startTime = new Date((new Date(getStartTime())).format("yyyy/MM/dd hh:mm:ss"));
        var endTime = new Date((new Date(getSubmitTime())).format("yyyy/MM/dd hh:mm:ss"));
        var duration = (endTime.getTime() - startTime.getTime()) / 1000;
        time = getDuration() * 60 - duration;
    }
    function getRTime() {
        time--;
        if(time==0){
            submitTestTitle(getTestId());
            clearInterval(int);
        }
        var h = Math.floor(time / 60 / 60);
        var m = Math.floor(time / 60 % 60);
        var s = Math.floor(time % 60);
        document.getElementById("test_t_h").innerHTML = h + "时";
        document.getElementById("test_t_m").innerHTML = m + "分";
        document.getElementById("test_t_s").innerHTML = s + "秒";
    }

    int = setInterval(getRTime, 1000);
}
var int1;
function setContestTime() {
    var time;
    getContestRecord(getStudentId());
    if(getContestEnd()==null){
        time=getDuration()*60;
    }else {
        var startTime = new Date((new Date(getContestStart())).format("yyyy/MM/dd hh:mm:ss"));
        var endTime = new Date((new Date(getContestEnd())).format("yyyy/MM/dd hh:mm:ss"));
        var duration = (endTime.getTime() - startTime.getTime()) / 1000;
        time = getDuration() * 60 - duration;
    }
    function getRTime() {
        time--;
        if(time==0){
            submitTestTitle(getContestId());
            clearInterval(int1);
        }
        var h = Math.floor(time / 60 / 60);
        var m = Math.floor(time / 60 % 60);
        var s = Math.floor(time % 60);
        document.getElementById("test_t_h").innerHTML = h + "时";
        document.getElementById("test_t_m").innerHTML = m + "分";
        document.getElementById("test_t_s").innerHTML = s + "秒";
    }

    int = setInterval(getRTime, 1000);
}



function addTitleToTestList(title, choice) {
    for (var i = 0; i < 4; i++) {
        if (i !== choice)
            testTitleList[title].options[i].checked = 0;
    }
    testTitleList[title].options[choice].checked = 1;
}
function submitAll() {
    var id=document.getElementById("testId").innerText;
    if(id==getTestId()){
        submitTestTitle(getTestId());
    }if(id==getContestId()){
        submitTestTitle(getContestId());
    }
}
function submitTestTitle(testId) {
    var studentId=getStudentId();
    var testrecord={studentId:studentId,testId:testId};
    var recordTitles = {testrecord: testrecord, titleList: testTitleList};
    var recordTitlesJson = $.toJSON(recordTitles);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/submitTest',
        dataType: 'json',
        method: 'POST',
        data: recordTitlesJson,
        success: function (data) {
            $('#testModal').modal('hide');
            getTestRecord(getStudentId());
            getContestRecord(getStudentId());
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