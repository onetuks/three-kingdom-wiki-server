package com.onetuks.threekingdomwikiserver.application.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.threekingdomwikiserver.ThreeKingdomWikiServerApplicationTests;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.fixtures.PersonFixture;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonApplicationServiceTest extends ThreeKingdomWikiServerApplicationTests {

  @Test
  @DisplayName("인물을 생성한다")
  void createPerson() {
    // Given
    Person person = PersonFixture.create(null);

    // When
    Person result = personApplicationService.createPerson(person);

    // Then
    assertThat(result.personId()).isNotNull();
  }

  @Test
  @DisplayName("인물을 수정한다")
  void editPerson() {
    // Given
    Person origin = personApplicationService.createPerson(PersonFixture.create(null));
    Person expected = PersonFixture.create(null);

    // When
    Person result = personApplicationService.editPerson(origin.personId(), expected);

    // Then
    assertAll(
        () -> assertThat(result.personId()).isEqualTo(origin.personId()),
        () -> assertThat(result.name()).isEqualTo(expected.name()),
        () -> assertThat(result.alias()).isEqualTo(expected.alias()),
        () -> assertThat(result.job()).isEqualTo(expected.job()),
        () -> assertThat(result.nation()).isEqualTo(expected.nation()),
        () -> assertThat(result.gender()).isEqualTo(expected.gender()),
        () -> assertThat(result.birthYear()).isEqualTo(expected.birthYear()),
        () -> assertThat(result.deathYear()).isEqualTo(expected.deathYear()));
  }

  @Test
  @DisplayName("인물을 조회한다")
  void searchPerson() {
    // Given
    Person person = personApplicationService.createPerson(PersonFixture.create(null));

    // When
    Person result = personApplicationService.searchPerson(person.personId());

    // Then
    assertThat(result.personId()).isEqualTo(person.personId());
  }

  @Test
  @DisplayName("인물을 삭제한다")
  void removePerson() {
    // Given
    Person person = personApplicationService.createPerson(PersonFixture.create(null));

    // When
    personApplicationService.removePerson(person.personId());

    // Then
    assertThatThrownBy(() -> personApplicationService.searchPerson(person.personId()))
        .isInstanceOf(NoSuchElementException.class);
  }
}
