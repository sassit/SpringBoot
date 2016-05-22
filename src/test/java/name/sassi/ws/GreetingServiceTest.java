package name.sassi.ws;

import name.sassi.ws.model.Greeting;
import name.sassi.ws.service.GreetingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by tsassi on 22/05/2016.
 */
@Transactional
public class GreetingServiceTest extends AbstractTest {
    @Autowired
    private GreetingService greetingService;

    @Before
    public void setup() {
        greetingService.evictCache();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void shouldFindAll() {
        Collection<Greeting> list = greetingService.findAll();
        assertThat(list, not(is(nullValue())));
        assertThat(list, hasSize(2));
    }
}
