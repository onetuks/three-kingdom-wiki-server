package com.onetuks.threekingdomwikiserver.application.port.in;

import com.onetuks.threekingdomwikiserver.application.command.person.RelateWithAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.person.RelateWithSubCommand;

public interface RelateWithUseCases {

  void addRelateWith(RelateWithAddCommand command);

  void subRelateWith(RelateWithSubCommand command);
}
