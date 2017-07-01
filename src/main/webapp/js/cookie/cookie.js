/**
 * Created by CMM on 2017/7/1.
 */
function setCookie(name, value) {
    //获取当前时间
    var date = new Date();
    var expiresDays = 10;
    //将date设置为10天以后的时间
    date.setTime(date.getTime() + expiresDays * 24 * 3600 * 1000);
    //将userId和userName两个cookie设置为10天后过期
    document.cookie = name + "=" + value+";expires"+date.toUTCString();
}

function getCookie(attr) {
    //获取cookie字符串
    var strCookie = document.cookie;
    //将多cookie切割为多个名/值对
    var arrCookie = strCookie.split("; ");
    var result;
    //遍历cookie数组，处理每个cookie对
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        //找到名称为userId的cookie，并返回它的值
        if (attr == arr[0]) {
            result = arr[1];
            break;
        }
    }
    return result;
}

function deleteCookie(name){
    var date=new Date();
    date.setTime(date.getTime()-10000);
    document.cookie=name+"=v; expires="+date.toUTCString();
}
