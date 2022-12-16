package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.CalendarData;
import com.example.service.ListDataService;

@RestController
@RequestMapping("/recipt")
public class CalendarController{
	
	@Autowired
	private ListDataService listDataService;
	
	// 指定の月データを取得
	@GetMapping("/calendar/{yyyyMM}")
	public ModelAndView getCalendar(@PathVariable("yyyyMM") String yyyyMM) {
		ModelAndView modelAndView = new ModelAndView();
		CalendarData calendarData = new CalendarData();
		calendarData.setYyyyMM(yyyyMM);
		// 月のデータ全てを取得
		calendarData.setListData(listDataService.getMonthListData(yyyyMM));
		// 日付ごとの金額データを取得
		calendarData.setDateMoney(listDataService.getMonthDateMoneyData(yyyyMM));
		// 費目ごとの金額データを取得
		calendarData.setCategoryMoney(listDataService.getMonthCategoryMoneyData(yyyyMM));
		
		modelAndView.addObject("calendarData", calendarData);
	    modelAndView.setViewName("recipt/calendar");
	     
	    return modelAndView;
	}
}