package vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/voteSubject")
public class VoteSubjectController {
    @RequestMapping(method = RequestMethod.GET)
    public String getUrl(){
        return "voteSubject";
    }
}
