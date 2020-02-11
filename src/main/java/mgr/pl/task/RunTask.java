package mgr.pl.task;

import mgr.pl.service.GiosAgregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RunTask {

    @Autowired
    private GiosAgregator giosAgregator;


    @Scheduled(cron = "0 0 3 1/1 * ?")
    public void loadFile(){
        giosAgregator.createContentFile();
    }
}
