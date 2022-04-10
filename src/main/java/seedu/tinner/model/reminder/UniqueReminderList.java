package seedu.tinner.model.reminder;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.company.ReadOnlyRoleList;
import seedu.tinner.model.reminder.exceptions.DuplicateReminderException;
import seedu.tinner.model.role.Role;

/**
 * A list of Reminders that enforces uniqueness between its elements and does not allow nulls.
 * A Reminder is considered unique by comparing using {@code Reminder#isSameReminder(Reminder)}. As such, adding and
 * updating of reminders uses Reminder#isSameReminder(Reminder) for equality so as to ensure that the Reminder
 * being added or updated is unique in terms of identity in the UniqueReminderList. However, the removal of a Reminder
 * uses Reminder#equals(Object) so as to ensure that the Reminder with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Reminder#isSameReminder(Reminder)
 */
public class UniqueReminderList implements Iterable<Reminder> {

    private static UniqueReminderList reminderList;

    private final ObservableList<Reminder> internalList;
    private final ObservableList<Reminder> internalUnmodifiableList;
    private final HashMap<LocalDate, ArrayList<Reminder>> dateToReminderMap;

    /**
     * Constructor of UniqueReminderList
     */
    private UniqueReminderList() {
        internalList = FXCollections.observableArrayList();
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
        dateToReminderMap = new HashMap<>();
    }

    /**
     * Factory method of UniqueReminderList
     *
     * @return Produced UniqueReminderList object that is a Singleton.
     */
    public static UniqueReminderList getInstance() {
        if (reminderList == null) {
            reminderList = new UniqueReminderList();
        }
        return reminderList;
    }

    /**
     * Returns true if the list contains an equivalent Reminder as the given argument.
     */
    public boolean contains(Reminder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameReminder);
    }

    /**
     * Adds a Reminder to the list.
     * The Reminder must not already exist in the list.
     */
    public void add(Reminder toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateReminderException();
        }
        internalList.add(toAdd);

        LocalDateTime reminderDateTime = toAdd.getReminderDate().value;
        LocalDate reminderDate = reminderDateTime.toLocalDate();
        dateToReminderMap.computeIfAbsent(reminderDate, k -> new ArrayList<>());
        dateToReminderMap.get(reminderDate).add(toAdd);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public ObservableList<LocalDate> getReminderDates() {
        return FXCollections.unmodifiableObservableList(FXCollections
                .observableArrayList(new TreeSet<>(dateToReminderMap.keySet())));
    }

    /**
     * Returns the list of reminders associated with a specific date.
     *
     * @param date The date that which is used to retrieve the list of reminders.
     * @return the list of reminders mapped from the given date.
     */
    public ObservableList<Reminder> getDateSpecificReminders(LocalDate date) {
        ObservableList<Reminder> reminderList = FXCollections.observableArrayList();
        ArrayList<Reminder> retrievedReminders = dateToReminderMap.get(date);
        if (retrievedReminders == null) {
            return reminderList;
        }
        Collections.sort(retrievedReminders);
        reminderList.addAll(retrievedReminders);
        return reminderList;
    }

    /**
     * Resets the internal list of reminders using the given list of companies.
     *
     * @param companyList The company from which the reminders are to be produced.
     */
    public void setReminders(ObservableList<Company> companyList) {
        for (Company company : companyList) {
            ReadOnlyRoleList roleList = company.getRoleList();
            for (Role role : roleList.getRoleList()) {
                addFromRole(company, role);
            }
        }
    }

    /**
     * Checks the validity of the role with respect to the reminder window and
     * adds a new reminder generated from the given company and role.
     */
    public void addFromRole(Company company, Role role) {
        if (role.getReminderDate().isWithinReminderWindow()) {
            Reminder reminder = new Reminder(company.getName(),
                    role.getName(), role.getStatus(), role.getReminderDate());
            add(reminder);
        }
    }

    public Iterator<Reminder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueReminderList // instanceof handles nulls
                && internalList.equals(((UniqueReminderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
