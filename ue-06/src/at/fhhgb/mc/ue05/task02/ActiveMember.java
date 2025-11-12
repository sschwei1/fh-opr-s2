package at.fhhgb.mc.ue05.task02;

import at.fhhgb.mc.shared.ValueException;

public abstract class ActiveMember extends AbstractMember {
    protected int activityLevel;

    public ActiveMember(String name, int activityLevel) throws ValueException {
        super(name);
        this.validateValue(activityLevel);
        this.activityLevel = activityLevel;
    }
}
