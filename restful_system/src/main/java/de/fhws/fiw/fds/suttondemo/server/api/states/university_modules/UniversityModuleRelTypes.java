package de.fhws.fiw.fds.suttondemo.server.api.states.university_modules;

public interface UniversityModuleRelTypes {
	String CREATE_MODULE = "createModuleOfUniversity";
	String GET_ALL_LINKED_MODULES = "getAllModulesOfUniversity";
	String GET_ALL_MODULES = "getAllLinkableModules";
	String UPDATE_SINGLE_MODULE = "updateModuleOfUniversity";
	String CREATE_LINK_FROM_UNIVERSITY_TO_MODULE = "linkUniversityToModule";
	String DELETE_LINK_FROM_UNIVERSITY_TO_MODULE = "unlinkUniversityToModule";
	String GET_SINGLE_MODULE = "getModuleOfUniversity";

}
