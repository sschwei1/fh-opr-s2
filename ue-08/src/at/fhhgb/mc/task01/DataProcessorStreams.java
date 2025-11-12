package at.fhhgb.mc.task01;

import at.fhhgb.mc.opr.backblazedata.model.HardDisk;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataProcessorStreams extends DataProcessor {
    public DataProcessorStreams(Vector<HardDisk> hardDisks) {
        super(hardDisks);
    }

    @Override
    public Vector<HardDisk> filter(Predicate<HardDisk> filterFunction) {
        return this.hardDisks.stream()
            .filter(filterFunction)
            .collect(Collectors.toCollection(Vector::new));
    }

    @Override
    public HardDisk max(Comparator<HardDisk> comparator) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute max without hard disks");
        }

        return this.hardDisks.stream()
            .max(comparator)
            .orElse(null);
    }

    @Override
    public HardDisk min(Comparator<HardDisk> comparator) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute min without hard disks");
        }

        return this.hardDisks.stream()
            .min(comparator)
            .orElse(null);
    }

    @Override
    public double mean(Function<HardDisk, Long> mappingFunction) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute mean without hard disks");
        }

        return this.hardDisks.stream()
            .map(mappingFunction)
            .mapToDouble(Long::doubleValue)
            .average()
            .orElse(0);
    }

    @Override
    public long median(Comparator<HardDisk> comparator, Function<HardDisk, Long> mappingFunction) {
        if(this.hardDisks.isEmpty()) {
            throw new NoSuchElementException("Unable to compute median without hard disks");
        }

        boolean isEven = this.hardDisks.size() % 2 == 0;
        int medianIndex = this.hardDisks.size() / 2;

        if(isEven) {
            medianIndex -= 1;
        }

        return (long) (0.5 + this.hardDisks.stream()
            .sorted(comparator)
            .skip(medianIndex)
            .limit(isEven ? 2 : 1)
            .map(mappingFunction)
            .mapToLong(Long::longValue)
            .average()
            .orElse(0));
    }

    @Override
    public long countDistinctStrings(Function<HardDisk, String> mappingFunction) {
        return this.hardDisks.stream()
            .map(mappingFunction)
            .distinct()
            .count();
    }
}
