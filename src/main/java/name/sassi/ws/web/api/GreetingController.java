package name.sassi.ws.web.api;

import name.sassi.ws.model.Greeting;
import name.sassi.ws.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by tsassi on 21/05/2016.
 */
@RestController
public class GreetingController extends BaseController {
    @Autowired
    private GreetingService greetingService;

    @RequestMapping(value = "api/greetings", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        Greeting saveGreeting = greetingService.create(greeting);
        return new ResponseEntity<Greeting>(saveGreeting, OK);
    }

    @RequestMapping(value = "api/greetings", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings() {
        Collection<Greeting> greetings = greetingService.findAll();
        return new ResponseEntity<Collection<Greeting>>(greetings, OK);
    }

    @RequestMapping(value = "api/greetings/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
        Greeting greeting = greetingService.findOne(id);
        if (greeting == null) {
            return new ResponseEntity<Greeting>(NOT_FOUND);
        }
        return new ResponseEntity<Greeting>(greeting, OK);
    }

    @RequestMapping(value = "api/greetings/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
        Greeting update = greetingService.update(greeting);
        if (update == null) {
            return new ResponseEntity<Greeting>(INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Greeting>(update, OK);
    }

    @RequestMapping(value = "api/greetings/{id}", method = DELETE)
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id) {
        greetingService.delete(id);
        return new ResponseEntity<Greeting>(NO_CONTENT);
    }
}
