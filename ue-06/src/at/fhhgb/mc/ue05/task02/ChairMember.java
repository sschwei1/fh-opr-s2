package at.fhhgb.mc.ue05.task02;

import at.fhhgb.mc.shared.ValueException;

public class ChairMember extends AbstractMember {
    private int competenceLevel;

    public ChairMember(String name, int competenceLevel) throws ValueException {
        super(name);
        this.validateValue(competenceLevel);
        this.competenceLevel = competenceLevel;
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
