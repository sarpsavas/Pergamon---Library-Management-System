package com.pergamon.application.barroweds.take_barrowed;

import java.util.UUID;

public record TakeBarrowedCommand(
		UUID defaultAdminId, 
		UUID visitorId, 
		String organizationPerId,
		UUID barrowedId) {

}
