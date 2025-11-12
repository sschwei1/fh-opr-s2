package at.fhhgb.mc.task01;

import at.fhhgb.mc.opr.backblazedata.loaders.HardDiskDataSource;
import at.fhhgb.mc.opr.backblazedata.model.HardDisk;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DummyDataSource implements HardDiskDataSource {
    private int currentIndex;
    private List<HardDisk> hardDisks;

    public DummyDataSource() {
        this.currentIndex = 0;

        Date now = new Date();
        this.hardDisks = List.of(
            // Small capacity disks
            new HardDisk(now, "SN12345", "ModelX", 2500000L, false, new LinkedList<>()),
            new HardDisk(now, "SN00001", "TinyDisk", 512L, false, new LinkedList<>()),
            new HardDisk(now, "SN00002", "MiniDisk", 1024L, true, new LinkedList<>()),

            // Medium capacity disks
            new HardDisk(now, "SN67890", "ModelY", 123000000000L, true, new LinkedList<>()),
            new HardDisk(now, "SN50000", "Standard-1", 500107862016L, false, new LinkedList<>()),
            new HardDisk(now, "SN50001", "Standard-2", 1000204886016L, false, new LinkedList<>()),

            // Large capacity disks
            new HardDisk(now, "SN90000", "Enterprise-1", 4000000000000L, false, new LinkedList<>()),
            new HardDisk(now, "SN90001", "Enterprise-1", 8000000000000L, true, new LinkedList<>()),
            new HardDisk(now, "SN90002", "Enterprise-2", 16000000000000L, false, new LinkedList<>()),

            // Edge cases
            new HardDisk(now, "SN99999", "ZeroCap", 0L, false, new LinkedList<>()),
            new HardDisk(now, "SN99998", "MaxCap", Long.MAX_VALUE, false, new LinkedList<>()),
            new HardDisk(null, "SN00000", "NullDate", 1000000L, false, new LinkedList<>())
        );
    }

    @Override
    public HardDisk next() {
        if(this.currentIndex >= this.hardDisks.size()) {
            return null;
        }

        return this.hardDisks.get(this.currentIndex++);
    }
}
