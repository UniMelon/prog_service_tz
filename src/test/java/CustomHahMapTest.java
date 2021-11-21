import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomHahMapTest {

    @Test
    public void putAndGetTest() {
        CustomHashMap customHashMap = new CustomHashMap();
        customHashMap.put(1, 3);
        customHashMap.put(4, 31);
        customHashMap.put(9, 77);
        customHashMap.put(45, 46);
        customHashMap.put(21, 2020);

        assertEquals(31L, customHashMap.get(4));
        assertEquals(3L, customHashMap.get(1));
        assertEquals(77L, customHashMap.get(9));
        assertEquals(46L, customHashMap.get(45));
        assertEquals(2020L, customHashMap.get(21));
    }

    @Test
    public void putAndGetWhenValueOverriddenTest() {
        CustomHashMap customHashMap = new CustomHashMap();

        customHashMap.put(1, 5);
        assertEquals(5L, customHashMap.get(1));

        customHashMap.put(1, 7);
        assertEquals(7L, customHashMap.get(1));
    }

    @Test
    public void putAndGetWithCollisionTest() {
        CustomHashMap customHashMap = new CustomHashMap();
        customHashMap.put(12, 1);
        customHashMap.put(-22, 2);
        customHashMap.put(32, 3);
        customHashMap.put(-52, 4);
        customHashMap.put(72, 5);

        assertEquals(5, customHashMap.getSize());
    }

    @Test
    public void getSizeOfEmptyMapTest() {
        CustomHashMap customHashMap = new CustomHashMap();
        assertEquals(0, customHashMap.getSize());
        ;
    }

    @Test
    public void checkIfMapSizeIncreasedTest() {
        CustomHashMap customHashMap = new CustomHashMap();
        for (int i = 0; i < 100; i++)
            customHashMap.put(i, i * i);

        assertEquals(100, customHashMap.getSize());

        for (int i = 0; i < 100; i++)
            assertEquals(i * i, customHashMap.get(i));
    }
}
