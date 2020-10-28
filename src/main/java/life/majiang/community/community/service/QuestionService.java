package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.mode.Question;
import life.majiang.community.community.mode.User;
import life.majiang.community.community.mode.UserExample;
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
        Integer totalPage;
        if (totalCount %size ==0){
            totalPage = totalCount /size;
        }else{
            totalPage = totalCount /size+1;
        }
        paginationDTO.setTotalPage(totalPage);
        if (page <1){
            page=1;
        }
        if (page> paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        paginationDTO.setPagination(totalPage,size,page);

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
/*            User user = new User();
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(example);
            user=users.get(0);*/
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO findList(Integer userid, Integer page, Integer size) {
        Integer offset = size *(page-1);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList();
        List<Question> questions = questionMapper.findUserList(userid,offset,size);
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.CountUser(userid);
        Integer totalPage;
        if (totalCount %size ==0){
            totalPage = totalCount /size;
        }else{
            totalPage = totalCount /size+1;
        }
        paginationDTO.setTotalPage(totalPage);
        if (page <1){
            page=1;
        }
        if (page> paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        paginationDTO.setPagination(totalPage,size,page);

        for (Question question : questions) {
/*            User user = new User();
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(example);
            user=users.get(0);*/
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public QuestionDTO findByid(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.findById(id);
        BeanUtils.copyProperties(question,questionDTO);
//        User user = userMapper.findById(question.getCreator());
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(example);
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() ==null){
            //新建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.creat(question);
        }else{
            //更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
