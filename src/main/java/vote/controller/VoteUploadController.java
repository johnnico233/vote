package vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/voteUpload")
public class VoteUploadController {

    @RequestMapping(method = RequestMethod.GET)
    public String getUrl(){
        return "voteUpload";
    }
}
