package com.onetuks.threekingdomwikiserver.domain.person;

import com.onetuks.threekingdomwikiserver.common.type.Gender;
import com.onetuks.threekingdomwikiserver.common.type.Job;
import com.onetuks.threekingdomwikiserver.common.type.Nation;

public record Person(
    Long personId,
    String name,
    String alias,
    Job job,
    Gender gender,
    Nation nation,
    Integer birthYear,
    Integer deathYear
    // todo NoSQL 적용 시 주석 해제
    //    Long documentId,
    //    Long documentVersion
    ) {}
