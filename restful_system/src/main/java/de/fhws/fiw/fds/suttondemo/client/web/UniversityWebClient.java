package de.fhws.fiw.fds.suttondemo.client.web;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemo.client.models.ModuleClientModel;

import java.io.IOException;

public class UniversityWebClient {

    private GenericWebClient<ModuleClientModel> client;

    public UniversityWebClient() {
        this.client = new GenericWebClient<>();
    }

    public UniversityWebResponse getDispatcher( String url ) throws IOException
    {
        return createResponse( this.client.sendGetSingleRequest( url ) );
    }


    public UniversityWebResponse getSingleUniversity(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, ModuleClientModel.class));
    }

    public UniversityWebResponse getCollectionOfUniversities(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, ModuleClientModel.class));
    }

    public UniversityWebResponse postNewUniversity(String url, ModuleClientModel university)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, university));
    }

    public UniversityWebResponse putUniversity(String url, ModuleClientModel university) throws IOException {
        return createResponse(this.client.sendPutRequest(url, university));
    }

    public UniversityWebResponse deleteUniversity(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public UniversityWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    private UniversityWebResponse createResponse(WebApiResponse<ModuleClientModel> response) {
        return new UniversityWebResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }

}
