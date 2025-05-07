package com.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.dao.OrderDAO;
import com.demo.dto.TransactionDTO;
import com.demo.entity.Order;


@Controller
public class CallpackController {	
	@Autowired
	OrderDAO dao;
	
	@PostMapping("/callpack")
	public String postForSePay(@RequestBody TransactionDTO temp, Model model) {
		Optional<Order> order = dao.findById(temp.getContent());
		if (order.isPresent()) {
		    order.get().setStatusTT(true); // Cập nhật trạng thái thành true (Đã thanh toán)

		    boolean status = order.get().isStatusTT(); // Lấy giá trị boolean
		    model.addAttribute("maDH", temp.getContent());
			model.addAttribute("soTien", temp.getAccountNumber());
		    model.addAttribute("status", status); // Gửi xuống view
		}
		return "SePayResult";

	}
		
}
