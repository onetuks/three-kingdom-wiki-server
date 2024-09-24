package com.onetuks.threekingdomwikiserver.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.threekingdomwikiserver.ThreeKingdomWikiServerApplicationTests;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonEditCommand;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.fixtures.PersonFixture;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonServiceTest extends ThreeKingdomWikiServerApplicationTests {

  @Test
  @DisplayName("인물을 생성한다.")
  void createPerson() {
    // Given
    PersonAddCommand command = PersonFixture.createAddCommand();

    // When
    String personId = personService.addPerson(command);

    // Then
    assertThat(personId).isNotNull();
  }

  @Test
  @DisplayName("인물을 수정한다.")
  void editPerson() {
    // Given
    String personId = personService.addPerson(PersonFixture.createAddCommand());
    PersonEditCommand command = PersonFixture.createEditCommand(personId);

    // When
    personService.editPerson(command);

    // Then
    Person result = personService.searchPersonById(personId);

    assertAll(
        () -> assertThat(result.personId()).isEqualTo(command.personId()),
        () -> assertThat(result.name()).isEqualTo(command.name()),
        () -> assertThat(result.alias()).isEqualTo(command.alias()),
        () -> assertThat(result.job()).isEqualTo(command.job()),
        () -> assertThat(result.nation()).isEqualTo(command.nation()),
        () -> assertThat(result.gender()).isEqualTo(command.gender()),
        () -> assertThat(result.birthYear()).isEqualTo(command.birthYear()),
        () -> assertThat(result.deathYear()).isEqualTo(command.deathYear()));
  }

  @Test
  @DisplayName("인물을 조회한다.")
  void searchPerson() {
    // Given
    String personId = personService.addPerson(PersonFixture.createAddCommand());

    // When
    Person result = personService.searchPersonById(personId);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("인물을 삭제한다.")
  void removePerson() {
    // Given
    String personId = personService.addPerson(PersonFixture.createAddCommand());

    // When
    personService.removePerson(personId);

    // Then
    assertThatThrownBy(() -> personService.searchPersonById(personId))
        .isInstanceOf(NoSuchElementException.class);
  }
}
