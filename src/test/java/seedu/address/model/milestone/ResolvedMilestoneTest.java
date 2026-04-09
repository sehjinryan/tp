package seedu.address.model.milestone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Label;
import seedu.address.model.group.Group;
import seedu.address.model.person.StudentId;

public class ResolvedMilestoneTest {

    private final StudentId studentId = new StudentId("S1");
    private final Assignment assignment = new Assignment(
            new AssignmentId("A1"),
            new Label("Quiz 1"),
            Set.of(new Group("G1")),
            new DueDate("2026-04-01"));
    private final MilestoneRecord rawRecord = new MilestoneRecord(
            MilestoneStatus.COMPLETED,
            new CompletedAt("2026-03-30T1200H"));

    @Test
    public void constructorAndGetters_success() {
        ResolvedMilestone resolvedMilestone = new ResolvedMilestone(
                studentId,
                assignment,
                rawRecord,
                ResolvedMilestoneStatus.COMPLETED
        );

        assertEquals(studentId, resolvedMilestone.getStudentId());
        assertEquals(assignment, resolvedMilestone.getAssignment());
        assertEquals(new AssignmentId("A1"), resolvedMilestone.getAssignmentId());
        assertEquals(new DueDate("2026-04-01"), resolvedMilestone.getDueDate());
        assertEquals(rawRecord, resolvedMilestone.getRawRecord());
        assertEquals(MilestoneStatus.COMPLETED, resolvedMilestone.getRawStatus());
        assertEquals(new CompletedAt("2026-03-30T1200H"), resolvedMilestone.getCompletedAt());
        assertEquals(ResolvedMilestoneStatus.COMPLETED, resolvedMilestone.getResolvedStatus());
    }

    @Test
    public void equals() {
        ResolvedMilestone resolvedMilestone = new ResolvedMilestone(
                studentId,
                assignment,
                rawRecord,
                ResolvedMilestoneStatus.COMPLETED
        );

        ResolvedMilestone sameResolvedMilestone = new ResolvedMilestone(
                new StudentId("S1"),
                new Assignment(
                        new AssignmentId("A1"),
                        new Label("Quiz 1"),
                        Set.of(new Group("G1")),
                        new DueDate("2026-04-01")),
                new MilestoneRecord(
                        MilestoneStatus.COMPLETED,
                        new CompletedAt("2026-03-30T1200H")),
                ResolvedMilestoneStatus.COMPLETED
        );

        ResolvedMilestone differentResolvedMilestone = new ResolvedMilestone(
                studentId,
                assignment,
                new MilestoneRecord(MilestoneStatus.NOT_STARTED, new CompletedAt("")),
                ResolvedMilestoneStatus.NOT_STARTED
        );

        assertEquals(resolvedMilestone, resolvedMilestone);
        assertEquals(resolvedMilestone, sameResolvedMilestone);

        assertNotEquals(resolvedMilestone, null);
        assertNotEquals(resolvedMilestone, "not a resolved milestone");
        assertNotEquals(resolvedMilestone, differentResolvedMilestone);
    }
}
