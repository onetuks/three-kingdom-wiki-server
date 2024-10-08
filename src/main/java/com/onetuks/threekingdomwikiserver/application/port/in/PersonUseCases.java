package com.onetuks.threekingdomwikiserver.application.port.in;

import com.onetuks.threekingdomwikiserver.application.command.person.PersonAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.PersonEditCommand;
import com.onetuks.threekingdomwikiserver.domain.person.Person;

public interface PersonUseCases {

  String addPerson(PersonAddCommand command);

  void editPerson(PersonEditCommand command);

  Person searchPersonById(String personId);

  void removePerson(String personId);
}
