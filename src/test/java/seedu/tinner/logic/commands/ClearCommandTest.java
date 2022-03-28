package seedu.tinner.logic.commands;

import static seedu.tinner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tinner.testutil.TypicalCompanies.getTypicalCompanyList;

import org.junit.jupiter.api.Test;

import seedu.tinner.model.CompanyList;
import seedu.tinner.model.Model;
import seedu.tinner.model.ModelManager;
import seedu.tinner.model.UserPrefs;
import seedu.tinner.model.reminder.UniqueReminderList;

public class ClearCommandTest {

    @Test
    public void execute_emptyCompanyList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCompanyList_success() {
        UniqueReminderList reminderList = UniqueReminderList.getInstance();
        Model model = new ModelManager(getTypicalCompanyList(), new UserPrefs(), reminderList);
        Model expectedModel = new ModelManager(getTypicalCompanyList(), new UserPrefs(), reminderList);
        expectedModel.setCompanyList(new CompanyList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
