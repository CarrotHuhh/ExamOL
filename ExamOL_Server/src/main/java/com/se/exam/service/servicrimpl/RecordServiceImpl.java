package com.se.exam.service.servicrimpl;

import com.se.exam.mapper.RecordMapper;
import com.se.exam.po.Record;
import com.se.exam.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RecordServiceImpl implements RecordService{
	@Autowired
	RecordMapper recordMapper;
	
	@Override
	public List<Record> getRecord(Integer userId) {
		return recordMapper.getRecord(userId);
	}
}