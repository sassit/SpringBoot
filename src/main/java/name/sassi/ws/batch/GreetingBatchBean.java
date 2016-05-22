package name.sassi.ws.batch;

import name.sassi.ws.model.Greeting;
import name.sassi.ws.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by tsassi on 22/05/2016.
 */
@Profile("batch")
@Component
public class GreetingBatchBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GreetingService greetingService;

    @Scheduled(cron = "${batch.greeting.cron}")
    public void cronJob() {
        logger.info("> cronJob");
        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the datastore.", greetings.size());
        logger.info("< cronJob");
    }
}
