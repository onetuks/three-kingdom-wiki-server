package com.onetuks.threekingdomwikiserver.application;

import com.onetuks.threekingdomwikiserver.application.command.person.PersonAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonEditCommand;
import com.onetuks.threekingdomwikiserver.application.port.in.PersonUseCases;
import com.onetuks.threekingdomwikiserver.application.port.out.PersonPort;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonUseCases {

  private final PersonPort personPort;

  public PersonService(PersonPort personPort) {
    this.personPort = personPort;
  }

  @Override
  public String addPerson(PersonAddCommand command) {
    return personPort
        .create(
            new Person(
                null,
                command.name(),
                command.alias(),
                command.job(),
                command.gender(),
                command.nation(),
                command.birthYear(),
                command.deathYear()))
        .personId();
  }

  @Override
  public void editPerson(PersonEditCommand command) {
    personPort.update(
        new Person(
            command.personId(),
            command.name(),
            command.alias(),
            command.job(),
            command.gender(),
            command.nation(),
            command.birthYear(),
            command.deathYear()));
  }

  @Override
  public Person searchPersonById(String personId) {
    return personPort.readById(personId);
  }

  @Override
  public void removePerson(String personId) {
    personPort.delete(personId);
  }
}
