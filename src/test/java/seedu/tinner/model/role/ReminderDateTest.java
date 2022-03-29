package seedu.tinner.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReminderDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReminderDate(null));
    }

    @Test
    public void constructor_invalidReminderDate_throwsIllegalArgumentException() {
        String invalidReminderDate = "abc";
        assertThrows(IllegalArgumentException.class, () -> new ReminderDate(invalidReminderDate));
    }

    @Test
    public void isValidReminderDate() {
        // null reminder date
        assertThrows(NullPointerException.class, () -> ReminderDate.isValidReminderDate(null));

        // invalid reminder dates
        assertFalse(ReminderDate.isValidReminderDate(" ")); // spaces only
        assertFalse(ReminderDate.isValidReminderDate("00-00-0000 00:00")); // invalid date format

        // valid reminder dates
        assertTrue(ReminderDate.isValidReminderDate("")); // empty string
        assertTrue(ReminderDate.isValidReminderDate("15-06-2001 11:00"));
        assertTrue(ReminderDate.isValidReminderDate("24-02-2022 20:49"));
    }

}
