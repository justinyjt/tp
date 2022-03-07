package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.META;
import static seedu.address.testutil.TypicalCompanies.WHATSAPP;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class CompanyTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Company company = new CompanyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> company.getTags().remove(0));
    }

    @Test
    public void isSameCompany() {
        // same object -> returns true
        assertTrue(META.isSameCompany(META));

        // null -> returns false
        assertFalse(META.isSameCompany(null));

        // same name, all other attributes different -> returns true
        Company editedAlice = new CompanyBuilder(META).withPhone(VALID_PHONE_WHATSAPP).withEmail(VALID_EMAIL_WHATSAPP)
                .withAddress(VALID_ADDRESS_WHATSAPP).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(META.isSameCompany(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CompanyBuilder(META).withName(VALID_NAME_WHATSAPP).build();
        assertFalse(META.isSameCompany(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Company editedBob = new CompanyBuilder(WHATSAPP).withName(VALID_NAME_WHATSAPP.toLowerCase()).build();
        assertFalse(WHATSAPP.isSameCompany(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_WHATSAPP + " ";
        editedBob = new CompanyBuilder(WHATSAPP).withName(nameWithTrailingSpaces).build();
        assertFalse(WHATSAPP.isSameCompany(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Company aliceCopy = new CompanyBuilder(META).build();
        assertTrue(META.equals(aliceCopy));

        // same object -> returns true
        assertTrue(META.equals(META));

        // null -> returns false
        assertFalse(META.equals(null));

        // different type -> returns false
        assertFalse(META.equals(5));

        // different company -> returns false
        assertFalse(META.equals(WHATSAPP));

        // different name -> returns false
        Company editedAlice = new CompanyBuilder(META).withName(VALID_NAME_WHATSAPP).build();
        assertFalse(META.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CompanyBuilder(META).withPhone(VALID_PHONE_WHATSAPP).build();
        assertFalse(META.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CompanyBuilder(META).withEmail(VALID_EMAIL_WHATSAPP).build();
        assertFalse(META.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CompanyBuilder(META).withAddress(VALID_ADDRESS_WHATSAPP).build();
        assertFalse(META.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CompanyBuilder(META).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(META.equals(editedAlice));
    }
}
