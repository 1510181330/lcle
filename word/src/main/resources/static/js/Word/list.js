$(document).ready(function(){

    //对于错误信息以及页面的初始化，使之不显示
    init();
    init1();
    //对于密码位数的验证(增加)
    $(document).on("change", "#oldUserPwd", function(){
        var password = $('#oldUserPwd').val();
        if(password.length<6)
        {
            $('#error1').show();
        }
        else
        {
            $('#error1').hide();
        }
    })

    //对于密码位数的验证(修改)
    $(document).on("change", "#update_oldUserPwd", function(){
        var password = $('#update_oldUserPwd').val();
        if(password.length<6)
        {
            $('#error5').show();
        }
        else
        {
            $('#error5').hide();
        }
    })

    //验证QQ号码是否全部由数字组成(增加)
    $(document).on("change", "#QQnumber", function(){
        var QQ = $('#QQnumber').val();
        if(isNaN(QQ)){
            $('#error3').show();
        }
        else
        {
            $('#error3').hide();
        }
    })

    //验证QQ号码是否全部由数字组成(修改)
    $(document).on("change", "#update_QQnumber", function(){
        var QQ = $('#update_QQnumber').val();
        if(isNaN(QQ)){
            $('#error7').show();
        }
        else
        {
            $('#error7').hide();
        }
    })

    //验证电话号码是否全部由数字组成，菜菜的我不会正则表达式(增加)
    $(document).on("change", "#phone", function(){
        var phone = $('#phone').val();
        var flag = 0;
        if(isNaN(phone)){
            flag = 1;
        }
        if(phone.length!=11){
            flag = 1;
        }
        if(flag==1){
            $('#error4').show();
        }
        else{
            $('#error4').hide();
        }
    })

    //验证电话号码是否全部由数字组成，菜菜的我不会正则表达式(修改)
    $(document).on("change", "#update_phone", function(){
        var phone = $('#update_phone').val();
        var flag = 0;
        if(isNaN(phone)){
            flag = 1;
        }
        if(phone.length!=11){
            flag = 1;
        }
        if(flag==1){
            $('#error8').show();
        }
        else{
            $('#error8').hide();
        }
    })

    //增加模态框的确定按钮
    $('#addbtn_ok').click(function(){
        var name = $('#name').val();
        var oldUserPwd = $('#oldUserPwd').val();
        var repeatUserPwd = $('#repeatUserPwd').val();
        var sexSelect = $('#sexSelect').val();
        var QQnumber = $('#QQnumber').val();
        var phone = $('#phone').val();
        var depSelect = $('#depSelect').val();
        var flag = 0;
        //验证两次输入的密码是否是同一个
        if(oldUserPwd!=repeatUserPwd)
        {
            $('#error2').show();
        }
        else
        {
            $('#error2').hide();
        }
        if(name==""||name==null||name==undefined)
        {
            flag = 1;
        }
        if(oldUserPwd==""||oldUserPwd==null||oldUserPwd==undefined)
        {
            flag = 1;
        }
        if(repeatUserPwd==""||repeatUserPwd==null||repeatUserPwd==undefined)
        {
            flag = 1;
        }
        if(sexSelect==""||sexSelect==null||sexSelect==undefined)
        {
            flag = 1;
        }
        if(QQnumber==""||QQnumber==null||QQnumber==undefined)
        {
            flag = 1;
        }
        if(phone==""||phone==null||phone==undefined)
        {
            flag = 1;
        }
        if(depSelect==""||depSelect==null||depSelect==undefined)
        {
            flag = 1;
        }
        if(flag==1){
            alert("存在未填项，请填充完毕后提交");
        }
        else if((!$('#error1').is(':hidden'))||(!$('#error2').is(':hidden'))||(!$('#error3').is(':hidden'))||(!$('#error4').is(':hidden'))){
            alert("页面信息存在错误，请适当修改后重试");
        }
        else{
            var param = "";
            param = param + name;
            param = param + "%" + repeatUserPwd;
            param = param + "%" + sexSelect;
            param = param + "%" + QQnumber;
            param = param + "%" + phone;
            param = param + "%" +　depSelect;
            addemp(param);
        }
    })

    //修改模态框的确定按钮
    $('#updatebtn_ok').click(function(){
        var id = $('#id').val();
        var name = $('#update_name').val();
        var oldUserPwd = $('#update_oldUserPwd').val();
        var repeatUserPwd = $('#update_repeatUserPwd').val();
        var sexSelect = $('#update_sexSelect').val();
        var QQnumber = $('#update_QQnumber').val();
        var phone = $('#update_phone').val();
        var depSelect = $('#update_depSelect').val();
        var flag = 0;
        //验证两次输入的密码是否是同一个
        if(oldUserPwd!=repeatUserPwd)
        {
            $('#error6').show();
        }
        else
        {
            $('#error6').hide();
        }
        if(name==""||name==null||name==undefined)
        {
            flag = 1;
        }
        if(oldUserPwd==""||oldUserPwd==null||oldUserPwd==undefined)
        {
            flag = 1;
        }
        if(repeatUserPwd==""||repeatUserPwd==null||repeatUserPwd==undefined)
        {
            flag = 1;
        }
        if(sexSelect==""||sexSelect==null||sexSelect==undefined)
        {
            flag = 1;
        }
        if(QQnumber==""||QQnumber==null||QQnumber==undefined)
        {
            flag = 1;
        }
        if(phone==""||phone==null||phone==undefined)
        {
            flag = 1;
        }
        if(depSelect==""||depSelect==null||depSelect==undefined)
        {
            flag = 1;
        }
        if(flag==1){
            alert("存在未填项，请填充完毕后提交");
        }
        else if((!$('#error5').is(':hidden'))||(!$('#error6').is(':hidden'))||(!$('#error7').is(':hidden'))||(!$('#error8').is(':hidden'))){
            alert("页面信息存在错误，请适当修改后重试");
        }
        else{
            var param = "";
            param = param + id;
            param = param + "%" + name;
            param = param + "%" + repeatUserPwd;
            param = param + "%" + sexSelect;
            param = param + "%" + QQnumber;
            param = param + "%" + phone;
            param = param + "%" +　depSelect;
            update(param);
        }
    })

    //增加成员的ajax方法
    function addemp(param){
        $.ajax({
            type: "POST",
            url: "/users_add",
            data: {"param":param},
            async : true,
            success:function(data){
                alert(data.msg);
                window.location.reload();
            },
            error:function(){
                alert("请求失败，系统内部问题");
            }
        })
    }

    //修改的ajax方法
    function update(param){
        $.ajax({
            type: "POST",
            url: "/users_update",
            data: {"param":param},
            async : true,
            success:function(data){
                alert(data.msg);
                window.location.reload();
            },
            error:function(){
                alert("请求失败，系统内部问题");
            }
        })
    }

    function init(){
        $('#error1').hide();
        $('#error2').hide();
        $('#error3').hide();
        $('#error4').hide();
        GetTableData();
    }

    function init1(){
        $('#error5').hide();
        $('#error6').hide();
        $('#error7').hide();
        $('#error8').hide();
    }

    $(document).on("click","#delbtn",function(){
        var id = $(this).parent().siblings().first().text();
        $.ajax({
            type: "POST",
            url: "/users_del",
            data: {"id": id},
            async : true,
            success:function(data){
                alert(data.msg);
                window.location.reload();
            },
            error:function(){
                alert("请求失败，系统内部问题");
            }
        })
    });

    $(document).on("click","#updatebtn",function(){
        alert("普通用户仅可修改自身信息，管理员可修改其余成员信息，如果权限不足你的修改将不会生效");
        var id = $(this).parent().parent().children().eq(0).text();
        var name = $(this).parent().parent().children().eq(1).text();
        var sex = $(this).parent().parent().children().eq(2).text();
        var QQ = $(this).parent().parent().children().eq(3).text();
        var phone = $(this).parent().parent().children().eq(4).text();
        var depname = $(this).parent().parent().children().eq(5).text();

        //回显
        $('#id').val(id);
        $('#update_name').val(name);
        $('#update_sexSelect').val(sex);
        $('#update_QQnumber').val(QQ);
        $('#update_phone').val(phone);
        $('#update_depSelect').val(depname);

    });

    function GetTableData(){
        $.ajax({
            type: "GET",
            url: "/users_allempinfo",
            async : true,
            success:function(data){
                console.log(data);
                CreateChildRow(data);
                $('#row1').siblings('.row1child').hide();
                $('#row2').siblings('.row2child').hide();
                $('#row3').siblings('.row3child').hide();
                $('#row4').siblings('.row4child').hide();
                $('#row5').siblings('.row5child').hide();
            },
            error:function(){
                alert("请求失败，系统内部问题");
            }
        })
    }

    function CreateChildRow(data){
        for(var i = 0; i < data.row1.length; i++){
            var Row1 = "<tr class='row1child'>";
            var id = data.row1[i].id;
            Row1 = Row1 + "<td>" + id + "</td>";
            var name = data.row1[i].name;
            Row1 = Row1 + "<td>" + name + "</td>";
            var sex = data.row1[i].sex;
            Row1 = Row1 + "<td>" + sex + "</td>";
            var QQ = data.row1[i].qq;
            Row1 = Row1 + "<td>" + QQ + "</td>";
            var phone = data.row1[i].phone;
            Row1 = Row1 + "<td>" + phone + "</td>";
            var depname = data.row1[i].depname;
            Row1 = Row1 + "<td>" + depname + "</td>";
            Row1 = Row1 + "<td><button id=\"updatebtn\" type=\"button\" class=\"btn btn-sm btn-success\" data-toggle=\"modal\" data-target=\"#updatemodal\">更新信息</button></td>\n" +
                "<td><button id=\"delbtn\" type=\"button\" class=\"btn btn-sm btn-warning\">删除信息</button></td>";
            Row1 = Row1 + "</tr>";
            $('#row1').after(Row1);
        }
        for(var i = 0; i < data.row2.length; i++){
            var Row1 = "<tr class='row2child'>";
            var id = data.row2[i].id;
            Row1 = Row1 + "<td>" + id + "</td>";
            var name = data.row2[i].name;
            Row1 = Row1 + "<td>" + name + "</td>";
            var sex = data.row2[i].sex;
            Row1 = Row1 + "<td>" + sex + "</td>";
            var QQ = data.row2[i].qq;
            Row1 = Row1 + "<td>" + QQ + "</td>";
            var phone = data.row2[i].phone;
            Row1 = Row1 + "<td>" + phone + "</td>";
            var depname = data.row2[i].depname;
            Row1 = Row1 + "<td>" + depname + "</td>";
            Row1 = Row1 + "<td><button id=\"updatebtn\" type=\"button\" class=\"btn btn-sm btn-success\" data-toggle=\"modal\" data-target=\"#updatemodal\">更新信息</button></td>\n" +
                "<td><button id=\"delbtn\" type=\"button\" class=\"btn btn-sm btn-warning\">删除信息</button></td>";
            Row1 = Row1 + "</tr>";
            $('#row2').after(Row1);
        }
        for(var i = 0; i < data.row3.length; i++){
            var Row1 = "<tr class='row3child'>";
            var id = data.row3[i].id;
            Row1 = Row1 + "<td>" + id + "</td>";
            var name = data.row3[i].name;
            Row1 = Row1 + "<td>" + name + "</td>";
            var sex = data.row3[i].sex;
            Row1 = Row1 + "<td>" + sex + "</td>";
            var QQ = data.row3[i].qq;
            Row1 = Row1 + "<td>" + QQ + "</td>";
            var phone = data.row3[i].phone;
            Row1 = Row1 + "<td>" + phone + "</td>";
            var depname = data.row3[i].depname;
            Row1 = Row1 + "<td>" + depname + "</td>";
            Row1 = Row1 + "<td><button id=\"updatebtn\" type=\"button\" class=\"btn btn-sm btn-success\" data-toggle=\"modal\" data-target=\"#updatemodal\">更新信息</button></td>\n" +
                "<td><button id=\"delbtn\" type=\"button\" class=\"btn btn-sm btn-warning\">删除信息</button></td>";
            Row1 = Row1 + "</tr>";
            $('#row3').after(Row1);
        }
        for(var i = 0; i < data.row4.length; i++){
            var Row1 = "<tr class='row4child'>";
            var id = data.row4[i].id;
            Row1 = Row1 + "<td>" + id + "</td>";
            var name = data.row4[i].name;
            Row1 = Row1 + "<td>" + name + "</td>";
            var sex = data.row4[i].sex;
            Row1 = Row1 + "<td>" + sex + "</td>";
            var QQ = data.row4[i].qq;
            Row1 = Row1 + "<td>" + QQ + "</td>";
            var phone = data.row4[i].phone;
            Row1 = Row1 + "<td>" + phone + "</td>";
            var depname = data.row4[i].depname;
            Row1 = Row1 + "<td>" + depname + "</td>";
            Row1 = Row1 + "<td><button id=\"updatebtn\" type=\"button\" class=\"btn btn-sm btn-success\" data-toggle=\"modal\" data-target=\"#updatemodal\">更新信息</button></td>\n" +
                "<td><button id=\"delbtn\" type=\"button\" class=\"btn btn-sm btn-warning\">删除信息</button></td>";
            Row1 = Row1 + "</tr>";
            $('#row4').after(Row1);
        }
        for(var i = 0; i < data.row5.length; i++){
            var Row1 = "<tr class='row5child'>";
            var id = data.row5[i].id;
            Row1 = Row1 + "<td>" + id + "</td>";
            var name = data.row5[i].name;
            Row1 = Row1 + "<td>" + name + "</td>";
            var sex = data.row5[i].sex;
            Row1 = Row1 + "<td>" + sex + "</td>";
            var QQnumber = data.row5[i].qq;
            Row1 = Row1 + "<td>" + QQnumber + "</td>";
            var phone = data.row5[i].phone;
            Row1 = Row1 + "<td>" + phone + "</td>";
            var depname = data.row5[i].depname;
            Row1 = Row1 + "<td>" + depname + "</td>";
            Row1 = Row1 + "<td><button id=\"updatebtn\" type=\"button\" class=\"btn btn-sm btn-success\" data-toggle=\"modal\" data-target=\"#updatemodal\">更新信息</button></td>\n" +
                "<td><button id=\"delbtn\" type=\"button\" class=\"btn btn-sm btn-warning\">删除信息</button></td>";
            Row1 = Row1 + "</tr>";
            $('#row5').after(Row1);
        }
    }

    //表单的展开与关闭
    $('#row1').click(function(){
        $(this).toggleClass("selected");
        $(this).siblings('.row1child').toggle();
    });

    //表单的展开与关闭
    $('#row2').click(function(){
        $(this).toggleClass("selected");
        $(this).siblings('.row2child').toggle();
    })

    //表单的展开与关闭
    $('#row3').click(function(){
        $(this).toggleClass("selected");
        $(this).siblings('.row3child').toggle();
    })

    //表单的展开与关闭
    $('#row4').click(function(){
        $(this).toggleClass("selected");
        $(this).siblings('.row4child').toggle();
    })

    //表单的展开与关闭
    $('#row5').click(function(){
        $(this).toggleClass("selected");
        $(this).siblings('.row5child').toggle();
    })
});