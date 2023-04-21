import java.util.Comparator;

public class MapFactory {

    public static <K, V> CustomMap<K, V> createMap(String mapType, Comparator<K> comparator) {
        switch (mapType) {
            case "SplayTreeMap":
                return new SplayTreeMap<>(comparator);
            case "RedBlackTreeMap":
                return new RedBlackTreeMap<>(comparator);
            case "AVLTreeMap":
                return new AVLTreeMap<>(comparator);
            case "StandardHashMap":
                return new StandardHashMap<>();
            case "TwoThreeTreeMap":
                return new TwoThreeTreeMap<>(comparator);
            default:
                throw new IllegalArgumentException("Invalid map type: " + mapType);
        }
    }
}
