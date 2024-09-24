package com.onetuks.threekingdomwikiserver.fixtures;

import com.onetuks.threekingdomwikiserver.application.command.event.EventAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.event.EventEditCommand;
import java.util.List;
import java.util.Random;

public class EventFixture {

  private static final Random random = new Random();

  private static final List<String> EVENT_NAMES =
      List.of("군웅할거", "적벽대전", "고평릉사변", "삼고초려", "남만정벌", "관도대첩", "횡성한우");

  public static EventAddCommand createAddCommand() {
    return new EventAddCommand(createEventName(), createHappenedYear());
  }

  public static EventEditCommand createEditCommand(String eventId) {
    return new EventEditCommand(eventId, createEventName(), createHappenedYear());
  }

  private static String createEventName() {
    return EVENT_NAMES.get(random.nextInt(EVENT_NAMES.size()));
  }

  private static Integer createHappenedYear() {
    return random.nextInt(200) + 100;
  }
}
