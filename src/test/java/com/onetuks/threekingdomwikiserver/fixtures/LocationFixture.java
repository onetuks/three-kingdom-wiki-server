package com.onetuks.threekingdomwikiserver.fixtures;

import com.onetuks.threekingdomwikiserver.application.command.location.LocationAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.location.LocationEditCommand;
import java.util.List;
import java.util.Random;

public class LocationFixture {

  private static final Random random = new Random();

  private static final List<String> LOCATION_NAMES =
      List.of("장안", "낙양", "적벽", "호로관", "관도", "고평릉", "익주", "서주", "남만", "한수", "오장원");

  public static LocationAddCommand createAddCommand() {
    return new LocationAddCommand(createLocationName(), random.nextDouble(1), random.nextDouble(1));
  }

  public static LocationEditCommand createEditCommand(String locationId) {
    return new LocationEditCommand(
        locationId, createLocationName(), random.nextDouble(1), random.nextDouble(1));
  }

  private static String createLocationName() {
    return LOCATION_NAMES.get(random.nextInt(LOCATION_NAMES.size()));
  }
}
