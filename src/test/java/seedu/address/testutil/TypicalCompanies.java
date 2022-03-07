package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalRoles.ML_ENGINEER;
import static seedu.address.testutil.TypicalRoles.MOBILE_ENGINEER;
//import static seedu.address.testutil.TypicalRoles.NETWORK_ENGINEER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CompanyList;
import seedu.address.model.company.Company;

/**
 * A utility class containing a list of {@code Company} objects to be used in tests.
 */
public class TypicalCompanies {

    public static final Company ALICE = new CompanyBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withRoles(ML_ENGINEER, MOBILE_ENGINEER)
            .build();
    public static final Company BENSON = new CompanyBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withRoles(MOBILE_ENGINEER)
            .build();
    public static final Company CARL = new CompanyBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            // .withRoles(NETWORK_ENGINEER)
            .build();
    public static final Company DANIEL = new CompanyBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            // .withRoles(MOBILE_ENGINEER, NETWORK_ENGINEER)
            .build();
    public static final Company ELLE = new CompanyBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            // .withRoles(ML_ENGINEER)
            .build();
    public static final Company FIONA = new CompanyBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            // .withRoles(NETWORK_ENGINEER)
            .build();
    public static final Company GEORGE = new CompanyBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            // .withRoles(NETWORK_ENGINEER)
            .build();

    // Manually added
    public static final Company HOON = new CompanyBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            //  .withRoles(MOBILE_ENGINEER)
            .build();
    public static final Company IDA = new CompanyBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            // .withRoles(ML_ENGINEER, MOBILE_ENGINEER, NETWORK_ENGINEER)
            .build();

    // Manually added - Company's details found in {@code CommandTestUtil}
    public static final Company AMY = new CompanyBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            // .withRoles(NETWORK_ENGINEER)
            .build();
    public static final Company BOB = new CompanyBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            // .withRoles(ML_ENGINEER)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCompanies() {} // prevents instantiation

    /**
     * Returns an {@code CompanyList} with all the typical companies.
     */
    public static CompanyList getTypicalAddressBook() {
        CompanyList ab = new CompanyList();
        for (Company company : getTypicalCompanies()) {
            ab.addCompany(company);
        }
        return ab;
    }

    public static List<Company> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
