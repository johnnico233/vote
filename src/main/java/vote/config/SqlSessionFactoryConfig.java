package vote.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class SqlSessionFactoryConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactory factory=null;
        try{
            factory=new SqlSessionFactoryBuilder().build(new ClassPathResource("db/MyBatisConfig.xml").getInputStream());
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return factory;
    }
}
