package at.fhhgb.mc.ue05.task02;

public class HonoraryMember extends AbstractMember {
    public HonoraryMember(String name) {
        super(name);
    }

    @Override
    public double getIncome() {
        return 0;
    }

    @Override
    public double getCosts() {
        return 20.0;
    }
}
