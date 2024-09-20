package com.onetuks.threekingdomwikiserver.infrastructure.persistence.converter;

import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.infrastructure.persistence.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

  public PersonEntity toEntity(Person domain) {
    return new PersonEntity(
        domain.personId(),
        domain.name(),
        domain.alias(),
        domain.job(),
        domain.gender(),
        domain.nation(),
        domain.birthYear(),
        domain.deathYear());
  }

  public Person toDomain(PersonEntity entity) {
    return new Person(
        entity.getPersonId(),
        entity.getName(),
        entity.getAlias(),
        entity.getJob(),
        entity.getGender(),
        entity.getNation(),
        entity.getBirthYear(),
        entity.getDeathYear());
  }
}
