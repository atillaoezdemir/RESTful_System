package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.University;
import de.fhws.fiw.fds.suttondemo.server.database.UniversityDao;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UniversityStorage extends AbstractInMemoryStorage<University> implements UniversityDao {
    @Override
    public CollectionModelResult<University> readByNameAndCountry(String name, String country, SearchParameter searchParameter) {
        return InMemoryPaging.page(this.readAllByPredicate(
                byNameAndCountry(name, country),
                searchParameter
        ), searchParameter.getOffset(), searchParameter.getSize());
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    private Predicate<University> byNameAndCountry(String name, String country) {
        return p -> (name.isEmpty() || p.getUniName().equals(name) ) && ( country.isEmpty() || p.getCountry().equals(country));
    }

}

