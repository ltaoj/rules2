/**
 * Created by GF on 2017/6/24.
 */
function isRegisted() {
    var testId=getContestId();
    var studentId=getStudentId();
    var contestString = {studentId: studentId, testId: testId};
    var contest = $.toJSON(contestString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/isRegisted',
        dataType: 'json',
        method: 'post',
        data:contest,
        success: function (data) {
            if(data!=null) {
                $('#isRegisted').html("已报名");
                $('#regist').hide();
                $('#enter').show();
            }else{
                $('#isRegisted').html("未报名");
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