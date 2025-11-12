package at.fhhgb.mc.task01;

import at.fhhgb.mc.opr.backblazedata.loaders.HardDiskDataSource;
import at.fhhgb.mc.opr.backblazedata.model.HardDisk;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DataProcessorTest {
    public static final String parameterizedTestName = "[{index}] {0}";

    private static Vector<HardDisk> createTestDisks() {
        return DataProcessor.getVectorFromSource(new DummyDataSource());
    }

    public static Stream<Arguments> processorProvider() {
        return Stream.of(
            Arguments.of("Streams", new DataProcessorStreams(createTestDisks())),
            Arguments.of("Manual", new DataProcessorManual(createTestDisks()))
        );
    }

    private static DataProcessor createProcessor(String type, Vector<HardDisk> hardDisks) {
        return type.equals("Streams")
            ? new DataProcessorStreams(hardDisks)
            : new DataProcessorManual(hardDisks);
    }

    private static DataProcessor createEmptyProcessor(String type) {
        return createProcessor(type, new Vector<>());
    }

    @Test
    public void testFormatCapacity() {
        assertEquals("0 B", DataProcessor.formatCapacity(0));
        assertEquals("1 KB", DataProcessor.formatCapacity(1024));
        assertEquals("1 MB", DataProcessor.formatCapacity(Math.pow(1024, 2)));
        assertEquals("1 GB", DataProcessor.formatCapacity(Math.pow(1024, 3)));
        assertEquals("1 TB", DataProcessor.formatCapacity(Math.pow(1024, 4)));

        assertEquals("1.5 GB", DataProcessor.formatCapacity(1.5 * Math.pow(1024, 3)));
        assertEquals("1.23 TB", DataProcessor.formatCapacity(1.23 * Math.pow(1024, 4)));
        assertEquals("1.5 PB", DataProcessor.formatCapacity(1.5 * Math.pow(1024, 5)));
        assertEquals("1.5 EB", DataProcessor.formatCapacity(1.5 * Math.pow(1024, 6)));
    }

    @Test
    public void testFormatHardDisk() {
        HardDisk disk = new HardDisk(
            new Date(),
            "SN12345",
            "ModelX",
            (long) (2.5 * Math.pow(1024, 2)),
            false,
            new LinkedList<>()
        );
        String expected = "2.5 MB [ModelX - SN12345] - Status = OK";
        assertEquals(expected, DataProcessor.formatHardDisk(disk));

        HardDisk disk2 = new HardDisk(
            new Date(),
            "SN67890",
            "ModelY",
            (long) (123 * Math.pow(1024, 4)),
            true,
            new LinkedList<>()
        );
        expected = "123 TB [ModelY - SN67890] - Status = Damaged";
        assertEquals(expected, DataProcessor.formatHardDisk(disk2));

        // Test null disk
        assertEquals("", DataProcessor.formatHardDisk(null));
    }

    @Test
    public void testGetVectorFromSource() {
        Vector<HardDisk> hardDisks = DataProcessor.getVectorFromSource(new DummyDataSource());
        assertEquals(12, hardDisks.size());

        HardDiskDataSource someOtherSource1 = () -> null;
        hardDisks = DataProcessor.getVectorFromSource(someOtherSource1);
        assertEquals(0, hardDisks.size());

        HardDiskDataSource someOtherSource2 = new HardDiskDataSource() {
            private int index = 0;
            private final HardDisk[] disks = new HardDisk[] {
                new HardDisk(new Date(), "SN00001", "TestModel1", 1000L, false, new LinkedList<>()),
                new HardDisk(new Date(), "SN00002", "TestModel2", 2000L, true, new LinkedList<>())
            };

            @Override
            public HardDisk next() {
                if(index >= disks.length) {
                    return null;
                }

                return disks[index++];
            }
        };

        hardDisks = DataProcessor.getVectorFromSource(someOtherSource2);
        assertEquals(2, hardDisks.size());
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testSort(String type, DataProcessor processor) {
        // Make a copy of original data
        Vector<HardDisk> original = new Vector<>(processor.hardDisks);

        // Test capacity sort
        processor.sort(Comparator.comparing(HardDisk::getCapacityInBytes));
        assertSorted(processor.hardDisks, Comparator.comparing(HardDisk::getCapacityInBytes));
        assertSameElements(original, processor.hardDisks);

        // Test empty case
        DataProcessor empty = createEmptyProcessor(type);
        assertDoesNotThrow(() -> empty.sort(Comparator.comparing(HardDisk::getCapacityInBytes)));
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testCount(String type, DataProcessor processor) {
        assertEquals(12, processor.count());
        assertEquals(0, createEmptyProcessor(type).count());
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testFilter(String type, DataProcessor processor) {
        Vector<HardDisk> damaged = processor.filter(HardDisk::isFailing);
        assertEquals(3, damaged.size());
        assertTrue(damaged.stream().allMatch(HardDisk::isFailing));

        // Test empty case
        assertEquals(0, createEmptyProcessor(type).filter(HardDisk::isFailing).size());
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testMax(String type, DataProcessor processor) {
        // Test max capacity
        HardDisk maxDisk = processor.max(Comparator.comparing(HardDisk::getCapacityInBytes));
        assertEquals(Long.MAX_VALUE, maxDisk.getCapacityInBytes());

        // Test empty case
        DataProcessor empty = createEmptyProcessor(type);
        assertThrows(NoSuchElementException.class, () ->
            empty.max(Comparator.comparing(HardDisk::getCapacityInBytes))
        );
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testMin(String type, DataProcessor processor) {
        // Test min capacity
        HardDisk minDisk = processor.min(Comparator.comparing(HardDisk::getCapacityInBytes));
        assertEquals(0L, minDisk.getCapacityInBytes());

        // Test empty case
        DataProcessor empty = createEmptyProcessor(type);
        assertThrows(NoSuchElementException.class, () ->
            empty.min(Comparator.comparing(HardDisk::getCapacityInBytes))
        );
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testMean(String type, DataProcessor processor) {
        // Test mean capacity
        double mean = processor.mean(HardDisk::getCapacityInBytes);
        assertEquals(7.68616805014252E17, mean);

        // Test empty case
        DataProcessor empty = createEmptyProcessor(type);
        assertThrows(NoSuchElementException.class, () ->
            empty.mean(HardDisk::getCapacityInBytes)
        );
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testMedian(String type, DataProcessor processor) {
        long median = processor.median(
            Comparator.comparing(HardDisk::getCapacityInBytes),
            HardDisk::getCapacityInBytes
        );
        assertEquals(311553931008L ,median);

        // Test median with an odd number of elements
        Vector<HardDisk> subSet = new Vector<>(processor.hardDisks.subList(0, 3));
        processor = createProcessor(type, subSet);
        median = processor.median(
            Comparator.comparing(HardDisk::getCapacityInBytes),
            HardDisk::getCapacityInBytes
        );
        assertEquals(1024L, median);

        // Test empty case
        DataProcessor empty = createEmptyProcessor(type);
        assertThrows(NoSuchElementException.class, () ->
            empty.median(
                Comparator.comparing(HardDisk::getCapacityInBytes),
                HardDisk::getCapacityInBytes
            )
        );
    }

    @ParameterizedTest(name = DataProcessorTest.parameterizedTestName)
    @MethodSource("processorProvider")
    public void testCountDistinctStrings(String type, DataProcessor processor) {
        // Test distinct models (only 1 duplicate)
        assertEquals(11, processor.countDistinctStrings(HardDisk::getModel));

        // Test status strings (only "OK" and "Damaged")
        assertEquals(2, processor.countDistinctStrings(h -> h.isFailing() ? "Damaged" : "OK"));

        // Test empty case
        assertEquals(0, createEmptyProcessor(type).countDistinctStrings(HardDisk::getModel));
    }

    private void assertSorted(Vector<HardDisk> disks, Comparator<HardDisk> comparator) {
        for (int i = 0; i < disks.size() - 1; i++) {
            assertTrue(comparator.compare(disks.get(i), disks.get(i + 1)) <= 0);
        }
    }

    private void assertSameElements(Vector<HardDisk> expected, Vector<HardDisk> actual) {
        assertAll(
            () -> assertEquals(expected.size(), actual.size()),
            () -> assertTrue(actual.containsAll(expected)),
            () -> assertTrue(expected.containsAll(actual))
        );
    }
}
