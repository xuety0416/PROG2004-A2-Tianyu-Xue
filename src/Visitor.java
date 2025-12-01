public class Visitor extends Person {
    private String visitorId;       // Visitor ID (exclusive attribute)
    private String membershipLevel; // Membership level (exclusive attribute: Standard/Gold/Platinum)

    public Visitor() {}

    public Visitor(String name, int age, String idNumber, String visitorId, String membershipLevel) {
        super(name, age, idNumber);
        this.visitorId = visitorId;
        this.membershipLevel = membershipLevel;

        // Validate required fields (ID Number and Visitor ID are unique identifiers, cannot be empty)
        if (idNumber == null || idNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Visitor ID Number cannot be empty");
        }
        if (visitorId == null || visitorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Visitor ID cannot be empty");
        }
    }

    // getter/setter methods
    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Visitor{ID=" + visitorId + ", Name=" + getName() + ", Age=" + getAge() + ", Membership Level=" + membershipLevel + "}";
    }
}