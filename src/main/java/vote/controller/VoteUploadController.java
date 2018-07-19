package vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vote.dao.VoteDao;
import vote.domain.UploadVoteTopic;
import vote.result.ResultCode;

@Controller
@RequestMapping("/voteUpload")
public class VoteUploadController {
    @Autowired
    private VoteDao voteDao;
    @RequestMapping(method = RequestMethod.GET)
    public String getUrl(){
        return "voteUpload";
    }

    @RequestMapping(value = "/addNewTopic",method = RequestMethod.POST)
    public @ResponseBody VoteId addVoteTopic(@RequestBody UploadVoteTopic uploadVoteTopic){
        System.out.println(uploadVoteTopic);
        int id=voteDao.addNewVoteTopic(uploadVoteTopic);
        return new VoteId(id,id!=-1?ResultCode.SUCCESS:ResultCode.ADD_VOTE_ERROR);
    }
    static class VoteId{
        int id;
        ResultCode code;

        public VoteId() {
        }

        public VoteId(int id, ResultCode code) {
            this.id = id;
            this.code = code;
        }

        public ResultCode getCode() {
            return code;
        }

        public void setCode(ResultCode code) {
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
