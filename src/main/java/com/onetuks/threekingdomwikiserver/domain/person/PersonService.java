package com.onetuks.threekingdomwikiserver.domain.person;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

  public Person changePersonDetails(long personId, Person person) {
    return new Person(
        personId,
        person.name(),
        person.alias(),
        person.job(),
        person.gender(),
        person.nation(),
        person.birthYear(),
        person.deathYear());
  }
}
