package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " /assignment: Deletes an assignment from the assignment library.\n"
            + "Format: delete /assignment <assignmentId>\n"
            + "Example: delete /assignment A301";

    public static final String MESSAGE_NOT_FOUND = "Assignment not found: %s";

    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Deleted Assignment: %s";

    private final AssignmentId assignmentId;

    public DeleteAssignmentCommand(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        this.assignmentId = assignmentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Assignment> maybe = model.getAssignmentById(assignmentId);

        if (maybe.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NOT_FOUND, assignmentId));
        }

        Assignment assignmentToDelete = maybe.get();
        model.deleteAssignment(assignmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentId));
    }
}
