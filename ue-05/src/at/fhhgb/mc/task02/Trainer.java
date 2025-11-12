package at.fhhgb.mc.task02;

public class Trainer extends ActiveMember {
    public Trainer(String name, int activityLevel) {
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
