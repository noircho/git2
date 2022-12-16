package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.ListData;
import com.example.service.ListDataService;

@RestController
@RequestMapping("/recipt")
public class AllListController{
	
	@Autowired
	private ListDataService listDataService;
	
	// 指定の月データを取得
	@GetMapping("/all")
	public ModelAndView getAllList() {
		ModelAndView modelAndView = new ModelAndView();
		List<ListData> allListData = listDataService.getAllListData();
		
		modelAndView.addObject("allListData", allListData);
	    modelAndView.setViewName("recipt/all");
	     
	    return modelAndView;
	}
}