package at.fhhgb.mc.task02;

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

    @Override
    public int compareTo(AbstractMember o) {
        return this.name.compareTo(o.name);
    }
}
