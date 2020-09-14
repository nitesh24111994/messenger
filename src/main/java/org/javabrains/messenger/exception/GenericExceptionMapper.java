package org.javabrains.messenger.exception;

import org.javabrains.messenger.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),500,"http://javabrains.koushik.org");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				       .entity(errorMessage)
				       .build();
	}
     
}
