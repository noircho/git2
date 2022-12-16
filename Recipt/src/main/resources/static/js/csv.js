'use strict';

/** 画面ロード時の処理. */
jQuery(function($){
	
	$('#btn-input').click(function (event){
		inputCsv();
	});
	
	$('#btn-output').click(function (event){
		outputCsv();
	});
});

function inputCsv(){
	
	// フォームの値を取得
	var inputPath = $('#input').Value;
	
	// ajax通信
	$.ajax({
		type:"POST",
		cache:false,
		url:'/recipt/csv/input',
		data:inputPath,
		dataType:'json'
	}).done(function(data){
		// ajax成功時の処理
		alert('更新しました');
		// ユーザー一覧画面にリダイレクト
		window.location.href = '/recipt/csv';
	}).fail(function(jqXHR, textStatus, errorThrown){
		// ajax失敗時の処理
		alert('失敗しました');
	}).always(function(){
		// 常に実行する処理
	});
}

function outputCsv(){
	
	// フォームの値を取得
	var inputPath = $('#output').Value;
	
	// ajax通信
	$.ajax({
		type:"POST",
		cache:false,
		url:'/recipt/csv/output',
		data:inputPath,
		dataType:'json'
	}).done(function(data){
		// ajax成功時の処理
		alert('更新しました');
		// ユーザー一覧画面にリダイレクト
		window.location.href = '/recipt/csv';
	}).fail(function(jqXHR, textStatus, errorThrown){
		// ajax失敗時の処理
		alert('失敗しました');
	}).always(function(){
		// 常に実行する処理
	});
}