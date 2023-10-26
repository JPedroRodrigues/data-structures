import java.text.Normalizer;
import java.util.regex.Pattern;

public class Palindromo {
    private String texto;

    public Palindromo() {
        this("");
    }

    public Palindromo(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (this.texto != null) this.texto = texto;
        else System.out.println("Texto inválido.");
    }

    public boolean verificar() {
        String txt = texto.toLowerCase();
        txt = txt.trim().replace(" ", "");
        txt = txt.replaceAll("\\p{Punct}+", "");

        String norm = Normalizer.normalize(txt, Normalizer.Form.NFD);
        Pattern p = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        txt = p.matcher(norm).replaceAll("");

        int i = 0;
        int j = txt.length() - 1;

        while (i < j) {
            if (txt.charAt(i) == txt.charAt(j)) {
                i++;
                j--;
            }
            else return false;
        }
        return true;
    }
}
