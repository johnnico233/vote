package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vote.service.VoteService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private VoteService voteService;
    @RequestMapping("/")
    public String getHomeUrl(HttpSession httpSession, Model model, @RequestParam(required = false) String idx,
                             @RequestParam(required = false,value = "title") String searchContent){
        int index=1;
        int limit=5;
        if(idx!=null)
            index=Integer.valueOf(idx);
        model.addAttribute("index",index);
        if(searchContent==null){
            model.addAttribute("voteList",voteService.getVoteListInfo(index,limit));
            model.addAttribute("total",voteService.getVoteListInfoSize(limit));
            model.addAttribute("searchContent",null);
        }else{
            model.addAttribute("voteList",voteService.getVoteListInfoWithSearch(index,limit,searchContent));
            model.addAttribute("total",voteService.getVoteListInfoWithSearchSize(searchContent,limit));
            model.addAttribute("searchContent",searchContent);
        }
        return "home";
    }
}
