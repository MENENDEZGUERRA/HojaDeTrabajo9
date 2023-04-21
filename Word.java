// La clase Word representa una palabra en inglés y su traducción al español,
// así como su tipo de palabra (sustantivo, adjetivo, etc.).
public class Word {

    // Atributos para almacenar la palabra en inglés, su traducción al español y su tipo de palabra
    private final String englishWord;
    private final String spanishWord;
    private final WordType wordType;

    // Constructor de la clase Word que recibe la palabra en inglés, su traducción al español
    // y su tipo de palabra como argumentos.
    public Word(String englishWord, String spanishWord, WordType wordType) {
        this.englishWord = englishWord;
        this.spanishWord = spanishWord;
        this.wordType = wordType;
    }

    // Método getter para obtener la palabra en inglés
    public String getEnglishWord() {
        return englishWord;
    }

    // Método getter para obtener la palabra en español
    public String getSpanishWord() {
        return spanishWord;
    }

    // Método getter para obtener el tipo de palabra
    public WordType getWordType() {
        return wordType;
    }
}
