package de.fhws.fiw.fds.suttondemo.server.database;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;

public interface UniversityModuleDao extends IDatabaseRelationAccessObject<Module> {

    CollectionModelResult<Module> readByModuleName(long primaryId, String moduleName, SearchParameter searchParameter);

    void initializeDatabase();

    void resetDatabase();

}
