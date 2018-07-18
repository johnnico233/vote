package vote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vote.dao.*;

@Configuration
public class DaoConfig {
    @Bean
    public UserDao getUserDao(){
        return new UserDao();
    }
}
