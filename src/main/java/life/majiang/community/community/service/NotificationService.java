package life.majiang.community.community.service;

import life.majiang.community.community.Enum.NotificationTypeEnum;
import life.majiang.community.community.dto.NotificationDTO;
import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.NotificationMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.mode.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Long userid, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userid);
        Integer totalCount = (int)notificationMapper.countByExample(notificationExample);

        if (totalCount %size ==0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size+1;
        }
        if (page <1){
            page=1;
        }
        if (page >totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,size,page);
        Integer offset = size *(page -1);
        NotificationExample example=new NotificationExample();
        example.createCriteria()
            .andReceiverEqualTo(userid);
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if (notifications.size() == 0){
            return paginationDTO;
        }
        ArrayList<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setType(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        return notificationMapper.countByExample(notificationExample);
    }
}
