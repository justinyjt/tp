package seedu.tinner.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.tinner.commons.core.GuiSettings;
import seedu.tinner.commons.core.index.Index;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.role.Role;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true, for filtering companies.
     */
    Predicate<Company> PREDICATE_SHOW_ALL_COMPANIES = unused -> true;

    /**
     * {@code Predicate} that always evaluate to false, for filtering companies.
     */
    Predicate<Company> PREDICATE_SHOW_NO_COMPANIES = unused -> false;

    /**
     * Sets the reminder window in user prefs to the given input.
     *
     */
    void setReminderWindow(int reminderWindow);

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' company list file path.
     */
    Path getCompanyListFilePath();

    /**
     * Sets the user prefs' company list file path.
     */
    void setCompanyListFilePath(Path companyListFilePath);

    /**
     * Replaces company list data with the data in {@code companyList}.
     */
    void setCompanyList(ReadOnlyCompanyList companyList);

    /**
     * Returns the CompanyList
     */
    ReadOnlyCompanyList getCompanyList();

    /**
     * Returns true if a company with the same identity as {@code company} exists in the company list.
     */
    boolean hasCompany(Company company);

    /**
     * Deletes the given company.
     * The company must exist in the company list.
     */
    void deleteCompany(Company target);

    /**
     * Adds the given company.
     * {@code company} must not already exist in the company list.
     */
    void addCompany(Company company);

    /**
     * Replaces the given company {@code target} with {@code editedCompany}.
     * {@code target} must exist in the company list.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the
     * company list.
     */
    void setCompany(Company target, Company editedCompany);

    /**
     * Returns an unmodifiable view of the filtered company list
     */
    ObservableList<Company> getFilteredCompanyList();

    /**
     * Updates the filter of the filtered company list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyList(Predicate<Company> predicate, Predicate<Role> rolePredicate);

    /**
     * Adds the given role.
     * {@code role} must not already exist in the Company.
     */
    void addRole(Index companyIndex, Role role);

    /**
     * Returns true if a role with the same identity as {@code role} exists in the role list.
     */
    boolean hasRole(Index companyIndex, Role role);

    /**
     * Returns an unmodifiable view of the list of {@code Role}.
     */
    ObservableList<Role> getFilteredRoleList(Index companyIndex);

    /**
     * Deletes the given role.
     * The role must exist in the company.
     */
    void deleteRole(Index companyIndex, Role role);

    /**
     * Replaces the given role {@code role} with {@code editedROle}.
     * {@code target} must exist in the role list.
     * The role identity of {@code editedRole} must not be the same as another existing role in the
     * role list.
     */
    void setRole(Index companyIndex, Role target, Role editedRole);

    /**
     * Updates the filter of the filtered role list to filter by the given {@code predicate}.
     */
    void updateFilteredRoleList(Index companyIndex, Predicate<Role> predicate);
}
