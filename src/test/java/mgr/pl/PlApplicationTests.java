package mgr.pl;

import mgr.pl.service.AirPollutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class PlApplicationTests {

    @Autowired
    private AirPollutionService airPollutionService;

    @Test
    void contextLoads() {
        airPollutionService.sendEmail();
    }

}
