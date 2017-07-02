/**
 * Created by GF on 2017/6/29.
 */
$(function () {
    $(window).bind('beforeunload',function () {
            var studentId = getStudentId();
            var testId=getTestId();
            var testRecordString = {studentId: studentId, testId: testId};
            var testRecord = $.toJSON(testRecordString);
            $.ajaxSetup({contentType: 'application/json'});
            $.ajax({
                url: 'test/insertSubmitTime',
                dataType: 'json',
                method: 'post',
                data: testRecord,
                success: function (data) {
                    if (data.result == "success") {
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
    );
})
