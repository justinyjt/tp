package seedu.tinner.testutil;

import seedu.tinner.model.role.Description;
import seedu.tinner.model.role.ReminderDate;
import seedu.tinner.model.role.Role;
import seedu.tinner.model.role.RoleName;
import seedu.tinner.model.role.Status;
import seedu.tinner.model.role.Stipend;

/**
 * A utility class to help building Role objects.
 */
public class RoleBuilder {

    public static final String DEFAULT_NAME = "Google";
    public static final String DEFAULT_STATUS = "applying";
    public static final String DEFAULT_REMINDER_DATE = "01-01-2022 00:00";
    public static final String DEFAULT_DESCRIPTION = "Frontend web development";
    public static final String DEFAULT_STIPEND = "1000";


    private RoleName roleName;
    private Status status;
    private ReminderDate reminderDate;
    private Description description;
    private Stipend stipend;

    /**
     * Creates a {@code RoleBuilder} with the default details.
     */
    public RoleBuilder() {
        roleName = new RoleName(DEFAULT_NAME);
        status = new Status(DEFAULT_STATUS);
        reminderDate = new ReminderDate(DEFAULT_REMINDER_DATE);
        description = new Description(DEFAULT_DESCRIPTION);
        stipend = new Stipend(DEFAULT_STIPEND);
    }

    /**
     * Initializes the RoleBuilder with the data of {@code roleToCopy}.
     */
    public RoleBuilder(Role roleToCopy) {
        roleName = roleToCopy.getName();
        status = roleToCopy.getStatus();
        reminderDate = roleToCopy.getReminderDate();
        description = roleToCopy.getDescription();
        stipend = roleToCopy.getStipend();
    }

    /**
     * Sets the {@code Name} of the {@code Role} that we are building.
     */
    public RoleBuilder withName(String name) {
        this.roleName = new RoleName(name);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Role} that we are building.
     */
    public RoleBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code ReminderDate} of the {@code Role} that we are building.
     */
    public RoleBuilder withReminderDate(String reminderDate) {
        this.reminderDate = new ReminderDate(reminderDate);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Role} that we are building.
     */
    public RoleBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Stipend} of the {@code Role} that we are building.
     */
    public RoleBuilder withStipend(String stipend) {
        this.stipend = new Stipend(stipend);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Role} to be empty.
     */
    public RoleBuilder withoutDescription() {
        this.description = new Description("");
        return this;
    }

    /**
     * Sets the {@code Stipend} of the {@code Role} to be empty.
     */
    public RoleBuilder withoutStipend() {
        this.stipend = new Stipend("");
        return this;
    }

    public Role build() {
        return new Role(roleName, status, reminderDate, description, stipend);
    }
}
