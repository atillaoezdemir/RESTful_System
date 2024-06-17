/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.suttondemo.server.api.states.universities;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.University;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class DeleteSingleUniversity extends AbstractDeleteState<Response, University> {

    public DeleteSingleUniversity(ServiceContext serviceContext, long modelIdToDelete) {
        super(serviceContext, modelIdToDelete);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<University> loadModel() {
        return DaoFactory.getInstance().getUniversityDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getUniversityDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

    }

}
