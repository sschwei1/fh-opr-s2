package at.fhhgb.mc.ue05.task02;

public class SupportingMember extends AbstractMember {
    public SupportingMember(String name) {
        super(name);
    }

    @Override
    public double getIncome() {
        return 100.0;
    }

    @Override
    public double getCosts() {
        return 15.0;
    }
}
