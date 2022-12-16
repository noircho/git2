package com.example.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({"品目ID", "レシートID", "日付", "費目名", "品目", "店舗名", "支出金額", "入力日" ,"更新日", "削除フラグ"})
@Data
public class CsvTData {
	@JsonProperty("品目jID")
	private String itemId;
	@JsonProperty("レシートID")
	private String reciptId;
	@JsonProperty("日付")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date buyDate;
	@JsonProperty("費目名")
	private String categoryName;
	@JsonProperty("品目")
	private String itemName;
	@JsonProperty("店舗名")
	private String shopName;
	@JsonProperty("支出金額")
	private BigDecimal spentMoney;
	@JsonProperty("入力日")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date inputDate;
	@JsonProperty("更新日")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date updateDate;
	@JsonProperty("削除フラグ")
	private boolean delFlg;
}