/**
 * Created by CMM on 2017/7/1.
 */
$(function () {
    alert(document.cookie);
    var signon = getCookie('signon');
    login(signon);
})