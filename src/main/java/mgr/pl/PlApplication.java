package mgr.pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlApplication.class, args);
    }

}
