package com.onetuks.threekingdomwikiserver.infrastructure.web.dto;

import com.onetuks.threekingdomwikiserver.common.type.Gender;
import com.onetuks.threekingdomwikiserver.common.type.Job;
import com.onetuks.threekingdomwikiserver.common.type.Nation;
import com.onetuks.threekingdomwikiserver.domain.person.Person;

public record PersonResponse(
    long personId,
    String name,
    String alias,
    Job job,
    Gender gender,
    Nation nation,
    int birthYear,
    int deathYear) {

  public static PersonResponse from(Person domain) {
    return new PersonResponse(
        domain.personId(),
        domain.name(),
        domain.alias(),
        domain.job(),
        domain.gender(),
        domain.nation(),
        domain.birthYear(),
        domain.deathYear());
  }
}
