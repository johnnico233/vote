import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vote.domain.vote.VoteListInfo;
;import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(classes = vote.config.SqlSessionFactoryConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MbTest {
    @Autowired
    private SqlSessionFactory factory;
    @Test
    public void test(){
        SqlSession session=factory.openSession();
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("myId",5);
            map.put("otherId",2);
            int list= session.selectOne("checkUserRelationShip",map);
            System.out.println(list);
        }finally {
            session.close();
        }
    }
}
