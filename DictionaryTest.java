import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DictionaryTest {
    private Dictionary dictionary;

    @Before
    public void setUp() {
        Comparator<String> stringComparator = Comparator.naturalOrder();
        dictionary = new Dictionary("SplayTreeMap", stringComparator);
        dictionary.map.put("house", "casa");
        dictionary.map.put("dog", "perro");
        dictionary.map.put("cat", "gato");
    }

    @Test
    public void testPutAndGet() {
        // Test put and get with existing words
        assertEquals("casa", dictionary.map.get("house"));
        assertEquals("perro", dictionary.map.get("dog"));
        assertEquals("gato", dictionary.map.get("cat"));

        // Test put and get with new words
        assertNull(dictionary.map.get("tree"));
        dictionary.map.put("tree", "árbol");
        assertEquals("árbol", dictionary.map.get("tree"));

        // Test put and get with upper case words
        assertNull(dictionary.map.get("HOUSE"));
        dictionary.map.put("house", "hogar");
        assertEquals("hogar", dictionary.map.get("house"));
    }
}
