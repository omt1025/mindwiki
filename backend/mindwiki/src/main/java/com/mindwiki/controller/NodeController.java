package com.mindwiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.mindwiki.service.NodeService;

@CrossOrigin("*")
@RestController
@Controller
public class NodeController {
	
	@Autowired
	private NodeService service;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	//	@PostMaping("")

}
