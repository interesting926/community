package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.mode.Question;
import life.majiang.community.community.mode.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO findList(Integer page, Integer size){
        Integer offset = size *(page-1);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList();
        List<Question> questions = questionMapper.findList(offset,size);
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.Count();
        paginationDTO.setPagination(totalCount,size,page);
        if (page <1){
            paginationDTO.setPage(1);
        }
        if (page> paginationDTO.getTotalPage()){
            paginationDTO.setPage(paginationDTO.getTotalPage());
        }
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

}
