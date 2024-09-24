package com.onetuks.threekingdomwikiserver.application.command.person;

import com.onetuks.threekingdomwikiserver.domain.person.Gender;
import com.onetuks.threekingdomwikiserver.domain.person.Job;
import com.onetuks.threekingdomwikiserver.domain.person.Nation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PersonAddCommand(
    @NotBlank String name,
    String alias,
    @NotNull Job job,
    @NotNull Gender gender,
    @NotNull Nation nation,
    @Positive @Min(100) @Max(300) Integer birthYear,
    @Positive @Min(100) @Max(300) Integer deathYear) {}
