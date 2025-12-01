import java.util.LinkedList;
import java.util.Queue;

public class Ride implements RideInterface {
    private String rideName;
    private int minAge;
    private int maxAge;
    private Queue<Visitor> waitingQueue;
    // 新增：骑行历史（LinkedList）
    private LinkedList<Visitor> rideHistory;

    public Ride(String rideName, int minAge, int maxAge) {
        this.rideName = rideName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>(); // 初始化历史链表
    }

    // getter/setter（不变，可新增rideHistory的getter供后续使用）
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }
    public int getMaxAge() { return maxAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }
    public LinkedList<Visitor> getRideHistory() { return rideHistory; } // 新增getter

    // 队列相关方法（不变）
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor.getAge() >= minAge && visitor.getAge() <= maxAge) {
            waitingQueue.offer(visitor);
            System.out.println("Added visitor " + visitor.getVisitorId() + " to queue.");
        } else {
            System.out.println("Visitor " + visitor.getVisitorId() + " age not eligible.");
        }
    }
    @Override
    public void removeVisitorFromQueue(Visitor visitor) {
        if (waitingQueue.contains(visitor)) {
            waitingQueue.remove(visitor);
            System.out.println("Removed visitor " + visitor.getVisitorId() + " from queue.");
        } else {
            System.out.println("Visitor " + visitor.getVisitorId() + " not in queue.");
        }
    }
    @Override
    public void printQueue() {
        System.out.println("\nWaiting Queue for " + rideName + ":");
        if (waitingQueue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        for (Visitor v : waitingQueue) {
            System.out.println("Visitor ID: " + v.getVisitorId() + ", Name: " + v.getName());
        }
    }

    // 实现骑行历史方法（Part4A核心）
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        rideHistory.add(visitor);
        System.out.println("Added visitor " + visitor.getVisitorId() + " to ride history.");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        return rideHistory.contains(visitor);
    }

    @Override
    public int countVisitorsInHistory() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        System.out.println("\nRide History for " + rideName + ":");
        if (rideHistory.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }
        for (Visitor v : rideHistory) {
            System.out.println("ID: " + v.getVisitorId() + ", Name: " + v.getName() + ", Age: " + v.getAge());
        }
    }
}