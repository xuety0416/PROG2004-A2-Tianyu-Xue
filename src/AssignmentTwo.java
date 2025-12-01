public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("PROG2004 A2 Assignment - Part4A: Ride History Storage");

        RideInterface rollerCoaster = new Ride("Roller Coaster", 12, 60);
        Visitor v1 = new Visitor("Alice", 15, "V001", "123456");
        Visitor v3 = new Visitor("Charlie", 20, "V003", "345678");

        // 演示历史存储
        rollerCoaster.addVisitorToHistory(v1);
        rollerCoaster.addVisitorToHistory(v3);
        rollerCoaster.printRideHistory();

        System.out.println("\nIs Alice in history? " + rollerCoaster.checkVisitorFromHistory(v1));
        System.out.println("Total visitors in history: " + rollerCoaster.countVisitorsInHistory());
    }
}