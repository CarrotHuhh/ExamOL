package com.se.exam.service;

import com.se.exam.po.Record;

import java.util.List;

public interface RecordService {
    public List<Record> getRecord(Integer userId);

    public int setTime(Integer min, Integer sec, Integer examId);
}