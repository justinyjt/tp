package seedu.tinner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tinner.model.role.Role;

/**
 * An UI component that displays information of a {@code Role}.
 */
public class RoleCard extends UiPart<Region> {

    private static final String FXML = "RoleCard.fxml";

    public final Role role;

    @FXML
    private HBox cardPane;
    @FXML
    private Label roleName;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private Label reminderDate;
    @FXML
    private Label description;
    @FXML
    private Label stipend;

    /**
     * Creates a {@code RoleCard} with the given {@code Role}.
     */
    public RoleCard(Role role, int displayedIndex) {
        super(FXML);
        this.role = role;
        id.setText(displayedIndex + ". ");
        roleName.setText(role.getName().value);
        status.setText("Status: " + role.getStatus().value);
        if (role.getReminderDate().toString().isEmpty()) {
            reminderDate.setText("Reminder Date: N/A");
        } else {
            reminderDate.setText("Reminder Date: " + role.getReminderDate().toString());
        }
        if (role.getDescription().value.isEmpty()) {
            description.setText("Description: N/A");
        } else {
            description.setText("Description: " + role.getDescription().value);
        }
        if (role.getStipend().value.isEmpty()) {
            stipend.setText("Stipend: N/A");
        } else {
            stipend.setText("Stipend: $" + role.getStipend().value);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoleCard)) {
            return false;
        }

        // state check
        RoleCard card = (RoleCard) other;
        return role.equals(card.role);
    }
}
