package de.fhws.fiw.fds.suttondemo.server.api.comparators;

import de.fhws.fiw.fds.suttondemo.server.api.models.University;

import java.util.Comparator;
public class UniversityComparator {
	public static Comparator<University> by(final String orderAttribute) {
        return switch (orderAttribute) {
            case "+name" -> byNames();
            case "-name" -> byNames().reversed();
            case "+country" -> byCountry();
            case "-country" -> byCountry().reversed();
            default -> byId();
        };
	}

	public static Comparator<University> byId() {
		return Comparator.comparing(University::getId);
	}

	public static Comparator<University> byNames() {
		return Comparator.comparing((University u) -> u.getUniName().toLowerCase()).thenComparing(University::getDepartmant);
	}

	public static Comparator<University> byCountry() {
		return Comparator.comparing((University u) -> u.getCountry().toLowerCase());
	}
}

