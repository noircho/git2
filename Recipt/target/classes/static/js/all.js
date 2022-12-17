'use strict';

var table = null;

// 初期表示
window.onload = function () {
	// データ取得
	createDataTables();
};

function createDataTables(){
	// データリストの作成
	if(table !== null){
		// DataTable破棄
		table.destroy();
	}
	// DataTable作成
	table = $('#data-list-table').DataTable({
		language:{
			url:'/webjars/datatables-plugins/i18n/Japanese.json'
		},
		lengthChange: false,
		data: listData,
		columns: [
			{data: 'reciptId'},
			{
				data: 'buyDate',
				render:function(data,type,row){
					var date = new Date(data);
					var day = date.getMonth()+1;
					return date.getFullYear() + "/" + day + "/" + date.getDate();
				}
			},
			{data: 'categoryName'},
			{data: 'itemName'},
			{data: 'shopName'},
			{data: 'spentMoney'},
		]
	});	
}