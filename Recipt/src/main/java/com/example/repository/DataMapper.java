package com.example.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.CsvTData;
import com.example.model.ListData;
import com.example.model.TData;

@Mapper
public interface DataMapper {
	
	// データが存在する年月抽出
	public List<String> getyyyyMM();
	
	// 全データ取得
	public List<ListData> getAllListData();
	
	// 月データ取得
	public List<ListData> getMonthListData(String yyyymm);
	
	// ひと月における日付ごとの金額データを取得
	public List<Map<String, BigDecimal>> getMonthDateMoneyData(String yyyymm);
	
	// ひと月における費目ごとの金額データを取得
	public List<Map<String, BigDecimal>> getMonthCategoryMoneyData(String yyyymm);
	
	// 一括削除
	public int deleteListData();
	
	// CSV1件登録
	public int insertListData(CsvTData tData);
	
	// 全件取得
	public List<TData> getAllTData();
}