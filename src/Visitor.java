public class Visitor extends Person {
    private String visitorId;
    private String contactNumber;

    public Visitor(String name, int age, String visitorId, String contactNumber) {
        super(name, age);
        this.visitorId = visitorId;
        this.contactNumber = contactNumber;
    }

    // getter/setter
    public String getVisitorId() { return visitorId; }
    public void setVisitorId(String visitorId) { this.visitorId = visitorId; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}