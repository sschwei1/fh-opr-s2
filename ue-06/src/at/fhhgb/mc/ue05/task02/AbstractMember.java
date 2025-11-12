package at.fhhgb.mc.ue05.task02;

import at.fhhgb.mc.shared.ValueException;

public abstract class AbstractMember implements Comparable<AbstractMember> {
    protected String name;

    public AbstractMember(String name) {
        this.name = name;
    }

    public abstract double getIncome();
    public abstract double getCosts();

    public double getSurplus() {
        return this.getIncome() - this.getCosts();
    }

    public String toString() {
        return this.toString(true);
    }

    public String toString(boolean ascending) {
        return this.toString(ascending, "");
    }

    protected String toString(boolean ascending, String prefix) {
        String className = this.getClass().getSimpleName();

        // In case of anonymous classes, the class name is empty
        if (className.isEmpty()) {
            className = "AbstractMember";
        }

        return String.format("%s [%s, Surplus: %.2f€]",
                this.name,
                className,
                this.getSurplus()
        );
    }

    protected void validateValue(int value) throws ValueException{
        validateValue(value, 0, 10);
    }

    protected void validateValue(int value, int min, int max) throws ValueException {
        if (value < min || value > max) {
            throw new ValueException(value);
        }
    }

    @Override
    public int compareTo(AbstractMember o) {
        return this.name.compareTo(o.name);
    }
}
