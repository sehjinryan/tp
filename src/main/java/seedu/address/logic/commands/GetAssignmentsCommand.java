package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENTS;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

/**
 * Gets and lists all assignments in LeTutor
 */
public class GetAssignmentsCommand extends Command {

    public static final String MESSAGE_USAGE =
            "get /assignments: Lists all assignments.\n"
                    + "Example: get /assignments";

    private static final String MESSAGE_NO_ASSIGNMENTS = "No assignments found.";
    private static final String MESSAGE_LIST_HEADER = "Assignments:";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENTS);

        ObservableList<Assignment> assignments = model.getFilteredAssignmentList();
        if (assignments.isEmpty()) {
            return new CommandResult(MESSAGE_NO_ASSIGNMENTS);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_LIST_HEADER);

        return new CommandResult("Now displaying all assignments");
    }
}
