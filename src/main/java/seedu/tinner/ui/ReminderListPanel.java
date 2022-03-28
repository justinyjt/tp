package seedu.tinner.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tinner.commons.core.LogsCenter;
import seedu.tinner.model.reminder.Reminder;

/**
 * Panel containing the list of companies.
 */
public class ReminderListPanel extends UiPart<Region> {
    private static final String FXML = "ReminderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReminderListPanel.class);

    @FXML
    private ListView<Reminder> reminderListView;

    /**
     * Creates a {@code ReminderListPanel} with the given {@code LocalDate}.
     */
    public ReminderListPanel(ObservableList<Reminder> dateSpecificReminders) {
        super(FXML);
        reminderListView.setItems(dateSpecificReminders);
        reminderListView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Role} using a {@code RoleCard}.
     */
    static class ReminderListViewCell extends ListCell<Reminder> {
        @Override
        protected void updateItem(Reminder reminder, boolean empty) {
            super.updateItem(reminder, empty);

            if (empty || reminder == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderCard(reminder).getRoot());
            }
        }
    }

}
