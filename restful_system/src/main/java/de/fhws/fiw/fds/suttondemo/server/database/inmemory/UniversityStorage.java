package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.comparators.UniversityComparator;
import de.fhws.fiw.fds.suttondemo.server.api.models.University;
import de.fhws.fiw.fds.suttondemo.server.database.UniversityDao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UniversityStorage extends AbstractInMemoryStorage<University> implements UniversityDao {

    @Override
    public CollectionModelResult<University> readByAll(String search, String order, String uniName, String country, SearchParameter searchParameter) {
        // Combine predicates for search, country, and name
        Predicate<University> predicate = all();

        if (search != null && !search.isEmpty())
            predicate = predicate.and(containsIgnoreCase(search));

        if (country != null && !country.isEmpty())
            predicate = predicate.and(byCountry(country));

        if (uniName != null && !uniName.isEmpty())
            predicate = predicate.and(byName(uniName));

        // Filter universities based on combined predicate
        List<University> filteredUniversities = new ArrayList<>(this.filterBy(predicate));

        // Apply order based on the parameter
        if (order != null && !order.isEmpty()) {
            filteredUniversities.sort(UniversityComparator.by(order));
        }

        // Apply paging and return the result
        return InMemoryPaging.page(
                new CollectionModelResult<>(filteredUniversities),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    private Predicate<University> byCountry(String country) {
        return p -> p.getCountry().equalsIgnoreCase(country);
    }

    private Predicate<University> byName(String name) {
        return p -> p.getUniName().equalsIgnoreCase(name);
    }

    private Predicate<University> containsIgnoreCase(String search) {
        String lowerCaseSearch = search.toLowerCase();
        return p -> p.getUniName().toLowerCase().contains(lowerCaseSearch) ||
                p.getCountry().toLowerCase().contains(lowerCaseSearch);
    }

    public void resetDatabase() {
        this.storage.clear();
    }
}
