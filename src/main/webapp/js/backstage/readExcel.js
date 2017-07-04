/**
 * Created by CMM on 2017/7/4.
 */
/*
 FileReader共有4种读取方法：
 1.readAsArrayBuffer(file)：将文件读取为ArrayBuffer。
 2.readAsBinaryString(file)：将文件读取为二进制字符串
 3.readAsDataURL(file)：将文件读取为Data URL
 4.readAsText(file, [encoding])：将文件读取为文本，encoding缺省值为'UTF-8'
 */
var obj;
var wb;//读取完成的数据
var rABS = false; //是否将文件读取为二进制字符串
function setObj(objt) {
    obj = objt;
}
function importf(t) {//导入
    if (!obj.files) {
        return;
    }
    var f = obj.files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        var data = e.target.result;
        if (rABS) {
            wb = XLSX.read(btoa(fixdata(data)), {//手动转化
                type: 'base64'
            });
        } else {
            wb = XLSX.read(data, {
                type: 'binary'
            });
        }
        //wb.SheetNames[0]是获取Sheets中第一个Sheet的名字
        //wb.Sheets[Sheet名]获取第一个Sheet的数据
        var list = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
        assemble(list, t);
    };
    if (rABS) {
        reader.readAsArrayBuffer(f);
    } else {
        reader.readAsBinaryString(f);
    }
}
function assemble(list, type) {
    var url;
    var tep_list;
    if (type == 0) {
        url = 'title/addTitles';
        tep_list = new Array(list.length);
        for (var i = 0; i < list.length; i++) {
            tep_list[i] =
                '{' +
                '"titleId":' + list[i]['题目编号'] + ',' +
                '"name": "' + list[i]['题目内容'] + '",' +
                '"diffId":' + (list[i]['难度等级'] == '易' ? 1 : (list[i]['难易等级'] == '中' ? 2 : 3)) + ',' +
                '"score":' + list[i]['分值'] + ',' +
                '"options":[' +
                '{' +
                '"optionId": 1,' +
                '"titleId":' + list[i]['题目编号'] + ',' +
                '"content": "' + list[i]['选项A'] + '1",' +
                '"checked":' + (list[i]['正确选项'].indexOf('A') == '-1' ? 0 : 1) +
                '},' +
                '{' +
                '"optionId": 2,' +
                '"titleId":' + list[i]['题目编号'] + ',' +
                '"content": "' + list[i]['选项A'] + '1",' +
                '"checked":' + (list[i]['正确选项'].indexOf('B') == '-1' ? 0 : 1) +
                '},' +
                '{' +
                '"optionId": 3,' +
                '"titleId":' + list[i]['题目编号'] + ',' +
                '"content": "' + list[i]['选项A'] + '1",' +
                '"checked":' + (list[i]['正确选项'].indexOf('C') == '-1' ? 0 : 1) +
                '},' +
                '{' +
                '"optionId": 4,' +
                '"titleId":' + list[i]['题目编号'] + ',' +
                '"content": "' + list[i]['选项A'] + '1",' +
                '"checked":' + (list[i]['正确选项'].indexOf('D') == '-1' ? 0 : 1) +
                '}' +
                ']' +
                '}';
        }
        document.getElementById("demo").innerHTML = tep_list;
    }
    else {
        url = 'account/insertAccount'
        tep_list = new Array(list.length);
        for (var i = 0; i < list.length; i++) {
            tep_list[i] = '{"studentId":"+' + list[i]["学号"] + '","username":"+' + list[i]['姓名'] + '","clazz":"' + list[i]['班级'] + '","grade":"' + list[i]['年级'] + '","major":"' + list[i][专业] + '","college":"' + list[i]['学院'] + '}';
        }
    }
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: url,
        dataType: 'json',
        method: 'POST',
        data: '[' + tep_list + ']',
        success: function (data) {

        }
        ,
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

function fixdata(data) { //文件流转BinaryString
    var o = "",
        l = 0,
        w = 10240;
    for (; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
    o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
    return o;
}