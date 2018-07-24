package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.domain.FollowUser;
import vote.domain.User;
import vote.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserService userService;
    @RequestMapping("/{id}")
    public String getUserOverview(HttpSession httpSession, Model model,@PathVariable int id){
        User user=userService.getUserOverviewInfo(id);
        List<FollowUser> followUsers=userService.getFollowList(id,0,5);
        if(user!=null){
            model.addAttribute("userInfo",user);
            model.addAttribute("infoType","overview");
            model.addAttribute("followList",followUsers);
            return "user";
        }
        return "home";
    }
    @RequestMapping("/**")
    public String getUrl(HttpSession httpSession,Model model){
        if(httpSession.getAttribute("userID")!=null){
            return "user";
        }
        return "home";
    }
}
