package com.pergamon.application.organizations.delete_organization;

import java.util.UUID;

public record DeleteOrganizationCommand(String organizationPerId, UUID sAdminId) {

}
