package com.pergamon.application.users.teacher_register;

import java.util.UUID;

import com.pergamon.core.enums.ApprovalVisitorRegister;

public record TeacherRegisterCommand(String name,
		String lastname,
		String password,
		String email,
		String organizationperid) {

}
