package de.fhws.fiw.fds.suttondemo.server.api.comparators;

import de.fhws.fiw.fds.suttondemo.server.api.models.University;

import java.util.Comparator;
public class UniversityComparator {
	public static Comparator<University> by(final String orderAttribute) {
		switch (orderAttribute) {
			case "+name":
				return byNames();
			case "-name":
				return byNames().reversed();
			case "+country":
				return byCountry();
			case "-country":
				return byCountry().reversed();
			default:
				return byId();
		}
	}

	public static Comparator<University> byId() {
		return Comparator.comparing(University::getId);
	}

	public static Comparator<University> byNames() {
		return Comparator.comparing(University::getUniName).thenComparing(University::getDepartmant);
	}

	public static Comparator<University> byCountry() {
		return Comparator.comparing(University::getCountry);
	}
}
