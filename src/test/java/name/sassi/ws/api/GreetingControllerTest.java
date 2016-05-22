package name.sassi.ws.api;

import name.sassi.ws.AbstractControllerTest;
import name.sassi.ws.service.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Created by tsassi on 22/05/2016.
 */
@Transactional
public class GreetingControllerTest extends AbstractControllerTest {
    @Autowired
    private GreetingService greetingService;

    @Before
    public void setup() {
        super.setup();
        greetingService.evictCache();
    }

    @Test
    public void shouldGetGreeting() throws Exception {
        String uri = "/api/greetings";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .accept(APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        int status = response.getStatus();
        assertThat(status, is(200));
        assertThat(content, not(isEmptyString()));
    }
}
