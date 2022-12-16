package com.example.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ListData {
	private String reciptId;
	private Date buyDate;
	private String categoryId;
	private String categoryName;
	private String itemName;
	private String shopName;
	private BigDecimal spentMoney;
	private Date inputDate;
	private Date updateDate;
	private boolean delFlg;
}