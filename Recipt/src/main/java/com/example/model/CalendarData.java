package com.example.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CalendarData {
	private String yyyyMM;
	private List<ListData> listData;
	// 日付ごとの金額<日付、金額>
	private List<Map<String, BigDecimal>> dateMoney;
	// 費目ごとの金額<費目コード、金額>
	private List<Map<String, BigDecimal>> categoryMoney;
}