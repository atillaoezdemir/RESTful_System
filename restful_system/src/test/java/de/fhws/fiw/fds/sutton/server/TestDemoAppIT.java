package de.fhws.fiw.fds.sutton.server;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.suttondemo.client.models.UniversityClientModel;
import de.fhws.fiw.fds.suttondemo.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDemoAppIT {
    final private Faker faker = new Faker();
    private DemoRestClient client;

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test
    public void testDispatcherIsAvailable() throws IOException {
        client.start();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    public void testDispatcherGetAllUniversitiesAllowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());
    }

    @Test
    public void testDispatcherCreateUniversityAllowed() throws IOException {
        client.start();
        assertTrue(client.isCreateUniversityAllowed());
    }

    @Test
    public void testDispatcherUpdateUniversityNotAllowed() throws IOException {
        client.start();
        assertFalse(client.isUpdateUniversityAllowed());
    }

    @Test
    public void testDispatcherGetSingleUniversityNotAllowed() throws IOException {
        client.start();
        assertFalse(client.isGetSingleUniversityAllowed());
    }

    @Test
    public void testDispatcherDeleteUniversityNotAllowed() throws IOException {
        client.start();
        assertFalse(client.isDeleteUniversityAllowed());
    }

    @Test
    public void testDispatcherCreateModuleNotAllowed() throws IOException {
        client.start();
        assertFalse(client.isCreateModuleAllowed());
    }

    @Test
    public void isCreateModuleAllowedAfterGetSingleUni() throws IOException {
        client.start();

        UniversityClientModel university = createSampleUniversity();
        client.createUniversity(university);

        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleUniversityAllowed());

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        UniversityClientModel universityFromServer = client.universityData().getFirst();
        assertEquals("University", universityFromServer.getUniName());
        assertTrue(client.isCreateModuleAllowed());
    }

    @Test
    public void testCreateUniversity() throws IOException {
        client.start();

        UniversityClientModel university = createSampleUniversity();
        client.createUniversity(university);

        assertEquals(201, client.getLastStatusCode());
    }

    @Test
    public void testCreateAndRetrieveUniversity() throws IOException {
        client.start();

        UniversityClientModel university = createSampleUniversity();
        client.createUniversity(university);

        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleUniversityAllowed());

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        UniversityClientModel universityFromServer = client.universityData().getFirst();
        assertEquals("University", universityFromServer.getUniName());
    }

    @Test
    public void testCreateMultipleUniversitiesAndGetAll() throws IOException {
        for (int i = 0; i < 5; i++) {
            client.start();
            UniversityClientModel university = createRandomUniversity();
            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());

        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.universityData().size());

        client.setUniversityCursor(0);
        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    public void testUpdateUniversityAfterPostNotAllowed() throws IOException {
        client.start();
        UniversityClientModel university = createSampleUniversity();
        client.createUniversity(university);

        assertFalse(client.isUpdateUniversityAllowed());
    }

    @Test
    public void testDeleteUniversityAfterPostNotAllowed() throws IOException {
        client.start();
        UniversityClientModel university = createSampleUniversity();
        client.createUniversity(university);

        assertFalse(client.isDeleteUniversityAllowed());
    }

    private UniversityClientModel createSampleUniversity() {
        UniversityClientModel university = new UniversityClientModel();
        university.setUniName("University");
        university.setCountry("Germany");
        university.setDepartmant("Informatik");
        university.setDepartmantUrl("www.university.de");
        university.setContactPerson("Max Mustermann");
        university.setSendStudents(15);
        university.setAcceptStudents(30);
        university.setFirstDaySpring(LocalDate.of(2000, 3, 15));
        university.setFirstDayAutumn(LocalDate.of(2000, 10, 1));
        return university;
    }

    private UniversityClientModel createRandomUniversity() {
        UniversityClientModel university = new UniversityClientModel();
        university.setUniName(faker.university().name());
        university.setCountry(faker.country().name());
        university.setDepartmant("Informatik");
        university.setDepartmantUrl(faker.internet().url());
        university.setContactPerson(faker.educator().university());
        university.setSendStudents(faker.number().numberBetween(0, 100));
        university.setAcceptStudents(faker.number().numberBetween(0, 100));
        university.setFirstDaySpring(LocalDate.of(2024, 3, 15));
        university.setFirstDayAutumn(LocalDate.of(2024, 10, 1));
        return university;
    }

    @Test
    public void testCreateAndDeleteUniversity() throws IOException {
        client.start();

        // Create a sample university
        UniversityClientModel university = createSampleUniversity();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        // Get the created university to retrieve its ID
        if (client.isGetSingleUniversityAllowed()) {
            client.getSingleUniversity();
            assertEquals(200, client.getLastStatusCode());
        } else {
            throw new IllegalStateException("GET SINGLE operation is not allowed");
        }

        List<UniversityClientModel> universities = client.universityData();
        assertFalse(universities.isEmpty());

        // Find the created university in the list and get its ID
        UniversityClientModel createdUniversity = universities.stream()
                .filter(u -> u.getUniName().equals(university.getUniName())) // Assuming name is unique
                .findFirst()
                .orElseThrow(() -> new AssertionError("Created university not found"));

        long universityId = createdUniversity.getId();

        // Verify that delete is allowed and perform the delete operation
        assertTrue(client.isDeleteUniversityAllowed());
        client.deleteUniversity(createdUniversity);
        assertEquals(204, client.getLastStatusCode());

        // Verify that the university is no longer retrievable
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());

        // Check if the current university data is empty before accessing it
        List<UniversityClientModel> updatedUniversities;
        try {
            updatedUniversities = client.universityData();
        } catch (IllegalStateException e) {
            // If an exception is thrown, it means the list is empty, which is acceptable in this case
            updatedUniversities = Collections.emptyList();
        }

        assertTrue(updatedUniversities.stream().noneMatch(u -> u.getId() == universityId));
    }

}
