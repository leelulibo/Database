import org.example.Database;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    private static final String DATABASE_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeClass
    public static void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        // Initialize your in-memory database schema here if necessary
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE phone_events (Phone TEXT, Event_type TEXT, Date TEXT, Time_Stamp TEXT)");
            statement.executeUpdate("CREATE TABLE email_adresses (employee_guid TEXT, first_name TEXT, last_name TEXT, " +
                    "gender TEXT, email TEXT, age TEXT, birthday TEXT, active TEXT, street TEXT, postal TEXT, province TEXT, " +
                    "city TEXT, longitude TEXT, latitude TEXT, package TEXT, vendor TEXT, phone TEXT, package_cost TEXT, " +
                    "contract_start TEXT, contract_end TEXT)");
        }
    }

    @AfterClass
    public static void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testEmailAddresses() {
        // Clear the content of the output stream before the test
        outContent.reset();

        // Call the method that interacts with the database
        Database.emailAdresses();

        // Assert that the expected output is printed
        assertTrue(outContent.toString().contains("SQLite database created, data imported, and email addresses updated successfully."));
    }

    @Test
    public void testPhoneEvents() {
        // Clear the content of the output stream before the test
        outContent.reset();

        // Call the method that interacts with the database
        Database.phoneEvents();

        // Assert that the expected output is printed
        assertTrue(outContent.toString().contains("SQLite database created and data imported successfully."));
    }
}
