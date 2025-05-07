package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dao.OrderDAO;
import com.demo.entity.Order;

@Controller
public class SubmitController {
	@Autowired
	OrderDAO dao;
	
	@PostMapping("/submit")
	public String getSePayResult(@RequestParam String maDH
			, @RequestParam String soTien 
			, Model model) {
		System.out.println(maDH);
		System.out.println(soTien);
		model.addAttribute("maDH", maDH);
		model.addAttribute("soTien", soTien);
		model.addAttribute("status", false);
		
		Order temp = new Order(maDH, false);
		dao.save(temp);
		
		return "SePayResult";
	}
}
