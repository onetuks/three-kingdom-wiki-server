package com.onetuks.threekingdomwikiserver.fixtures;

import com.onetuks.threekingdomwikiserver.common.type.Gender;
import com.onetuks.threekingdomwikiserver.common.type.Job;
import com.onetuks.threekingdomwikiserver.common.type.Nation;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import java.util.List;
import java.util.Random;

public class PersonFixture {

  private static final Random random = new Random();
  private static final List<String> NAMES = List.of("유비", "관우", "장비", "제갈량", "조조", "사마의");
  private static final List<String> ALIASES = List.of("현덕", "운장", "익덕", "와룡", "맹덕", "뭐였더라");

  public static Person create(Long personId) {
    return new Person(
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
