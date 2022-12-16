package com.signomix.jobs.application.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ServiceException> {

    @ConfigProperty(name = "com.signomix.jobs.exception.user.not.found")
    String userNotFound;
    @ConfigProperty(name = "com.signomix.jobs.exception.organization.not.found")
    String organizationNotFound;

    @Override
    public Response toResponse(ServiceException e) {

        if (e.getMessage().equalsIgnoreCase(userNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage(e.getMessage()))
                    .build();
        }else if (e.getMessage().equalsIgnoreCase(organizationNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage(e.getMessage()))
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(e.getMessage()))
                    .build();
        }
    }
}
