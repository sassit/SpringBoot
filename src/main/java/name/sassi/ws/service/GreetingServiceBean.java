package name.sassi.ws.service;

import name.sassi.ws.model.Greeting;
import name.sassi.ws.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * Created by tsassi on 21/05/2016.
 */
@Service
public class GreetingServiceBean implements GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public Collection<Greeting> findAll() {
        return greetingRepository.findAll();
    }

    @Override
    @Cacheable(value = "greetings", key = "#id")
    public Greeting findOne(Long id) {
        return greetingRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = REQUIRED, readOnly = false)
    @CachePut(value = "greetings", key = "#result.id")
    public Greeting create(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    @Override
    @CachePut(value = "greetings", key = "#greeting.id")
    public Greeting update(Greeting greeting) {
        Greeting persisted = findOne(greeting.getId());
        if (persisted == null) return null;
        persisted.setText(greeting.getText());
        return greetingRepository.save(persisted);
    }

    @Override
    @Transactional(propagation = REQUIRED, readOnly = false)
    @CacheEvict(value = "greetings", key = "#id")
    public void delete(Long id) {
        greetingRepository.delete(id);
    }

    @CacheEvict(value = "greetings", allEntries = true)
    public void evictCache() {
    }
}
