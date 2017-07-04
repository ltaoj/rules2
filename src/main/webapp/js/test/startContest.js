/**
 * Created by GF on 2017/7/2.
 */
var contestTitleList;
function startContest() {
    $('#testTitle').html("");
    var studentId = getStudentId();
    var tsetId=getContestId();
    var testRecordString = {studentId: studentId, testId: testId};
    var testRecord = $.toJSON(testRecordString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/startContest',
        dataType: 'json',
        method: 'post',
        data: testRecord,
        success: function (data) {
            contestTitleList=data;
            $('#testId').html(testId);
            for (var i = 0; i < data.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + data[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < 4; j++) {
                    var str = "A";
                    $('#testTitle').append("<tr><td><input name=\"" + data[i].titleId + "\" type=\"radio\" onclick=\"addTitleToContestList(" + i + "," + j + ")\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + data[i].options[j].content + "&nbsp;</td></tr>");
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
function enterContest() {
    if(getScore()==null) {
        setContestTime();
        $('#startContestModal').attr("data-toggle", 'modal');
        $('#startContestModal').attr("data-target", '#testModal');
        startContest();
    }else{
        $('#startContestModal').attr("data-toggle", '');
        $('#startContestModal').attr("data-target", '');
        $('#isContested').html("已考试");
    }
}
var int1;
function setContestTime() {
    var time;
    getContestRecord(getStudentId());
    if(getContestEnd()==null){
        var startTime = new Date((new Date(getContestStart())).format("yyyy/MM/dd hh:mm:ss"));
        var endTime = new Date((new Date(getContestEndTime())).format("yyyy/MM/dd hh:mm:ss"));
        time = (endTime.getTime() - startTime.getTime()) / 1000;
    }else {
        var startTime = new Date((new Date(getContestStart())).format("yyyy/MM/dd hh:mm:ss"));
        var endTime = new Date((new Date(getContestEnd())).format("yyyy/MM/dd hh:mm:ss"));
        var duration = (endTime.getTime() - startTime.getTime()) / 1000;
        time = getContestDuration() * 60 - duration;
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