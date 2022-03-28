package seedu.tinner.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tinner.testutil.TypicalCompanies.getTypicalCompanyList;

import org.junit.jupiter.api.Test;

import seedu.tinner.commons.core.Messages;
import seedu.tinner.model.Model;
import seedu.tinner.model.ModelManager;
import seedu.tinner.model.UserPrefs;
import seedu.tinner.model.reminder.UniqueReminderList;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SetReminderWindowCommand}.
 */
public class SetReminderWindowCommandTest {

    private Model model = new ModelManager(getTypicalCompanyList(), new UserPrefs(),
            UniqueReminderList.getInstance());

    @Test
    public void execute_invalidReminderWindowHigh_throwsCommandException() {
        // Boundary and equivalence partitioning (above 30)
        SetReminderWindowCommand setReminderWindowCommand = new SetReminderWindowCommand(31);

        assertCommandFailure(setReminderWindowCommand, model, Messages.MESSAGE_INVALID_REMINDER_WINDOW);
    }

    @Test
    public void execute_invalidReminderWindowLow_throwsCommandException() {
        // Boundary and equivalence partitioning (negative values)
        SetReminderWindowCommand setReminderWindowCommand = new SetReminderWindowCommand(-1);

        assertCommandFailure(setReminderWindowCommand, model, Messages.MESSAGE_INVALID_REMINDER_WINDOW);
    }

    @Test
    public void execute_invalidReminderWindowZero_throwsCommandException() {
        // Boundary and equivalence partitioning (zero)
        SetReminderWindowCommand setReminderWindowCommand = new SetReminderWindowCommand(0);

        assertCommandFailure(setReminderWindowCommand, model, Messages.MESSAGE_INVALID_REMINDER_WINDOW);
    }

    @Test
    public void equals() {
        SetReminderWindowCommand setReminderWindowCommandOne = new SetReminderWindowCommand(1);
        SetReminderWindowCommand setReminderWindowCommandTwo = new SetReminderWindowCommand(2);

        // same object -> returns true
        assertTrue(setReminderWindowCommandOne.equals(setReminderWindowCommandOne));

        // same values -> returns true
        SetReminderWindowCommand setReminderWindowCommandOneCopy = new SetReminderWindowCommand(1);
        assertTrue(setReminderWindowCommandOne.equals(setReminderWindowCommandOneCopy));

        // different types -> returns false
        assertFalse(setReminderWindowCommandOne.equals(1));

        // null -> returns false
        assertFalse(setReminderWindowCommandOne.equals(null));

        // different window -> returns false
        assertFalse(setReminderWindowCommandOne.equals(setReminderWindowCommandTwo));
    }
}
