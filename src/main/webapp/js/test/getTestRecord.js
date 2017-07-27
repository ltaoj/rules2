/**
 * Created by GF on 2017/6/24.
 */
var start_time;
var submit_time;
function getTestRecord(studentId) {
    var testId=getTestId();
    var testRecordString = {studentId: studentId, testId: testId};
    var testRecord = $.toJSON(testRecordString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/testRecordInfo',
        dataType: 'json',
        method: 'post',
        async: false,
        data:testRecord,
        success: function (data) {
            if(data.score==null) {
                $('#isTested').html("未考试");
                start_time=data.startTime;
                submit_time=data.submitTime;
            }else if(data.score==-1){
                $('#isTested').html("试卷批阅中");
                $('#yesToTest').hide();
                $('#noToTest').show();
            }else{
                $('#isTested').html("已考试");
                $('#testRecord').html(data.score);
                $('#yesToTest').hide();
                $('#noToTest').show();
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
function getStartTime() {
    return start_time;
}
function getSubmitTime() {
    return submit_time;
}
