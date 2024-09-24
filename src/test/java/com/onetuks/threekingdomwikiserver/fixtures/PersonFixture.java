package com.onetuks.threekingdomwikiserver.fixtures;

import com.onetuks.threekingdomwikiserver.application.command.person.PersonAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonEditCommand;
import com.onetuks.threekingdomwikiserver.domain.person.Gender;
import com.onetuks.threekingdomwikiserver.domain.person.Job;
import com.onetuks.threekingdomwikiserver.domain.person.Nation;
import java.util.List;
import java.util.Random;

public class PersonFixture {

  private static final Random random = new Random();
  private static final List<String> NAMES = List.of("유비", "관우", "장비", "제갈량", "조조", "사마의");
  private static final List<String> ALIASES = List.of("현덕", "운장", "익덕", "와룡", "맹덕", "뭐였더라");

  public static PersonAddCommand createAddCommand() {
    return new PersonAddCommand(
        createName(),
        createAlias(),
        createJob(),
        createGender(),
        createNation(),
        createBirthYear(),
        createDeathYear());
  }

  public static PersonEditCommand createEditCommand(String personId) {
    return new PersonEditCommand(
        personId,
        createName(),
        createAlias(),
        createJob(),
        createGender(),
        createNation(),
        createBirthYear(),
        createDeathYear());
  }

  private static String createName() {
    return NAMES.get(random.nextInt(NAMES.size()));
  }

  private static String createAlias() {
    return ALIASES.get(random.nextInt(ALIASES.size()));
  }

  private static Job createJob() {
    return Job.values()[random.nextInt(Job.values().length)];
  }

  private static Gender createGender() {
    return Gender.values()[random.nextInt(Gender.values().length)];
  }

  private static Nation createNation() {
    return Nation.values()[random.nextInt(Nation.values().length)];
  }

  private static Integer createBirthYear() {
    return random.nextInt(100) + 100;
  }

  private static Integer createDeathYear() {
    return random.nextInt(200) + 100;
  }
}
