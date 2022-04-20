package com.example.demoSpring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ProductService {
	@Autowired
	ProductRepo repo;
	
	//read the data
	public List<Product> listAll(){
		return repo.findAll();
	}
	
	// fetch the individual  record
	public Optional<Product> getById(Integer id) {
		return repo.findById(id);
	}
	
	//save the data
	public void save(Product p) {
		repo.save(p);
	}
	//update
	public Product get(Integer id) {
		return repo.findById(id).get();
	}
	//delete
	public void delete( Integer id) {
		repo.deleteById(id);
	}

	public String exportReport(String format) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public String exportReport1(String format) throws FileNotFoundException, JRException {
		List<Product> listProduct = repo.findAll();
		String path = "C:\\Users\\Dell\\Desktop\\";
		File file = ResourceUtils.getFile("classpath:product.jrxml");
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listProduct);
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("file", "file path");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper,parameters, ds);
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint,path+"product.pdf");
		}
		return "path"+path;
		
		
	}

	
}
