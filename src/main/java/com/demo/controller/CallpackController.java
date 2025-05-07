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

		String fullContent = temp.getContent(); // "DH907 FT2512..."
	        System.out.println("Full content: " + fullContent);
	
	        // Tách lấy mã đơn hàng từ đầu chuỗi
	        String[] parts = fullContent.split(" ");
	        String maDH = parts.length > 0 ? parts[0] : null;
		Optional<Order> order = dao.findById(maDH);
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
