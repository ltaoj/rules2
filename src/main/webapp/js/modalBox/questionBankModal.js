/**
 * Created by CMM on 2017/6/22.
 */
var practiceTitle;
var practiceBlankTitle;
var choicepage=1;
var blankpage=1;
var shortpage=1;
var casepage=1;
var discusspage=1;
function enterTitle() {
    if (getStudentId() != null) {
        $('#title').html("");
        createQuestionBank('选择题练习');
        $('#enterSimulation').attr("data-toggle", 'modal');
        $('#enterSimulation').attr("data-target", '#titleModal');
    } else {
        $('#enterSimulation').attr("data-toggle", '');
        $('#enterSimulation').attr("data-target", '');
        $("#isSimulationLogin").html("请先登录");
    }
}

function enterBlankTitle() {
    if (getStudentId() != null) {
        $('#title').html("");
        blankTitlePractice('填空题练习');
        $('#enterSimulationBlank').attr("data-toggle", 'modal');
        $('#enterSimulationBlank').attr("data-target", '#titleModal');
    } else {
        $('#enterSimulationBlank').attr("data-toggle", '');
        $('#enterSimulationBlank').attr("data-target", '');
        $("#isSimulationBlankLogin").html("请先登录");
    }
}
// function enterJudgeTitle() {
//     if (getStudentId() != null) {
//         $('#enterSimulationJudge').attr("data-toggle", 'modal');
//         $('#enterSimulationJudge').attr("data-target", '#titleModal');
//         createQuestionBank('校规校纪模拟考试');
//     } else {
//         $('#enterSimulationJudge').attr("data-toggle", '');
//         $('#enterSimulationJudge').attr("data-target", '');
//         $("#isSimulationJudgeLogin").html("请先登录");
//     }
// }
function enterShortTitle() {
    if (getStudentId() != null) {
        $('#title').html("");
        console.log("简答题",123);
        shortTitlePractice('简答题练习');
        $('#enterSimulationShort').attr("data-toggle", 'modal');
        $('#enterSimulationShort').attr("data-target", '#titleModal');
    } else {
        $('#enterSimulationShort').attr("data-toggle", '');
        $('#enterSimulationShort').attr("data-target", '');
        $("#isSimulationShortLogin").html("请先登录");
    }
}
function enterCaseTitle() {
    if (getStudentId() != null) {
        $('#title').html("");
        caseTitlePractice('案例分析题练习');
        $('#enterSimulationCase').attr("data-toggle", 'modal');
        $('#enterSimulationCase').attr("data-target", '#titleModal');
    } else {
        $('#enterSimulationCase').attr("data-toggle", '');
        $('#enterSimulationCase').attr("data-target", '');
        $("#isSimulationCaseLogin").html("请先登录");
    }
}
function enterDiscussTitle() {
    if (getStudentId() != null) {
        $('#title').html("");
        discussTitlePractice('论述题练习');
        $('#enterSimulationDiscuss').attr("data-toggle", 'modal');
        $('#enterSimulationDiscuss').attr("data-target", '#titleModal');
    } else {
        $('#enterSimulationDiscuss').attr("data-toggle", '');
        $('#enterSimulationDiscuss').attr("data-target", '');
        $("#isSimulationDiscussLogin").html("请先登录");
    }
}
function enterWrongTitle() {
    if (getStudentId() != null) {
        $('#enterWrongTitle').attr("data-toggle", 'modal');
        $('#enterWrongTitle').attr("data-target", '#titleModal');
        createQuestionBank('错题重做');
    } else {
        $('#enterWrongTitle').attr("data-toggle", '');
        $('#enterWrongTitle').attr("data-target", '');
        $("#isWrongTitleLogin").html("请先登录");
    }
}
function createQuestionBank(subjectTitle) {
    $('#subjectTitle').html(subjectTitle);
    $('#title').html("");
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'title/practice',
        dataType: 'text',
        method: 'get',
        data:{
            page:1,
            count:10,
            type:0,
        },
        success: function (data) {
            var Result = JSON.parse(data);
            practiceTitle = Result.object;
            for (var i = 0; i < practiceTitle.length; i++) {
                $('#title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + practiceTitle[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                for (var j = 0; j < 4; j++) {
                    var str = "A";
                    practiceTitle[i].options[j].checked = 0;
                    $('#title').append("<tr><td><input name=\"" + practiceTitle[i].titleId + "\" type=\"radio\" onclick=\"addTitleToList(" + i + "," + j + ")\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + practiceTitle[i].options[j].content + "&nbsp;</td></tr>"
                    );
                }
                $('#title').append("</table>");
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
        // else {
        //     $.ajaxSetup({contentType: 'application/json'});
        //     $.ajax({
        //         url: 'title/wrongList',
        //         dataType: 'json',
        //         method: 'POST',
        //         data: $.toJSON(getAccount()),
        //         success: function (data) {
        //             practiceTitle = data.object;
        //             for (var i = 0; i < practiceTitle.length; i++) {
        //                 $('#title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
        //                     "<tr>" +
        //                     "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + practiceTitle[i].name + "</th>" +
        //                     "</tr>" +
        //                     "<br>");
        //                 for (var j = 0; j < 4; j++) {
        //                     var str = "A";
        //                     practiceTitle[i].options[j].checked = 0;
        //                     $('#title').append("<tr><td><input name=\"" + practiceTitle[i].titleId + "\" type=\"radio\" onclick=\"addTitleToList(" + i + "," + j + ")\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + practiceTitle[i].options[j].content + "&nbsp;</td></tr>"
        //                     );
        //                 }
        //                 $('#title').append("</table>");
        //             }
        //         }
        //         ,
        //         error: function (xhr) {
        //             // 导致出错的原因较多，以后再研究
        //             alert('error:' + JSON.stringify(xhr));
        //         }
        //     }).done(function (data) {
        //         // 请求成功后要做的工作
        //         console.log('success');
        //     }).fail(function () {
        //         // 请求失败后要做的工作
        //         console.log('error');
        //     }).always(function () {
        //         // 不管成功或失败都要做的工作
        //         console.log('complete');
        //     });



function addTitleToList(title, choice) {
    for (var i = 0; i < 4; i++) {
        if (i !== choice)
            practiceTitle[title].options[i].checked = 0;
    }
    practiceTitle[title].options[choice].checked = 1;
}
function submitPractice() {
    var str=document.getElementById("subjectTitle").innerText;
    if(str=="简答题练习"){
        shortTitlePractice('简答题练习');
    }else if(str=="案例分析题练习"){
        caseTitlePractice('案例分析题练习');
    }else if(str=="论述题练习"){
        discussTitlePractice('论述题练习');
    }else if(str=="填空题练习"){
        blankTitlePractice('填空题练习');
    }else if(str=="选择题练习"){
        submitTitle();
    }
}
function submitTitle() {
    if ($('#submitTitleBT').html() == '提交') {
        this.account = getAccount();
        var accountTitles = {account: this.account, titleList: practiceTitle};
        var accountTitlesJson = $.toJSON(accountTitles);
        $.ajaxSetup({contentType: 'application/json'});
        $.ajax({
            url: 'title/submit',
            dataType: 'json',
            method: 'POST',
            data: accountTitlesJson,
            success: function (data) {
                alert(JSON.stringify(data));
                $('#submitTitleBT').attr("class", "btn btn-success")
                $('#submitTitleBT').html("下一组");
                practiceTitle = null;
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
    } else {
        createQuestionBank(this.subjectTitle);
        $('#submitTitleBT').attr("class", "btn btn-primary")
        $('#submitTitleBT').html("提交");
    }
}

for (var j = 0; j < 4; j++) {
    var str = "A";
    document.writeln(String.fromCharCode(str.charCodeAt() + j));
}

function blankTitlePractice(subjectTitle){
        $('#subjectTitle').html(subjectTitle);
        $('#title').html("");
        $.ajaxSetup({contentType: 'application/json'});
            $.ajax({
                url: 'title/practice',
                dataType: 'text',
                method: 'get',
                data:{
                    page:blankpage,
                    count:10,
                    type:1,
                },
                success: function (data) {
                    var Result = JSON.parse(data);
                    practiceBlankTitle = Result.object;
                    for (var i = 0; i < practiceBlankTitle.length; i++) {
                        $('#title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                            "<tr>" +
                            "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + practiceBlankTitle[i].name.replace(/#/g, "___") + "</th>" +
                            "</tr>" +
                            "<br>");
                        for (var j = 0; j < (practiceBlankTitle[i].name.split("#").length) - 1; j++) {
                            $('#title').append("<tr><td>" + (j + 1) + ".&nbsp;" + "<input type=\"text\" name=\"" + practiceBlankTitle[i].titleId + "\" id=\"practiceBlankAnswer" + i + "" + j + "\"  value=\""+practiceBlankTitle[i].answer.split("#")[j]+"\"/>&nbsp;</td></tr>");
                            // +practiceBlankTitle[i].answer.split("#")[j] 在练习的时候显示答案
                        }
                        $('#title').append("</table>");
                    }
                    $('#submitTitleBT').attr("class", "btn btn-success")
                    $('#submitTitleBT').html("下一组");
                    blankpage=blankpage+1;
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
function shortTitlePractice(subjectTitle){
    $('#subjectTitle').html(subjectTitle);
    $('#title').html("");
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'title/practice',
        dataType: 'text',
        method: 'get',
        data:{
            page:shortpage,
            count:5,
            type:3,
        },
        success: function (data) {
            var Result = JSON.parse(data);
            var practiceShortTitle = Result.object;
            for (var i = 0; i < practiceShortTitle.length; i++) {
                $('#title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + practiceShortTitle[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#title').append("<br><textarea rows='5' cols='70' id=\"practiceShortAnswer"+i+"\">"+practiceShortTitle[i].answer+"</textarea>");
                $('#title').append("</table>");
            }
            $('#submitTitleBT').attr("class", "btn btn-success")
            $('#submitTitleBT').html("下一组");
            shortpage=shortpage+1;
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
function caseTitlePractice(subjectTitle){
    $('#subjectTitle').html(subjectTitle);
    $('#title').html("");
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'title/practice',
        dataType: 'text',
        method: 'get',
        data:{
            page:casepage,
            count:5,
            type:4,
        },
        success: function (data) {
            var Result = JSON.parse(data);
            var practiceCaseTitle = Result.object;
            for (var i = 0; i < practiceCaseTitle.length; i++) {
                $('#title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + practiceCaseTitle[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#title').append("<br><textarea rows='5' cols='70' id=\"practiceCaseAnswer"+i+"\">"+practiceCaseTitle[i].answer+"</textarea>");
                $('#title').append("</table>");
            }
            $('#submitTitleBT').attr("class", "btn btn-success")
            $('#submitTitleBT').html("下一组");
            casepage=casepage+1;
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
function discussTitlePractice(subjectTitle){
    $('#subjectTitle').html(subjectTitle);
    $('#title').html("");
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'title/practice',
        dataType: 'text',
        method: 'get',
        data:{
            page:discusspage,
            count:5,
            type:5,
        },
        success: function (data) {
            var Result = JSON.parse(data);
            var practiceDiscussTitle = Result.object;
            for (var i = 0; i < practiceDiscussTitle.length; i++) {
                $('#title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                    "<tr>" +
                    "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + practiceDiscussTitle[i].name + "</th>" +
                    "</tr>" +
                    "<br>");
                $('#title').append("<br><textarea rows='5' cols='70' id=\"practiceDiscussAnswer"+i+"\">"+practiceDiscussTitle[i].answer+"</textarea>");
                $('#title').append("</table>");
            }
            $('#submitTitleBT').attr("class", "btn btn-success")
            $('#submitTitleBT').html("下一组");
            discusspage=discusspage+1;
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
function exitPractice() {
    $('#titleModal').modal('hide');
    blankpage=1;
    shortpage=1;
    casepage=1;
    discusspage=1;
}