import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Dictionary {
    private CustomMap<String, String> map;

    public Dictionary(String mapType, Comparator<String> comparator) {
        this.map = MapFactory.createMap(mapType, comparator);
    }

    public void loadWordsFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "\t");
                String englishWord = st.nextToken().toLowerCase();
                String spanishWord = st.nextToken().toLowerCase();
                map.put(englishWord, spanishWord);
            }
        }
    }

    public String translate(String word) {
        String translatedWord = map.get(word.toLowerCase());
        return translatedWord != null ? translatedWord : "*" + word + "*";
    }

    public String translateText(String text) {
        StringTokenizer st = new StringTokenizer(text, " ");
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            sb.append(translate(word));
            if (st.hasMoreTokens()) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String translateTextFromFile(String filename) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append(" ");
            }
        }
        return translateText(text.toString());
    }
    
}

