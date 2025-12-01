public class Ride implements RideInterface { // 新增实现接口
    private String rideName;
    private int minAge;
    private int maxAge;

    public Ride(String rideName, int minAge, int maxAge) {
        this.rideName = rideName;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    // getter/setter（不变）
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }
    public int getMaxAge() { return maxAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }

    // 实现接口的空方法（仅占位，后续Part3/4补逻辑）
    @Override
    public void addVisitorToQueue(Visitor visitor) {}
    @Override
    public void removeVisitorFromQueue(Visitor visitor) {}
    @Override
    public void printQueue() {}
    @Override
    public void addVisitorToHistory(Visitor visitor) {}
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) { return false; }
    @Override
    public int countVisitorsInHistory() { return 0; }
    @Override
    public void printRideHistory() {}
}