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

import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Hyperlinks;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.suttondemo.server.api.models.University;
import jakarta.ws.rs.core.Response;

public class GetAllUniversities extends AbstractGetCollectionState<Response, University> {
    private final String search;
    private final String country;
    private final String name;
    private final String order;

    public GetAllUniversities(ServiceContext serviceContext, AbstractQuery<Response, University> query, String search, String country, String name, String order) {
        super(serviceContext, query);
        this.search = search;
        this.country = country;
        this.name = name;
        this.order = order;
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniversityUri.REL_PATH, UniversityRelTypes.CREATE_UNIVERSITY, getAcceptRequestHeader());

        if (search.isEmpty())
            addSearchLink();

        if (country.isEmpty())
            addCountryLink();

        if (name.isEmpty())
            addNameLink();

        addOrderLink();
    }

    private void addSearchLink() {
        // The URI template for search could include the path and query parameter placeholder
        String searchUri = UniversityUri.REL_PATH + "?search={SEARCH}";
        Hyperlinks.addLink(
                this.uriInfo,
                this.suttonResponse,
                searchUri,
                "searchForUniByNameAndCountry",
                getAcceptRequestHeader(),
                "{SEARCH}"
        );
    }

    private void addCountryLink() {
        // The URI template for country filter could include the path and query parameter placeholder
        String countryUri = UniversityUri.REL_PATH + "?country={COUNTRY}";
        Hyperlinks.addLink(
                this.uriInfo,
                this.suttonResponse,
                countryUri,
                "filterByCountry",
                getAcceptRequestHeader(),
                "{COUNTRY}"
        );
    }

    private void addNameLink() {
        // The URI template for name filter could include the path and query parameter placeholder
        String nameUri = UniversityUri.REL_PATH + "?name={NAME}";
        Hyperlinks.addLink(
                this.uriInfo,
                this.suttonResponse,
                nameUri,
                "filterByName",
                getAcceptRequestHeader(),
                "{NAME}"
        );
    }

    private void addOrderLink() {
        String alternateOrderName = "desc_name".equals(order) ? "asc_name" : "desc_name";
        String orderUriName = UniversityUri.REL_PATH + "?order=" + alternateOrderName;
        Hyperlinks.addLink(
                this.uriInfo,
                this.suttonResponse,
                orderUriName,
                "reverseSearchOrderByName",
                getAcceptRequestHeader()
        );

        String alternateOrderCountry = "desc_country".equals(order) ? "asc_country" : "desc_country";
        String orderUriCountry = UniversityUri.REL_PATH + "?order=" + alternateOrderCountry;
        Hyperlinks.addLink(
                this.uriInfo,
                this.suttonResponse,
                orderUriCountry,
                "reverseSearchOrderByName",
                getAcceptRequestHeader()
        );
    }
}