package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIndexPredicate;

/**
 * Gets a student identified using their student ID.
 */
public class GetStudentCommand extends Command {

    public static final String COMMAND_WORD = "get";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " /students <studentId>: Gets the student identified by the given student ID.\n"
            + "Example: " + COMMAND_WORD + " /students S1";

    public static final String MESSAGE_GOT_PERSON_SUCCESS = "Got Person: %1$s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found: %1$s";

    private final StudentId studentId;

    public GetStudentCommand(StudentId studentId) {
        requireNonNull(studentId);
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToGet = null;
        for (Person person : lastShownList) {
            if (person.getStudentId().equals(studentId)) {
                personToGet = person;
                break;
            }
        }

        if (personToGet == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, studentId));
        }

        model.updateFilteredPersonList(new StudentIndexPredicate(personToGet));
        return new CommandResult(String.format(MESSAGE_GOT_PERSON_SUCCESS, Messages.format(personToGet)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GetStudentCommand)) {
            return false;
        }

        GetStudentCommand otherGetStudentCommand = (GetStudentCommand) other;
        return studentId.equals(otherGetStudentCommand.studentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .toString();
    }
}
