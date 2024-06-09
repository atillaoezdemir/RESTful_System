package de.fhws.fiw.fds.suttondemo.server.api.states.university_modules;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PutSingleModuleOfUniversity extends AbstractPutRelationState<Response, Module> {

    public PutSingleModuleOfUniversity(ServiceContext serviceContext, long primaryId, long requestedId, Module modelToUpdate) {
        super(serviceContext, primaryId, requestedId, modelToUpdate);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override
    protected boolean clientDoesNotKnowCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected SingleModelResult<Module> loadModel() {
        return DaoFactory.getInstance().getModuleDao().readById(this.requestedId);
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getUniversityModuleDao().update(this.primaryId, this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniversityModuleUri.REL_PATH_ID,
                UniversityModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId, this.requestedId);
    }
}
