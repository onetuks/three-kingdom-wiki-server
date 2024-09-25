package com.onetuks.threekingdomwikiserver.adapter.in.web;

import com.onetuks.threekingdomwikiserver.application.command.location.LocationAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.location.LocationEditCommand;
import com.onetuks.threekingdomwikiserver.application.port.in.LocationUseCases;
import com.onetuks.threekingdomwikiserver.domain.location.Location;
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
@RequestMapping(path = "/api/locations")
public class LocationRestController {

  private final LocationUseCases locationUseCases;

  public LocationRestController(LocationUseCases locationUseCases) {
    this.locationUseCases = locationUseCases;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewLocation(@RequestBody LocationAddCommand command) {
    String locationId = locationUseCases.addLocation(command);

    return ResponseEntity.created(URI.create("/api/locations/" + locationId)).build();
  }

  @PatchMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchLocation(@RequestBody LocationEditCommand command) {
    locationUseCases.editLocation(command);

    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/{location-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Location> getLocation(@PathVariable("location-id") String locationId) {
    Location location = locationUseCases.searchLocationById(locationId);

    return ResponseEntity.ok(location);
  }

  @DeleteMapping(path = "/{location-id}")
  public ResponseEntity<Void> deleteLocation(@PathVariable("location-id") String locationId) {
    locationUseCases.removeLocation(locationId);

    return ResponseEntity.noContent().build();
  }
}
