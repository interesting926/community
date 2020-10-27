package life.majiang.community.community.controller;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mode.Question;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    public String question(@RequestParam("id")Integer id,
                           Model model){
        Question question = questionMapper.findById(id);
        if (question != null){
            QuestionDTO questionDTO = questionService.findByid(id);
            model.addAttribute("question",questionDTO);
            return "question";
        }else {
            model.addAttribute("error","不存在这个问题");
            return "redirect:/";
        }
    }

}
