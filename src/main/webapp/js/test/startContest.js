/**
 * Created by GF on 2017/7/2.
 */
var contestTitleList;
var contestBlankTitleList;
var contestJudgeTitleList;
function startContest() {
    $('#testTitle').html("");
    var studentId = getStudentId();
    var testId=getContestId();
    var testRecordString = {studentId: studentId, testId: testId};
    var testRecord = $.toJSON(testRecordString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/startContest',
        dataType: 'json',
        method: 'post',
        async: false,
        data: testRecord,
        success: function (data) {
            contestTitleList=data.titleList;
            contestBlankTitleList=data.blanksList;
            contestJudgeTitleList=data.judgeList;
            var contestShortTitleList=data.shortList;
            var contestCaseTitleList=data.caseList;
            var contestDiscussTitleList=data.discussList;
            $('#testId').html(testId);
            //选择题
            $('#testTitle').append("一、选择题")
            for (var i = 0; i < contestTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + contestTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < 4; j++) {
                    var str = "A";
                    $('#testTitle').append("<tr><td><input name=\"" + contestTitleList[i].titleId + "\" type=\"radio\" onclick=\"addTitleToContestList(" + i + "," + j + ")\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + contestTitleList[i].options[j].content + "&nbsp;</td></tr>");
                }
                $('#testTitle').append("</table>");
            }
            //填空题
            $('#testTitle').append("<br>二、填空题")
            for (var i = 0; i < contestBlankTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + contestBlankTitleList[i].name.replace(/#/g,"___") + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < (contestBlankTitleList[i].name.split("#").length)-1; j++) {
                    $('#testTitle').append("<tr><td>"+(j+1) + ".&nbsp;"+"<input type=\"text\" name=\"" + contestBlankTitleList[i].titleId + "\"/>"+ "&nbsp;</td></tr>");
                }
                $('#testTitle').append("</table>");
            }
            //判断题
            // $('#testTitle').append("<br>三、判断题")
            // for (var i = 0; i < testJudgeTitleList.length; i++) {
            //     $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
            //         "<tr>" +
            //         "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + testJudgeTitleList[i].name + "</th>" +
            //         "</tr>" +
            //         "<br>");
            //     for (var j = 0; j < 4; j++) {
            //         var str = "A";
            //         $('#testTitle').append("<tr><td><input name=\"" + testTitleList[i].titleId + "\" type=\"radio\" onclick=\"addTitleToTestList(" + i + "," + j + ")\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + testTitleList[i].options[j].content + "&nbsp;</td></tr>");
            //     }
            //     $('#testTitle').append("</table>");
            // }
            //简答题
            $('#testTitle').append("<br><br>四、简答题")
            for (var i = 0; i < contestShortTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + contestShortTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#testTitle').append("<br><textarea rows='5' cols='70'></textarea>");
                $('#testTitle').append("</table>");
            }
            //案例分析题
            $('#testTitle').append("<br><br>五、案例分析题")
            for (var i = 0; i < contestCaseTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + contestCaseTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#testTitle').append("<br><textarea rows='5' cols='70'></textarea>");
                $('#testTitle').append("</table>");
            }
            //论述题
            $('#testTitle').append("<br><br>六、论述题")
            for (var i = 0; i < contestDiscussTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + contestDiscussTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#testTitle').append("<br><textarea rows='5' cols='70'></textarea>");
                $('#testTitle').append("</table>");
            }
            getContestRecord(getStudentId());
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
function enterContest() {
    if(getScore()==null) {
        startContest();
        setContestTime();
        $('#startContestModal').attr("data-toggle", 'modal');
        $('#startContestModal').attr("data-target", '#testModal');

    }else{
        $('#startContestModal').attr("data-toggle", '');
        $('#startContestModal').attr("data-target", '');
        $('#isContested').html("已考试");
    }
}
var int1;
function setContestTime() {
    var time;
    if(getContestEnd()==null){
        var startTime = new Date((new Date(getContestStart())).format("yyyy/MM/dd hh:mm:ss"));
        var endTime = new Date((new Date(getContestEndTime())).format("yyyy/MM/dd hh:mm:ss"));
        time = (endTime.getTime() - startTime.getTime()) / 1000;
    }else {
        var contestEndTime = new Date((new Date(getContestEndTime())).format("yyyy/MM/dd hh:mm:ss"));
        var startTime = new Date((new Date(getContestStart())).format("yyyy/MM/dd hh:mm:ss"));
        if((contestEndTime.getTime()-startTime.getTime())<(getContestDuration()*60*1000)){
            time = (contestEndTime.getTime() - startTime.getTime()) / 1000;
        }else {
            var endTime = new Date((new Date(getContestEnd())).format("yyyy/MM/dd hh:mm:ss"));
            var duration = (endTime.getTime() - startTime.getTime()) / 1000;
            time = getContestDuration() * 60 - duration;
        }
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

    int1 = setInterval(getRTime, 1000);
}
function addTitleToContestList(title, choice) {
    for (var i = 0; i < 4; i++) {
        if (i !== choice)
            contestTitleList[title].options[i].checked = 0;
    }
    contestTitleList[title].options[choice].checked = 1;
}