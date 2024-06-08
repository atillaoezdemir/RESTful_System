/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.suttondemo.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.api.models.University;
import de.fhws.fiw.fds.suttondemo.server.api.queries.QueryByUniName;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("universities")
public class UniversityJerseyService extends AbstractJerseyService {

    public UniversityJerseyService() {
        super();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllUniversities(
            @DefaultValue("") @QueryParam("uniname") final String uniName,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size) {
        try {
            return new GetAllUniversities(
                    this.serviceContext,
                    new QueryByUniName<>(uniName, offset, size)
            ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
        }
    }

    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSingleUniversity(@PathParam("id") final long id) {
        try {
            return new GetSingleUniversity(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response
                    .status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build()
            );
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSingleUniversity(final University universityModel) {
        try {
            return new PostNewUniversity(this.serviceContext, universityModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSingleUniversity(@PathParam("id") final long id, final University universityModel) {
        try {
            return new PutSingleUniversity(this.serviceContext, id, universityModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSingleUniversity(@PathParam("id") final long id) {
        try {
            return new DeleteSingleUniversity(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{universityId: \\d+}/modules")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getModulesOfUniversity(@PathParam("universityId") final long universityId) {
        try {
            return new GetAllModulesOfUniversity(this.serviceContext, universityId).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{universityId: \\d+}/modules/{moduleId: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getModuleByIdOfUniversity(@PathParam("universityId") final long universityId,
                                            @PathParam("moduleId") final long moduleId) {
        try {
            return new GetSingleModuleOfUniversity( this.serviceContext, universityId, moduleId ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Path("{universityId: \\d+}/locations")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createNewModuleOfUniversity(@PathParam("universityId") final long universityId, final Module module) {
        try {
            return new PostNewLocationOfUniversity( this.serviceContext, universityId, module).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{universityId: \\d+}/locations/{locationId: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateNewModuleOfUniversity(@PathParam("universityId") final long universityId,
                                              @PathParam("moduleId") final long moduleId, final Module module) {
        try {
            return new PutSingleLocationOfUniversity( this.serviceContext, universityId, moduleId, module).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{universityId: \\d+}/modules/{moduleId: \\d+}")
    public Response deleteLocationOfUniversity(@PathParam("universityId") final long universityId,
                                           @PathParam("moduleId") final long moduleId) {
        try {
            return new DeleteSingleLocationOfUniversity( this.serviceContext, moduleId, universityId ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

}
