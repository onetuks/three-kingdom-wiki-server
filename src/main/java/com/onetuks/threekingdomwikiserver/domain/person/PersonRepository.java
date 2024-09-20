package com.onetuks.threekingdomwikiserver.domain.person;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository {

  Person create(Person person);

  Person read(Long personId);

  Person update(Person person);

  void delete(Long personId);
}
