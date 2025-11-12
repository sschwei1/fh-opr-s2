package at.fhhgb.mc.task02;

public class TopAthlete extends ActiveMember {
    public TopAthlete(String name, int activityLevel) {
        super(name, activityLevel);
    }

    @Override
    public double getIncome() {
        return 10.0;
    }

    @Override
    public double getCosts() {
        return this.activityLevel * 5;
    }
}
