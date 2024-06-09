package de.fhws.fiw.fds.suttondemo.server.api.states.university_modules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewModuleOfUniversity extends AbstractPostRelationState<Response, Module> {

    public PostNewModuleOfUniversity(ServiceContext serviceContext, long primaryId, Module modelToStore) {
        super(serviceContext, primaryId, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getUniversityModuleDao( ).create( this.primaryId, this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {

    }
}
