package at.fhhgb.mc.task01;

import at.fhhgb.mc.opr.backblazedata.model.HardDisk;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataProcessorManual extends DataProcessor {
    public DataProcessorManual(Vector<HardDisk> hardDisks) {
        super(hardDisks);
    }

    @Override
    public Vector<HardDisk> filter(Predicate<HardDisk> filterFunction) {
        Vector<HardDisk> filteredDisks = new Vector<>();

        for (HardDisk disk : this.hardDisks) {
            if (filterFunction.test(disk)) {
                filteredDisks.add(disk);
            }
        }

        return filteredDisks;
    }

    @Override
    public HardDisk max(Comparator<HardDisk> comparator) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute max without hard disks");
        }

        HardDisk maxDisk = this.hardDisks.getFirst();

        for (HardDisk disk : this.hardDisks) {
            if (comparator.compare(disk, maxDisk) > 0) {
                maxDisk = disk;
            }
        }

        return maxDisk;
    }

    @Override
    public HardDisk min(Comparator<HardDisk> comparator) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute min without hard disks");
        }

        HardDisk minDisk = this.hardDisks.getFirst();

        for (HardDisk disk : this.hardDisks) {
            if (comparator.compare(disk, minDisk) < 0) {
                minDisk = disk;
            }
        }

        return minDisk;
    }

    @Override
    public double mean(Function<HardDisk, Long> mappingFunction) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute mean without hard disks");
        }

        double sum = 0;

        for (HardDisk disk : this.hardDisks) {
            sum += mappingFunction.apply(disk);
        }

        return sum / this.hardDisks.size();
    }

    @Override
    public long median(Comparator<HardDisk> sortingComparator, Function<HardDisk, Long> mappingFunction) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute median without hard disks");
        }

        Vector<HardDisk> sortedDisks = new Vector<>(this.hardDisks);
        sortedDisks.sort(sortingComparator);

        boolean isEven = sortedDisks.size() % 2 == 0;
        int medianIndex = sortedDisks.size() / 2;

        HardDisk medianDisk = sortedDisks.get(medianIndex);
        double median = mappingFunction.apply(medianDisk);

        if (isEven) {
            HardDisk evenDisk = sortedDisks.get(medianIndex - 1);
            median += mappingFunction.apply(evenDisk);
            median /= 2;
        }

        return (long) (0.5 + median);
    }

    @Override
    public long countDistinctStrings(Function<HardDisk, String> mappingFunction) {
        HashSet<String> hashSet = new HashSet<>();

        for (HardDisk disk : this.hardDisks) {
            String value = mappingFunction.apply(disk);
            hashSet.add(value);
        }

        return hashSet.size();
    }
}
