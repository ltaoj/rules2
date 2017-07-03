/**
 * Created by GF on 2017/7/3.
 */
function initBackstage(name) {
    switch (name){
        case "2":contestAndTest();break;
        default:break;
    }
}
//查询竞赛信息
function contestAndTest() {
    $.ajax({
        url: 'test/getContestInfo',
        dataType: 'text',
        method: 'GET',
        success: function (data) {
            var contestInfo = JSON.parse(data);
            if(contestInfo.name!=null) {
                $('#contest_info_name').val(contestInfo.name);
                $('#contest_start_year').val(new Date(contestInfo.startTime).format("yyyy"));
                $('#contest_start_month').val(new Date(contestInfo.startTime).format("MM"));
                $('#contest_start_day').val(new Date(contestInfo.startTime).format("dd"));
                $('#contest_start_hour').val(new Date(contestInfo.startTime).format("hh"));
                $('#contest_start_minute').val(new Date(contestInfo.startTime).format("mm"));
                $('#contest_start_seconds').val(new Date(contestInfo.startTime).format("ss"));
                $('#contest_info_time').val(contestInfo.duration+"分钟");
                $('#contest_end_year').val(new Date(contestInfo.startTime).format("yyyy"));
                $('#contest_end_month').val(new Date(contestInfo.startTime).format("MM"));
                $('#contest_end_day').val(new Date(contestInfo.startTime).format("dd"));
                $('#contest_end_hour').val(new Date(contestInfo.startTime).format("hh"));
                $('#contest_end_minute').val(new Date(contestInfo.startTime).format("mm"));
                $('#contest_end_seconds').val(new Date(contestInfo.startTime).format("ss"));
            }else{
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
//时间格式化
Date.prototype.format = function(format) {
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