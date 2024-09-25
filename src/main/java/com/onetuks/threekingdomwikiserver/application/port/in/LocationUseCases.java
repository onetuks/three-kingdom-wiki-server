package com.onetuks.threekingdomwikiserver.application.port.in;

import com.onetuks.threekingdomwikiserver.application.command.location.LocationAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.location.LocationEditCommand;
import com.onetuks.threekingdomwikiserver.domain.location.Location;

public interface LocationUseCases {

  String addLocation(LocationAddCommand command);

  void editLocation(LocationEditCommand command);

  Location searchLocationById(String locationId);

  void removeLocation(String locationId);
}
