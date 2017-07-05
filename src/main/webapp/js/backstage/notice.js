/**
 * Created by CMM on 2017/7/5.
 */
var flag1 = false;
var flag2 = false;
function getNoticeForPicture() {
    if (!flag1) {
        flag1 = true;
        $.ajax({
            url: 'notice/getPictureNotice',
            dataType: 'text',
            method: 'GET',
            success: function (data) {
                var pictureNotice = new Array;
                pictureNotice = JSON.parse(data);
                for (var i = 0; pictureNotice.length; i++) {
                    $('#backstage_notice_picture').append("<div id='" + pictureNotice[i].noticeId + "'>" + pictureNotice[i]['title'] + "<span style='float:right '><button class='btn btn-success btn-sm'>修改</button><button class='btn btn-danger btn-sm' onclick='deleteNotice(this)'>删除</button></span></div>");
                }

            },
            error: function (xhr) {
                // 导致出错的原因较多，以后再研究
                flag1 = false;
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

function getNoticeForText() {
    if (!flag2) {
        flag2 = true;
        $.ajax({
            url: 'notice/getAllTextNotice',
            dataType: 'text',
            method: 'GET',
            success: function (data) {
                var textNotice = new Array;
                textNotice = JSON.parse(data);
                for (var i = textNotice.length - 1; i >= 0; i--) {
                    $('#backstage_notice_text').append("<div id='" + textNotice[i].noticeId + "'>" + textNotice[i]['title'] + "<span style='float:right '><button class='btn btn-success btn-sm'>修改</button><button class='btn btn-danger btn-sm' onclick='deleteNotice(this)'>删除</button></span></div>");
                }
            },
            error: function (xhr) {
                // 导致出错的原因较多，以后再研究
                flag2 = false;
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
function deleteNotice(obj) {
    $('#' + obj.parentNode.parentNode.id).hide();
    var titleId = '';
    titleId = obj.parentNode.parentNode.id;
    var titleString = {titleId: titleId};
    var title = $.toJSON(titleString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'notice/deleteNotice',
        dataType: 'json',
        method: 'POST',
        data: title,
        success: function (data) {

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