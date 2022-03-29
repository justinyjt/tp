package seedu.tinner.model.role;

import static seedu.tinner.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Role in Tinner.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Role {

    private final RoleName roleName;
    private final Status status;
    private final ReminderDate reminderDate;

    // "optional" in the sense that they can be empty strings
    private final Description description;
    private final Stipend stipend;

    /**
     * Every field must be present and not null.
     */
    public Role(RoleName roleName, Status status, ReminderDate reminderDate, Description description,
                Stipend stipend) {
        requireAllNonNull(roleName, status, reminderDate, description, stipend);
        this.roleName = roleName;
        this.status = status;
        this.reminderDate = reminderDate;
        this.description = description;
        this.stipend = stipend;
    }

    public RoleName getName() {
        return roleName;
    }

    public Status getStatus() {
        return status;
    }

    public ReminderDate getReminderDate() {
        return reminderDate;
    }

    public Description getDescription() {
        return description;
    }

    public Stipend getStipend() {
        return stipend;
    }

    /**
     * Returns true if both roles have the same name.
     * This defines a weaker notion of equality between two roles.
     */
    public boolean isSameRole(Role otherRole) {
        if (otherRole == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(otherRole instanceof Role)) {
            return false;
        }

        return otherRole.getName().toString().replaceAll("\\s+", "")
                .equals(getName().toString().replaceAll("\\s+", ""));
    }

    /**
     * Returns true if the role is within the reminder period and status is not pending, rejected or complete
     */
    public boolean needsReminding() {
        return reminderDate.isWithinReminderWindow();
    }

    /**
     * Returns true if both roles have the same identity and data fields.
     * This defines a stronger notion of equality between two roles.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return otherRole.getName().equals(getName())
                && otherRole.getStatus().equals(getStatus())
                && otherRole.getReminderDate().equals(getReminderDate())
                && otherRole.getDescription().equals(getDescription())
                && otherRole.getStipend().equals(getStipend());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, status, reminderDate, description, stipend);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Status: ")
                .append(getStatus())
                .append("; Reminder Date: ")
                .append(getReminderDate())
                .append("; Description: ")
                .append(getDescription())
                .append("; Stipend: ")
                .append(getStipend());

        return builder.toString();
    }

}
