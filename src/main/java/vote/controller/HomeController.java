package vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String getHomeUrl(HttpSession httpSession, Model model){
        return "home";
    }
}
