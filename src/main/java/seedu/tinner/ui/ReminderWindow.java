package seedu.tinner.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.tinner.commons.core.LogsCenter;
import seedu.tinner.model.reminder.UniqueReminderList;

/**
 * Controller for a reminder page
 */
public class ReminderWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);
    private static final String FXML = "ReminderWindow.fxml";

    @FXML
    private StackPane reminderDateListPanelPlaceholder;

    /**
     * Creates a new ReminderWindow
     * @param root
     */
    public ReminderWindow(Stage root) {
        super(FXML, root);
        UniqueReminderList reminderList = UniqueReminderList.getInstance();
        setReminderListPanelListViewPlaceholder(reminderList.getReminderDates());
    }

    /**
     * Creates a new ReminderWindow.
     */
    public ReminderWindow() {
        this(new Stage());
    }

    /**
     * Updates <code>reminderListPanelPlaceholder</code> to reflect the contents of the current
     * <code>reminderList</code>
     */
    public void setReminderListPanelListViewPlaceholder(ObservableList<LocalDate> roleList) {
        ReminderDateListPanel reminderDateListPanel = new ReminderDateListPanel(roleList);
        reminderDateListPanelPlaceholder.getChildren().add(reminderDateListPanel.getRoot());
    }

    /**
     * Shows the reminder window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application
     *                               Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing reminder page for upcoming reminder dates.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the reminder window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the reminder window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the reminder window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
