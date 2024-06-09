package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.ModuleDao;
import de.fhws.fiw.fds.suttondemo.server.database.UniversityModuleDao;

public class UniversityModuleStorage extends AbstractInMemoryRelationStorage<Module> implements UniversityModuleDao {
    final private ModuleDao moduleStorage;

    public UniversityModuleStorage(ModuleDao moduleStorage) {
        this.moduleStorage = moduleStorage;
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage() {
        return this.moduleStorage;
    }

    @Override
    public CollectionModelResult<Module> readByModuleName(long primaryId, String moduleName, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> moduleName.isEmpty() || p.getModuleName().equals(moduleName)),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public CollectionModelResult<Module> readAllLinked(long primaryId, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> true),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public void resetDatabase() {

    }

    @Override
    public void initializeDatabase() {

    }
}
