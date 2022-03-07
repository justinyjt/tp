package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.META;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.company.Company;
import seedu.address.model.company.exceptions.DuplicateCompanyException;
import seedu.address.testutil.CompanyBuilder;

public class CompanyListTest {

    private final CompanyList companyList = new CompanyList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), companyList.getCompanyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> companyList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        CompanyList newData = getTypicalAddressBook();
        companyList.resetData(newData);
        assertEquals(newData, companyList);
    }

    @Test
    public void resetData_withDuplicateCompanies_throwsDuplicateCompanyException() {
        // Two companies with the same identity fields
        Company editedAlice = new CompanyBuilder(META).withAddress(VALID_ADDRESS_WHATSAPP).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Company> newCompanies = Arrays.asList(META, editedAlice);
        CompanyListStub newData = new CompanyListStub(newCompanies);

        assertThrows(DuplicateCompanyException.class, () -> companyList.resetData(newData));
    }

    @Test
    public void hasCompany_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> companyList.hasCompany(null));
    }

    @Test
    public void hasCompany_companyNotInAddressBook_returnsFalse() {
        assertFalse(companyList.hasCompany(META));
    }

    @Test
    public void hasCompany_companyInAddressBook_returnsTrue() {
        companyList.addCompany(META);
        assertTrue(companyList.hasCompany(META));
    }

    @Test
    public void hasCompany_companyWithSameIdentityFieldsInAddressBook_returnsTrue() {
        companyList.addCompany(META);
        Company editedAlice = new CompanyBuilder(META).withAddress(VALID_ADDRESS_WHATSAPP).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(companyList.hasCompany(editedAlice));
    }

    @Test
    public void getCompanyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> companyList.getCompanyList().remove(0));
    }

    /**
     * A stub ReadOnlyCompanyList whose companies list can violate interface constraints.
     */
    private static class CompanyListStub implements ReadOnlyCompanyList {
        private final ObservableList<Company> companies = FXCollections.observableArrayList();

        CompanyListStub(Collection<Company> companies) {
            this.companies.setAll(companies);
        }

        @Override
        public ObservableList<Company> getCompanyList() {
            return companies;
        }
    }

}
