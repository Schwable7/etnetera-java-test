package com.etnetera.hr.controller;

import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etnetera.hr.data.JavaScriptFramework;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Simple REST controller for accessing application logic.
 * 
 * @author Etnetera
 *
 */
@RestController
public class JavaScriptFrameworkController {

	private final JavaScriptFrameworkService service;

	@Autowired
	public JavaScriptFrameworkController(JavaScriptFrameworkService service) {
		this.service = service;
	}

	@GetMapping("/frameworks")
	public Iterable<JavaScriptFramework> frameworks() {
		return service.getFrameworks();
	}

	@PostMapping("/frameworks")
	public JavaScriptFramework createFramework(String name, String version, BigDecimal hypeLevel, Date deprecationDate) {
		return service.createJavaScriptFramework(name, version, hypeLevel, deprecationDate);
	}

	@PutMapping("/frameworks/{name}")
	public JavaScriptFramework updateFramework(@PathVariable String name, String version, BigDecimal hypeLevel, Date deprecationDate) {
		return service.updateFramework(name, version, hypeLevel, deprecationDate);
	}

	@DeleteMapping("/frameworks/{name}")
	public void deleteFramework(@PathVariable String name) {
		service.deleteFramework(name);
	}


}
