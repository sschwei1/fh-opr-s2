package at.fhhgb.mc.task02;

public class AmateurAthlete extends ActiveMember {
    public AmateurAthlete(String name, int activityLevel) {
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
