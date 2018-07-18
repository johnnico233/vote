package vote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public ThymeleafViewResolver resolver(TemplateEngine engine){
        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(engine);
        resolver.addStaticVariable("local","vote");
        return resolver;
    }

    @Bean
    public TemplateEngine templateEngine(SpringResourceTemplateResolver resourceTemplateResolver){
        SpringTemplateEngine engine=new SpringTemplateEngine();
        engine.setTemplateResolver(resourceTemplateResolver);
        engine.setEnableSpringELCompiler(true);
        return engine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("html5");
        templateResolver.setCharacterEncoding("utf-8");
        return templateResolver;
    }
}
