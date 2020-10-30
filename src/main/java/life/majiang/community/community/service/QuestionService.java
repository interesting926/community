package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.QuestionExtMapper;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.mode.Question;
import life.majiang.community.community.mode.QuestionExample;
import life.majiang.community.community.mode.User;
import life.majiang.community.community.mode.UserExample;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO findList(Integer page, Integer size){
        Integer offset = size *(page-1);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList();
//        List<Question> questions = questionMapper.findList(offset,size);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        PaginationDTO paginationDTO = new PaginationDTO();
//        Integer totalCount = questionMapper.Count();
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
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
//        List<Question> questions = questionMapper.findUserList(userid,offset,size);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userid);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        PaginationDTO paginationDTO = new PaginationDTO();

//        Integer totalCount = questionMapper.CountUser(userid);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userid);
        Integer totalCount =(int) questionMapper.countByExample(questionExample);
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
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() ==null){
            //新建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //更新
            Question updateQustion = new Question();
            updateQustion.setGmtModified(System.currentTimeMillis());
            updateQustion.setTitle(question.getTitle());
            updateQustion.setDescription(question.getDescription());
            updateQustion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQustion, example);
            if (updated != 1){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {

        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
