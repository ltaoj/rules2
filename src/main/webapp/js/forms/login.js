var student_id;
var account;
function login() {
    var studentId;
    var password;
    studentId = $('#studentId').val();
    password = $('#password').val();

    var signonString = {studentId: studentId, password: password};
    var signonJson = $.toJSON(signonString);
    login(signonJson);
}
function login(signonJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'account/login',
        dataType: 'json',
        method: 'POST',
        data: signonJson,
        success: function (data) {
            if (data.code == undefined) {
                setCookie('signon', signonJson);
                $('#loginModal').modal('hide');
                $('#login-li').hide();
                $('#personal-li').show();
                setAccount(data);
                student_id = data.studentId;
                getTestRecord(data.studentId);
                getContestRecord(data.studentId);
                isRegisted();
                $("#isSimulationLogin").html("&nbsp;");
                $("#isWrongTitleLogin").html("&nbsp;");
                $("#isLogin").html("&nbsp;");
                $("#isContestLogin").html("&nbsp;");
                changeStatus();
            } else {
                $('#loginMessage').html("用户名或密码错误");
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
function getStudentId() {
    return student_id;
}

function getAccount() {
    return account;
}
