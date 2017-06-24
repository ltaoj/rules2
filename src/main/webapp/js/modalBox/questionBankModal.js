/**
 * Created by CMM on 2017/6/22.
 */
var subjectTitle = '';
function createQuestionBank(subjectTitle) {
    if (this.subjectTitle !== subjectTitle) {
        this.subjectTitle = subjectTitle;
        $('#subjectTitle').html(subjectTitle);
        $('#title').html("");
        $.ajax({
            url: 'title/practice',
            dataType: 'text',
            method: 'GET',
            data: {page: '1', count: '5'},
            success: function (data) {
                var Result = JSON.parse(data);
                var practiceTitle = Result.object;
                for (var i = 0; i < practiceTitle.length; i++) {
                    $('#title').append("<table style=\"font-family: '宋体'; font-size: 20px;\">" +
                        "<tr>" +
                        "<th colspan=\"4\"><span>" + (i + 1) + ".</span>" + practiceTitle[i].name + "</th>" +
                        "</tr>" +
                        "<br>");
                    for (var j = 0; j < 4; j++) {
                        var str = "A";
                        $('#title').append("<tr><td><input name=\"" + i + "\" type=\"radio\" value=\"1\"/>" + String.fromCharCode(str.charCodeAt() + j) + ".&nbsp;" + practiceTitle[i].options[0].content + "&nbsp;</td></tr>");
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