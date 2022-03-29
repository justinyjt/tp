package seedu.tinner.model.reminder;

import static seedu.tinner.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.tinner.model.company.CompanyName;
import seedu.tinner.model.role.ReminderDate;
import seedu.tinner.model.role.RoleName;
import seedu.tinner.model.role.Status;


/**
 * Represents a Reminder in Tinner.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder implements Comparable<Reminder> {

    private final CompanyName companyName;
    private final ReminderDate reminderDate;
    private final RoleName roleName;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Reminder(CompanyName companyName, RoleName roleName, Status status, ReminderDate reminderDate) {
        requireAllNonNull(companyName, roleName, status, reminderDate);
        this.companyName = companyName;
        this.roleName = roleName;
        this.status = status;
        this.reminderDate = reminderDate;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public Status getStatus() {
        return status;
    }

    public ReminderDate getReminderDate() {
        return reminderDate;
    }

    /**
     * Returns true if both roles have the same name.
     * This defines a weaker notion of equality between two roles.
     */
    public boolean isSameReminder(Reminder otherReminder) {
        if (otherReminder == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(otherReminder instanceof Reminder)) {
            return false;
        }

        return otherReminder.getCompanyName().equals(getCompanyName())
                && otherReminder.getRoleName().equals(getRoleName());
    }

    /**
     * Returns true if both reminders have the same identity and data fields.
     * This defines a stronger notion of equality between two reminders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Reminder)) {
            return false;
        }

        Reminder otherReminder = (Reminder) other;
        return otherReminder.getCompanyName().equals(getCompanyName())
                && otherReminder.getRoleName().equals(getRoleName())
                && otherReminder.getStatus().equals(getStatus())
                && otherReminder.getReminderDate().equals(getReminderDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, roleName, reminderDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ")
                .append(getCompanyName())
                .append("; Role: ")
                .append(getRoleName())
                .append("; Reminder Date: ")
                .append(getReminderDate());

        return builder.toString();
    }

    @Override
    public int compareTo(Reminder other) {
        return this.getReminderDate().value.compareTo(other.getReminderDate().value);
    }
}
