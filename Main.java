import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al traductor Inglés-Español");
        System.out.println("Por favor, ingresa el tipo de mapa que deseas usar:");
        System.out.println("1. SplayTreeMap");
        System.out.println("2. RedBlackTreeMap");
        System.out.println("3. AVLTreeMap");
        System.out.println("4. StandardHashMap");
        System.out.println("5. TwoThreeTreeMap");
        System.out.print("Opción: ");
        int option = scanner.nextInt();
        String mapType;
        switch (option) {
            case 1:
                mapType = "SplayTreeMap";
                break;
            case 2:
                mapType = "RedBlackTreeMap";
                break;
            case 3:
                mapType = "AVLTreeMap";
                break;
            case 4:
                mapType = "StandardHashMap";
                break;
            case 5:
                mapType = "TwoThreeTreeMap";
                break;
            default:
                System.out.println("Opción no válida. Usando SplayTreeMap por defecto.");
                mapType = "SplayTreeMap";
                break;
        }
        scanner.nextLine(); // Consume newline left-over
        Comparator<String> stringComparator = Comparator.naturalOrder();
        Dictionary dictionary = new Dictionary(mapType, stringComparator);

        System.out.print("Por favor, ingresa el nombre del archivo de diccionario (por ejemplo, Spanish.txt): ");
        String dictionaryFile = scanner.nextLine();
        try {
            dictionary.loadWordsFromFile(dictionaryFile);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo del diccionario.");
            e.printStackTrace();
            return;
        }

        System.out.print("Por favor, ingresa el nombre del archivo de texto en inglés a traducir (por ejemplo, texto.txt): ");
        String inputFile = scanner.nextLine();
        String translatedText;
        try {
            translatedText = dictionary.translateTextFromFile(inputFile);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de texto.");
            e.printStackTrace();
            return;
        }

        System.out.println("Texto traducido:");
        System.out.println(translatedText);
    }
}
