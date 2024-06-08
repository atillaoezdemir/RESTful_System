package de.fhws.fiw.fds.suttondemo.server.api.states.university_modules;

import de.fhws.fiw.fds.suttondemo.Start;

public interface UniversityModuleUri {

    String SHOW_ALL_PARAMETER = "showAll";
    String PATH_ELEMENT = "universities/{id}/modules";

    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_SHOW_ALL = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
    String REL_PATH_SHOW_ONLY_LINKED = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=false";
    String REL_PATH_ID = REL_PATH + "/{id}";

}
