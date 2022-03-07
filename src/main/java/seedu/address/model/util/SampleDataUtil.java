package seedu.address.model.util;


import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.CompanyList;
import seedu.address.model.ReadOnlyCompanyList;
import seedu.address.model.company.Address;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Email;
import seedu.address.model.company.Phone;
import seedu.address.model.role.Deadline;
import seedu.address.model.role.Description;
import seedu.address.model.role.Role;
import seedu.address.model.role.Status;
import seedu.address.model.role.Stipend;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code CompanyList} with sample data.
 */
public class SampleDataUtil {

    private static final Role SAMPLE_ROLE_1 = new Role(new seedu.address.model.role.RoleName("Software engineer"),
            new Status("pending"), new Deadline("27-02-2022 18:00"),
            new Description("Backend engineer"), new Stipend("1000"));
    private static final Role SAMPLE_ROLE_2 = new Role(new seedu.address.model.role.RoleName("Software engineer"),
            new Status("pending"), new Deadline("28-02-2022 22:00"),
            new Description("Frontend engineer"), new Stipend("1000"));

    private static final List<Role> SAMPLE_ROLES_1 = Arrays.asList(SAMPLE_ROLE_1);
    private static final List<Role> SAMPLE_ROLES_2 = Arrays.asList(SAMPLE_ROLE_2);
    private static final List<Role> SAMPLE_ROLES_3 = Arrays.asList(SAMPLE_ROLE_1, SAMPLE_ROLE_2);

    public static Company[] getSampleCompanies() {
        return new Company[]{
                new Company(new CompanyName("Meta"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends"), SAMPLE_ROLES_1),
                new Company(new CompanyName("Amazon"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends"), SAMPLE_ROLES_1),
                new Company(new CompanyName("Netflix"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("friends"), SAMPLE_ROLES_2),
                new Company(new CompanyName("Google"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family"), SAMPLE_ROLES_2),
                new Company(new CompanyName("Apple"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates"), SAMPLE_ROLES_3),
                new Company(new CompanyName("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues"), SAMPLE_ROLES_3)
        };
    }

    public static ReadOnlyCompanyList getSampleAddressBook() {
        CompanyList sampleAb = new CompanyList();
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static List<Role> getRoleList(Role... roles) {
        return Arrays.stream(roles)
                .collect(Collectors.toList());
    }

}
