package at.fhhgb.mc.task01;

import at.fhhgb.mc.opr.backblazedata.loaders.LiveHardDiskDataSource;
import at.fhhgb.mc.opr.backblazedata.model.HardDisk;

import java.util.Comparator;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<HardDisk> hardDisks = DataProcessor.getVectorFromSource(new LiveHardDiskDataSource());

        DataProcessor dataProcessorStreams = new DataProcessorStreams(hardDisks);
        DataProcessor dataProcessorManual = new DataProcessorManual(hardDisks);

        Main.printHardDisks(dataProcessorStreams);
        System.out.println("========================================");
        Main.printHardDisks(dataProcessorManual);
    }

    private static void printHardDisks(DataProcessor dataProcessor) {
        StringBuilder sb = new StringBuilder();

        sb.append("Disk Count: ")
            .append(dataProcessor.count())
            .append(System.lineSeparator());

        var damagedDisks = dataProcessor.filter(HardDisk::isFailing);
        sb.append("Defect Disks: ")
            .append(damagedDisks.size())
            .append(System.lineSeparator());

        var sizeComparator = Comparator.comparingLong(HardDisk::getCapacityInBytes);

        HardDisk maxDisk = dataProcessor.max(sizeComparator);
        sb.append("Biggest Disk: ")
            .append(DataProcessor.formatHardDisk(maxDisk))
            .append(System.lineSeparator());

        HardDisk minDisk = dataProcessor.min(sizeComparator);
        sb.append("Smallest Disk: ")
            .append(DataProcessor.formatHardDisk(minDisk))
            .append(System.lineSeparator());

        double meanSize = dataProcessor.mean(HardDisk::getCapacityInBytes);
        sb.append("Average Capacity: ")
            .append(DataProcessor.formatCapacity(meanSize))
            .append(System.lineSeparator());

        double medianSize = dataProcessor.median(sizeComparator, HardDisk::getCapacityInBytes);
        sb.append("Median Capacity: ")
            .append(DataProcessor.formatCapacity(medianSize))
            .append(System.lineSeparator());

        sb.append("Distinct Models: ")
            .append(dataProcessor.countDistinctStrings(HardDisk::getModel))
            .append(System.lineSeparator());

        var medianSmartValue = dataProcessor.median(
            Comparator.comparingInt(hd -> hd.getSmartValues().size()),
            hardDisk -> (long) hardDisk.getSmartValues().size()
        );
        sb.append("Median SMART Value: ")
            .append(medianSmartValue)
            .append(System.lineSeparator());

        System.out.println(sb);
    }
}
