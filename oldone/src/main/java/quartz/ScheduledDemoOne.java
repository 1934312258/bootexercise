package quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledDemoOne {
    @Scheduled(cron = "0 0/1 * * * ?")
    public void schedule() {
        System.out.println(new Date());
    }
}
