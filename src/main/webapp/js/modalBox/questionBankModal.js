/**
 * Created by CMM on 2017/6/22.
 */
var practiceTitle;
function createQuestionBank(subjectTitle) {
    if (this.subjectTitle !== subjectTitle) {
        this.subjectTitle = subjectTitle;
        $('#subjectTitle').html(subjectTitle);
        $('#title').html("");
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
}

function addTitleToList(title, choice) {
    for (var i = 0; i < 4; i++) {
        if (i !== choice)
            practiceTitle[title].options[i].checked = 0;
    }
    practiceTitle[title].options[choice].checked = 1;
}
function submitTitle() {
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
            var checkedTitleList = data.object;
            //看要不要返回错题  还是在重做中出现
            alert(JSON.stringify(checkedTitleList));
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