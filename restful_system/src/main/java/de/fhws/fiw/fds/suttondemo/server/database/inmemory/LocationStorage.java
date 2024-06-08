package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.LocationDao;

public class LocationStorage extends AbstractInMemoryStorage<Module> implements LocationDao {
}
