package com.pergamon.application.organizations.add_organization;

import java.util.UUID;

public record AddOrganizationCommand(String organizationName, UUID gAdminId) {

}
