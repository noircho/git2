package com.example.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.CsvTData;
import com.example.model.ListData;
import com.example.model.TData;
import com.example.repository.DataMapper;
import com.example.service.ListDataService;

@Service
public class ListDataServiceImpl implements ListDataService {
	
	@Autowired
	private DataMapper mapper;
	
	/** データが存在する年月抽出 */
	@Override
	public List<String> getyyyyMM() {
		return mapper.getyyyyMM();
	}
	
	/** 全データ取得 */
	public List<ListData> getAllListData(){
		return mapper.getAllListData();
	}
	
	/** 月データ取得 */
	@Override
	public List<ListData> getMonthListData(String yyyyMM) {
		return mapper.getMonthListData(yyyyMM);
	}
	
	/** ひと月における日付ごとの金額データを取得 */
	@Override
	public List<Map<String,BigDecimal>> getMonthDateMoneyData(String yyyyMM) {
		return mapper.getMonthDateMoneyData(yyyyMM);
	}
	
	/** ひと月における費目ごとの金額データを取得 */
	@Override
	public List<Map<String,BigDecimal>> getMonthCategoryMoneyData(String yyyyMM) {
		return mapper.getMonthCategoryMoneyData(yyyyMM);
	}
	
	/** 一括削除 */
	@Override
	public int deleteListData() {
		return mapper.deleteListData();
	};
	
	/** CSV1件登録 */
	@Override
	public int insertListData(CsvTData tData) {
		return mapper.insertListData(tData);
	};
	
	/** 全件取得 */
	@Override
	public List<TData> getAllTData(){
		return mapper.getAllTData();
	};
}