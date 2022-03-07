package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.APPLE;
import static seedu.address.testutil.TypicalCompanies.GOVTECH;
import static seedu.address.testutil.TypicalCompanies.ZOOM;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.CompanyNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyNameContainsKeywordsPredicate firstPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyNameContainsKeywordsPredicate secondPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCompanyFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 0);
        CompanyNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCompanyList());
    }

    @Test
    public void execute_multipleKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 3);
        CompanyNameContainsKeywordsPredicate predicate = preparePredicate("zoom apple tech");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ZOOM, APPLE, GOVTECH), model.getFilteredCompanyList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private CompanyNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CompanyNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
