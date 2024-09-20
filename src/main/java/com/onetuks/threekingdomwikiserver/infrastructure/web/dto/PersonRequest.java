package com.onetuks.threekingdomwikiserver.infrastructure.web.dto;

import com.onetuks.threekingdomwikiserver.common.type.Gender;
import com.onetuks.threekingdomwikiserver.common.type.Job;
import com.onetuks.threekingdomwikiserver.common.type.Nation;
import com.onetuks.threekingdomwikiserver.domain.person.Person;

public record PersonRequest(
    String name,
    String alias,
    Job job,
    Gender gender,
    Nation nation,
    Integer birthYear,
    Integer deathYear) {

  public Person toDomain() {
    return new Person(null, name(), alias(), job(), gender(), nation(), birthYear(), deathYear());
  }
}
