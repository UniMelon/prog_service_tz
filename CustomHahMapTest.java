import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomHahMapTest {

    private CustomHashMap hashMap;

    @Before
    public void setUp() {
        hashMap = new CustomHashMap(10);
    }

    @After
    public void tearDown(){
        hashMap = null;
    }

    @Test
    public void testNotCollision(){
        assertEquals(hashMap.hashFunc(0), 0);
        assertEquals(hashMap.hashFunc(1), 1);
        assertEquals(hashMap.hashFunc(9), 9);
    }

    @Test
    public void testIsCollision(){
        assertEquals(hashMap.hashFunc(10), 0);
        assertEquals(hashMap.hashFunc(11), 1);
        assertEquals(hashMap.hashFunc(99), 9);
    }

    @Test
    public void testPutNull(){
        assertEquals(hashMap.put(null, 100l), 100l);
        assertEquals(hashMap.put(null, 101l), 101l);
        assertEquals(hashMap.put(null, 200l), 200l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutIllegal() throws IllegalArgumentException {
        assertEquals(hashMap.put(-1, 100l), 100l);
    }

    @Test
    public void testPutAddValid(){
        assertEquals(hashMap.put(1, 100l), 100l);
        assertEquals(hashMap.put(11, 102l), 102l);
        assertEquals(hashMap.put(2, 200l), 200l);
        assertEquals(hashMap.put(3, 300l), 300l);
        assertEquals(hashMap.put(77, 707l), 707l);
    }

    @Test
    public void testPutUpdate(){
        assertEquals(hashMap.put(1, 100l), 100l);
        assertEquals(hashMap.put(1, 101l), 101l);
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
        assertEquals(hashMap.get(5), null);
        assertEquals(hashMap.get(7), null);
    }

    @Test
    public void testSize(){
        hashMap.put(1, 100l);
        hashMap.put(1, 101l);
        hashMap.put(11, 102l);
        hashMap.put(2, 200l);
        hashMap.put(3, 300l);
        hashMap.put(77, 707l);

        assertEquals(hashMap.size(), 5);
    }
}
