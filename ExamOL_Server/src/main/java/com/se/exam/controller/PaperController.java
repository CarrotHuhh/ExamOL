package com.se.exam.controller;

import com.se.exam.po.Paper;
import com.se.exam.po.Question;
import com.se.exam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PaperController")
public class PaperController {
    @Autowired
    PaperService paperService;
    @RequestMapping("/getPaperInfoById")
    public Paper getPaperById(Integer paperId){
        return paperService.getPaperInfoById(paperId);
    }
    @RequestMapping("/getPaperChoice")
    public List<Question> getPaperChoice(Integer paperId){
        return paperService.getPaperChoice(paperId);
    }
    @RequestMapping("/getPaperFilling")
    public List<Question> getPaperFilling(Integer paperId){
        return paperService.getPaperFilling(paperId);
    }
}
