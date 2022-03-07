package seedu.address.model.role;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents the list of all roles tagged to a Company found in the address book.
 */
public class RoleManager {
    private ObservableList<Role> roleList;

    public RoleManager() {
        this.roleList = FXCollections.observableArrayList();
    }

    public RoleManager(ObservableList<Role> roleList) {
        this.roleList = roleList;
    }

    /**
     * Obtains the list of roles tagged to a Company
     *
     * @return <code>ObservableList</code> representing the list of roles
     */
    public ObservableList<Role> getRoles() {
        return this.roleList;
    }

    /**
     * Obtains the unmodifiable set of roles tagged to a Company
     *
     * @return <code>Set</code> representing the set of roles
     */
    public Set<Role> getSetRoles() {
        Set<Role> roleSet = new HashSet<Role>(this.roleList);
        return Collections.unmodifiableSet(roleSet);
    }

    /**
     * Obtains the number of roles
     *
     * @return Integer representing the number of roles
     */
    public int countRoles() {
        return this.roleList.size();
    }

    /**
     * Add <code>Role</code> to list of roles tagged to a company
     * @param role Role to be added to the list of roles
     */
    public void addRole(Role role) {
        this.roleList.add(role);
    }

    /**
     * Checks if this role exists in the roleList
     * @param role role to check if it is duplicated
     * @return Boolean representing if this role is already stored
     */
    public boolean hasRole(Role role) {
        requireNonNull(role);
        return roleList.contains(role);
    }

    public void deleteRole(int index) {
        this.roleList.remove(index);
    }
}
