import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 先按姓名升序排序，姓名相同按年龄升序
        int nameCompare = v1.getName().compareToIgnoreCase(v2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}