package at.fhhgb.mc.task01;

import at.fhhgb.mc.opr.backblazedata.loaders.HardDiskDataSource;
import at.fhhgb.mc.opr.backblazedata.model.HardDisk;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class DataProcessor {
    protected Vector<HardDisk> hardDisks;

    /**
     * Constructor for DataProcessor that initializes the hardDisks vector.
     *
     * @param hardDisks Vector of HardDisk objects to be processed.
     */
    public DataProcessor(Vector<HardDisk> hardDisks) {
        this.hardDisks = hardDisks;
    }

    public static String formatCapacity(double bytes) {
        String[] units = {"B", "KB", "MB", "GB", "TB", "PB", "EB"};

        if(bytes == 0) {
            return "0 B";
        }

        int digitGroup = (int) (Math.log10(bytes) / Math.log10(1024));
        digitGroup = Math.min(digitGroup, units.length - 1);

        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(bytes / Math.pow(1024, digitGroup)) + " " + units[digitGroup];
    }

    public static String formatHardDisk(HardDisk disk) {
        if(disk == null) {
            return "";
        }

        return String.format("%s [%s - %s] - Status = %s",
            DataProcessor.formatCapacity(disk.getCapacityInBytes()),
            disk.getModel(),
            disk.getSerialNumber(),
            disk.isFailing() ? "Damaged" : "OK"
        );
    }

    public static Vector<HardDisk> getVectorFromSource(HardDiskDataSource source) {
        Vector<HardDisk> hardDisks = new Vector<>();

        HardDisk disk = source.next();
        while(disk != null) {
            hardDisks.add(disk);
            disk = source.next();
        }

        return hardDisks;
    }

    /**
     * Sorts the hardDisks vector using the provided comparator.
     *
     * @param comparator Comparator to determine the order of the hard disks.
     */
    public void sort(Comparator<HardDisk> comparator) {
        this.hardDisks.sort(comparator);
    }

    /**
     * Returns the number of hard disks in the vector.
     *
     * @return The count of hard disks.
     */
    public long count() {
        return this.hardDisks.size();
    }

    /**
     * Filters the hardDisks vector based on the provided filter function.
     *
     * @param filterFunction Predicate function to filter hard disks.
     * @return A new Vector containing hard disks that match the filter criteria.
     */
    public abstract Vector<HardDisk> filter(Predicate<HardDisk> filterFunction);

    /**
     * Finds the maximum hard disk based on the provided comparator.
     *
     * @param comparator Comparator to determine the maximum hard disk.
     * @return The hard disk with the maximum value according to the comparator.
     * @throws NoSuchElementException if the hardDisks vector is empty.
     */
    public abstract HardDisk max(Comparator<HardDisk> comparator);

    /**
     * Finds the minimum hard disk based on the provided comparator.
     *
     * @param comparator Comparator to determine the minimum hard disk.
     * @return The hard disk with the minimum value according to the comparator.
     * @throws NoSuchElementException if the hardDisks vector is empty.
     */
    public abstract HardDisk min(Comparator<HardDisk> comparator);

    /**
     * Calculates the mean of a specific attribute of the hard disks
     *
     * @param mappingFunction Function to map each HardDisk to a Long value (e.g., capacity).
     * @return The mean value of the mapped attribute.
     * @throws NoSuchElementException if the hardDisks vector is empty.
     */
    public abstract double mean(Function<HardDisk, Long> mappingFunction);

    /**
     * Calculates the median of a specific attribute of the hard disks.
     *
     * @param comparator Comparator to sort the hard disks.
     * @param mappingFunction Function to map each HardDisk to a Long value (e.g., capacity).
     * @return The median value of the mapped attribute.
     * @throws NoSuchElementException if the hardDisks vector is empty.
     */
    public abstract long median(Comparator<HardDisk> comparator, Function<HardDisk, Long> mappingFunction);

    /**
     * Counts the number of distinct strings based on a mapping function applied to the hard disks.
     *
     * @param mappingFunction Function to map each HardDisk to a String value.
     * @return The count of distinct strings produced by the mapping function.
     */
    public abstract long countDistinctStrings(Function<HardDisk, String> mappingFunction);
}
