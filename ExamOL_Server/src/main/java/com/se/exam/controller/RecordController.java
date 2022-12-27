package com.se.exam.controller;

import com.se.exam.po.Record;
import com.se.exam.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/RecordController")
public class RecordController{
	@Autowired
	RecordService recordservice;
	
	@RequestMapping("/getRecord")
    public List<Record> getRecord(Integer userId){
        return recordservice.getRecord(userId);
    }
	
	@RequestMapping("/setTime")
	public int setTime(Integer min, Integer sec, Integer examId) {
		return recordservice.setTime(min, sec, examId);
	}
}