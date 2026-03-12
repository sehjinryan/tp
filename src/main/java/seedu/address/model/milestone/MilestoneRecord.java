package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Immutable milestone record for one student-assignment pair.
 */
public final class MilestoneRecord {
    public static final String MESSAGE_COMPLETED_AT_REQUIRES_COMPLETED_STATUS =
            "completedAt can only be non-empty when status is COMPLETED.";

    private final MilestoneStatus status;
    private final CompletedAt completedAt;

    /**
     * Constructs a {@code MilestoneRecord} with the specified status and completion timestamp.
     *
     * @param status The milestone status.
     * @param completedAt The completion timestamp of the milestone.
     * @throws NullPointerException if either argument is null.
     * @throws IllegalArgumentException if {@code completedAt} is non-empty but the status is not COMPLETED.
     */
    public MilestoneRecord(MilestoneStatus status, CompletedAt completedAt) {
        requireNonNull(status);
        requireNonNull(completedAt);

        if (!completedAt.isEmpty() && status != MilestoneStatus.COMPLETED) {
            throw new IllegalArgumentException(MESSAGE_COMPLETED_AT_REQUIRES_COMPLETED_STATUS);
        }

        this.status = status;
        this.completedAt = completedAt;
    }

    /**
     * Returns the current status of this milestone.
     *
     * @return The {@code MilestoneStatus} of the milestone.
     */
    public MilestoneStatus getStatus() {
        return status;
    }

    /**
     * Returns the completion timestamp associated with this milestone.
     *
     * @return The {@code CompletedAt} value indicating when the milestone was completed.
     */
    public CompletedAt getCompletedAt() {
        return completedAt;
    }

    /**
     * Returns true if the specified object is equal to this milestone record.
     * Two {@code MilestoneRecord} objects are considered equal if their
     * status and completion timestamp are equal.
     *
     * @param other The object to compare with.
     * @return True if the objects represent the same milestone record.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MilestoneRecord)) {
            return false;
        }
        MilestoneRecord otherRecord = (MilestoneRecord) other;
        return status.equals(otherRecord.status)
                && completedAt.equals(otherRecord.completedAt);
    }

    /**
     * Returns the hash code for this milestone record.
     * The hash code is computed using the milestone status and completion timestamp.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return status.hashCode() * 31 + completedAt.hashCode();
    }

    /**
     * Returns the string representation of this milestone record.
     *
     * @return A string containing the milestone status and completion timestamp.
     */
    @Override
    public String toString() {
        return "MilestoneRecord{status=" + status + ", completedAt=" + completedAt + "}";
    }
}
