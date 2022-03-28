package seedu.tinner.model;

import static java.util.Objects.requireNonNull;
import static seedu.tinner.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tinner.model.company.RoleManager.PREDICATE_SHOW_ALL_ROLES;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.tinner.commons.core.GuiSettings;
import seedu.tinner.commons.core.LogsCenter;
import seedu.tinner.commons.core.index.Index;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.company.RoleManager;
import seedu.tinner.model.reminder.UniqueReminderList;
import seedu.tinner.model.role.Role;

/**
 * Represents the in-memory model of the company list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CompanyList companyList;
    private final UserPrefs userPrefs;
    private final FilteredList<Company> filteredCompanies;
    private UniqueReminderList reminderList;

    /**
     * Initializes a ModelManager with the given companyList, userPrefs and reminderList.
     */
    public ModelManager(ReadOnlyCompanyList companyList, ReadOnlyUserPrefs userPrefs, UniqueReminderList reminderList) {
        requireAllNonNull(companyList, userPrefs, reminderList);
        logger.fine("Initializing with company list: " + companyList + " and user prefs " + userPrefs);

        this.companyList = new CompanyList(companyList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCompanies = new FilteredList<>(this.companyList.getCompanyList());
        this.reminderList = reminderList;
    }

    public ModelManager() {
        this(new CompanyList(), new UserPrefs(), UniqueReminderList.getInstance());
    }

    //=========== ReminderWindow ==================================================================================
    @Override
    public void setReminderWindow(int reminderWindow) {
        this.userPrefs.setReminderWindow(reminderWindow);

    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getCompanyListFilePath() {
        return userPrefs.getCompanyListFilePath();
    }

    @Override
    public void setCompanyListFilePath(Path companyListFilePath) {
        requireNonNull(companyListFilePath);
        userPrefs.setCompanyListFilePath(companyListFilePath);
    }

    //=========== CompanyList ================================================================================

    @Override
    public void setCompanyList(ReadOnlyCompanyList companyList) {
        this.companyList.resetData(companyList);
    }

    @Override
    public ReadOnlyCompanyList getCompanyList() {
        return companyList;
    }

    @Override
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return companyList.hasCompany(company);
    }

    @Override
    public void deleteCompany(Company target) {
        companyList.removeCompany(target);
    }

    @Override
    public void addCompany(Company company) {
        companyList.addCompany(company);
        updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES, PREDICATE_SHOW_ALL_ROLES);
    }

    @Override
    public void setCompany(Company target, Company editedCompany) {
        requireAllNonNull(target, editedCompany);
        companyList.setCompany(target, editedCompany);
    }

    //=========== Filtered Company List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Company} backed by the internal list of
     * {@code versionedCompanyList}
     */
    @Override
    public ObservableList<Company> getFilteredCompanyList() {
        return filteredCompanies;
    }

    @Override
    public void updateFilteredCompanyList(Predicate<Company> companyPredicate, Predicate<Role> rolePredicate) {
        requireAllNonNull(companyPredicate, rolePredicate);
        filteredCompanies.setPredicate(companyPredicate);
        filteredCompanies.forEach(company -> company.getRoleManager().updateFilteredRoleList(rolePredicate));
        assert (filteredCompanies.stream().allMatch(companyPredicate));
        assert (filteredCompanies.stream().allMatch(company -> company.getRoleManager().getFilteredRoleList()
                .stream().allMatch(rolePredicate)));
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return companyList.equals(other.companyList)
                && userPrefs.equals(other.userPrefs)
                && filteredCompanies.equals(other.filteredCompanies);
    }

    //=========== RoleManager ================================================================================
    /**
     * Adds the given role.
     * @param role must not already exist in the Company.
     * @param companyIndex must be a non-negative index that is smaller than
     *                     the size of a filtered company list.
     */
    @Override
    public void addRole(Index companyIndex, Role role) {
        assert (companyIndex.getZeroBased() < filteredCompanies.size());
        requireNonNull(role);

        Company company = this.filteredCompanies.get(companyIndex.getZeroBased());
        RoleManager roleManager = company.getRoleManager();
        roleManager.addRole(role);
    }

    /**
     * Checks if a role with the same identity as {@code role} exists in the role list.
     * @param role to be checked if it exists in the Company.
     * @param companyIndex must be a non-negative index that is smaller than
     *                     the size of a filtered company list.
     * @return true if a role with the same identity as {@code role} exists in the role list.
     */
    @Override
    public boolean hasRole(Index companyIndex, Role role) {
        assert (companyIndex.getZeroBased() < filteredCompanies.size());
        requireNonNull(role);

        Company company = this.filteredCompanies.get(companyIndex.getZeroBased());
        RoleManager roleManager = company.getRoleManager();
        return roleManager.hasRole(role);
    }

    /**
     * Deletes the given role.
     * @param roleToDelete must exists in the Company.
     * @param companyIndex must be a non-negative index that is smaller than
     *                     the size of a filtered company list.
     */
    @Override
    public void deleteRole(Index companyIndex, Role roleToDelete) {
        assert (companyIndex.getZeroBased() < filteredCompanies.size());
        requireNonNull(roleToDelete);

        Company company = this.filteredCompanies.get(companyIndex.getZeroBased());
        RoleManager roleManager = company.getRoleManager();
        roleManager.deleteRole(roleToDelete);
    }

    /**
     * Replaces the given role {@code target} with {@code editedRole}.
     * @param companyIndex must be a non-negative index that is smaller than
     *                     the size of a filtered company list.
     * @param target must exists in the Company.
     * @param editedRole must not be the same as an existing role in the role list.
     */
    @Override
    public void setRole(Index companyIndex, Role target, Role editedRole) {
        assert (companyIndex.getZeroBased() < filteredCompanies.size());
        requireAllNonNull(target, editedRole);

        Company company = this.filteredCompanies.get(companyIndex.getZeroBased());
        RoleManager roleManager = company.getRoleManager();
        roleManager.setRole(target, editedRole);
    }

    /**
     * Updates the filter of the filtered role list to filter by the given {@code predicate}.
     * @param companyIndex must be a non-negative index that is smaller than
     *                     the size of a filtered company list.
     */
    @Override
    public void updateFilteredRoleList(Index companyIndex, Predicate<Role> predicate) {
        assert (companyIndex.getZeroBased() < filteredCompanies.size());

        Company company = this.filteredCompanies.get(companyIndex.getZeroBased());
        RoleManager roleManager = company.getRoleManager();
        roleManager.updateFilteredRoleList(predicate);
    }

    //=========== Filtered Company List Accessors =============================================================
    /**
     * Returns a filtered role list.
     * @param companyIndex must be a non-negative index that is smaller than
     *                     the size of a filtered company list.
     * @return the filtered role list with a given company.
     */
    @Override
    public ObservableList<Role> getFilteredRoleList(Index companyIndex) {
        assert (companyIndex.getZeroBased() < filteredCompanies.size());

        Company company = this.filteredCompanies.get(companyIndex.getZeroBased());
        RoleManager roleManager = company.getRoleManager();
        return roleManager.getFilteredRoleList();
    }

    //=================== Reminder List Accessors =============================================================
    public UniqueReminderList getReminderList() {
        return reminderList;
    }
}
