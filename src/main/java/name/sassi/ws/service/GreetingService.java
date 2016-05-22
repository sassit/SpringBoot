package name.sassi.ws.service;

import name.sassi.ws.model.Greeting;

import java.util.Collection;

/**
 * Created by tsassi on 21/05/2016.
 */
public interface GreetingService {
    Collection<Greeting> findAll();

    Greeting findOne(Long id);

    Greeting create(Greeting greeting);

    Greeting update(Greeting greeting);

    void delete(Long id);
}
