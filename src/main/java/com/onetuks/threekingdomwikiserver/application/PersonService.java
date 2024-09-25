package com.onetuks.threekingdomwikiserver.application;

import com.onetuks.threekingdomwikiserver.application.command.person.PersonAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonEditCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.RelateWithAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.RelateWithSubCommand;
import com.onetuks.threekingdomwikiserver.application.port.in.PersonUseCases;
import com.onetuks.threekingdomwikiserver.application.port.in.RelateWithUseCases;
import com.onetuks.threekingdomwikiserver.application.port.out.PersonPort;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonUseCases, RelateWithUseCases {

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
                command.deathYear(),
                Set.of()))
        .personId();
  }

  @Override
  public void editPerson(PersonEditCommand command) {
    personPort.update(
        personPort
            .readById(command.personId())
            .changePersonInfo(
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

  @Override
  public void addRelateWith(RelateWithAddCommand command) {
    Person sourcePerson = personPort.readById(command.sourcePersonId());
    Person targetPerson = personPort.readById(command.targetPersonId());

    personPort.update(sourcePerson.addRelateWithTypes(targetPerson, command.relateWithTypes()));
  }

  @Override
  public void subRelateWith(RelateWithSubCommand command) {
    Person sourcePerson = personPort.readById(command.sourcePersonId());
    Person targetPerson = personPort.readById(command.targetPersonId());

    personPort.update(sourcePerson.subRelateWithTypes(targetPerson, command.relateWithTypes()));
  }
}
