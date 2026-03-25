package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupManager;
import seedu.address.model.milestone.MilestoneStore;
import seedu.address.model.person.Person;

import java.util.ArrayList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    ObservableList<Person> getPersonList();

    MilestoneStore getMilestoneStore();

    /**
     * Returns an unmodifiable view of the assignments list.
     */
    ObservableList<Assignment> getAssignmentList();

    ArrayList<Group> getGroups();
}
