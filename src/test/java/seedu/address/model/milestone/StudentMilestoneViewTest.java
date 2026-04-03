package seedu.address.model.milestone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;
import seedu.address.model.person.StudentId;

public class StudentMilestoneViewTest {

    private final StudentId studentId = new StudentId("S1");

    @Test
    public void constructor_defensiveCopy() {
        ResolvedMilestone resolvedMilestone = createResolvedMilestone("A1");

        List<ResolvedMilestone> originalList = new ArrayList<>();
        originalList.add(resolvedMilestone);

        StudentMilestoneView studentMilestoneView = new StudentMilestoneView(studentId, originalList);

        originalList.clear();

        assertEquals(1, studentMilestoneView.getMilestones().size());
    }

    @Test
    public void getMilestones_unmodifiableList() {
        ResolvedMilestone resolvedMilestone = createResolvedMilestone("A1");
        StudentMilestoneView studentMilestoneView =
                new StudentMilestoneView(studentId, List.of(resolvedMilestone));

        assertThrows(UnsupportedOperationException.class, () ->
                studentMilestoneView.getMilestones().add(createResolvedMilestone("A2")));
    }

    @Test
    public void equals() {
        ResolvedMilestone firstMilestone = createResolvedMilestone("A1");
        ResolvedMilestone secondMilestone = createResolvedMilestone("A2");

        StudentMilestoneView firstView =
                new StudentMilestoneView(studentId, List.of(firstMilestone, secondMilestone));

        StudentMilestoneView sameView =
                new StudentMilestoneView(new StudentId("S1"),
                        List.of(createResolvedMilestone("A1"), createResolvedMilestone("A2")));

        StudentMilestoneView differentView =
                new StudentMilestoneView(studentId, List.of(firstMilestone));

        assertEquals(firstView, firstView);
        assertEquals(firstView, sameView);

        assertNotEquals(firstView, null);
        assertNotEquals(firstView, "not a view");
        assertNotEquals(firstView, differentView);
    }

    private ResolvedMilestone createResolvedMilestone(String assignmentId) {
        Assignment assignment = new Assignment(
                new AssignmentId(assignmentId),
                new Label("Quiz " + assignmentId),
                new Group("G1"),
                new DueDate("2026-04-01")
        );

        MilestoneRecord rawRecord = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));

        return new ResolvedMilestone(
                studentId,
                assignment,
                rawRecord,
                ResolvedMilestoneStatus.NOT_STARTED
        );
    }
}
