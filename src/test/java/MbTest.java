import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vote.domain.PrivateUser;

import java.util.List;

@ContextConfiguration(classes = vote.config.SqlSessionFactoryConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MbTest {
    @Autowired
    private SqlSessionFactory factory;
    @Test
    public void test(){
        SqlSession session=factory.openSession();
        try{
            List<PrivateUser> users=session.selectList("selectAllUser");
            for(PrivateUser user:users)
                System.out.println(user);
        }finally {
            session.close();
        }
    }
}