package at.fhhgb.mc.ue05.task02;

import at.fhhgb.mc.shared.ValueException;
import at.fhhgb.mc.ue05.task01.BinarySearchTree;

public class Section extends AbstractMember {

    protected BinarySearchTree<AbstractMember> members;

    public Section(String name) {
        super(name);
        members = new BinarySearchTree<>(AbstractMember.class);
    }

    public boolean addMember(AbstractMember member) throws ValueException {
        return members.insert(member);
    }

    public boolean removeMember(AbstractMember member) throws ValueException {
        return members.remove(member);
    }

    public boolean isMember(AbstractMember member) throws ValueException {
        return members.find(member);
    }

    @Override
    public double getIncome() {
        AbstractMember[] memberArray = members.toArray(true);
        double totalIncome = 0;

        for (AbstractMember member : memberArray) {
            totalIncome += member.getIncome();
        }

        return totalIncome;
    }

    @Override
    public double getCosts() {
        AbstractMember[] memberArray = members.toArray(true);
        double totalCosts = 0;

        for (AbstractMember member : memberArray) {
            totalCosts += member.getCosts();
        }

        return totalCosts;
    }

    @Override
    protected String toString(boolean ascending, String prefix) {
        String self = super.toString(ascending, prefix);
        AbstractMember[] memberArray = members.toArray(ascending);

        StringBuilder sb = new StringBuilder();
        sb.append(self);

        prefix += "  ";

        for(AbstractMember member : memberArray) {
            sb.append(System.lineSeparator())
                .append(prefix)
                .append(member.toString(ascending, prefix));
        }

        return sb.toString();
    }
}
