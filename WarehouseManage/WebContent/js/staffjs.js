$(document).ready(function() {
	$("#staffid").val(createStaffId());
	$("#createStaffId").attr("readonly", "readonly");

});

// 生成物品编号
function createStaffId() {
	var id;
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();
	var hour = today.getHours();
	var minite = today.getMinutes();
	var second = today.getSeconds();
	if (month < 10)
		month = "0" + month;
	else
		month = "" + month;
	if (day < 10)
		day = "0" + day;
	else
		day = day + "";
	if (hour < 10)
		hour = "0" + hour;
	else
		hour = "" + hour;
	if (minite < 10)
		minite = "0" + minite;
	else
		minite = "" + minite;
	if (second < 10)
		second = "0" + second;
	else
		second = "" + second;
	id = year.toString().substring(2, 4) + month + day + hour + minite + second;
	return id;
}

function queryStaff() {
	var choice = $("#choice").val();
	var condition = $("#condition").val();
	window.location.href = "staff/queryStaff.action?choice=" + choice
			+ "&condition=" + condition;
}

function updateStaff(choice, condition, currentPage, staffId) {
	window.location.href = "staff/findStaffByStaffId.action?choice=" + choice
			+ "&condition=" + condition + "&currentpage=" + currentPage
			+ "&staffid=" + staffId;
}

function deleteStaff(choice, condition, currentPage, staffId){
	if (window.confirm("确定删除？")){
		window.location.href = "staff/deleteStaff.action?choice=" + choice
		+ "&condition=" + condition + "&currentpage=" + currentPage
		+ "&staffid=" + staffId;
	}
}

function exportStaff(choice, condition){
	window.location.href = "staff/exportStaff.action?choice=" + choice
	+ "&condition=" + condition;
}