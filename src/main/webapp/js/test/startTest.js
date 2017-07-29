/**
 * Created by GF on 2017/6/24.
 */
var testTitleList;
var testBlankTitleList;
var testJudgeTitleList;
var shortAnswer;
var caseAnswer;
var discussAnswer;
function startTest() {
    $('#testTitle').html("");
    var studentId = getStudentId();
    var tsetId=getTestId();
    var testRecordString = {studentId: studentId, testId: testId};
    var testRecord = $.toJSON(testRecordString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/startTest',
        dataType: 'json',
        method: 'post',
        async: false,
        data: testRecord,
        success: function (data) {
            testTitleList=data.titleList;
            testBlankTitleList=data.blanksList;
            testJudgeTitleList=data.judgeList;
            var testShortTitleList=data.shortList;
            var testCaseTitleList=data.caseList;
            var testDiscussTitleList=data.discussList;
            $('#testId').html(testId);
            //选择题
            $('#testTitle').append("一、选择题")
            for (var i = 0; i < testTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + testTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < 4; j++) {
                    var str = "A";
                    $('#testTitle').append("<tr><td><input name=\"" + testTitleList[i].titleId + "\" type=\"radio\" onclick=\"addTitleToTestList(" + i + "," + j + ")\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + testTitleList[i].options[j].content + "&nbsp;</td></tr>");
                }
                $('#testTitle').append("</table>");
            }
            //填空题
            $('#testTitle').append("<br>二、填空题")
            for (var i = 0; i < testBlankTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + testBlankTitleList[i].name.replace(/#/g,"___") + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < (testBlankTitleList[i].name.split("#").length)-1; j++) {
                    $('#testTitle').append("<tr><td>"+(j+1) + ".&nbsp;"+"<input type=\"text\" name=\"" + testBlankTitleList[i].titleId + "\" id=\"blankAnswer"+i+""+j+"\" />"+ "&nbsp;</td></tr>");
                }
                $('#testTitle').append("</table>");
            }
            //onblur =\"addBlankTitleToTestList(" + i + "," + j + ",blankAnswer"+i+""+j+")\"
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
            for (var i = 0; i < testShortTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + testShortTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#testTitle').append("<br><textarea rows='5' cols='70' id=\"shortAnswer"+i+"\"></textarea>");
                $('#testTitle').append("</table>");
            }
            //案例分析题
            $('#testTitle').append("<br><br>五、案例分析题")
            for (var i = 0; i < testCaseTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + testCaseTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#testTitle').append("<br><textarea rows='5' cols='70' id=\"caseAnswer"+i+"\"></textarea>");
                $('#testTitle').append("</table>");
            }
            //论述题
            $('#testTitle').append("<br><br>六、论述题")
            for (var i = 0; i < testDiscussTitleList.length; i++) {
                $('#testTitle').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + testDiscussTitleList[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#testTitle').append("<br><textarea rows='5' cols='70' id=\"discussAnswer"+i+"\"></textarea>");
                $('#testTitle').append("</table>");
            }
            getTestRecord(getStudentId());
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
        startTest();
        setTime();
        $('#startTestModal').attr("data-toggle", 'modal');
        $('#startTestModal').attr("data-target", '#testModal');
    } else {
        $('#startTestModal').attr("data-toggle", '');
        $('#startTestModal').attr("data-target", '');
        $("#isLogin").html("请先登录");
    }
}
function exitAll() {
    var id=document.getElementById("testId").innerText;
    if(id==getTestId()){
        $('#testModal').modal('hide');
        exitByX(getTestId());
    }if(id==getContestId()){
        $('#testModal').modal('hide');
        exitByX(getContestId());
    }
}
function exitByX(testId) {
    var studentId = getStudentId();
    var testRecordString = {studentId: studentId, testId: testId};
    var testRecord = $.toJSON(testRecordString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/deleteTestRecord',
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
    if(getSubmitTime()==null){
        var endTime=new Date((new Date(getTestEndTime())).format("yyyy/MM/dd hh:mm:ss"));
        var startTime=new Date((new Date(getStartTime())).format("yyyy/MM/dd hh:mm:ss"));
        if((endTime.getTime()-startTime.getTime())<(getDuration()*60*1000)){
            time = (endTime.getTime() - startTime.getTime()) / 1000;
        }else {
            time = getDuration() * 60;
        }
    }else {
        var testEndTime=new Date((new Date(getTestEndTime())).format("yyyy/MM/dd hh:mm:ss"));
        var startTime=new Date((new Date(getStartTime())).format("yyyy/MM/dd hh:mm:ss"));
        if((testEndTime.getTime()-startTime.getTime())<(getDuration()*60*1000)){
            time = (testEndTime.getTime() - startTime.getTime()) / 1000;
        }else {
            // var startTime = new Date((new Date(getStartTime())).format("yyyy/MM/dd hh:mm:ss"));
            var endTime = new Date((new Date(getSubmitTime())).format("yyyy/MM/dd hh:mm:ss"));
            var duration = (endTime.getTime() - startTime.getTime()) / 1000;
            time = getDuration() * 60 - duration;
        }
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

function addTitleToTestList(title, choice) {
    for (var i = 0; i < 4; i++) {
        if (i !== choice)
            testTitleList[title].options[i].checked = 0;
    }
    testTitleList[title].options[choice].checked = 1;
}

// var eachBlankTitleAnswer=new Array();
// function addBlankTitleToTestList(blankTitle,blankEach,blankId) {
//     // eachBlankTitleAnswer[blankEach]=document.getElementById(blankId).innerText;
//     eachBlankTitleAnswer[blankEach]=$(blankId).val();
//     for(var i=0;i<eachBlankTitleAnswer.length;i++){
//         if(eachBlankTitleAnswer[i]==null||eachBlankTitleAnswer[i]==""){
//             eachBlankTitleAnswer[i]="@csu";
//         }
//     }
//     var blankAnswer=eachBlankTitleAnswer.join("#");
//     testBlankTitleList[blankTitle].answer=blankAnswer;
//     console.log("填空题答案为：",blankAnswer);
// }

function submitBlankTitle(){
    var blankTitle=new Array();
    for(var i=0;i<testBlankTitleList.length;i++){
        var blankAnswerArray=new Array();
        var k=0;
        while($("#"+"blankAnswer"+i+k).length>0){
            var str=$("#" + "blankAnswer" +i+ k).val();
            var str1=str.replace(/#/g,"");
            var str2=str1.replace(/@csu/g,"");
            if(str2==null||str2==""){
                str2="@csu";
            }
            blankAnswerArray[k]=str2;
            k++;
        }
        blankTitle[i]=blankAnswerArray.join("#");
        testBlankTitleList[i].answer=blankTitle[i];
    }
}
function submitShortTitle() {
    var i=0;
    var shortAnswerArray=new Array();
    while($("#"+"shortAnswer"+i).length>0){
        var str=$("#"+"shortAnswer"+i).val();
        var str1=str.replace(/#/g,"");
        var str2=str1.replace(/@csu/g,"");
        if(str2==null||str2==""){
            str2="@csu";
        }
        shortAnswerArray[i]=str2;
        i++;
    }
    shortAnswer=shortAnswerArray.join("#");
}
function submitCaseTitle() {
    var i=0;
    var caseAnswerArray=new Array();
    while($("#"+"caseAnswer"+i).length>0){
        var str=$("#"+"caseAnswer"+i).val();
        var str1=str.replace(/#/g,"");
        var str2=str1.replace(/@csu/g,"");
        if(str2==null||str2==""){
            str2="@csu";
        }
        caseAnswerArray[i]=str2;
        i++;
    }
    caseAnswer=caseAnswerArray.join("#");
}
function submitDiscussTitle() {
    var i=0;
    var discussAnswerArray=new Array();
    while($("#"+"discussAnswer"+i).length>0){
        var str=$("#"+"discussAnswer"+i).val();
        var str1=str.replace(/#/g,"");
        var str2=str1.replace(/@csu/g,"");
        if(str2==null||str2==""){
            str2="@csu";
        }
        discussAnswerArray[i]=str2;
        i++;
    }
    discussAnswer=discussAnswerArray.join("#");
}
function submitAll() {
    var id=document.getElementById("testId").innerText;
    if(id==getTestId()){
        testJudgeTitleList=null;
        submitBlankTitle();
        submitCaseTitle();
        submitShortTitle();
        submitDiscussTitle();
        submitPaper(getTestId(),testTitleList,testBlankTitleList,testJudgeTitleList,shortAnswer,caseAnswer,discussAnswer);
    }if(id==getContestId()){
        contestJudgeTitleList=null;
        submitContestBlankTitle();
        submitContestShortTitle();
        submitContestCaseTitle();
        submitContestDiscussTitle();
        submitPaper(getContestId(),contestTitleList,contestBlankTitleList,contestJudgeTitleList,contestShortAnswer,contestCaseAnswer,contestDiscussAnswer);
    }
}
function submitPaper(testId,choiceList,blankList,judgeList,shortStr,caseStr,discussStr) {
    var studentId=getStudentId();
    var testrecord={studentId:studentId,testId:testId};
    var submitPaper={titleList:choiceList,blanksList:blankList,judgeList:judgeList,shortAnswer:shortStr,caseAnswer:caseStr,discussAnswer:discussStr};
    var recordTitles = {testrecord: testrecord, submitPaper: submitPaper};
    var recordTitlesJson = $.toJSON(recordTitles);
    alert(recordTitlesJson);
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