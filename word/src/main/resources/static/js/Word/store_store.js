//分类号
var sortNos = new Array(100,200,216,501);
var sortNames = new Array("silo","raw wheat","tempering","flour");
var binTypeNos = new Array(1,2,3,4,5,6,7);
var binTypeNames = new Array("silo","raw wheat","tempering","flour","additives","packer","handintake");
var lockNos = new Array(1,2,3,4);
var locks = new Array("free","lock","lockin","lockout");
$(function() {
	UpDataPace();// 初始化页面
	pagedefault(3, 2);// 初始化导航栏
	GetmatterLine('matter-box',-1);// 创建按物料名查询的下拉框
})


/*
 *创建带有回显的下拉框
 *id 下拉框的id
 *No 存放value的数组
 *Name 存放No[i]对应的名字
 *no 被选中的将要回显的value
 */
function creatDownBox(id,No,Name,no){
	
    for(var i = 0; i < No.length; i++){
    	if(No[i] == no){
    		var temp = $("<option value= " + No[i] + " selected = 'selected'>"+Name[i]+"</option>");// 为获取到的matterName创建option
        	temp.appendTo($("#"+id));
    	}
    	else{
    		var temp = $("<option value= " + No[i] + ">"+Name[i]+"</option>");// 为获取到的matterName创建option
    		temp.appendTo($("#"+id));
    	}
    	
    }
}

function searchName(No,Name,no){
	for(var i = 0; i< No.length; i++){
		if(No[i] == no){
			return Name[i];
		}
	}
	return false;
}
var BINID;
// 初始化页面
function UpDataPace() {
	$.ajax({
		type : "GET",// 请求方式"
		url : "/goldengrain/user/bin/InitSelect",// 地址
		async : true,// 异步传输
		success : function(data) {
			console.log(data);
			var flag = Page.toDo(data);
		},
		error : function() {
			layer.msg(i18n("text_serverError_tryAgainLater")+"！",{time:700});
		}
	});
}



/*
 * 点击查询按钮 实现查询
 */
$(document).on("click", "#query", function() {
	var param;
	//debugger
	var binType = $('#a1').val();
	var matterId = $('#matter-box').val();
	if(binType != 0 && binType != 'null' && binType != undefined){
		param="&binType="+binType;
	}
	if(matterId != 0 && matterId != 'null' && matterId != undefined){
		param = param + "&matterId=" + matterId;
	}
	console.info(param);
	if(param != 0 && param !='null' && param != undefined){
		queryBybinType(param);
	}
})


/*
 * 编辑按钮的click函数
 */ 
var arrMove = [];
var par_this = null;
$(document).on("click", "#btn-redact", function() {
	arrMove = []
	par_this = $(this).parents("tr")
	$(".movefade").fadeIn(600)
	$(this).parent().siblings().each(function() {
		arrMove.push($(this).text())
	})
	$(this).parents().find('#movemodal input').each(function(i) {
		$(this).val(arrMove[i])
	})
})



// 按仓库类型查询
function queryBybinType(bintype) {

	$.ajax({
		type : "post",// 请求方式"
		url : "/goldengrain/user/bin/getBinByInfo?" + bintype,// 地址
		async : true,// 异步传输
		success : function(data) {
			$('#dom1').empty();
			var flag = Page.toDo(data);
		},
		error : function() {
			layer.msg(i18n("text_serverError_tryAgainLater")+"！",{time:700});
		}
	});
}

/*
 * 按照物料编号查询
 */
function queryBymatterId(matterId){
	$.ajax({
		type : "post",// 请求方式"
		url : "/goldengrain/user/bin/getBinByInfo?matterId=" + matterId,// 地址
		async : true,// 异步传输
		success : function(data) {
			$('#dom1').empty();// 主表清空
			var flag = Page.toDo(data);// 按照matterId要求创建新的表
		},
		error : function() {
			layer.msg(i18n("text_serverError_tryAgainLater")+"！",{time:700});
		}
	});
}

/*
 * 创建查询动态物料下拉框
 */
function GetmatterLine(prame,p){
	$.ajax({
    type:"GET",
    url:"/goldengrain/user/bin/downBox",
    success:function(data){
        var info=data.data.data;
        if(prame == 'matter-box'){
        	 var temp0 = $("<option value = null>"+i18n("text_pleaseChoose")+"</option>");
             temp0.appendTo($("#"+prame));
        }
        for(var i = 0; i < info.length; i++){
        	if(info[i].matterId == p){
        		var temp = $("<option value= " + info[i].matterId + " selected='selected'>"+info[i].matterName+"</option>");// 为获取到的matterName创建option
            	//temp.append(info.matterName);// 得到一个matterName
            	temp.appendTo($("#"+prame));
        	}
        	else{
        		var temp = $("<option value= " + info[i].matterId + ">"+info[i].matterName+"</option>");// 为获取到的matterName创建option
            	//temp.append(info[i].matterName);// 得到一个matterName
            	temp.appendTo($("#"+prame));
        	}
        	
        }
    }
});
}


/**
 * Description:成功操作
 * 
 * @param data
 *            成功返回的数据
 * 
 */
var Page = {
	toDo : function(data) {
		// 如果数据异常，则进行以下操作
		if (data.code == 200) {
			resultTip(data.msg);
		} else {
			getbody(data.data.result.bins);// 创建表格
		}
	}
}

/*
 * 判断添加中的内容是否有空pram1:input数据 pram2:input框
 */
function judjeInput(pram1,a){
	if (pram1 == '' || pram1 == undefined || pram1 == null){
	    a=1;
	}
	return a;
}
function resultTip(text) {
	layer.msg(text, {
		time : 3000
	});
}

function cheackName(param) {
	var flag=0;
	$.ajax({
		type:"get",
		url:"/goldengrain/user/bin/checkBinName?binNo="+param,
		async:false,
		success:function(data){
				flag = data.data.result;
		}
	});
	return flag;
}

function cheackMove(param) {
	var flag=0;
	$.ajax({
		type:"get",
		url:"/goldengrain/user/bin/checkBinName?binId="+param,
		async:false,
		success:function(data){
				flag = data.data.result;
		}
	});
	return flag;
}
//限于不粘锅
var oldBinNo;
$(document).on("change","#move1",function(){
	binNo = $("#move1").val();
	binId=BinId[BINID-1];
	param=binId+"&binNo="+binNo;
	
	checkAllInfo($("#move1"),$("#move1").val(),3);
	if(oldBinNo == binNo && $("#move1").parent().siblings(".showMessage").find("span").eq(0).is(":visible")){
		$("#move1").parent().siblings(".showMessage").find("span").eq(0).hide();
	}
	if(!$("#move1").parent().siblings(".showMessage").find("span").is(":visible") && oldBinNo != binNo){
		
		if(cheackMove(param) == 1){
			$("#move1").parent().siblings(".showMessage").find("span").eq(0).show();
		}else {
			$("#move1").parent().siblings(".showMessage").find("span").hide();
		}
	}
 
})

function checkAllInfo(param,paramVal,allNum) {
	//防止多个错误信息重复打印
	//限于不粘锅
	$(param).parent().siblings(".showMessage").find("span").eq(allNum-1).hide();
	$(param).parent().siblings(".showMessage").find("span").eq(allNum-2).hide();
	if(allNum==3){
		$(param).parent().siblings(".showMessage").find("span").eq(allNum-3).hide();
	}
	if(isNaN(paramVal)){
		$(param).parent().siblings(".showMessage").find("span").eq(allNum-2).show();
	}else if(paramVal.length>8){
		$(param).parent().siblings(".showMessage").find("span").eq(allNum-1).show();
	}else{
		$(param).parent().siblings(".showMessage").find("span").eq(allNum-1).hide();
		$(param).parent().siblings(".showMessage").find("span").eq(allNum-2).hide();
	}
	
}

$(document).on("change","#add1",function(){
	checkAllInfo($("#add1"),$("#add1").val(),3);
	if(!$("#add1").parent().siblings(".showMessage").find("span").is(":visible")){
		//用户名为空，不能打印仓号重复这个信息
		if($("#add1").val()==""){
			$("#add1").parent().siblings(".showMessage").find("span").eq(0).hide();
		}
		else if(cheackName($("#add1").val()) == 1){
			$("#add1").parent().siblings(".showMessage").find("span").eq(0).show();
		}else {
			$("#add1").parent().siblings(".showMessage").find("span").hide();
		}
	}
	
})

function bangCheckInfo(param) {
	$(document).on("change",param,function(){
		checkAllInfo($(param),$(param).val(),2);
	})
}

bangCheckInfo("#add2");
bangCheckInfo("#add6");
bangCheckInfo("#add7");
bangCheckInfo("#add8");
bangCheckInfo("#add9");
bangCheckInfo("#add10");
bangCheckInfo("#add11");
bangCheckInfo("#add12");

bangCheckInfo("#move2");
bangCheckInfo("#move6");
bangCheckInfo("#move7");
bangCheckInfo("#move8");
bangCheckInfo("#move9");
bangCheckInfo("#move10");
bangCheckInfo("#move11");
bangCheckInfo("#move12");



// 增加
$(document).on("click", "#addBtn", function() {
	$(".addfade").fadeIn(600);
	$("#add3").empty();
	$("#add4").empty();
	$("#add5").empty();
	$("#add13").empty();
	creatDownBox('add3',sortNos,sortNames,0);
	creatDownBox('add4',binTypeNos,binTypeNames,0);
	GetmatterLine('add5',-1);
	creatDownBox('add13',lockNos,locks,0);
});
// 增加确定
$(document).on("click", "#addbtn_ok", function() {
	var a = 0;
	binNo=$('#add1').val(); 
	ident = $('#add2').val();
	sortNo = $('#add3').val();
	binType = $('#add4').val();
	matterName = $('#add5').val();
	levelActual = $('#add6').val();
	capacityMax = $('#add7').val();
	levelHigh = $('#add8').val();
	levelRefill = $('#add9').val();
	levelLow = $('#add10').val();
	priorityIn = $('#add11').val();
	priorityOut = $('#add12').val();
	lock = $('#add13').val();
	remark = $('#add14').val();
	a=judjeInput(binNo,a);
	a=judjeInput(ident,a);
	a=judjeInput(sortNo,a);
	a=judjeInput(binType,a);
	a=judjeInput(matterName,a);
	a=judjeInput(levelActual,a);
	a=judjeInput(capacityMax,a);
	a=judjeInput(levelHigh,a);
	a=judjeInput(levelRefill,a);
	a=judjeInput(levelLow,a);
	a=judjeInput(priorityIn,a);
	a=judjeInput(priorityOut,a);
	a=judjeInput(lock,a);
	var p =binNo+"&ident="+ident+"&sortNo="+sortNo+"&binType="+binType+
	"&matter.matterId="+matterName+"&levelActual="+levelActual+"&capacityMax="+capacityMax+"&levelHigh="+levelHigh+
	"&levelRefill="+levelRefill+"&levelLow="+levelLow+"&priorityIn="+priorityIn+"&priorityOut="+priorityOut+
	"&lock="+lock+"&remark="+remark;
	 
	if(a == 1 || $("#addmodal .showMessage span").is(":visible")){
		//resultTip("加*信息为空或有误11");
		
		layer.msg(i18n("text_requiredInformation_emptyOrError")+"！",{time:900});
		return false;
	}else {
		selectaddData(p);
		$(".addfade").fadeOut(600);
	}
	 
});


 

// 增加取消
$(document).on("click", "#addbtn_no", function() {
	$(".addfade").fadeOut(600);
    $(".addfade input").val("");
    $(".addfade .showMessage span").hide();
});
// 点击模态框右上方的叉号实现关闭模态框
$(document).on("click", ".modal-header i", function() {
	$(".addfade").fadeOut(600);
	 $(".addfade input").val("");
	 $(".addfade .showMessage span").hide();
});

// 编辑
$(document).on("click", "#movebtn", function() {
	$(".movefade").fadeIn(600);
	var arr=new Array();
	$(this).parent().siblings().each(function() {
		arr.push($(this).text());
	});
	$("#move3").empty();
	$("#move4").empty();
	$("#move5").empty();
	$("#move13").empty();
	BINID=arr[0];
	oldBinNo = arr[1];
	$('#move1').val(arr[1]);
	$('#move2').val(arr[2]);
	$('#move6').val(arr[8]);
	$('#move7').val(arr[9]);
	$('#move8').val(arr[10]);
	$('#move9').val(arr[11]);
	$('#move10').val(arr[12]);
	$('#move11').val(arr[13]);
	$('#move12').val(arr[14]);
	$('#move14').val(arr[17]);
	creatDownBox('move3',sortNos,sortNames,arr[3]);
	creatDownBox('move4',binTypeNos,binTypeNames,arr[5]);
	GetmatterLine('move5',arr[20]);
	creatDownBox('move13',lockNos,locks,arr[20]);
});
// 编辑确定
$(document).on("click", ".movebtnok", function() {
	var a = 0;
	binId=BinId[BINID-1];
	binNo=$('#move1').val();
	ident = $('#move2').val();
	sortNo = $('#move3').val();
	binType = $('#move4').val();
	matterName = $('#move5').val();
	levelActual = $('#move6').val();
	capacityMax = $('#move7').val();
	levelHigh = $('#move8').val();
	levelRefill = $('#move9').val();
	levelLow = $('#move10').val();
	priorityIn = $('#move11').val();
	priorityOut = $('#move12').val();
	lock = $('#move13').val();
	remark = $('#move14').val();
	a=judjeInput(binNo,a);
	a=judjeInput(ident,a);
	a=judjeInput(sortNo,a);
	a=judjeInput(binType,a);
	a=judjeInput(matterName,a);
	a=judjeInput(levelActual,a);
	a=judjeInput(capacityMax,a);
	a=judjeInput(levelHigh,a);
	a=judjeInput(levelRefill,a);
	a=judjeInput(levelLow,a);
	a=judjeInput(priorityIn,a);
	a=judjeInput(priorityOut,a);
	a=judjeInput(lock,a);
	var p = binId+"&binNo="+binNo+"&ident="+ident+"&sortNo="+sortNo+"&binType="+binType+
	"&matter.matterId="+matterName+"&levelActual="+levelActual+"&capacityMax="+capacityMax+"&levelHigh="+levelHigh+
	"&levelRefill="+levelRefill+"&levelLow="+levelLow+"&priorityIn="+priorityIn+"&priorityOut="+priorityOut+
	"&lock="+lock+"&remark="+remark;
 
	
	
	if(a == 1 || $("#movemodal .showMessage span").is(":visible")){
		layer.msg(i18n("text_requiredInformation_emptyOrError")+"！",{time:900});
		return false;
	}else {
		selectmoveData(p);
		$(".movefade").fadeOut(600);
	}
	
	 
});
// 编辑取消
$(document).on("click", ".movebtnno", function() {
	$(".movefade").fadeOut(600);
	$(".movefade .showMessage span").hide();
});
// 点击模态框右上方的叉号实现关闭模态框
$(document).on("click", ".modal-header i", function() {
	$(".movefade").fadeOut(600);
	$(".movefade .showMessage span").hide();
});

/**
 * 增加
 * 
 * @returns
 */
function selectaddData(param) {

	$.ajax({
		type : "post",
		url : "/goldengrain/user/bin/addBin?binNo="+param,// 获取数据地址
		async : true,// 请求为异步请求
		success : function(data) {
			// 返回操作是否成功
			UpDataPace();
			resultTip(data.msg);
		},
		error : function() {
			layer.msg(i18n("text_serverError_tryAgainLater")+"！",{time:700});
		}
	});
}


/**
 * 编辑
 * 
 * @returns
 */
function selectmoveData(param) {
	$.ajax({
		type : "post",// 请求方式lineId="+id+"&ftyId="+ftyId
		url : "/goldengrain/user/bin/newDistributionBin?binId="+param,// 获取数据地址
		async : true,// 请求为异步请求
		success : function(data) {
			UpDataPace();
			resultTip(data.msg);
		},
		error : function() {
			layer.msg(i18n("text_serverError_tryAgainLater")+"！",{time:700});
		}
	});
}
// 向后台发送仓编号binId 对现仓容量清零
function selectdelData(binId) {
	$.ajax({
		type : "post",// 请求方式
		url : "/goldengrain/user/bin/resetBinCurrentCapacity?binId=" + binId,// 获取数据地址
		async : true,// 请求为异步请求
		success : function(data) {
			window.location.reload();
		},
		error : function() {
			layer.msg(i18n("text_serverError_tryAgainLater")+"！",{time:700});
		}
	});
}
//click清零时执行以下操作
function delLevelActual(num){
	layer.confirm(i18n("text_areYouSure_clearActualLevel")+'？', {
		  btn: [i18n("button_confirm"),i18n("button_cancel")], //按钮
	title : i18n("text_reminder"),
		}, function(){
			debugger
				selectdelData(num);
		}, function(){
			window.location.reload();
		});
}
// 给报表添加滚动条
function initTable() {
	var showWidth = $("#middle-content").width();
	var showHeight = $("#middle-content").height() - $(".middle-operate").height();
	$("#dom1").css("width", 1800);
	FixTable("dom1", 1, showWidth - 20, showHeight - 35);
}



// 创建表头
function getthead() {
	$('#dom1').empty();
	var thead = $("<thead class='thead'></thead>");
	$('table').append(thead);
	bantr = $("<tr></tr>");
	for (i = 0; i < 18; i++) {
		th = $("<th></th>")
		bantr.append(th);
	}
	thead.append(bantr);
	bantr.find("th:eq(" + (0) + ")").html(i18n("text_index"));
	bantr.find("th:eq(" + (1) + ")").html(i18n("table_binNumber"));
	bantr.find("th:eq(" + (2) + ")").html(i18n("table_ident"));
	bantr.find("th:eq(" + (3) + ")").html(i18n("table_sortNumber"));
	bantr.find("th:eq(" + (4) + ")").html(i18n("table_binType"));
	bantr.find("th:eq(" + (5) + ")").html(i18n("table_material"));
	bantr.find("th:eq(" + (6) + ")").html(i18n("table_actualLevel"));
	bantr.find("th:eq(" + (7) + ")").html(i18n("table_maximumCapacity"));
	bantr.find("th:eq(" + (8) + ")").html(i18n("table_highLevel"));
	bantr.find("th:eq(" + (9) + ")").html(i18n("table_refillLevel"));
	bantr.find("th:eq(" + (10) + ")").html(i18n("table_lowLevel"));
	bantr.find("th:eq(" + (11) + ")").html(i18n("table_priorityIn"));
	bantr.find("th:eq(" + (12) + ")").html(i18n("table_priorityOut"));
	bantr.find("th:eq(" + (13) + ")").html(i18n("table_lock"));
	bantr.find("th:eq(" + (14) + ")").html(i18n("table_remark"));
	bantr.find("th:eq(" + (15) + ")").html(i18n("table_latestDefiner"));
	bantr.find("th:eq(" + (16) + ")").html(i18n("table_latestDefinitionTime"));
	bantr.find("th:eq(" + (17) + ")").html(i18n("table_operation"));
}
// 创建表体
function getbody(Tdata) {
	
	getthead();
	BinId = [];
	var tbody = $("<tbody></tbody>");
	$('table').append(tbody);
	for (j = 0; j < Tdata.length; j++) {
		BinId[j] = Tdata[j].binId;
		var sortName=searchName(sortNos,sortNames,Tdata[j].sortNo);
		var binTypeName = binTypeNames[Tdata[j].binType-1];
		pantr = $("<tr></tr>");
		for (i = 0; i < 21; i++) {
			td = $("<td></td>")
			pantr.append(td);
		}
		
		pantr.find("td:eq(" + (0) + ")").html(j+1);
		pantr.find("td:eq(" + (1) + ")").html(Tdata[j].binNo);
		pantr.find("td:eq(" + (2) + ")").html(Tdata[j].ident);
		pantr.find("td:eq(" + (3) + ")").html(Tdata[j].sortNo);
		pantr.find("td:eq(" + (3) + ")").hide();
		pantr.find("td:eq(" + (4) + ")").html(sortName);
		pantr.find("td:eq(" + (5) + ")").html(Tdata[j].binType);
		pantr.find("td:eq(" + (5) + ")").hide();
		pantr.find("td:eq(" + (6) + ")").html(binTypeName);
		pantr.find("td:eq(" + (7) + ")").html(Tdata[j].matter.matterName);
		pantr.find("td:eq(" + (8) + ")").html(Tdata[j].levelActual);
		pantr.find("td:eq(" + (9) + ")").html(Tdata[j].capacityMax);
		pantr.find("td:eq(" + (10) + ")").html(Tdata[j].levelHigh);
		pantr.find("td:eq(" + (11) + ")").html(Tdata[j].levelRefill);
		pantr.find("td:eq(" + (12) + ")").html(Tdata[j].levelLow);
		pantr.find("td:eq(" + (13) + ")").html(Tdata[j].priorityIn);
		pantr.find("td:eq(" + (14) + ")").html(Tdata[j].priorityOut);
		pantr.find("td:eq(" + (15) + ")").html(Tdata[j].lock);
		pantr.find("td:eq(" + (15) + ")").hide();
		pantr.find("td:eq(" + (16) + ")").html(locks[Tdata[j].lock-1]);
		pantr.find("td:eq(" + (17) + ")").html(Tdata[j].remark);
		pantr.find("td:eq(" + (18) + ")").html(Tdata[j].user.userName);
		pantr.find("td:eq(" + (19) + ")").html(Tdata[j].lastModificationDateTime);
		pantr.find("td:eq(" + (20) + ")").html(Tdata[j].matter.matterId);
		pantr.find("td:eq(" + (20) + ")").hide();
		td = $("<td id='delbtn-td' class='opertion'></td>")
		pantr.append(td);
		var deleteBtn = $("<button id='"+Tdata[j].binId+"' class='btn btn-danger' onclick='delLevelActual(id)'>"+i18n("button_clear")+"</button>");
		// 现容量清零
		var redectBtn = $("<button id='movebtn' class='btn btn-warning'>"+i18n("button_modify")+"</button>");
		redectBtn.click(function() {

		})
		td.append(deleteBtn);
		td.append(" ");
		td.append(redectBtn);
		tbody.append(pantr);
	}

}


