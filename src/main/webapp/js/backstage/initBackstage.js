/**
 * Created by GF on 2017/7/3.
 */
function initBackstage(name) {
    switch (name) {
        case "2":
            getContestInfo();
            getTestInfo();
            getContestregistionNum();
            break;
        default:
            break;
    }
}
//查询竞赛信息
var contest_Id;
function getContestInfo() {
    $.ajax({
        url: 'test/getContestInfo',
        dataType: 'text',
        method: 'GET',
        success: function (data) {
            var contestInfo = JSON.parse(data);
            if (contestInfo.name != null) {
                contest_Id=contestInfo.testId;
                $('#contest_info_name').val(contestInfo.name);
                $('#contest_start_year').val(new Date(contestInfo.startTime).format("yyyy"));
                $('#contest_start_month').val(new Date(contestInfo.startTime).format("MM"));
                $('#contest_start_day').val(new Date(contestInfo.startTime).format("dd"));
                $('#contest_start_hour').val(new Date(contestInfo.startTime).format("hh"));
                $('#contest_start_minute').val(new Date(contestInfo.startTime).format("mm"));
                $('#contest_start_seconds').val(new Date(contestInfo.startTime).format("ss"));
                $('#contest_info_time').val(contestInfo.duration + "分钟");
                $('#contest_end_year').val(new Date(contestInfo.startTime).format("yyyy"));
                $('#contest_end_month').val(new Date(contestInfo.startTime).format("MM"));
                $('#contest_end_day').val(new Date(contestInfo.startTime).format("dd"));
                $('#contest_end_hour').val(new Date(contestInfo.startTime).format("hh"));
                $('#contest_end_minute').val(new Date(contestInfo.startTime).format("mm"));
                $('#contest_end_seconds').val(new Date(contestInfo.startTime).format("ss"));
            } else {
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
function getContest_id() {
    return contest_Id;
}
var test_id;
function getTestInfo() {
    $.ajax({
        url: 'test/getTestInfo',
        dataType: 'text',
        method: 'GET',
        success: function (data) {
            var testInfo = JSON.parse(data);
            if (testInfo.name != null) {
                test_id=testInfo.testId;
                $('#test_info_name').val(testInfo.name);
                $('#test_start_year').val(new Date(testInfo.startTime).format("yyyy"));
                $('#test_start_month').val(new Date(testInfo.startTime).format("MM"));
                $('#test_start_day').val(new Date(testInfo.startTime).format("dd"));
                $('#test_start_hour').val(new Date(testInfo.startTime).format("hh"));
                $('#test_start_minute').val(new Date(testInfo.startTime).format("mm"));
                $('#test_start_seconds').val(new Date(testInfo.startTime).format("ss"));
                $('#test_info_time').val(testInfo.duration + "分钟");
                $('#test_end_year').val(new Date(testInfo.startTime).format("yyyy"));
                $('#test_end_month').val(new Date(testInfo.startTime).format("MM"));
                $('#test_end_day').val(new Date(testInfo.startTime).format("dd"));
                $('#test_end_hour').val(new Date(testInfo.startTime).format("hh"));
                $('#test_end_minute').val(new Date(testInfo.startTime).format("mm"));
                $('#test_end_seconds').val(new Date(testInfo.startTime).format("ss"));
            } else {
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
function getTest_id() {
    return test_id;
}
//查看竞赛报名人数
function getContestregistionNum() {
    $.ajax({
        url: 'test/getContestregistionCount',
        dataType: 'text',
        method: 'GET',
        success: function (data) {
           $('#contestregistionNum').html(data);
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
//时间格式化
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        // month
        "d+": this.getDate(),
        // day
        "h+": this.getHours(),
        // hour
        "m+": this.getMinutes(),
        // minute
        "s+": this.getSeconds(),
        // second
        "q+": Math.floor((this.getMonth() + 3) / 3),
        // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};