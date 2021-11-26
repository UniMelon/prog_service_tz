import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CustomHahMapTest {

    private CustomHashMap hashMap;

    @Before
    public void setUp() {
        hashMap = new CustomHashMap();
    }

    @After
    public void tearDown(){
        hashMap = null;
    }

    @Test
    public void testNotCollision(){
        assertEquals(hashMap.hashIndex(0), 0);
        assertEquals(hashMap.hashIndex(1), 1);
        assertEquals(hashMap.hashIndex(9), 9);
        assertEquals(hashMap.hashIndex(15), 15);
    }

    @Test
    public void testIsCollision(){
        assertEquals(hashMap.hashIndex(16), 0);
        assertEquals(hashMap.hashIndex(17), 1);
        assertEquals(hashMap.hashIndex(30), 14);
    }

    @Test
    public void testPutNull(){
        hashMap.put(null, 1l);
        assertEquals(1l, hashMap.get(null));

        hashMap.put(null, 2l);
        assertEquals(2l, hashMap.get(null));

        hashMap.put(null, 3l);
        assertEquals(3l, hashMap.get(null));

        assertEquals(1, hashMap.getSize());
    }

    @Test
    public void testGetIsFound(){
        hashMap.put(1, 100l);
        hashMap.put(1, 101l);
        hashMap.put(11, 102l);
        hashMap.put(2, 200l);
        hashMap.put(3, 300l);
        hashMap.put(77, 707l);

        assertEquals(hashMap.get(1), 101l);
        assertEquals(hashMap.get(11), 102l);
        assertEquals(hashMap.get(2), 200l);
        assertEquals(hashMap.get(3), 300l);
        assertEquals(hashMap.get(77), 707l);
    }

    @Test
    public void testGetNotFound(){
        assertThrows(NoSuchElementException.class, () -> hashMap.get(5));
        assertThrows(NoSuchElementException.class, () -> hashMap.get(7));
    }

    @Test
    public void testSize(){
        hashMap.put(1, 100l);
        hashMap.put(1, 101l);
        hashMap.put(11, 102l);
        hashMap.put(2, 200l);
        hashMap.put(3, 300l);
        hashMap.put(77, 707l);

        assertEquals(hashMap.getSize(), 5);
    }
}
