package at.fhhgb.mc.ue05.task02;

import at.fhhgb.mc.shared.ValueException;

public class AmateurAthlete extends ActiveMember {
    public AmateurAthlete(String name, int activityLevel) throws ValueException {
        super(name, activityLevel);
    }

    @Override
    public double getIncome() {
        return 25.0;
    }

    @Override
    public double getCosts() {
        return this.activityLevel * 2.5;
    }
}
