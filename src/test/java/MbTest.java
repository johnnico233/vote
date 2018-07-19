import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;;
import vote.domain.PrivateUser;
import vote.domain.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
