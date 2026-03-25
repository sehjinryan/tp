package seedu.address.model.group;

/**
 * Value object representing the groupId of a particular Group.
 */
public final class GroupName {
    private final String name;

    /**
     * Constructs an {@code GroupName} with the given id.
     *
     * @param name The group name.
     * @throws NullPointerException if {@code name} is null.
     */
    public GroupName(String name) {
        this.name = name;
    }

    /**
     * Returns the String value of this Group identifier.
     *
     * @return The Group identifier as a String.
     */
    public String getGroupName() {
        return this.name;
    }
}
