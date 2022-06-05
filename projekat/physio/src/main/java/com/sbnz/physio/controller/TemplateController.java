package com.sbnz.physio.controller;

import java.io.IOException;

import javax.websocket.server.PathParam;

import com.sbnz.physio.service.TemplateService;
import com.sbnz.physio.service.TherapyTemplateService;
import com.sbnz.template.TherapyTemplateModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/template")
public class TemplateController {
	
	@Autowired
	TemplateService templateService;
	
	@Autowired
	TherapyTemplateService therapyTemplateService;


    @GetMapping("/gen-and-save")
    public void genAndSaveDiagnosis(@RequestParam String resultPath, @RequestParam String dataFilePath, @RequestParam String templatePath) {
    	
        try {
        	templateService.generateRulesAndSaveThem(templatePath, dataFilePath, resultPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/just-gen")
    public ResponseEntity<String> justGenDiagnosis(@RequestParam String templatePath, @RequestParam String dataFilePath) {
        try {
            String drl = templateService.generateRules(templatePath, dataFilePath);
            return new ResponseEntity<>(drl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/gen-and-save-therapy")
    public void genAndSaveTherapy(@RequestParam String resultPath, @RequestParam String dataFilePath, @RequestParam String templatePath) {
        try {
        	therapyTemplateService.generateRulesAndSaveThem(templatePath, dataFilePath, resultPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/just-gen-therapy")
    public ResponseEntity<String> justGenTherapy(@RequestParam String templatePath, @RequestParam String dataFilePath) {
        try {
            String drl = therapyTemplateService.generateRules(templatePath, dataFilePath);
            return new ResponseEntity<>(drl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}