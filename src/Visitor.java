public class Visitor extends Person {
    private String visitorId;       // 访客编号（专属属性）
    private String membershipLevel; // 会员等级（专属属性：Standard/Gold/Platinum）

    public Visitor() {}

    public Visitor(String name, int age, String idNumber, String visitorId, String membershipLevel) {
        super(name, age, idNumber);
        this.visitorId = visitorId;
        this.membershipLevel = membershipLevel;

        // 校验必填字段（作业隐含：身份证号和访客ID为唯一标识，不能为空）
        if (idNumber == null || idNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("访客身份证号不能为空");
        }
        if (visitorId == null || visitorId.trim().isEmpty()) {
            throw new IllegalArgumentException("访客ID不能为空");
        }
    }

    // getter/setter 方法
    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    // 重写 toString()，方便打印
    @Override
    public String toString() {
        return "Visitor{ID=" + visitorId + ", 姓名=" + getName() + ", 年龄=" + getAge() + ", 会员等级=" + membershipLevel + "}";
    }
}