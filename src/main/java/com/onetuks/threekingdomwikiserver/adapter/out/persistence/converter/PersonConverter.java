package com.onetuks.threekingdomwikiserver.adapter.out.persistence.converter;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.PersonNode;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

  public PersonNode toNode(Person domain) {
    return new PersonNode(
        domain.personId(),
        domain.name(),
        domain.alias(),
        domain.job(),
        domain.gender(),
        domain.nation(),
        domain.birthYear(),
        domain.deathYear());
  }

  public Person toDomain(PersonNode node) {
    return new Person(
        node.getPersonId(),
        node.getName(),
        node.getAlias(),
        node.getJob(),
        node.getGender(),
        node.getNation(),
        node.getBirthYear(),
        node.getDeathYear());
  }
}
