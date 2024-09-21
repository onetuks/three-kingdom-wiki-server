package com.onetuks.threekingdomwikiserver.adapter.in.web;

import com.onetuks.threekingdomwikiserver.application.command.PersonAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.PersonEditCommand;
import com.onetuks.threekingdomwikiserver.application.port.in.PersonUseCases;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import jakarta.validation.Valid;
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
@RequestMapping(path = "/api/person")
public class PersonRestController {

  private final PersonUseCases personUseCases;

  public PersonRestController(PersonUseCases personUseCases) {
    this.personUseCases = personUseCases;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewPerson(@Valid @RequestBody PersonAddCommand command) {
    String personId = personUseCases.addPerson(command);

    return ResponseEntity.created(URI.create("/api/person/" + personId)).build();
  }

  @PatchMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchPerson(@Valid @RequestBody PersonEditCommand command) {
    personUseCases.editPerson(command);

    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/{person-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> getPerson(@PathVariable("person-id") String personId) {
    Person person = personUseCases.searchPersonById(personId);

    return ResponseEntity.ok(person);
  }

  @DeleteMapping(path = "/{person-id}")
  public ResponseEntity<Void> deletePerson(@PathVariable("person-id") String personId) {
    personUseCases.removePerson(personId);

    return ResponseEntity.noContent().build();
  }
}
