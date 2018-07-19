package vote.domain;

public class TopicOption {
    private String name;
    private int id;
    private int count;
    private int topicId;

    public TopicOption() {
    }

    public TopicOption(String name, int id, int count, int topicId) {
        this.name = name;
        this.id = id;
        this.count = count;
        this.topicId = topicId;
    }

    public TopicOption(String name, int topicId) {
        this.name = name;
        this.topicId = topicId;
        count=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "TopicOption{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", count=" + count +
                ", topicId=" + topicId +
                '}';
    }
}
