package at.fhhgb.mc.task02;

public abstract class ActiveMember extends AbstractMember {
    protected int activityLevel;

    public ActiveMember(String name, int activityLevel) {
        super(name);
        this.activityLevel = Math.clamp(activityLevel, 0, 10);
    }
}
