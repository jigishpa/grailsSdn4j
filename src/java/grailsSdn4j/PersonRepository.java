// PersonRepository.java
package grailsSdn4j;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {    
    Person findByName(String name);    
}
