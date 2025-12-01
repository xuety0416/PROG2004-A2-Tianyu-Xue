public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("PROG2004 A2 Assignment - Part2: Abstract Class + Interface");

        // 演示接口实现
        RideInterface rollerCoaster = new Ride("Roller Coaster", 12, 60);
        System.out.println("Ride created: " + ((Ride) rollerCoaster).getRideName());
    }
}