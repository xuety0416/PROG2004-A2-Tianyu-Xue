public class Ride {
    private String rideName;
    private int minAge;
    private int maxAge;

    public Ride(String rideName, int minAge, int maxAge) {
        this.rideName = rideName;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    // getter/setter
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }
    public int getMaxAge() { return maxAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }
}