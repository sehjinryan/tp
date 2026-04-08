package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.group.exceptions.NotInGroupException;
import seedu.address.model.person.StudentId;

public class AssignmentList {

    public final ArrayList<AssignmentId> assignments;

    /**
     * Constructs an {@code AssignmentList}.
     */
    public AssignmentList() {
        assignments = new ArrayList<>();
    }

    /**
     * Constructs a filled {@code AssignmentList}.
     * @param assignments assignmentId to fill
     */
    public AssignmentList(ArrayList<AssignmentId> assignments) {
        this.assignments = new ArrayList<>();
        this.assignments.addAll(assignments);
    }

    /**
     * Returns the ArrayList of AssignmentId that correspond to the Assignments in the Group.
     * @return The ArrayList of AssignmentId.
     */
    public ArrayList<AssignmentId> getAssignmentList() {
        return this.assignments;
    }

    /**
     * Adds an assignment identifier to this list.
     *
     * @param id The assignment identifier to add.
     * @throws NullPointerException if {@code id} is null.
     * @throws AlreadyInGroupException if {@code id} already exists in this list.
     */
    public void addAssignment(AssignmentId id) throws AlreadyInGroupException {
        requireNonNull(id);
        if (assignments.contains(id)) {
            throw new AlreadyInGroupException(
                    "Specified assignment is already in this group!");
        }

        assignments.add(id);
    }

    /**
     * Removes an assignment identifier from this list.
     *
     * @param id The assignment identifier to remove.
     * @throws NullPointerException if {@code id} is null.
     * @throws NotInGroupException if {@code id} does not exist in this list.
     */
    public void removeAssignment(AssignmentId id) throws NotInGroupException {
        requireNonNull(id);
        if (!assignments.contains(id)) {
            throw new NotInGroupException(
                    "Specified assignment is not in this group!");
        }

        assignments.remove(id);
    }

    @Override
    public String toString() {
        return assignments.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignmentList)) {
            return false;
        }

        AssignmentList otherAssignments = (AssignmentList) other;
        return assignments.equals(otherAssignments.assignments);
    }

    @Override
    public int hashCode() {
        return assignments.hashCode();
    }
}
