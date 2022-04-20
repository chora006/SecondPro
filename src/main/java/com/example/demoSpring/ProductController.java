package com.example.demoSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/")
    public String viewPage(Model model) {
		List<Product> listProducts = service.listAll();
 		model.addAttribute("listP",listProducts);
		return "index";
	}
	
	@RequestMapping("/new")
	public String newProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "newproduct";
	}
	
	@RequestMapping("/save")
	public String saveData(@ModelAttribute("product") Product product) 
	{
		service.save(product);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editData(@PathVariable(name="id") Integer id) {
		ModelAndView mav = new ModelAndView("editdata");
		Product product = service.get(id);
		mav.addObject(product);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deletedata(@PathVariable(name="id") Integer id) {
		service.delete(id);
		return "redirect:/";
	}
	
	
}
