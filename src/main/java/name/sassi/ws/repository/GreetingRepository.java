package name.sassi.ws.repository;

import name.sassi.ws.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tsassi on 21/05/2016.
 */
@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
