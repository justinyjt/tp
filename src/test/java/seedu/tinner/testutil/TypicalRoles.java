package seedu.tinner.testutil;

import seedu.tinner.model.role.Role;

public class TypicalRoles {
    public static final Role NETWORK_ENGINEER = new RoleBuilder().withName("Network engineer")
            .withReminderDate("02-02-2022 00:00").withDescription("Implement new networks.")
            .withStatus("applying")
            .withStipend("10000").build();
    public static final Role MOBILE_ENGINEER = new RoleBuilder().withName("Mobile engineer")
            .withReminderDate("01-01-2021 00:00").withDescription("Develop mobile applications.")
            .withStatus("rejected")
            .withStipend("1000").build();
    public static final Role ML_ENGINEER = new RoleBuilder().withName("ML engineer")
            .withReminderDate("01-01-2020 00:00").withDescription("Develop machine learning algorithms.")
            .withStatus("offered")
            .withStipend("3000").build();
    public static final Role SOFTWARE_ENGINEER = new RoleBuilder().withName("Software engineer")
            .withReminderDate("01-01-2023 00:00").withDescription("Develop web applications.")
            .withStatus("offered")
            .withStipend("8888").build();
}
