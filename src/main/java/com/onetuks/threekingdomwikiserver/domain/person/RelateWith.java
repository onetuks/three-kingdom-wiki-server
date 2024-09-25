package com.onetuks.threekingdomwikiserver.domain.person;

import java.util.Set;

public record RelateWith(
    String relateWithId,
    Set<RelateWithType> relateWithTypes,
    String targetPersonId,
    String targetPersonName) {}
