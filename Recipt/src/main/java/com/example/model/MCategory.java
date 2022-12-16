package com.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class MCategory {
	private String categoryId;
	private String categoryName;
	private Date inputDate;
	private Date updateDate;
	private boolean delFlg;
}