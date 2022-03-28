package seedu.tinner.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tinner.commons.core.LogsCenter;

/**
 * Panel containing the list of companies.
 */
public class ReminderDateListPanel extends UiPart<Region> {

    private static final String HEADER_TEXT = "Reminders";

    private static final String FXML = "ReminderDateListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReminderDateListPanel.class);

    @FXML
    private Label header;
    @FXML
    private ListView<LocalDate> reminderDateListView;

    /**
     * Creates a {@code CompanyListPanel} with the given {@code ObservableList}.
     */
    public ReminderDateListPanel(ObservableList<LocalDate> dateList) {
        super(FXML);

        header.setText(HEADER_TEXT);

        reminderDateListView.setItems(dateList);
        reminderDateListView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Company} using a {@code CompanyCard}.
     */
    class ReminderListViewCell extends ListCell<LocalDate> {
        @Override
        protected void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);

            if (empty || date == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderDateCard(date).getRoot());
            }
        }
    }

}
