'use strict';

const week = ["日", "月", "火", "水", "木", "金", "土"];
const today = new Date();
var targetDate = new Date(yyyyMM.substr(0,4) + "/" + yyyyMM.substr(4,2) + "/01");
// 月末だとずれる可能性があるため、1日固定で取得
var showDate = new Date(today.getFullYear(), today.getMonth(), 1);

var table1 = null;
var table2 = null;

// 初期表示
window.onload = function () {
	// カレンダーの作成
    showProcess(targetDate);
	// データ取得
	createDataTables();
};

// カレンダー表示
function showProcess(date) {
    var year = date.getFullYear();
    var month = date.getMonth();
    document.querySelector('#header').innerHTML = year + "年 " + (month + 1) + "月";
    var calendar = createProcess(year, month);
    document.querySelector('#calendar').innerHTML = calendar;
}

// カレンダー作成
function createProcess(year, month) {
    // 曜日
    var calendar = "<table><tr class='dayOfWeek'>";
    for (var i = 0; i < week.length; i++) {
        calendar += "<th>" + week[i] + "</th>";
    }
    calendar += "</tr>";

    var count = 0;
    var startDayOfWeek = new Date(year, month, 1).getDay();
    var endDate = new Date(year, month + 1, 0).getDate();
    var lastMonthEndDate = new Date(year, month, 0).getDate();
    var row = Math.ceil((startDayOfWeek + endDate) / week.length);
	var day = 0;
	var dayOfMoney = "";
	var arrListByDay = new Array();
	arrListByDay = listByDay;
	
    // 1行ずつ設定
    for (var i = 0; i < row; i++) {
        calendar += "<tr>";
        // 1colum単位で設定
        for (var j = 0; j < week.length; j++) {
            if (i == 0 && j < startDayOfWeek) {
                // 1行目で1日まで先月の日付を設定
                calendar += "<td class='disabled'>" + (lastMonthEndDate - startDayOfWeek + j + 1) + "</td>";
            } else if (count >= endDate) {
                // 最終行で最終日以降、翌月の日付を設定
                count++;
                calendar += "<td class='disabled'>" + (count - endDate) + "</td>";
            } else {
                // 当月の日付を曜日に照らし合わせて設定
                count++;
                if(year == today.getFullYear()
                  && month == (today.getMonth())
                  && count == today.getDate()){
                    calendar += "<td class='today'>" + count + "</td>";
                } else {
                    calendar += "<td>" + count + "</td>";
                }
            }
        }
        calendar += "</tr>";
		// 金額行の追加
		calendar += "<tr class='dayOfMoney'>";
		for (var n = 0; n < week.length; n++){
			if(n == 0 || n < startDayOfWeek){
				// 先月の日付はなにもしない
				calendar += "<td></td>";
			} else if (day >= endDate) {
				// 翌月の日付はなにもしない
				calendar += "<td></td>";
			} else {
				day++;
				dayOfMoney = arrListByDay.find(item => Number(item.DD) === day);
				if (dayOfMoney != undefined) {
					calendar += '<td><a href="#" name="day' + day + '">' + dayOfMoney.SUM + "</a></td>";
					
				} else {
					calendar += "<td></td>";
				}
			}
		}
		calendar += "</tr>";
    }
    return calendar;
}

function createDataTables(){
	// データリストの作成
	if(table1 !== null){
		// DataTable破棄
		table1.destroy();
	}
	// DataTable作成
	table1 = $('#data-list-table').DataTable({
		language:{
			url:'/webjars/datatables-plugins/i18n/Japanese.json'
		},
		lengthChange: false,
		ordering: false,
		paging: false,
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
	
	// データリストの作成
	if(table2 !== null){
		// DataTable破棄
		table2.destroy();
	}
	// DataTable作成
	table2 = $('#category-data-list-table').DataTable({
		language:{
			url:'/webjars/datatables-plugins/i18n/Japanese.json'
		},
		lengthChange: false,
		searching: false,
		ordering: false,
		paging: false,
		data: listByCategory,
		columns: [
			{data: 'CATEGORY_NAME'},
			{data: 'SUM'},
		]
	});
}

$(document).on('click', '.dayOfMoney td', function(e) {
	var eDay = e.target.attributes[1].value.substr(3,2);
	var eDate = "2022/" + "12/" + eDay;
	document.getElementById( "data-list-table_filter" ).firstChild.lastChild.value = "";
	//console.dir(document.getElementById( "data-list-table_filter" ).firstChild.lastChild.value);
	document.getElementById( "data-list-table_filter" ).firstChild.lastChild.value = eDate
	document.getElementById( "data-list-table_filter" ).firstChild.lastChild.focus();
	
	document.getElementById( "data-list-table_filter" ).firstChild.lastChild.dispatchEvent(new Event('input', {bubbles:true}));
});