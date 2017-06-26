/**
 * Created by GF on 2017/6/24.
 */
function changeStatus() {
    var testId=getContestId();
    var studentId=getStudentId();
    var contestString = {studentId: studentId, testId: testId};
    var contest = $.toJSON(contestString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/getContestStatus',
        dataType: 'json',
        method: 'post',
        data:contest,
        success: function (data) {
            var status;
            if(data.status==0){
                status="未开始"
            }if(data.status==1){
                status="进行中"
            }if(data.status==2){
                status="已结束"
            }
            $('#contestStatus').append("竞赛状态:"+status);
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
