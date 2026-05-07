package com.pergmaon.application.users.visitor_register_approval;

import java.util.UUID;

import com.pergamon.core.enums.ApprovalVisitorRegister;

public record VisitorRegisterApprovalCommand(UUID id, ApprovalVisitorRegister approval, String organizationperid) {
	
}
