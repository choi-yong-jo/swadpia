package kr.co.swadpia;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableSchedulerLock(defaultLockAtMostFor = "30s")
@EnableScheduling
@EnableJpaRepositories(basePackages = {"kr.co.swadpia.repository"})

public class SwAdpiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwAdpiaApplication.class, args);
    }

}
