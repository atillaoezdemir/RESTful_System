package de.fhws.fiw.fds.suttondemo.server.api.states.university_modules;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import jakarta.ws.rs.core.Response;

public class GetAllModulesOfUniversity extends AbstractGetCollectionRelationState<Response, Module> {

    public GetAllModulesOfUniversity(ServiceContext serviceContext, long primaryId, AbstractRelationQuery<Response, Module> query) {
        super(serviceContext, primaryId, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniversityModuleUri.REL_PATH,
                UniversityModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);


        addLink(UniversityModuleUri.REL_PATH_SHOW_ALL,
                UniversityModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader(),
                this.primaryId);

    }
}
