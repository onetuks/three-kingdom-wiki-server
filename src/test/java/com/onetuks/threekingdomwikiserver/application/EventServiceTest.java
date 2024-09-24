package com.onetuks.threekingdomwikiserver.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.onetuks.threekingdomwikiserver.ThreeKingdomWikiServerApplicationTests;
import com.onetuks.threekingdomwikiserver.application.command.event.EventAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.event.EventEditCommand;
import com.onetuks.threekingdomwikiserver.domain.event.Event;
import com.onetuks.threekingdomwikiserver.fixtures.EventFixture;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest extends ThreeKingdomWikiServerApplicationTests {

  @Test
  @DisplayName("사건을 추가한다.")
  void addEvent() {
    // Given
    EventAddCommand command = EventFixture.createAddCommand();

    // When
    String eventId = eventService.addEvent(command);

    // Then
    assertThat(eventId).isNotNull();
  }

  @Test
  @DisplayName("사건을 수정한다.")
  void editEvent() {
    // Given
    EventEditCommand command = EventFixture.createEditCommand(UUID.randomUUID().toString());

    // When
    eventService.editEvent(command);

    // Then
    Event result = eventService.searchEventById(command.eventId());

    assertAll(
        () -> assertThat(result.eventId()).isEqualTo(command.eventId()),
        () -> assertThat(result.eventName()).isEqualTo(command.eventName()),
        () -> assertThat(result.happenedYear()).isEqualTo(command.happenedYear()));
  }

  @Test
  @DisplayName("사건을 단건 조회한다.")
  void searchEventById() {
    // Given
    String eventId = eventService.addEvent(EventFixture.createAddCommand());

    // When
    Event result = eventService.searchEventById(eventId);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("존재하지 않는 아이디로 사건 조회 시 예외를 던진다.")
  void searchEventById_WithInvalidId_Exception() {
    // Given
    String invalidEventId = "invalid";

    // When & Then
    assertThatThrownBy(() -> eventService.searchEventById(invalidEventId))
        .isInstanceOf(NoSuchElementException.class);
  }

  @Test
  @DisplayName("사건을 삭제한다.")
  void removeEvent() {
    // Given
    String eventId = eventService.addEvent(EventFixture.createAddCommand());

    // When
    eventService.removeEvent(eventId);

    // Then
    assertThatThrownBy(() -> eventService.searchEventById(eventId))
        .isInstanceOf(NoSuchElementException.class);
  }
}
