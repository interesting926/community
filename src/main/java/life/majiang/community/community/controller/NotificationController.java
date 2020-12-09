package life.majiang.community.community.controller;

import life.majiang.community.community.Enum.NotificationTypeEnum;
import life.majiang.community.community.dto.NotificationDTO;
import life.majiang.community.community.mode.User;
import life.majiang.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification")
    public String profile(HttpServletRequest request,
                          @RequestParam(name = "id") Long id){
        User user =(User)request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id,user);
        if ((NotificationTypeEnum.REPLAY_COMMENT.getType() == notificationDTO.getType())||
                (NotificationTypeEnum.REPLAY_QUESTION.getType() ==  notificationDTO.getType())){
            return "redirect:/question/?id="+notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }
    }

}
