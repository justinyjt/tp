package seedu.tinner.model.reminder;

import static seedu.tinner.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.tinner.model.company.CompanyName;
import seedu.tinner.model.role.Deadline;
import seedu.tinner.model.role.RoleName;
import seedu.tinner.model.role.Status;


/**
 * Represents a Reminder in Tinner.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder implements Comparable<Reminder> {

    private final CompanyName companyName;
    private final Deadline deadline;
    private final RoleName roleName;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Reminder(CompanyName companyName, RoleName roleName, Status status, Deadline deadline) {
        requireAllNonNull(companyName, roleName, status, deadline);
        this.companyName = companyName;
        this.roleName = roleName;
        this.status = status;
        this.deadline = deadline;
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

    public Deadline getDeadline() {
        return deadline;
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
                && otherReminder.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, roleName, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ")
                .append(getCompanyName())
                .append("; Role: ")
                .append(getRoleName())
                .append("; Deadline: ")
                .append(getDeadline());

        return builder.toString();
    }

    @Override
    public int compareTo(Reminder other) {
        return this.getDeadline().value.compareTo(other.getDeadline().value);
    }
}
