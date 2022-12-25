package com.se.exam.controller;

import com.se.exam.po.Record;
import com.se.exam.service.RecordService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RecordController")
public class RecordController{
	@Autowired
	RecordService recordservice;
	
	@RequestMapping("/getRecord")
    public List<Record> getRecord(Integer userId){
        return recordservice.getRecord(userId);
    }
}