package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.milestone.CompletedAt;
import seedu.address.model.milestone.MilestoneStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Sets one milestone record for a student-assignment pair.
 */
public class SetMilestoneCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE =
            "set /students <studentId> /milestones <assignmentId> <status> [completedAt]: "
                    + "Sets one milestone for a student.\n"
                    + "Allowed statuses: NOT_STARTED, COMPLETED\n"
                    + "If status is NOT_STARTED, do not provide completedAt.\n"
                    + "If status is COMPLETED, completedAt is required.\n"
                    + "Example: set /students S1 /milestones A1 NOT_STARTED\n"
                    + "Example: set /students S1 /milestones A1 COMPLETED 2026-03-30T1200H";

    public static final String MESSAGE_SUCCESS =
            "Set milestone for student %1$s and assignment %2$s: %3$s, completedAt=%4$s";

    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found: %1$s";
    public static final String MESSAGE_ASSIGNMENT_NOT_FOUND = "Assignment not found: %1$s";

    private final StudentId studentId;
    private final AssignmentId assignmentId;
    private final MilestoneStatus status;
    private final CompletedAt completedAt;

    /**
     * Constructs a {@code SetMilestoneCommand}.
     */
    public SetMilestoneCommand(StudentId studentId, AssignmentId assignmentId,
                               MilestoneStatus status, CompletedAt completedAt) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);
        requireNonNull(status);
        requireNonNull(completedAt);

        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.status = status;
        this.completedAt = completedAt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!hasStudent(model, studentId)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, studentId));
        }

        if (!hasAssignment(model, assignmentId)) {
            throw new CommandException(String.format(MESSAGE_ASSIGNMENT_NOT_FOUND, assignmentId));
        }

        model.setMilestone(studentId, assignmentId, status, completedAt);

        String completedAtValue = completedAt.getValue().isBlank() ? "-" : completedAt.getValue();
        return new CommandResult(String.format(
                MESSAGE_SUCCESS, studentId, assignmentId, status, completedAtValue));
    }

    /**
     * Returns true if the student exists in the model.
     */
    private boolean hasStudent(Model model, StudentId studentId) {
        for (Person person : model.getAddressBook().getPersonList()) {
            if (person.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the assignment exists in the model.
     */
    private boolean hasAssignment(Model model, AssignmentId assignmentId) {
        for (Assignment assignment : model.getAssignmentList()) {
            if (assignment.getAssignmentId().equals(assignmentId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SetMilestoneCommand)) {
            return false;
        }

        SetMilestoneCommand otherCommand = (SetMilestoneCommand) other;
        return studentId.equals(otherCommand.studentId)
                && assignmentId.equals(otherCommand.assignmentId)
                && status.equals(otherCommand.status)
                && completedAt.equals(otherCommand.completedAt);
    }
}
