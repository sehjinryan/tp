package seedu.address.model.milestone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.AssignmentId;

public class StudentMilestonesTest {

    private final AssignmentId assignmentIdOne = new AssignmentId("A1");
    private final AssignmentId assignmentIdTwo = new AssignmentId("A2");

    @Test
    public void setAndGetMilestone_success() {
        StudentMilestones studentMilestones = new StudentMilestones();
        MilestoneRecord record = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-30T1200H"));

        studentMilestones.set(assignmentIdOne, record);

        assertEquals(Optional.of(record), studentMilestones.get(assignmentIdOne));
    }

    @Test
    public void getMilestone_missingEntry_returnsEmptyOptional() {
        StudentMilestones studentMilestones = new StudentMilestones();

        assertFalse(studentMilestones.get(assignmentIdOne).isPresent());
    }

    @Test
    public void removeMilestone_success() {
        StudentMilestones studentMilestones = new StudentMilestones();
        MilestoneRecord record = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));

        studentMilestones.set(assignmentIdOne, record);
        studentMilestones.remove(assignmentIdOne);

        assertFalse(studentMilestones.get(assignmentIdOne).isPresent());
    }

    @Test
    public void removeAllForAssignment_success() {
        StudentMilestones studentMilestones = new StudentMilestones();

        MilestoneRecord firstRecord = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));
        MilestoneRecord secondRecord = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-30T1200H"));

        studentMilestones.set(assignmentIdOne, firstRecord);
        studentMilestones.set(assignmentIdTwo, secondRecord);

        studentMilestones.remove(assignmentIdOne);

        assertFalse(studentMilestones.get(assignmentIdOne).isPresent());
        assertEquals(Optional.of(secondRecord), studentMilestones.get(assignmentIdTwo));
    }

    @Test
    public void set_existingAssignment_replacesValue() {
        StudentMilestones studentMilestones = new StudentMilestones();

        MilestoneRecord firstRecord = new MilestoneRecord(
                MilestoneStatus.NOT_STARTED,
                new CompletedAt(""));
        MilestoneRecord updatedRecord = new MilestoneRecord(
                MilestoneStatus.COMPLETED,
                new CompletedAt("2026-03-30T1200H"));

        studentMilestones.set(assignmentIdOne, firstRecord);
        studentMilestones.set(assignmentIdOne, updatedRecord);

        assertEquals(Optional.of(updatedRecord), studentMilestones.get(assignmentIdOne));
    }
}
