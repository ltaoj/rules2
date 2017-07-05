/**
 * Created by ltaoj on 17-7-4.
 */

function addOption(select, data, type) {
    for (var i = 0;i < data.length;i++) {
        var option = document.createElement("option");
        switch (type){
            case "college":
                option.value = data[i].collegeId;
                break;
            case "major":
                option.value = data[i].majorId;
                break;
            case "clazz":
                option.value = data[i].clazzId;
                break;
        }
        option.text = data[i].name;
        select.append(option);
    }
}

function clearOptions(select) {
    select.options.length = 0;
    var option = document.createElement("option");
    option.value = "";
    option.text = "请选择";
    select.append(option);
}

function setDisabled(select, flag) {
    select.disabled = flag;
}

function getColleges() {
    $.ajax({
        url: 'school/collegeList',
        method: 'GET',
        success: function (data) {
            addOption($('#college'), data, "college");
        }
        
    })
}

function getMajor(collegeId) {
    clearOptions($('#major')[0]);
    clearOptions($('#clazz')[0]);
    collegeId == "" ? setDisabled($('#major')[0], true) : setDisabled($('#major')[0], false);
    setDisabled($('#clazz')[0], true);
    var json = {};
    json.collegeId = parseInt(collegeId);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'school/majorList',
        dataType: 'json',
        method: 'POST',
        data: JSON.stringify(json),
        success: function (data) {
            addOption($('#major'), data, "major");
        },
        error: function (xhr) {
            console.log('error:' + JSON.stringify(xhr));
        }

    }).done(function (data) {
        console.log('success');
    }).fail(function () {
        console.log('error');
    }).always(function () {
        console.log('complete');
    })
}

function getClazz(majorId) {
    clearOptions($('#clazz')[0]);
    majorId == "" ? setDisabled($('#clazz')[0], true) : setDisabled($('#clazz')[0], false);
    var json = {};
    json.majorId = majorId;
    $.ajax({
        url: 'school/clazzList',
        dataType: 'json',
        method: 'POST',
        data: JSON.stringify(json),
        success: function (data) {
            addOption($('#clazz'), data, "clazz");
        },
        error: function (xhr) {
            console.log('error:' + JSON.stringify(xhr));
        }

    }).done(function (data) {
        console.log('success');
    }).fail(function () {
        console.log('error');
    }).always(function () {
        console.log('complete');
    })
}