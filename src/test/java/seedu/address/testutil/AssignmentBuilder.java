package seedu.address.testutil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_ASSIGNMENT_ID = "A1";
    public static final String DEFAULT_LABEL = "Jane Doe";
    public static final String DEFAULT_GROUP = "Group";
    public static final String DEFAULT_DUEDATE = "2026-04-01";

    private AssignmentId assignmentId;
    private Label label;
    private Set<Group> groups;
    private DueDate dueDate;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        assignmentId = new AssignmentId(DEFAULT_ASSIGNMENT_ID);
        label = new Label(DEFAULT_LABEL);
        groups = new HashSet<>(Collections.singleton(new Group(
                DEFAULT_GROUP)));
        dueDate = new DueDate(DEFAULT_DUEDATE);
    }

    /**
     * Initializes the {@code AssignmentBuilder} with the data of {@code assignmentToCopy}.
     */
    public AssignmentBuilder(Assignment assignmentToCopy) {
        assignmentId = assignmentToCopy.getAssignmentId();
        label = assignmentToCopy.getLabel();
        groups = new HashSet<>(assignmentToCopy.getGroups());
        dueDate = assignmentToCopy.getDueDate();
    }

    /**
     * Sets the {@code AssignmentId} of the {@code Assignment} being built.
     */
    public AssignmentBuilder withAssignmentId(String assignmentId) {
        this.assignmentId = new AssignmentId(assignmentId);
        return this;
    }

    /**
     * Sets the {@code Label} of the {@code Assignment} being built.
     */
    public AssignmentBuilder withLabel(String label) {
        this.label = new Label(label);
        return this;
    }

    /**
     * Sets the {@code Group} of the {@code Assignment} being built.
     */
    public AssignmentBuilder withGroups(String ... groups) {
        this.groups = new HashSet<>();
        for (String groupName : groups) {
            this.groups.add(new Group(groupName));
        }
        return this;
    }

    /**
     * Sets the {@code DueDate} of the {@code Assignment} being built.
     */
    public AssignmentBuilder withDueDate(String dueDate) {
        this.dueDate = new DueDate(dueDate);
        return this;
    }

    public Assignment build() {
        return new Assignment(assignmentId, label, groups, dueDate);
    }
}
