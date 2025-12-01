public interface RideInterface {
    // 等待队列相关方法（Part3预留）
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue(Visitor visitor);
    void printQueue();

    // 骑行历史相关方法（Part4预留）
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(Visitor visitor);
    int countVisitorsInHistory();
    void printRideHistory();
}