package com.onetuks.threekingdomwikiserver.application;

import static com.onetuks.threekingdomwikiserver.domain.person.RelateWithType.COLLEAGUE;
import static com.onetuks.threekingdomwikiserver.domain.person.RelateWithType.FRIEND;
import static com.onetuks.threekingdomwikiserver.domain.person.RelateWithType.SIBLING;
import static com.onetuks.threekingdomwikiserver.domain.person.RelateWithType.SPOUSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.threekingdomwikiserver.ThreeKingdomWikiServerApplicationTests;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonEditCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.RelateWithAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.RelateWithSubCommand;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.domain.person.RelateWithType;
import com.onetuks.threekingdomwikiserver.fixtures.PersonFixture;
import java.util.NoSuchElementException;
import java.util.Set;
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

  @Test
  @DisplayName("인물 간 관계를 추가한다.")
  void addRelateWith() {
    // Given
    String sourcePersonId = personService.addPerson(PersonFixture.createAddCommand());
    String targetPersonId = personService.addPerson(PersonFixture.createAddCommand());

    RelateWithAddCommand spouseCommand =
        new RelateWithAddCommand(sourcePersonId, targetPersonId, Set.of(SPOUSE));
    RelateWithAddCommand friendCommand =
        new RelateWithAddCommand(sourcePersonId, targetPersonId, Set.of(RelateWithType.FRIEND));

    // When
    personService.addRelateWith(spouseCommand);
    personService.addRelateWith(friendCommand);

    // Then
    Person result = personService.searchPersonById(sourcePersonId);

    assertThat(result.relateWiths())
        .hasSize(1)
        .allSatisfy(
            relateWith ->
                assertThat(relateWith.relateWithTypes()).contains(SPOUSE, RelateWithType.FRIEND));
  }

  @Test
  @DisplayName("인물 간 관계타입을 삭제한다.")
  void subRelateWith() {
    // Given
    String sourcePersonId = personService.addPerson(PersonFixture.createAddCommand());
    String targetPersonId = personService.addPerson(PersonFixture.createAddCommand());

    personService.addRelateWith(
        new RelateWithAddCommand(
            sourcePersonId, targetPersonId, Set.of(SPOUSE, COLLEAGUE, FRIEND)));

    RelateWithSubCommand command =
        new RelateWithSubCommand(sourcePersonId, targetPersonId, Set.of(SPOUSE, SIBLING));

    // When
    personService.subRelateWith(command);

    // Then
    Person result = personService.searchPersonById(sourcePersonId);

    assertThat(result.relateWiths())
        .hasSize(1)
        .allSatisfy(
            relateWith ->
                assertThat(relateWith.relateWithTypes())
                    .contains(COLLEAGUE, FRIEND)
                    .doesNotContain(SPOUSE, SIBLING));
  }
}
