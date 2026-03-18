package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;

public class DeleteAssignmentCommandParser implements Parser<DeleteAssignmentCommand> {

    private static final String PATH_ASSIGNMENTS = "/assignment";

    @Override
    public DeleteAssignmentCommand parse(String args) throws ParseException {
        String trimmed = args.trim();

        if (!trimmed.startsWith(PATH_ASSIGNMENTS)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));
        }

        String idText = trimmed.substring(PATH_ASSIGNMENTS.length()).trim();

        if (idText.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));
        }

        AssignmentId assignmentId = ParserUtil.parseAssignmentId(idText);

        return new DeleteAssignmentCommand(assignmentId);
    }
}