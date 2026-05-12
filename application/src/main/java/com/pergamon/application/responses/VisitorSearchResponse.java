package com.pergamon.application.responses;

import java.util.List;
import java.util.UUID;

import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.AccountProfile;
import com.pergamon.core.enums.VisitorStatus;
import com.pergamon.core.value_objects.EMail;

public class VisitorSearchResponse {
	public List<Visitor> visitors;
}
