package com.onetuks.threekingdomwikiserver.domain.person;

public record Person(
    String personId,
    String name,
    String alias,
    Job job,
    Gender gender,
    Nation nation,
    Integer birthYear,
    Integer deathYear) {}
