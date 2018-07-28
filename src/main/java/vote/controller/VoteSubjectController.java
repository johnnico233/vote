package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vote.domain.vote.VoteMessage;
import vote.domain.vote.VoteTopicWithOption;
import vote.result.Result;
import vote.result.ResultCode;
import vote.service.UserService;
import vote.service.VoteService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/voteSubject")
public class VoteSubjectController {
    @Autowired
    private VoteService voteService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{topicId}",method = RequestMethod.GET)
    public String getUrl(HttpSession httpSession, Model model, @PathVariable int topicId, @RequestParam(required = false) String idx){
        VoteTopicWithOption voteTopic=voteService.getVoteInformation(topicId,model,false);
        if(voteTopic!=null){
            int index=idx==null?1:(isNumber(idx)?Integer.valueOf(idx):1);
            voteService.getVoteMessages(model,topicId,index,5);
            model.addAttribute("index",index);
            model.addAttribute("topicUser",userService.getUserOverviewInfo(voteTopic.getUserId()));
            model.addAttribute("step",5);
            System.out.println(voteTopic);
            return "voteSubject";
        }else
            return "home";
    }

    @RequestMapping(value="/**")
    public String handleErrorUrl(){
        return "home";
    }
    @RequestMapping(value = "/sendVote",method = RequestMethod.POST)
    public @ResponseBody Result sendVoteItem(@RequestBody UserVote userVote){
        ResultCode resultCode=voteService.addNewVoteHistoryAndCount(userVote);
        return new Result(resultCode,"投票成功");
    }
    @RequestMapping(value="/sendVoteMessage",method = RequestMethod.POST)
    public @ResponseBody ResultWithIndex sendVoteMessage(@RequestBody VoteMessage voteMessage){
        int total=voteService.addNewVoteMessage(voteMessage);
        boolean result=total!=-1;
        return new ResultWithIndex(Integer.valueOf(((total-1)/5))+1,new Result(
                result?ResultCode.SUCCESS:ResultCode.FAILED,result?"留言成功":"留言失败,请联系系统管理员"));
    }
    @RequestMapping(value="/updateVoteMessage",method = RequestMethod.POST)
    public @ResponseBody Result updateVoteMessage(@RequestBody VoteMessage voteMessage){
        ResultCode code=voteService.updateVoteMessage(voteMessage);
        return new Result(code,code==ResultCode.SUCCESS?"更新成功":"更新失败");
    }


    public boolean isNumber(String str){
        char[] sequence=str.toCharArray();
        for(char c:sequence)
            if(!Character.isDigit(c))
                return false;
        return true;
    }

    public static class UserVote{
        private int userId;
        private List<Integer> voteOptions;
        private int topicId;

        public UserVote() {
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<Integer> getVoteOptions() {
            return voteOptions;
        }

        public void setVoteOptions(List<Integer> voteOptions) {
            this.voteOptions = voteOptions;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        @Override
        public String toString() {
            return "UserVote{" +
                    "userId=" + userId +
                    ", voteOptions=" + voteOptions +
                    ", topicId=" + topicId +
                    '}';
        }
    }
    public static class ResultWithIndex{
        private int idx;
        Result result;

        public ResultWithIndex(int idx, Result result) {
            this.idx = idx;
            this.result = result;
        }

        public ResultWithIndex() {
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }
}
