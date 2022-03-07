package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCommand.EditCompanyDescriptor(DESC_INSTAGRAM);
        assertTrue(DESC_INSTAGRAM.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_INSTAGRAM.equals(DESC_INSTAGRAM));

        // null -> returns false
        assertFalse(DESC_INSTAGRAM.equals(null));

        // different types -> returns false
        assertFalse(DESC_INSTAGRAM.equals(5));

        // different values -> returns false
        assertFalse(DESC_INSTAGRAM.equals(DESC_WHATSAPP));

        // different name -> returns false
        EditCommand.EditCompanyDescriptor editedAmy =
                new EditCompanyDescriptorBuilder(DESC_INSTAGRAM).withName(VALID_NAME_WHATSAPP).build();

        assertFalse(DESC_INSTAGRAM.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_INSTAGRAM).withPhone(VALID_PHONE_WHATSAPP).build();
        assertFalse(DESC_INSTAGRAM.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_INSTAGRAM).withEmail(VALID_EMAIL_WHATSAPP).build();
        assertFalse(DESC_INSTAGRAM.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_INSTAGRAM).withAddress(VALID_ADDRESS_WHATSAPP).build();
        assertFalse(DESC_INSTAGRAM.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_INSTAGRAM).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_INSTAGRAM.equals(editedAmy));
    }
}
