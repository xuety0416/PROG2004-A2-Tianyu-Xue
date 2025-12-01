import java.util.LinkedList;
import java.util.Queue;

public class Ride implements RideInterface {
    private String rideName;
    private int minAge;
    private int maxAge;
    // 新增：等待队列
    private Queue<Visitor> waitingQueue;

    public Ride(String rideName, int minAge, int maxAge) {
        this.rideName = rideName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.waitingQueue = new LinkedList<>(); // 初始化队列
    }

    // getter/setter（不变）
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }
    public int getMaxAge() { return maxAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }

    // 实现队列相关方法（Part3核心）
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        // 年龄校验
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

    // 其他接口方法（仍空实现，后续补）
    @Override
    public void addVisitorToHistory(Visitor visitor) {}
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) { return false; }
    @Override
    public int countVisitorsInHistory() { return 0; }
    @Override
    public void printRideHistory() {}
}