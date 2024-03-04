package com.example.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CanaraBody;
import com.example.model.CanaraHeader;
import com.example.model.CanaraInput;
import com.example.service.CanaraService;

@RestController
public class EmpController {

	@Autowired
	CanaraService canaraService;

	@PostMapping("/save")
	public void saveDetails(@RequestBody CanaraInput canarainput) {
		CanaraBody canarabody = canarainput.getBody();
		CanaraHeader header = canarainput.getHeader();

		CanaraHeader role = canaraService.getRole(header);

		if (role.getRole().equalsIgnoreCase("admin")) {
			canaraService.saveEmpDetails(canarabody);
		} else {
			System.out.println("you are not admin to perfrom this operation");
		}

	}

	@DeleteMapping("/api/{id}")
	public String deleteDetails(@PathVariable Long id, @RequestBody CanaraInput canarainput) {

		CanaraHeader header = canarainput.getHeader();

		CanaraHeader role = canaraService.getRole(header);

		if (role.getRole().equalsIgnoreCase("admin")) {
			
			canaraService.deleteDetails(id, header);
			
			return id+"deleted";
			
		} else {
			
			return "you are not admin to perfrom this operation";
		}
	
	}
	

	 

}
