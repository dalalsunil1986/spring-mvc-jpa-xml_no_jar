package org.javasavvy.demo.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.javasavvy.demo.entity.Customer;
import org.javasavvy.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	Logger  LOG = Logger.getLogger(CustomerController.class.getName());
	
	@Autowired(required=true)
	@Qualifier("customerService")
	private CustomerService customerService;
	
	
	@GetMapping("/")
	public String getHome(){
		LOG.info("Home page invoked");
		return "redirect:customers.action";
	}
	
	@GetMapping("/customers")
	public ModelAndView getCustomers(){
		LOG.info("Customer page invoked");
		ModelAndView model  = new ModelAndView("customers");
		List<Customer> list = customerService.getAllCustomers();
		model.addObject("customers", list);
		return model;
	}
	@GetMapping("/editCustomerView/{customerId}")
	public ModelAndView getEditCustomerForm(@PathVariable("customerId") String customerId){
		
		
		ModelAndView model  = new ModelAndView("editCustomer");
		Customer cus = customerService.getCustomer(Long.parseLong(customerId));
		model.addObject("customer", cus);
		return model;
		
	}
	@RequestMapping(value="/editCustomer",method=RequestMethod.POST)
	public ModelAndView editCustomer(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		model.setViewName("customers");
		String customerId = request.getParameter("customerId");
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		LOG.info("Adding customer with name:"+name);
		Customer customer = customerService.updateCustomer(Long.parseLong(customerId),name, country);
		if(customer!=null){
			model.addObject("saveSuccess", "Customer Added SuccessFully:"+customer.getCustomerName());
		}else{
			model.addObject("saveError", "Customer creation failed");
		}
		List<Customer> list = customerService.getAllCustomers();
		model.addObject("customers", list);
		
		return model;
		
	}
	@RequestMapping(value="/addCustomer",method=RequestMethod.POST)
	public ModelAndView addCustomer(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		model.setViewName("customers");
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		
		Customer customer = customerService.addCustomer(name, country);
		if(customer!=null){
			model.addObject("saveSuccess", "Customer Added SuccessFully:"+customer.getCustomerName());
		}else{
			model.addObject("saveError", "Customer creation failed");
		}
		List<Customer> list = customerService.getAllCustomers();
		model.addObject("customers", list);
		
		return model;
		
	}
	
	
}
