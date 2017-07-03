/**
 * Created by GF on 2017/7/3.
 */
function operate() {
    alert("点击操作按钮");
    if ($('#isClick').html() == "删除") {
        deleteContest();
    }
}
function deleteContest() {
    var testId = getContest_id();
    var testInfoString = {testId: testId};
    var testinfo = $.toJSON(testInfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/deleteTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                $('#contest_id').html("");
                $('#contest_info_name').val("");
                $('#contest_start_year').val("");
                $('#contest_start_month').val("");
                $('#contest_start_day').val("");
                $('#contest_start_hour').val("");
                $('#contest_start_minute').val("");
                $('#contest_start_seconds').val("");
                $('#contest_info_time').val("");
                $('#contest_end_year').val("");
                $('#contest_end_month').val("");
                $('#contest_end_day').val("");
                $('#contest_end_hour').val("");
                $('#contest_end_minute').val("");
                $('#contest_end_seconds').val("");
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
function deleteTest() {
    testId = $('#test_id').html();
    var testInfoString = {testId: testId};
    var testinfo = $.toJSON(testInfoString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'test/deleteTest',
        dataType: 'json',
        method: 'POST',
        data: testinfo,
        success: function (data) {
            if (data.result == "success") {
                $('#test_id').html("");
                $('#test_info_name').val("");
                $('#test_start_year').val("");
                $('#test_start_month').val("");
                $('#test_start_day').val("");
                $('#test_start_hour').val("");
                $('#test_start_minute').val("");
                $('#test_start_seconds').val("");
                $('#test_info_time').val("");
                $('#test_end_year').val("");
                $('#test_end_month').val("");
                $('#test_end_day').val("");
                $('#test_end_hour').val("");
                $('#test_end_minute').val("");
                $('#test_end_seconds').val("");
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