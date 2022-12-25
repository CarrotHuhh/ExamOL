package com.se.exam.service;

import java.util.List;

import com.se.exam.po.Record;

public interface RecordService {
	public List<Record> getRecord(Integer userId);
}