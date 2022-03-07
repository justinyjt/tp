package seedu.address.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.company.Company;
import seedu.address.model.role.Role;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CompanyList level 4</a>
     */

    public final Company company;
    public final ObservableList<Role> roleList;
    public final RoleListPanel roleListPanel;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane roleTags;
    @FXML
    private ListView<Role> rolesListView;
    @FXML
    private StackPane roleListPanelPlaceholder;

    /**
     * Creates a {@code CompanyCard} with the given {@code Company} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getName().fullName);

        String phoneField = company.getPhone().value;
        String addressField = company.getAddress().value;
        String emailField = company.getEmail().value;

        phone.setText(phoneField);
        address.setText(addressField);
        email.setText(emailField);

        if (phoneField == "") {
            phone.setManaged(false);
        }
        if (addressField == "") {
            address.setManaged(false);
        }
        if (emailField == "") {
            email.setManaged(false);
        }
        setRoleTags();
        roleList = company.getRoleManager().getRoles();
        roleListPanel = new RoleListPanel(roleList);
        roleListPanelPlaceholder.getChildren().add(roleListPanel.getRoot());

        roleList.addListener((ListChangeListener<Role>) change -> {
            roleTags.getChildren().clear();
            setRoleTags();
            if (roleList.isEmpty()) {
                roleListPanelPlaceholder.getChildren().clear();
            }
        });
    }

    /**
     * Populate <code>roleTags</code> with names of roles tagged to the company represented by this
     * <code>CompanyCard</code>
     */
    public void setRoleTags() {
        company.getRoleManager().getSetRoles().stream()
                .forEach(roleTag -> roleTags.getChildren().add(new Label(roleTag.getName().fullName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyCard)) {
            return false;
        }

        // state check
        CompanyCard card = (CompanyCard) other;
        return id.getText().equals(card.id.getText())
                && company.equals(card.company);
    }
}
