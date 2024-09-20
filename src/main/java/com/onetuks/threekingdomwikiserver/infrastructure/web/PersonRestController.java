package com.onetuks.threekingdomwikiserver.infrastructure.web;

import com.onetuks.threekingdomwikiserver.application.person.PersonApplicationService;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.infrastructure.web.dto.PersonRequest;
import com.onetuks.threekingdomwikiserver.infrastructure.web.dto.PersonResponse;
import org.springframework.http.HttpStatus;
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

  private final PersonApplicationService personApplicationService;

  public PersonRestController(PersonApplicationService personApplicationService) {
    this.personApplicationService = personApplicationService;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonResponse> postNewPerson(@RequestBody PersonRequest request) {
    Person result = personApplicationService.createPerson(request.toDomain());
    PersonResponse response = PersonResponse.from(result);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(
      path = "/{person-id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonResponse> patchPerson(
      @PathVariable("person-id") Long personId, @RequestBody PersonRequest request) {
    Person result = personApplicationService.editPerson(personId, request.toDomain());
    PersonResponse response = PersonResponse.from(result);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping(path = "/{person-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonResponse> getPerson(@PathVariable("person-id") Long personId) {
    Person result = personApplicationService.searchPerson(personId);
    PersonResponse response = PersonResponse.from(result);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping(path = "/{person-id}")
  public ResponseEntity<Void> deletePerson(@PathVariable("person-id") Long personId) {
    personApplicationService.removePerson(personId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
