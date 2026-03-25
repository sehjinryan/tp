package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.StudentList;
import seedu.address.model.person.StudentId;

import java.util.ArrayList;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String name;
    private final ArrayList<StudentId> students;

    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("name") String name,
                            @JsonProperty("students") ArrayList<StudentId> students) {
        this.name = name;
        this.students = students;
    }

    public JsonAdaptedGroup(Group source) {
        name = source.getGroupName();
        students = source.getStudentIds();
    }

    public Group toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupName.class.getSimpleName()));
        }
        final GroupName modelName = new GroupName(name);

        if (students == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentList.class.getSimpleName()));
        }

        final StudentList modelStudentList = new StudentList(students);

        return new Group(modelName, modelStudentList);
    }
}
