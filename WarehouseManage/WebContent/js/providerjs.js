$(document).ready(function() {
	$("#providerid").val("PI" + createProviderId());
	$("#providerid").attr("readonly", "readonly");
});

// 生成物品编号
function createProviderId() {
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
	id = year + month + day + hour + minite + second;
	return id;
}

function queryProvider() {
	var choice = $("#choice").val();
	var condition = $("#condition").val();
	window.location.href = "provider/queryProvider.action?choice=" + choice
			+ "&condition=" + condition;
}

function updateProvider(choice, condition, currentPage, providerId) {
	window.location.href = "provider/findProviderById.action?choice=" + choice
			+ "&condition=" + condition + "&currentpage=" + currentPage
			+ "&providerid=" + providerId;
}

function deleteProvider(choice, condition, currentPage, providerId) {
	if (window.confirm("确定删除？")) {
		window.location.href = "provider/deleteProvider.action?choice="
				+ choice + "&condition=" + condition + "&currentpage="
				+ currentPage + "&providerid=" + providerId;
	}

}

function exportProvider(choice, condition) {
	window.location.href = "provider/exportProvider.action?choice=" + choice
			+ "&condition=" + condition;

}