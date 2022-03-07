package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_INSTAGRAM;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCompanies.INSTAGRAM;
import static seedu.address.testutil.TypicalCompanies.WHATSAPP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCompanyCommand;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Email;
import seedu.address.model.company.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CompanyBuilder;

public class AddCompanyCommandParserTest {
    private AddCompanyCommandParser parser = new AddCompanyCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Company expectedCompany = new CompanyBuilder(WHATSAPP).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_WHATSAPP
                + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP
                + ADDRESS_DESC_WHATSAPP + TAG_DESC_FRIEND, new AddCompanyCommand(expectedCompany));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_INSTAGRAM + NAME_DESC_WHATSAPP
                + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP
                + ADDRESS_DESC_WHATSAPP + TAG_DESC_FRIEND, new AddCompanyCommand(expectedCompany));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_WHATSAPP + PHONE_DESC_INSTAGRAM
                + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP
                + ADDRESS_DESC_WHATSAPP + TAG_DESC_FRIEND, new AddCompanyCommand(expectedCompany));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP
                + EMAIL_DESC_INSTAGRAM + EMAIL_DESC_WHATSAPP
                + ADDRESS_DESC_WHATSAPP + TAG_DESC_FRIEND, new AddCompanyCommand(expectedCompany));

        // multiple addresses - last address accepted
        assertParseSuccess(parser,
                NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP
                        + ADDRESS_DESC_INSTAGRAM + ADDRESS_DESC_WHATSAPP + TAG_DESC_FRIEND,
                new AddCompanyCommand(expectedCompany));

        // multiple tags - all accepted
        Company expectedCompanyMultipleTags =
                new CompanyBuilder(WHATSAPP).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser,
                NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP + ADDRESS_DESC_WHATSAPP
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCompanyCommand(expectedCompanyMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Company expectedCompany = new CompanyBuilder(INSTAGRAM).withTags().withRoles().build();
        assertParseSuccess(parser, NAME_DESC_INSTAGRAM + PHONE_DESC_INSTAGRAM
                        + EMAIL_DESC_INSTAGRAM + ADDRESS_DESC_INSTAGRAM,
                new AddCompanyCommand(expectedCompany));

        // missing phone prefix and value
        expectedCompany = new CompanyBuilder(WHATSAPP).withTags().withoutPhone().withRoles().build();
        assertParseSuccess(parser, NAME_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP + ADDRESS_DESC_WHATSAPP,
                new AddCompanyCommand(expectedCompany));

        // missing email prefix and value
        expectedCompany = new CompanyBuilder(WHATSAPP).withTags().withoutEmail().withRoles().build();
        assertParseSuccess(parser, NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP + ADDRESS_DESC_WHATSAPP,
                new AddCompanyCommand(expectedCompany));

        // missing address prefix
        expectedCompany = new CompanyBuilder(WHATSAPP).withTags().withoutAddress().withRoles().build();
        assertParseSuccess(parser, NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP,
                new AddCompanyCommand(expectedCompany));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_WHATSAPP + PHONE_DESC_WHATSAPP
                        + EMAIL_DESC_WHATSAPP + ADDRESS_DESC_WHATSAPP,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_WHATSAPP + VALID_PHONE_WHATSAPP
                        + VALID_EMAIL_WHATSAPP + VALID_ADDRESS_WHATSAPP,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP
                + ADDRESS_DESC_WHATSAPP + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_WHATSAPP + INVALID_PHONE_DESC + EMAIL_DESC_WHATSAPP
                + ADDRESS_DESC_WHATSAPP + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP + INVALID_EMAIL_DESC
                + ADDRESS_DESC_WHATSAPP + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        // assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB +
        // INVALID_ADDRESS_DESC
        // + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP + EMAIL_DESC_WHATSAPP
                + ADDRESS_DESC_WHATSAPP + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_WHATSAPP
                        + EMAIL_DESC_WHATSAPP + INVALID_ADDRESS_DESC,
                CompanyName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_WHATSAPP + PHONE_DESC_WHATSAPP
                + EMAIL_DESC_WHATSAPP + ADDRESS_DESC_WHATSAPP + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
    }
}
