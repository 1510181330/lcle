$(document).ready(function(){

    $('#init').show();
    $('#create').hide();
    initGradles();
    var input1 = $('#name1');
    var input2 = $('#name2');
    var input3 = $('#name3');
    $('#submit').click(function () {
        var person1 = input1.val();
        var person2 = input2.val();
        var person3 = input3.val();
        var number = $('#select1').val();
        msg(person1, person2, person3, number);
    })
    function msg(name1, name2, name3, number){
        if((name1=="")||(name2=="")||(name3=="")){
            input1.val("");
            input2.val("");
            input3.val("");
            alert("制表人与查课人姓名不能为空");
        }
        else if((name3!=name1)&&(name3!=name2)){
            input1.val("");
            input2.val("");
            input3.val("");
            alert("制表人应为查课人中的一个哦");
        }
        else{
            $('#init').hide();
            $('#create').show();
            $('#div3').hide();
            $('#div4').hide();
            $('#div5').hide();
            $('#div6').hide();
            $('#div7').hide();
            $('#div8').hide();
            //隐匿所有错误信息，伺机而动
            initError();
            alert("本次记录查课人："+name1+"、"+name2+"制表人："+name3);
            var gradle = $('#select2').val();
            var param = name1+"-"+name2+"-"+name3+"-"+number+"-20"+gradle;
            getdata(param);
        }
    }
    function getdata(param){
        $.ajax({
            type: "GET",
            url: "/getTableData",
            data: {"param":param},
            async : true,
            success:function(data){
                createDownlist(data.allClasses);
            },
            error:function () {
                alert("请求失败，请稍后重试");
            }
        })
    }
    //生成班级下拉框
    function createDownlist(allClasses){
        var number = $('#select1').val();
        for(var i = 1; i <= 5; i++)
        {
            var div = "classSelect" + i +"div";
            var select = "classSelect" + i;
            if(i <= number)
            {
                $('#'+div).show();
                $('#'+select).empty();
                for(var j = 0; j < allClasses.length; j++)
                {
                    var node = $("<option value= "+allClasses[j]+">"+allClasses[j]+"</option>")
                    node.appendTo($('#'+select));
                }
            }
            else
            {
                $('#'+div).hide();
            }
        }
    }

    //选中年级以后，要使得下拉框的内容变成指定年级
    $(document).on("change","#select2",function(){
        var gradle = $('#select2').val();
        var person1 = input1.val();
        var person2 = input2.val();
        var person3 = input3.val();
        var number = $('#select1').val();
        var param1 = person1+"-"+person2+"-"+person3+"-"+number+"-20"+gradle;
        getdata(param1);
    });

    // 确定选中班级的前提下，生成下面的内容
    //原因：需要判断班级名称是否重复
    $('#submit1').click(function(){
        var number = $('#select1').val();
        var flag = 1;
        for(var i = 1; i < number; i++){
            var selecti = "classSelect"+i;
            var namei = $('#'+selecti).val();
            for(var j = (i+1); j <= number; j++){
                var selectj = "classSelect"+j;
                var namej = $('#'+selectj).val();
                if(namei.trim()==namej.trim()) {
                    flag=0;
                }
            }
        }
        if(flag == 0)
        {
            alert("不得存在有相同的班级名，请修改下拉框选值");
        }
        else{
            $('#submit1div').hide();
            createNumberDowanList();
            $('#div3').fadeIn(600);
            $('#div4').fadeIn(900);
            $('#div5').fadeIn(1200);
            $('#div6').fadeIn(1500);
            $('#div7').fadeIn(1800);
            $('#div8').fadeIn(2100);
        }
    })

    function createNumberDowanList(){
        var number = $('#select1').val();
        for(var i = 1; i <= 5; i++)
        {
            var div = "absenceNumber" + i +"div";
            var select = "absenceNumber" + i;
            var inputdiv1 = "absenperson" + i + "div";
            var inputdiv2 = "leaveperson" + i + "div";
            if(i <= number)
            {
                $('#'+inputdiv1).show();
                $('#'+inputdiv2).show();
                $('#'+div).show();
                $('#'+select).empty();
                for(var j = 0; j <= 25; j++)
                {
                    var node = $("<option value= "+j+">"+j+"</option>")
                    node.appendTo($('#'+select));
                }
            }
            else
            {
                $('#'+inputdiv1).hide();
                $('#'+inputdiv2).hide();
                $('#'+div).hide();
            }
        }
    }

    //最终的时刻，收集表格信息，组成url参数，开始为制表做准备
    //不过总要先判断缺课人员的数量和填入的个数，是否匹配
    $('#submit2').click(function() {

        var number = $('#select1').val();
        var params = "";
        var flag = 0;
        for(var i = 1; i <= number; i++){
            var name1 = $('#name1').val();
            params = params + name1;//0
            var name2 = $('#name2').val();
            params = params + "%" + name2;//1
            var name3 = $('#name3').val();
            params = params + "%" + name3;//2
            var gradel = $('#select2').val();
            params = params + "%" + gradel;//3
            var classesId = "classSelect"+i;
            var classes = $('#'+classesId).val();
            params = params + "%" + classes;//4
            var week1 = $('#weekSelect1').val();
            var week2 = $('#weekSelect2').val();
            var week = "周"+week1+"第"+week2+"大节";
            params = params + "%" + week;//5
            var teacher = $('#teacher').val();
            params = params + "%" + teacher;//6
            var lession = $('#lession').val();
            params = params + "%" + lession;//7
            var absenceNumberid = "absenceNumber"+i;
            var absenceNumber = $('#'+absenceNumberid).val();
            params = params + "%" + absenceNumber;//8
            var absenpersonid = "absenperson"+i;
            var absenperson = $('#'+absenpersonid).val();
            params = params + "%" + absenperson;//9
            var leavepersonid = "leaveperson"+i;
            var leaveperson = $('#'+leavepersonid).val();
            if(i == number){
                params = params + "%" + leaveperson;
            }
            else{
                params = params + "%" + leaveperson+"=";
            }
            if(teacher==""||teacher==undefined||teacher==null){
                $('#error1').show();
                flag = 1;
            }
            if(lession==""||lession==undefined||lession==null){
                $('#error1').show();
                flag = 1;
            }
            if(absenperson==""||absenperson==undefined||absenperson==null){
                $('#error4').show();
                flag = 1;
            }
            if(leaveperson==""||leaveperson==undefined||leaveperson==null){
                $('#error5').show();
                flag = 1;
            }
        }
        if(flag==1)
        {
            alert("输入信息有误，请注意页面提示信息");
        }
        else if($('#error1').is(':hidden')&&$('#error2').is(':hidden')&&$('#error3').is(':hidden')&&$('#error4').is(':hidden')&&$('#error5').is(':hidden'))
        {
            maketable(params);
        }
        else
        {
            alert("尚有问题未解决，请做出适当修改");
        }
    })

    function maketable(params){
        $.ajax({
            type: "GET",
            url: "/maketable",
            data: {"param":params},
            async : true,
            success:function(){
                alert("请求成功！");
            },
            error:function(){
                alert("请求失败，请稍后重试");
            }
        })
    }

    //对于错误信息提示框初始化隐藏
    function initError(){
        $('#error1').hide();
        $('#error2').hide();
        $('#error3').hide();
        $('#error4').hide();
        $('#error5').hide();
    }

    $(document).on("change","#absenperson1",function(){
        inputErrorForAbseccePerson(1);
    });
    $(document).on("change","#absenperson2",function(){
        inputErrorForAbseccePerson(2);
    });

    $(document).on("change","#absenperson3",function(){
        inputErrorForAbseccePerson(3);
    });

    $(document).on("change","#absenperson4",function(){
        inputErrorForAbseccePerson(4);
    });

    $(document).on("change","#absenperson5",function(){
        inputErrorForAbseccePerson(5);
    });

    //判断输入框是否存在输入错误
    function inputErrorForAbseccePerson(index){
        var temp = "absenperson"+index;
        var absenperson = $('#'+temp).val();
        var absenceNumberid = "absenceNumber"+index;
        var absenceNumber = $('#'+absenceNumberid).val();
        var error = 0;
        if(absenperson!=""&&absenperson!=null&&absenperson!=undefined)
        {
            if(absenceNumber == 0)
            {
                if(absenperson!="无")
                {
                    error = 1;//迟到人数为0,人员信息应填写无
                }
            }
            else{
                var localNumber = absenperson.split('+');
                if (localNumber.length != absenceNumber) {
                    error = 2;//未使用合法分隔符分隔姓名，或者选择人数与姓名个数不匹配
                }
                if(absenperson=="无")
                {
                    error = 2;
                }
            }
        }
        if(error==0)
        {
            $('#error2').hide();
            $('#error3').hide();
            $('#error4').hide();
        }
        else if(error==1)
        {
            $('#error2').show();
            $('#error3').hide();
            $('#error4').hide();
        }
        else
        {
            $('#error2').hide();
            $('#error3').show();
            $('#error4').hide();
        }
    }

    //请假人员的验证
    $(document).on("change","#leaveperson1",function(){
        inputErrorForLeavePerson();
    });
    $(document).on("change","#leaveperson2",function(){
        inputErrorForLeavePerson();
    });

    $(document).on("change","#leaveperson3",function(){
        inputErrorForLeavePerson();
    });

    $(document).on("change","#leaveperson4",function(){
        inputErrorForLeavePerson();
    });

    $(document).on("change","#leaveperson5",function(){
        inputErrorForLeavePerson();
    });

    function inputErrorForLeavePerson() {
        $('#error5').hide();
    }

    //教师课程是否为空的验证
    $(document).on("change","#teacher",function(){
        inputErrorForTeacher();
    });
    $(document).on("change","#lession",function(){
        inputErrorForTeacher();
    });

    function inputErrorForTeacher() {
        $('#error1').hide();
    }

    function initGradles(){
        getGradles();
    }

    function getGradles(){
        $.ajax({
            type: "GET",
            url: "/getGradles",
            data: {"param":"hello"},
            async : true,
            success:function(data){
                console.log(data);
                createGradles(data);
            },
            error:function(){
                alert("请求失败，请稍后重试");
            }
        })
    }

    function createGradles(data){

        $('#select2').empty();
        for(var i = 0; i < 3; i++)
        {
            var node = $("<option value= "+(data.gradles[i]%100)+">"+(data.gradles[i]%100)+"</option>")
            node.appendTo($('#select2'));
        }
    }
});