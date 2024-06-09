package de.fhws.fiw.fds.suttondemo.server.api.states.university_modules;

public interface UniversityModuleRelTypes {
	String CREATE_MODULE = "createModuleOfPerson";
	String GET_ALL_LINKED_MODULES = "getAllModulesOfPerson";
	String GET_ALL_MODULES = "getAllLinkableModules";
	String UPDATE_SINGLE_MODULE = "updateModuleOfPerson";
	String CREATE_LINK_FROM_UNIVERSITY_TO_MODULE = "linkPersonToModule";
	String DELETE_LINK_FROM_UNIVERSITY_TO_MODULE = "unlinkPersonToModule";
	String GET_SINGLE_MODULE = "getModuleOfPerson";

}
