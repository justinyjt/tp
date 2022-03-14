package seedu.tinner.logic.commands;

import static seedu.tinner.logic.commands.CommandTestUtil.VALID_NAME_WHATSAPP;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_STATUS_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_STIPEND_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tinner.testutil.TypicalCompanies.getTypicalCompanyList;
import static seedu.tinner.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.tinner.testutil.TypicalIndexes.INDEX_FIRST_ROLE;

import org.junit.jupiter.api.Test;

import seedu.tinner.commons.core.index.Index;
import seedu.tinner.model.CompanyList;
import seedu.tinner.model.Model;
import seedu.tinner.model.ModelManager;
import seedu.tinner.model.UserPrefs;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.company.RoleManager;
import seedu.tinner.model.role.Role;
import seedu.tinner.testutil.EditRoleDescriptorBuilder;
import seedu.tinner.testutil.RoleBuilder;

public class EditRoleCommandTest {
    private Model model = new ModelManager(getTypicalCompanyList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedList_success() {
        Role editedRole = new RoleBuilder().build();
        EditRoleCommand.EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder(editedRole).build();
        EditRoleCommand editRoleCommand = new EditRoleCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_ROLE,
                descriptor);
        String expectedMessage = String.format(EditRoleCommand.MESSAGE_EDIT_ROLE_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new CompanyList(model.getCompanyList()), new UserPrefs());
        RoleManager expectedRM = expectedModel.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased())
                .getRoleManager();
        Role expectedRole = expectedRM.getFilteredRoleList().get(INDEX_FIRST_ROLE.getZeroBased());
        expectedRM.setRole(expectedRole, editedRole);

        assertCommandSuccess(editRoleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Index indexLastCompany = Index.fromOneBased(model.getFilteredCompanyList().size());
        Company lastCompany = model.getFilteredCompanyList().get(indexLastCompany.getZeroBased());

        RoleManager roleManager = lastCompany.getRoleManager();
        Index indexLastRole = Index.fromOneBased(roleManager.getFilteredRoleList().size());
        Role lastRole = roleManager.getFilteredRoleList().get(indexLastRole.getZeroBased());

        RoleBuilder roleInList = new RoleBuilder(lastRole);
        Role editedRole = roleInList.withName(VALID_NAME_WHATSAPP)
                .withStatus(VALID_STATUS_SOFTWARE_ENGINEER).withStipend(VALID_STIPEND_SOFTWARE_ENGINEER)
                .build();

        EditRoleCommand.EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder().withName(VALID_NAME_WHATSAPP)
                .withStatus(VALID_STATUS_SOFTWARE_ENGINEER).withStipend(VALID_STIPEND_SOFTWARE_ENGINEER).build();

        EditRoleCommand editRoleCommand = new EditRoleCommand(indexLastCompany, indexLastRole, descriptor);

        String expectedMessage = String.format(EditRoleCommand.MESSAGE_EDIT_ROLE_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new CompanyList(model.getCompanyList()), new UserPrefs());
        RoleManager expectedRM = expectedModel.getFilteredCompanyList().get(indexLastCompany.getZeroBased())
                .getRoleManager();
        expectedRM.setRole(lastRole, editedRole);

        assertCommandSuccess(editRoleCommand, model, expectedMessage, expectedModel);
    }



}
