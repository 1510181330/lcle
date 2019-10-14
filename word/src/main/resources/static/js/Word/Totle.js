$(document).ready(function(){

    //初始化情况下隐藏周次下拉框
    init();

    $('#start').click(function(){
        judge();
    })

    function init(){
        $('#week').hide();
    }

    //制作文件判定权限的ajax方法
    function judge(){
        $.ajax({
            type: "GET",
            url: "/judge_totle",
            data: {"param":"hello"},
            async : true,
            success:function(data){
                if(data.flag==1){
                    $('#start').hide();
                    $('#week').show();
                }
                else{
                    alert("寂寥辰时须臾问，长游不归莫相送");
                }
            },
            error:function(){
                alert("请求失败，系统内部问题");
            }
        })
    }

    $('#pushbutton1').click(function(){
        var week = $('#select2').val();
        $.ajax({
            type: "GET",
            url: "/totle_files_word",
            data: {"args":week},
            async : true,
            success:function(){
                alert("请求成功");
            },
            error:function(){
                alert("请求失败，系统内部问题");
            }
        })
    })
});