package com.onetuks.threekingdomwikiserver.application.command.person;

import com.onetuks.threekingdomwikiserver.domain.person.RelateWithType;
import java.util.Set;

public record RelateWithSubCommand(
    String sourcePersonId, String targetPersonId, Set<RelateWithType> relateWithTypes) {}
