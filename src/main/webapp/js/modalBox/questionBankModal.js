/**
 * Created by CMM on 2017/6/22.
 */
var practiceTitle;
function enterTitle() {
    if (getStudentId() != null) {
        $('#enterSimulation').attr("data-toggle", 'modal');
        $('#enterSimulation').attr("data-target", '#titleModal');
        createQuestionBank('校规校纪模拟考试');
    } else {
        $('#enterSimulation').attr("data-toggle", '');
        $('#enterSimulation').attr("data-target", '');
        $("#isSimulationLogin").html("请先登录");
    }
}
function enterBlankTitle() {
    if (getStudentId() != null) {
        $('#enterSimulationBlank').attr("data-toggle", 'modal');
        $('#enterSimulationBlank').attr("data-target", '#titleModal');
        createQuestionBank('校规校纪模拟考试');
    } else {
        $('#enterSimulationBlank').attr("data-toggle", '');
        $('#enterSimulationBlank').attr("data-target", '');
        $("#isSimulationBlankLogin").html("请先登录");
    }
}
function enterJudgeTitle() {
    if (getStudentId() != null) {
        $('#enterSimulationJudge').attr("data-toggle", 'modal');
        $('#enterSimulationJudge').attr("data-target", '#titleModal');
        createQuestionBank('校规校纪模拟考试');
    } else {
        $('#enterSimulationJudge').attr("data-toggle", '');
        $('#enterSimulationJudge').attr("data-target", '');
        $("#isSimulationJudgeLogin").html("请先登录");
    }
}
function enterShortTitle() {
    if (getStudentId() != null) {
        $('#enterSimulationShort').attr("data-toggle", 'modal');
        $('#enterSimulationShort').attr("data-target", '#titleModal');
        createQuestionBank('校规校纪模拟考试');
    } else {
        $('#enterSimulationShort').attr("data-toggle", '');
        $('#enterSimulationShort').attr("data-target", '');
        $("#isSimulationShortLogin").html("请先登录");
    }
}
function enterCaseTitle() {
    if (getStudentId() != null) {
        $('#enterSimulationCase').attr("data-toggle", 'modal');
        $('#enterSimulationCase').attr("data-target", '#titleModal');
        createQuestionBank('校规校纪模拟考试');
    } else {
        $('#enterSimulationCase').attr("data-toggle", '');
        $('#enterSimulationCase').attr("data-target", '');
        $("#isSimulationCaseLogin").html("请先登录");
    }
}
function enterDiscussTitle() {
    if (getStudentId() != null) {
        $('#enterSimulationDiscuss').attr("data-toggle", 'modal');
        $('#enterSimulationDiscuss').attr("data-target", '#titleModal');
        createQuestionBank('校规校纪模拟考试');
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
    if (this.subjectTitle !== subjectTitle) {
        this.subjectTitle = subjectTitle;
        $('#subjectTitle').html(subjectTitle);
        $('#title').html("");
        if (subjectTitle == '校规校纪模拟考试') {
            $.ajax({
                url: 'title/practice',
                dataType: 'text',
                method: 'GET',
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
        else {
            $.ajaxSetup({contentType: 'application/json'});
            $.ajax({
                url: 'title/wrongList',
                dataType: 'json',
                method: 'POST',
                data: $.toJSON(getAccount()),
                success: function (data) {
                    practiceTitle = data.object;
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
                }
                ,
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
    }
}

function addTitleToList(title, choice) {
    for (var i = 0; i < 4; i++) {
        if (i !== choice)
            practiceTitle[title].options[i].checked = 0;
    }
    practiceTitle[title].options[choice].checked = 1;
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