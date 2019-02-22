package hotelbookings.usecases;

import hotelbookings.journey.tasks.checkBookingTask;
import org.junit.Test;

import java.util.UUID;

public class sadPathTests extends AutomatedTests {

    private static final String DEPOSIT_PAID = "true";
    private String randomFirstName;
    private String lastName;

    @Test
    public void shouldNotMakeAReservationWithoutDates() {
        randomFirstName = UUID.randomUUID().toString();
        lastName = "SAD_PATH";
        String price = "250.55";

        givenUserFillsInBookingForm(randomFirstName, lastName, price, DEPOSIT_PAID);

        whenUserSavesBooking();

        thenBookingIsNotSavedFor(randomFirstName, lastName);
    }

    private void thenBookingIsNotSavedFor(String firstName, String lastName) {
        checkBookingTask.assertUnsavedBooking(firstName, lastName);
    }

}
