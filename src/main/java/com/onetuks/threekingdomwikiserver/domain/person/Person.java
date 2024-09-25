package com.onetuks.threekingdomwikiserver.domain.person;

import java.util.Objects;
import java.util.Set;

public record Person(
    String personId,
    String name,
    String alias,
    Job job,
    Gender gender,
    Nation nation,
    Integer birthYear,
    Integer deathYear,
    Set<RelateWith> relateWiths) {

  public Person changePersonInfo(
      String name,
      String alias,
      Job job,
      Gender gender,
      Nation nation,
      Integer birthYear,
      Integer deathYear) {
    return new Person(
        this.personId(),
        name,
        alias,
        job,
        gender,
        nation,
        birthYear,
        deathYear,
        this.relateWiths());
  }

  public Person addRelateWithTypes(Person targetPerson, Set<RelateWithType> relateWithTypes) {
    this.relateWiths.stream()
        .filter(relateWith -> Objects.equals(relateWith.targetPersonId(), targetPerson.personId()))
        .peek(relateWith -> relateWith.relateWithTypes().addAll(relateWithTypes))
        .findAny()
        .orElseGet(
            () -> {
              RelateWith relateWith =
                  new RelateWith(
                      this.personId(),
                      relateWithTypes,
                      targetPerson.personId(),
                      targetPerson.name());
              this.relateWiths().add(relateWith);
              return relateWith;
            });

    return new Person(
        personId(),
        name(),
        alias(),
        job(),
        gender(),
        nation(),
        birthYear(),
        deathYear(),
        relateWiths());
  }

  public Person subRelateWithTypes(Person targetPerson, Set<RelateWithType> relateWithTypes) {
    this.relateWiths().stream()
        .filter(relateWith -> Objects.equals(relateWith.targetPersonId(), targetPerson.personId()))
        .peek(relateWith -> relateWith.relateWithTypes().removeAll(relateWithTypes))
        .filter(relateWith -> relateWith.relateWithTypes().isEmpty())
        .forEach(relateWith -> this.relateWiths().remove(relateWith));

    return new Person(
        personId(),
        name(),
        alias(),
        job(),
        gender(),
        nation(),
        birthYear(),
        deathYear(),
        relateWiths());
  }
}
