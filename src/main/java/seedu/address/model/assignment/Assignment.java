package seedu.address.model.assignment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

/**
 * Represents an Assignment in LeTutor.
 * Guarantees: details are present, field values are validated, immutable.
 */
public class Assignment {

    private final AssignmentId assignmentId;
    private final Label label;
    private final Set<Group> groups;
    private final DueDate dueDate;

    /**
     * Creates an {@code Assignment} with the given identifier, label, group, and due date.
     * Every field must be present.
     *
     * @param assignmentId Unique identifier for the assignment (e.g., A301).
     * @param label The label shown to users (e.g., A-JUnit).
     * @param groups The groups associated with this assignment (e.g., G01, G02).
     * @param dueDate Due date of assignment (can be "" depending on DueDate rules).
     */
    public Assignment(AssignmentId assignmentId, Label label, Set<Group> groups, DueDate dueDate) {
        this.assignmentId = assignmentId;
        this.label = label;
        this.groups = new HashSet<>(groups);
        this.dueDate = dueDate;
    }

    /**
     * Returns the unique identifier of this assignment.
     *
     * @return The assignment identifier.
     */
    public AssignmentId getAssignmentId() {
        return assignmentId;
    }

    /**
     * Returns the label of this assignment.
     *
     * @return The assignment label.
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Returns the groups associated with this assignment.
     *
     * @return The groups associated with this assignment.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    /**
     * Returns the due date of this assignment.
     *
     * @return The assignment due date.
     */
    public DueDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns true if both Assignments have the same label.
     * This defines a weaker notion of equality between two Assignments.
     *
     * @param otherAssignment The assignment to compare against.
     * @return {@code true} if the assignments have the same label, {@code false} otherwise.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }
        return otherAssignment != null
                && otherAssignment.getLabel().equals(getLabel());
    }

    /**
     * Getter that find the {@Code GroupName} of all Groups
     * @return the GroupName of all the groups
     */
    private Set<GroupName> getGroupNames() {
        return groups.stream()
                .map(Group::getGroupName)
                .collect(Collectors.toSet());
    }

    /**
     * Returns true if both Assignments have the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        Set<GroupName> thisGroupNames = getGroupNames();
        Set<GroupName> otherGroupNames = otherAssignment.getGroupNames();
        return assignmentId.equals(otherAssignment.assignmentId)
                && label.equals(otherAssignment.label)
                && thisGroupNames.equals(otherGroupNames)
                && dueDate.equals(otherAssignment.dueDate);
    }

    /**
     * Returns the hash code value of this assignment.
     *
     * @return The hash code of this assignment.
     */
    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, label, groups, dueDate);
    }

    /**
     * Returns a string representation of this assignment.
     *
     * @return A string representation of this assignment.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("assignmentId", assignmentId)
                .add("label", label)
                .add("group", groups)
                .add("dueDate", dueDate)
                .toString();
    }
}
