package com.onetuks.threekingdomwikiserver.application.port.out;

import com.onetuks.threekingdomwikiserver.domain.person.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPort {

  Person create(Person person);

  Person readById(String personId);

  void update(Person person);

  void delete(String personId);
}
