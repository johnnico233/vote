package vote.domain.vote;

import vote.domain.user.User;

public class VoteListInfo {
    private VoteTopic voteTopic;
    private User user;

    public VoteTopic getVoteTopic() {
        return voteTopic;
    }

    public void setVoteTopic(VoteTopic voteTopic) {
        this.voteTopic = voteTopic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "VoteListInfo{" +
                "voteTopic=" + voteTopic +
                ", user=" + user +
                '}';
    }
}
