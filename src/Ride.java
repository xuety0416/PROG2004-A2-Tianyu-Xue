import java.util.LinkedList;
import java.util.Queue;

/**
 * Ride class implements RideInterface, contains waiting queue and ride history functions.
 * Added Part5: Ride cycle functionality (maxRider, numOfCycles, runOneCycle)
 */
public class Ride implements RideInterface {
    private String rideName;
    private int minAge;
    private int maxAge;
    private Queue<Visitor> waitingQueue;
    private LinkedList<Visitor> rideHistory;

    // Part5新增：最大承载人数、已运行周期数
    private int maxRider;
    private int numOfCycles;

    // 构造器新增 maxRider 参数
    public Ride(String rideName, int minAge, int maxAge, int maxRider) {
        this.rideName = rideName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.maxRider = maxRider;
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0; // 初始周期数为0
    }

    // Getter/Setter（原有+新增）
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }
    public int getMaxAge() { return maxAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }
    public LinkedList<Visitor> getRideHistory() { return rideHistory; }

    // 提交3的队列方法（无变动）
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

    // 提交4的历史方法（无变动）
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

    // Part5核心方法：运行一个骑行周期（从队列取游客，转存到历史）
    public void runOneCycle() {
        System.out.println("\nRunning one cycle for " + rideName + "...");
        int ridersThisCycle = 0;

        // 取队列游客，直到达到最大承载或队列为空
        while (!waitingQueue.isEmpty() && ridersThisCycle < maxRider) {
            Visitor visitor = waitingQueue.poll();
            addVisitorToHistory(visitor); // 队列转历史
            ridersThisCycle++;
        }

        numOfCycles++; // 周期数+1
        System.out.println("Cycle " + numOfCycles + " completed. Riders: " + ridersThisCycle);
    }
}