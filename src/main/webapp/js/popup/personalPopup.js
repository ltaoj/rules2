/**
 * Created by CMM on 2017/6/21.
 */
var account = null;
var personalPopFlag = false;
function setAccount(account) {
    this.account = account;
}

function personalPopover() {
    $("[data-toggle='personal']")
        .popover(
            {
                trigger: "click",
                html: true,
                container: 'body',
                content: "姓名：<label >" + account.username + "</label> <br>学号：<label>" + account.studentId + "</label> <br> 学校：<label>" + account.school + "</label><br> 学院：<label>" + account.college + "</label><br><div align='right'><button class='btn btn-success btn-xs' onClick='exit()'>注销</button></div>"
            });
    if (!personalPopFlag) {
        var btn = document.getElementById("personal");
        btn.click();
    }
    personalPopFlag=true;
}

function exit() {
    var btn = document.getElementById("personal");
    btn.click();
    $("[data-toggle='personal']").popover('hide');

}