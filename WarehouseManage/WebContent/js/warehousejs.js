$(document).ready(function() {
	$("#warehouseid").val("WH" + createWarehouseId());
	$("#warehouseid").attr("readonly", "readonly");
});

// 生成编号
function createWarehouseId() {
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

function queryWarehouse() {
	var choice = $("#choice").val();
	var condition = $("#condition").val();
	window.location.href = "warehouse/queryWarehouse.action?choice=" + choice
			+ "&condition=" + condition;
}

function updateWarehouse(choice, condition, currentPage, warehouseId) {
	window.location.href = "warehouse/findWarehouseById.action?choice="
			+ choice + "&condition=" + condition + "&currentpage="
			+ currentPage + "&warehouseid=" + warehouseId;
}

function deleteWarehouse(choice, condition, currentPage, warehouseId) {
	if (window.confirm("确定删除？")) {
		window.location.href = "warehouse/deleteWarehouse.action?choice="
				+ choice + "&condition=" + condition + "&currentpage="
				+ currentPage + "&warehouseid=" + warehouseId;
	}
}

function queryWarehouseGoods(warehouseId) {
	window.location.href = "warehouse/queryWarehouseGoods.action?warehouseid="
			+ warehouseId;
}

function exportWarehouse(choice, condition) {
	window.location.href = "warehouse/exportWarehouse.action?choice=" + choice
			+ "&condition=" + condition;
}