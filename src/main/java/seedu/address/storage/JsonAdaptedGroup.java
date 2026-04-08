package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.group.AssignmentList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.StudentList;
import seedu.address.model.person.StudentId;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String name;
    private final ArrayList<String> students;
    private final ArrayList<String> assignments;

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("name") String name,
                            @JsonProperty("students") ArrayList<String> students,
                            @JsonProperty("assignments") ArrayList<String> assignments) {
        this.name = name;
        this.students = students;
        this.assignments = assignments;
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        name = source.getGroupName().name;
        students = new ArrayList<>();
        assignments = new ArrayList<>();

        for (StudentId id : source.getStudentIds().list) {
            students.add(id.getValue());
        }

        for (AssignmentId id : source.getAssignmentIds().assignments) {
            assignments.add(id.getValue());
        }
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupName.class.getSimpleName()));
        }
        final GroupName modelName = new GroupName(name);

        if (students == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentList.class.getSimpleName()));
        }

        final ArrayList<StudentId> studentIds = new ArrayList<>();

        for (String id : students) {
            studentIds.add(new StudentId(id));
        }

        final StudentList modelStudentList = new StudentList(studentIds);

        if (assignments == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupName.class.getSimpleName()));
        }

        final ArrayList<AssignmentId> assignmentIds = new ArrayList<>();

        for (String id : assignments) {
            assignmentIds.add(new AssignmentId(id));
        }

        final AssignmentList modelAssignmentList = new AssignmentList(assignmentIds);

        return new Group(modelName, modelStudentList, modelAssignmentList);
    }
}
