$(document).ready(function(){
    $('#showDiv').hide();
    $('#checkDiv').show();
    $('#submit').click(function(){
        var param = "";
        if($('#C1').prop("checked")){
            param = param + 1;
        }
        else{
            param = param + 0;
        }
        for(var i = 2; i <= 20; i++){
            var tag = "C" + i;
            if($('#'+tag).prop("checked")){
                param = param + "%1";
            }
            else{
                param = param + "%0";
            }
        }
        sendfreeClass(param);
    })

    function sendfreeClass(param) {
        $.ajax({
            type: "POST",
            url: "/users_sendfreeClass",
            data: {"param":param},
            async : true,
            success:function(){
                alert("请求成功！");
            },
            error:function(){
                alert("请求失败，请稍后重试");
            }
        })
    }

    $('#query').click(function(){
        $('#checkDiv').hide();
        GetTableInfo();
    })

    function GetTableInfo() {
        $.ajax({
            type: "GET",
            url: "/users_getTarget",
            data: {"param":"hello"},
            async : true,
            success:function(data){
                alert(data.msg);
                if(data.flag==1){
                    CreateTable(data);
                    $('#showDiv').fadeIn(1200);
                }
                if(data.flag==2) {
                    CreateTable1(data);
                    $('#showDiv').fadeIn(1200);
                }
                if(data.flag==3) {
                    $('#checkDiv').show();
                }
            },
            error:function(){
                alert("请求失败，请稍后重试");
            }
        })
    }

    function CreateTable(data) {
        for(var i = 0; i < 5; i++){
            if(i==0){
                var snoop = "<tr><td>周一</td>";
            }
            if(i==1){
                var snoop = "<tr><td>周二</td>";
            }
            if(i==2){
                var snoop = "<tr><td>周三</td>";
            }
            if(i==3){
                var snoop = "<tr><td>周四</td>";
            }
            if(i==4){
                var snoop = "<tr><td>周五</td>";
            }
            for(var j = 0; j < 4; j++)
            {
                var index = (i * 4) + j;
                var name = data.name1[index];
                var phone = data.phone1[index];
                console.log(index);
                snoop = snoop + "<td>" + name + "<br>" + phone +"</td>";
            }
            snoop = snoop + "</tr>";
            $('#table tbody').append(snoop);
        }

    }

    function CreateTable1(data) {
        for(var i = 0; i < 5; i++){
            if(i==0){
                var snoop = "<tr><td>周一</td>";
            }
            if(i==1){
                var snoop = "<tr><td>周二</td>";
            }
            if(i==2){
                var snoop = "<tr><td>周三</td>";
            }
            if(i==3){
                var snoop = "<tr><td>周四</td>";
            }
            if(i==4){
                var snoop = "<tr><td>周五</td>";
            }
            for(var j = 0; j < 4; j++)
            {
                var index = (i * 4) + j;
                var name = data.name1[index];
                var phone = data.phone1[index];
                var name1 = data.name2[index];
                var phone1 = data.phone2[index];
                console.log(index);
                snoop = snoop + "<td>" + name + "<br>" + phone + "<br>" + name1 + "<br>" + phone1 +"</td>";
            }
            snoop = snoop + "</tr>";
            $('#table tbody').append(snoop);
        }

    }
});