package at.fhhgb.mc.task02;

public class ChairMember extends AbstractMember {
    private int competenceLevel;

    public ChairMember(String name, int competenceLevel) {
        super(name);
        this.competenceLevel = Math.clamp(competenceLevel, 0, 10);
    }

    @Override
    public double getIncome() {
        return this.competenceLevel * 100.0;
    }

    @Override
    public double getCosts() {
        return this.getIncome() * 0.2;
    }
}
