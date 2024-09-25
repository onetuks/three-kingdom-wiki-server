package com.onetuks.threekingdomwikiserver.application;

import com.onetuks.threekingdomwikiserver.application.command.location.LocationAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.location.LocationEditCommand;
import com.onetuks.threekingdomwikiserver.application.port.in.LocationUseCases;
import com.onetuks.threekingdomwikiserver.application.port.out.LocationPort;
import com.onetuks.threekingdomwikiserver.domain.location.Coordinate;
import com.onetuks.threekingdomwikiserver.domain.location.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationService implements LocationUseCases {

  private final LocationPort locationPort;

  public LocationService(LocationPort locationPort) {
    this.locationPort = locationPort;
  }

  @Override
  public String addLocation(LocationAddCommand command) {
    return locationPort.create(
        new Location(
            null, command.locationName(), new Coordinate(command.xRate(), command.yRate())));
  }

  @Override
  public void editLocation(LocationEditCommand command) {
    locationPort.update(
        new Location(
            command.locationId(),
            command.locationName(),
            new Coordinate(command.xRate(), command.yRate())));
  }

  @Override
  public Location searchLocationById(String locationId) {
    return locationPort.readById(locationId);
  }

  @Override
  public void removeLocation(String locationId) {
    locationPort.delete(locationId);
  }
}
