package com.onetuks.threekingdomwikiserver.adapter.in.web;

import com.onetuks.threekingdomwikiserver.application.command.event.EventAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.event.EventEditCommand;
import com.onetuks.threekingdomwikiserver.application.port.in.EventUseCases;
import com.onetuks.threekingdomwikiserver.domain.event.Event;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/events")
public class EventRestController {

  private final EventUseCases eventUseCases;

  public EventRestController(EventUseCases eventUseCases) {
    this.eventUseCases = eventUseCases;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewEvent(@RequestBody EventAddCommand command) {
    String eventId = eventUseCases.addEvent(command);

    return ResponseEntity.created(URI.create("/api/events/" + eventId)).build();
  }

  @PatchMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchEvent(@RequestBody EventEditCommand command) {
    eventUseCases.editEvent(command);

    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/{event-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Event> getEvent(@PathVariable("event-id") String eventId) {
    Event event = eventUseCases.searchEventById(eventId);

    return ResponseEntity.ok(event);
  }

  @DeleteMapping(path = "/{event-id}")
  public ResponseEntity<Void> deleteEvent(@PathVariable("event-id") String eventId) {
    eventUseCases.removeEvent(eventId);

    return ResponseEntity.noContent().build();
  }
}
