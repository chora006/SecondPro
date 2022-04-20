package com.example.demoSpring;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api")
public class ApiClient {
	@Autowired
	ProductService service;
	
	@GetMapping("/getdata")
	public List<Product> getProducts() {
		 return service.listAll();
	}
	
	@GetMapping("/getdata/{id}")
	public Optional<Product> getProductById(@PathVariable(name="id") Integer id){
		return service.getById(id);
	}
	
	@PostMapping("/savedata")
    public void saveData(Product p) {
		service.save(p);
	}	
	
	@PutMapping("/edit/{id}")
	public void editData(@PathVariable(name="id") Integer id, Product p)
	{
		service.get(id);
		service.save(p);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteData(@PathVariable(name="id") Integer id, Product p)
	{
		service.delete(id);
		}
	
	@GetMapping("/export/{format}")
	private String exportReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return service.exportReport(format);
				
	}
    	
	}
