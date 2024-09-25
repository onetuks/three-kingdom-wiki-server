package com.onetuks.threekingdomwikiserver.application;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.onetuks.threekingdomwikiserver.ThreeKingdomWikiServerApplicationTests;
import com.onetuks.threekingdomwikiserver.application.command.location.LocationAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.location.LocationEditCommand;
import com.onetuks.threekingdomwikiserver.domain.location.Coordinate;
import com.onetuks.threekingdomwikiserver.domain.location.Location;
import com.onetuks.threekingdomwikiserver.fixtures.LocationFixture;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationServiceTest extends ThreeKingdomWikiServerApplicationTests {

  @Test
  @DisplayName("장소를 추가한다.")
  void addLocation() {
    // Given
    LocationAddCommand command = LocationFixture.createAddCommand();

    // When
    String locationId = locationService.addLocation(command);

    // Then
    assertThat(locationId).isNotNull();
  }

  @Test
  @DisplayName("장소를 수정한다.")
  void editLocation() {
    // Given
    LocationEditCommand command = LocationFixture.createEditCommand(UUID.randomUUID().toString());

    // When
    locationService.editLocation(command);

    // Then
    Location result = locationService.searchLocationById(command.locationId());

    assertAll(
        () -> assertThat(result.locationId()).isEqualTo(command.locationId()),
        () -> assertThat(result.locationName()).isEqualTo(command.locationName()),
        () ->
            assertThat(result.coordinate())
                .isEqualTo(new Coordinate(command.xRate(), command.yRate())));
  }

  @Test
  @DisplayName("장소를 조회한다.")
  void searchLocationById() {
    // Given
    String locationId = locationService.addLocation(LocationFixture.createAddCommand());

    // When
    Location location = locationService.searchLocationById(locationId);

    // Then
    assertThat(location.locationId()).isNotNull();
  }

  @Test
  @DisplayName("장소를 삭제한다.")
  void removeLocation() {
    // Given
    String locationId = locationService.addLocation(LocationFixture.createAddCommand());

    // When
    locationService.removeLocation(locationId);

    // Then
    assertThatThrownBy(() -> locationService.searchLocationById(locationId))
        .isInstanceOf(NoSuchElementException.class);
  }
}
