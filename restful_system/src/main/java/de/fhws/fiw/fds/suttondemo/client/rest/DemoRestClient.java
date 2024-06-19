package de.fhws.fiw.fds.suttondemo.client.rest;


import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;
import de.fhws.fiw.fds.suttondemo.client.models.UniversityClientModel;
import de.fhws.fiw.fds.suttondemo.client.models.ModuleClientModel;
import de.fhws.fiw.fds.suttondemo.client.web.UniversityWebClient;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DemoRestClient extends AbstractRestClient {
    private static final String BASE_URL = "http://localhost:8080/demo/api"; //yalnızca burada base URL var
    private static final String GET_ALL_UNIVERSITIES = "getAllUniversities";
    private static final String CREATE_UNIVERSITY = "createUniversity";


    private List<UniversityClientModel> currentUniversityData;

    private int cursorUniversityData = 0;

    private List<ModuleClientModel> currentModuleData;

    private int cursorModuleData = 0;

    final private UniversityWebClient client;

    public DemoRestClient() {
        super();
        this.client = new UniversityWebClient();
        this.currentUniversityData = Collections.EMPTY_LIST;
    }

    public void resetDatabase() throws IOException {
        processResponse(this.client.resetDatabaseOnServer(BASE_URL), (response) -> {
        });
    }

    public void start() throws IOException {
        processResponse(this.client.getDispatcher(BASE_URL), (response) -> {
        });
    }

    public boolean isCreateUniversityAllowed() {
        return isLinkAvailable(CREATE_UNIVERSITY);
    }

    public void createUniversity(ModuleClientModel university) throws IOException {
        if (isCreateUniversityAllowed()) {
            processResponse(this.client.postNewUniversity(getUrl(CREATE_UNIVERSITY), university), (response) -> {
                this.currentUniversityData = Collections.EMPTY_LIST;
                this.cursorUniversityData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllUniversitiesAllowed() {
        return isLinkAvailable(GET_ALL_UNIVERSITIES);
    }

    public void getAllUniversities() throws IOException {
        if (isGetAllUniversitiesAllowed()) {
            processResponse(this.client.getCollectionOfUniversities(getUrl(GET_ALL_UNIVERSITIES)), (response) -> {
                this.currentUniversityData = new LinkedList(response.getResponseData());
                this.cursorUniversityData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSingleUniversityAllowed() {
        return !this.currentUniversityData.isEmpty() || isLocationHeaderAvailable();
    }

    public List<ModuleClientModel> universityData() {
        if (this.currentUniversityData.isEmpty()) {
            throw new IllegalStateException();
        }

        return this.currentUniversityData;
    }

    public void setUniversityCursor(int index) {
        if (0 <= index && index < this.currentUniversityData.size()) {
            this.cursorUniversityData= index;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void getSingleUniversity() throws IOException {
        if ( isLocationHeaderAvailable()) {
            getSingleUniversity(getLocationHeaderURL());
        }
        else if (!this.currentUniversityData.isEmpty()) {
            getSingleUniversity(this.cursorUniversityData);
        }
        else {
            throw new IllegalStateException();
        }
    }

    public void getSingleUniversity(int index) throws IOException {
        getSingleUniversity(this.currentUniversityData.get(index).getSelfLink().getUrl());
    }

    private void getSingleUniversity(String url) throws IOException {
        processResponse(this.client.getSingleUniversity(url), (response) -> {
            this.currentUniversityData = new LinkedList(response.getResponseData());
            this.cursorUniversityData = 0;
        });
    }

    /*
     *  The rest of the class is omitted for brevity
     */
}
