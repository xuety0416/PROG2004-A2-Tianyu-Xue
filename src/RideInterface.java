public interface RideInterface {
    // Queue management methods
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue();
    void printQueue();

    // Ride history methods
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(Visitor visitor);
    int numberOfVisitors();
    void printRideHistory();

    // Ride operation method
    void runOneCycle();
}