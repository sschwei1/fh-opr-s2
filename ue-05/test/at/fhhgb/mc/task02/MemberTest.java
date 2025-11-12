package at.fhhgb.mc.task02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    @Test
    public void testAbstractMember() {
        AbstractMember member = new AbstractMember("David") {
            @Override
            public double getIncome() {
                return 50.0;
            }

            @Override
            public double getCosts() {
                return 20.0;
            }
        };

        assertEquals(50.0, member.getIncome());
        assertEquals(20.0, member.getCosts());
        assertEquals(30, member.getSurplus());
        assertEquals("David [AbstractMember, Surplus: 30.00€]", member.toString());
    }

    @Test
    public void testActiveMember() {
        ActiveMember member = new Trainer("Chris", 5);
        assertEquals(10.0, member.getIncome());
        assertEquals(200.0, member.getCosts());
        assertEquals(-190, member.getSurplus());

        member = new TopAthlete("Alex", 3);
        assertEquals(10.0, member.getIncome());
        assertEquals(15.0, member.getCosts());
        assertEquals(-5, member.getSurplus());

        member = new AmateurAthlete("Jordan", 4);
        assertEquals(25.0, member.getIncome());
        assertEquals(10.0, member.getCosts());
        assertEquals(15, member.getSurplus());

        // Test activity level out of range 0-10
        member = new Trainer("Maximus", 100);
        assertEquals(400.0, member.getCosts());

        member = new TopAthlete("Minimus", -17);
        assertEquals(0.0, member.getCosts());
    }

    @Test
    public void testChairMember() {
        ChairMember member = new ChairMember("Alice", 8);
        assertEquals(800.0, member.getIncome());
        assertEquals(160.0, member.getCosts());
        assertEquals(640.0, member.getSurplus());
    }

    @Test
    public void testHonoraryMember() {
        HonoraryMember member = new HonoraryMember("Bob");
        assertEquals(0.0, member.getIncome());
        assertEquals(20.0, member.getCosts());
        assertEquals(-20.0, member.getSurplus());
    }

    @Test
    public void testSupportingMember() {
        SupportingMember member = new SupportingMember("Johanna");
        assertEquals(100.0, member.getIncome());
        assertEquals(15.0, member.getCosts());
        assertEquals(85, member.getSurplus());
    }

    @Test
    public void testSection() {
        Section section = new Section("Some Fancy Section or so");
        assertEquals(0.0, section.getIncome());
        assertEquals(0.0, section.getCosts());
        assertEquals(0.0, section.getSurplus());

        Section subSection1 = new Section("Happy stuff or so");
        assertTrue(subSection1.addMember(new Trainer("Christopher", 3)));
        assertTrue(subSection1.addMember(new TopAthlete("Baum", 2)));
        assertTrue(subSection1.addMember(new AmateurAthlete("Hermann", 4)));
        assertTrue(subSection1.addMember(new ChairMember("Lisa", 5)));
        assertTrue(subSection1.addMember(new HonoraryMember("Max")));

        Section subSection2 = new Section("Other things");
        assertTrue(section.addMember(new Trainer("Rudi", 3)));
        assertTrue(section.addMember(new TopAthlete("Anna", 2)));
        assertTrue(section.addMember(new AmateurAthlete("Tom", 4)));
        assertTrue(section.addMember(new ChairMember("Lara", 5)));
        assertTrue(section.addMember(new HonoraryMember("Max")));
        assertTrue(section.addMember(new SupportingMember("Sophie")));
        assertTrue(section.addMember(new AbstractMember("David") {
            @Override
            public double getIncome() {
                return 50.0;
            }

            @Override
            public double getCosts() {
                return 20.0;
            }
        }));

        Section nestedSection = new Section("Some Nested Section");
        assertTrue(nestedSection.addMember(new Trainer("Nested Trainer", 2)));
        assertTrue(nestedSection.addMember(new TopAthlete("Nested TopAthlete", 1)));
        assertTrue(nestedSection.addMember(new AmateurAthlete("Nested AmateurAthlete", 3)));

        subSection1.addMember(nestedSection);
        assertTrue(section.addMember(subSection1));
        assertTrue(section.addMember(subSection2));

        AbstractMember someMember = new Trainer("Max", 7);
        assertTrue(section.isMember(someMember));
        assertFalse(section.addMember(someMember));
        assertTrue(section.removeMember(someMember));
        assertFalse(section.isMember(someMember));
        assertFalse(section.removeMember(someMember));

        assertEquals(1285.0, section.getIncome());
        assertEquals(627.5, section.getCosts());
        assertEquals(1285.0 - 627.5, section.getSurplus());

        String expectedStringAscending = """
                Some Fancy Section or so [Section, Surplus: 657.50€]
                  Anna [TopAthlete, Surplus: 0.00€]
                  David [AbstractMember, Surplus: 30.00€]
                  Happy stuff or so [Section, Surplus: 237.50€]
                    Baum [TopAthlete, Surplus: 0.00€]
                    Christopher [Trainer, Surplus: -110.00€]
                    Hermann [AmateurAthlete, Surplus: 15.00€]
                    Lisa [ChairMember, Surplus: 400.00€]
                    Max [HonoraryMember, Surplus: -20.00€]
                    Some Nested Section [Section, Surplus: -47.50€]
                      Nested AmateurAthlete [AmateurAthlete, Surplus: 17.50€]
                      Nested TopAthlete [TopAthlete, Surplus: 5.00€]
                      Nested Trainer [Trainer, Surplus: -70.00€]
                  Lara [ChairMember, Surplus: 400.00€]
                  Other things [Section, Surplus: 0.00€]
                  Rudi [Trainer, Surplus: -110.00€]
                  Sophie [SupportingMember, Surplus: 85.00€]
                  Tom [AmateurAthlete, Surplus: 15.00€]""".trim();

        String expectedStringDescending = """
                Some Fancy Section or so [Section, Surplus: 657.50€]
                  Tom [AmateurAthlete, Surplus: 15.00€]
                  Sophie [SupportingMember, Surplus: 85.00€]
                  Rudi [Trainer, Surplus: -110.00€]
                  Other things [Section, Surplus: 0.00€]
                  Lara [ChairMember, Surplus: 400.00€]
                  Happy stuff or so [Section, Surplus: 237.50€]
                    Some Nested Section [Section, Surplus: -47.50€]
                      Nested Trainer [Trainer, Surplus: -70.00€]
                      Nested TopAthlete [TopAthlete, Surplus: 5.00€]
                      Nested AmateurAthlete [AmateurAthlete, Surplus: 17.50€]
                    Max [HonoraryMember, Surplus: -20.00€]
                    Lisa [ChairMember, Surplus: 400.00€]
                    Hermann [AmateurAthlete, Surplus: 15.00€]
                    Christopher [Trainer, Surplus: -110.00€]
                    Baum [TopAthlete, Surplus: 0.00€]
                  David [AbstractMember, Surplus: 30.00€]
                  Anna [TopAthlete, Surplus: 0.00€]
                """.trim();

        assertEquals(expectedStringAscending, section.toString().trim());
        assertEquals(expectedStringDescending, section.toString(false).trim());
    }
}
