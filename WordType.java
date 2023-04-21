// La clase WordType representa los diferentes tipos de palabras
// que se pueden encontrar en un diccionario.
public enum WordType {

    // Enumeración de los tipos de palabras:
    NOUN("sustantivo"),
    VERB("verbo"),
    ADJECTIVE("adjetivo"),
    ADVERB("adverbio"),
    PRONOUN("pronombre"),
    PREPOSITION("preposición"),
    CONJUNCTION("conjunción"),
    INTERJECTION("interjección");

    // Atributo para almacenar la representación en español del tipo de palabra
    private final String spanishName;

    // Constructor de la enumeración que recibe el nombre en español
    // del tipo de palabra como argumento.
    WordType(String spanishName) {
        this.spanishName = spanishName;
    }

    // Método getter para obtener el nombre en español del tipo de palabra.
    public String getSpanishName() {
        return spanishName;
    }
}
