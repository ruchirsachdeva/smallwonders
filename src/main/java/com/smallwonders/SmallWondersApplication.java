package com.smallwonders;

import com.smallwonders.config.RepositoryRestConfig;
import com.smallwonders.config.SecurityConfig;
import com.smallwonders.config.WebConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmallWondersApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SmallWondersApplication.class, WebConfig.class, SecurityConfig.class, RepositoryRestConfig.class).run(args);
    }

}
