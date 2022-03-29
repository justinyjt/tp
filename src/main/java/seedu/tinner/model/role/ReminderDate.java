package seedu.tinner.model.role;

import static java.util.Objects.requireNonNull;
import static seedu.tinner.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Role's reminder date in the role list.
 * Guarantees: immutable; is valid as declared in {@link #isValidReminderDate(String)}
 */
public class ReminderDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Reminder dates should not have passed and must be in the following format: dd-MM-yyyy HH:mm";

    public static final DateTimeFormatter VALIDATION_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter STRING_REPRESENTATION_FORMATTER =
            DateTimeFormatter.ofPattern("E, MMM dd yyyy, HH:mm a");

    private static int reminderWindow;

    public final LocalDateTime value;

    /**
     * Constructs a {@code Reminder date}.
     *
     * @param reminderDate A valid reminder date.
     */
    public ReminderDate(String reminderDate) {
        requireNonNull(reminderDate);
        checkArgument(isValidReminderDate(reminderDate), MESSAGE_CONSTRAINTS);
        if (reminderDate.isEmpty()) {
            value = null;
        } else {
            value = LocalDateTime.parse(reminderDate, VALIDATION_FORMATTER);
        }
    }

    public static void setReminderWindow(int newWindow) {
        reminderWindow = newWindow;
    }

    /**
     * Returns true if a given string is a valid reminder date.
     */
    public static boolean isValidReminderDate(String test) {
        if (test.isEmpty()) {
            return true;
        }
        try {
            VALIDATION_FORMATTER.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the reminder date is within a week from the current {@code LocalDateTime}
     * or if the date is null.
     *
     * @return true if is within a week from the current date.
     */
    public boolean isWithinReminderWindow() {
        if (this.value == null) {
            return false;
        }
        LocalDateTime oneReminderWindowAway = LocalDateTime.now().plusDays(reminderWindow);
        return !this.value.isBefore(LocalDateTime.now()) && oneReminderWindowAway.isAfter(this.value);
    }

    /**
     * Returns true if a given string, when parsed into {@code LocalDateTime} is after the current time or if the
     * string is empty.
     *
     * @param test User input to be tested.
     * @return true if user input is after current time.
     */
    public static boolean isReminderDateAfter(String test) {
        if (test.isEmpty()) {
            return true;
        }
        return LocalDateTime.parse(test, VALIDATION_FORMATTER).isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        return value.format(STRING_REPRESENTATION_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderDate // instanceof handles nulls
                && value.equals(((ReminderDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
