package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Email;
import seedu.address.model.company.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_INSTAGRAM, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_INSTAGRAM, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_INSTAGRAM, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, CompanyName.MESSAGE_CONSTRAINTS); //
        // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); //
        // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); //
        // invalid email
        //assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_INSTAGRAM,
                Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by
        // valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_WHATSAPP + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Company}
        // being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_INSTAGRAM + VALID_PHONE_INSTAGRAM,
                CompanyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_COMPANY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_WHATSAPP + TAG_DESC_HUSBAND
                + EMAIL_DESC_INSTAGRAM + ADDRESS_DESC_INSTAGRAM + NAME_DESC_INSTAGRAM + TAG_DESC_FRIEND;

        EditCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder().withName(VALID_NAME_INSTAGRAM)
                        .withPhone(VALID_PHONE_WHATSAPP).withEmail(VALID_EMAIL_INSTAGRAM)
                        .withAddress(VALID_ADDRESS_INSTAGRAM)
                        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_WHATSAPP + EMAIL_DESC_INSTAGRAM;

        EditCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_WHATSAPP)
                        .withEmail(VALID_EMAIL_INSTAGRAM).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_COMPANY;
        String userInput = targetIndex.getOneBased() + NAME_DESC_INSTAGRAM;
        EditCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder().withName(VALID_NAME_INSTAGRAM).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_INSTAGRAM;
        descriptor = new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_INSTAGRAM).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_INSTAGRAM;
        descriptor = new EditCompanyDescriptorBuilder().withEmail(VALID_EMAIL_INSTAGRAM).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_INSTAGRAM;
        descriptor = new EditCompanyDescriptorBuilder().withAddress(VALID_ADDRESS_INSTAGRAM).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditCompanyDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput =
                targetIndex.getOneBased() + PHONE_DESC_INSTAGRAM + ADDRESS_DESC_INSTAGRAM + EMAIL_DESC_INSTAGRAM
                        + TAG_DESC_FRIEND + PHONE_DESC_INSTAGRAM + ADDRESS_DESC_INSTAGRAM
                        + EMAIL_DESC_INSTAGRAM + TAG_DESC_FRIEND
                        + PHONE_DESC_WHATSAPP + ADDRESS_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP + TAG_DESC_HUSBAND;

        EditCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_WHATSAPP)
                        .withEmail(VALID_EMAIL_WHATSAPP).withAddress(VALID_ADDRESS_WHATSAPP).withTags(VALID_TAG_FRIEND,
                                VALID_TAG_HUSBAND)
                        .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput =
                targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_WHATSAPP;
        EditCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_WHATSAPP).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput =
                targetIndex.getOneBased() + EMAIL_DESC_WHATSAPP + INVALID_PHONE_DESC + ADDRESS_DESC_WHATSAPP
                        + PHONE_DESC_WHATSAPP;
        descriptor =
                new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_WHATSAPP).withEmail(VALID_EMAIL_WHATSAPP)
                        .withAddress(VALID_ADDRESS_WHATSAPP).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_COMPANY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
