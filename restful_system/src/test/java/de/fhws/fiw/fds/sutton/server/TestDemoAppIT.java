package de.fhws.fiw.fds.sutton.server;


import com.github.javafaker.Faker;
import de.fhws.fiw.fds.suttondemo.client.models.UniversityClientModel;
import de.fhws.fiw.fds.suttondemo.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoAppIT {
    final private Faker faker = new Faker();
    private DemoRestClient client;

    @BeforeEach
    public void setUp() throws IOException{
       this.client = new DemoRestClient();
       this.client.resetDatabase();
    }

    @Test
    public void test_dispatcher_is_available() throws IOException {
        client.start();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    public void test_dispatcher_is_get_all_universities_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());
    }

    @Test
    public void test_create_university_is_create_university_allowed() throws IOException {
        client.start();
        assertTrue(client.isCreateUniversityAllowed());
    }

    @Test
    public void test_create_university() throws IOException
    {
        client.start();

        var university = new UniversityClientModel();
        university.setUniName("University");
        university.setCountry("Germany");
        university.setDepartmant("Informatik");
        university.setDepartmantUrl("www.university.de");
        university.setContactPerson("Max Mustermann");
        university.setSendStudents(15);
        university.setAcceptStudents(30);
        university.setFirstDaySpring(LocalDate.of( 2000, 3, 15));
        university.setFirstDayAutumn(LocalDate.of( 2000, 10, 1));

        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
    }

    @Test void test_create_university_and_get_new_university() throws IOException
    {
        client.start();

        var university = new UniversityClientModel();
        university.setUniName("University");
        university.setCountry("Germany");
        university.setDepartmant("Informatik");
        university.setDepartmantUrl("www.university.de");
        university.setContactPerson("Max Mustermann");
        university.setSendStudents(15);
        university.setAcceptStudents(30);
        university.setFirstDaySpring(LocalDate.of( 2000, 3, 15));
        university.setFirstDayAutumn(LocalDate.of( 2000, 10, 1));

        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSingleUniversityAllowed() );

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        var universityFromServer = client.universityData().getFirst();
        assertEquals( "University", universityFromServer.getUniName() );
    }

    @Test void test_create_5_person_and_get_all() throws IOException
    {
        /*
         * The next statements look strange, because we call the dispatcher in all
         * iterations. But this is how the client works. The dispatcher is the entry point
         * and we need to call it in order to get the URL to create a new person.
         */
        for( int i=0; i<5; i++ ) {
            client.start();

            var university = new UniversityClientModel();
            university.setUniName(faker.university().name());
            university.setCountry(faker.country().name());
            university.setDepartmant("Informatik");
            university.setDepartmantUrl(faker.internet().url());
            university.setContactPerson(faker.educator().university());
            university.setSendStudents(faker.number().numberBetween(0, 100));
            university.setAcceptStudents(faker.number().numberBetween(0, 100));
            university.setFirstDaySpring(LocalDate.of( 20024, 3, 15));
            university.setFirstDayAutumn(LocalDate.of( 2024, 10, 1));

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }

        /* Now we call the dispatcher to get the URL to get all persons */
        client.start();
        assertTrue( client.isGetAllUniversitiesAllowed() );

        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.universityData().size());

        /* Set the cursor to the first person, not really necessary, but to make it clear here */
        client.setUniversityCursor(0);
        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());
    }
}
