package at.fhhgb.mc.ue05.task02;

import at.fhhgb.mc.shared.ValueException;

public class Trainer extends ActiveMember {
    public Trainer(String name, int activityLevel) throws ValueException {
        super(name, activityLevel);
    }

    @Override
    public double getIncome() {
        return 10.0;
    }

    @Override
    public double getCosts() {
        return this.activityLevel * 40;
    }
}
