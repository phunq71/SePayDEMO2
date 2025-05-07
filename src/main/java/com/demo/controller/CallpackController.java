package com.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.dao.OrderDAO;
import com.demo.dto.TransactionDTO;
import com.demo.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("*")
public class CallpackController {	
	@Autowired
	OrderDAO dao;
	
	@PostMapping("/callpack")
	public Boolean postForSePay(@RequestBody TransactionDTO temp, Model model) {
		System.out.println("HELOO________________________________________________");
		System.out.println(temp.toString());
		Optional<Order> order = dao.findById(temp.getContent());
		if (order.isPresent()) {
		    order.get().setStatusTT(true); // Cập nhật trạng thái thành true (Đã thanh toán)
		    dao.save(order.get());
		    boolean status = order.get().isStatusTT(); // Lấy giá trị boolean
		    
		}
		return true;

	}

	@GetMapping("/result")
	@ResponseBody
	public boolean checkPaymentStatus(@RequestParam("maDH") String maDH) {
	    Optional<Order> order = dao.findById(maDH);
	    return order.map(Order::isStatusTT).orElse(false);
	}
		
}
