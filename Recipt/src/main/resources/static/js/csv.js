'use strict';

/** 画面ロード時の処理. */
jQuery(function($){
	
	$('#btn-output').click(function (event){
		outputCsv();
	});
});

// ボタンを押したらファイル選択ダイアログを表示する
$("#upload_file").on("click", () => {
  $("#file").click();
});

function changeFile(obj){
 	
    console.log(obj.id);
    
    /*if (window.FormData){             //　FormDataにブラウザが対応しているかチェック
    	var fileObj = $('#file')[0].files[0];    // ファイルオブジェクトの取り出し
		if ( fileObj != null ){*/
			fileupload(obj.id);
	   /* }
    }else{
        alert("このブラウザはFormDataに対応していません。");
        return null;
 	}*/
}

function fileupload(fileObj){
	//var fd = new FormData();            //　FormDataオブジェクト生成
    //fd.append('uploadfile', fileObj);    //フォームアイテム名でオブジェクトを追加
	
	// ajax通信
	$.ajax({
		type:"POST",
		cache:false,
		url:'/recipt/csv/upload',
		data:fileObj,
		dataType:'text',
		processData: false,
		contentType: false
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