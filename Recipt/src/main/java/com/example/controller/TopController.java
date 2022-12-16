package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.service.ListDataService;

@RestController
public class TopController{
	
	@Autowired
	private ListDataService listDataService;
	
	@GetMapping("/top")
	public ModelAndView getNowCalendar() {
		ModelAndView modelAndView = new ModelAndView();
		// 現在のyyyyMMを取得
		Date nowDate = new Date();
		String yyyyMM = new SimpleDateFormat("yyyyMM").format(nowDate);
		
		// 年月の一覧を取得
		List<String> yyyyMMList = listDataService.getyyyyMM();
		
		modelAndView.addObject("yyyyMMList", yyyyMMList);
	    modelAndView.setViewName("forward:recipt/calendar/" + yyyyMM);
		
		return modelAndView;
	}
}