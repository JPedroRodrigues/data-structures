/* Estrutura de Dados II - Atividade: Revisão POO com Java
 * João Pedro Rodrigues Vieira                      10403595
 * Sabrina Midori Futami Teixeira de Carvalho       10410220
 * Turma: 04G11
 * Referência: https://github.com/JPedroRodrigues/vi-editor.git
 * Referência: https://github.com/JPedroRodrigues/learning-java/tree/main/data-structures-I/lab/josephus
 */

public class Movie {

    private String title;
    private int year;
    private float rating;
    private Movie next;

    public Movie() {
        this("N/A", 0, 0.0f, null);
    }

    public Movie(String title, int year, float rating, Movie next) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.next = next;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public Movie getNext() { return next; }
    public void setNext(Movie next) { this.next = next; }

    @Override
    public String toString() { return title + " (" + year + ") " + rating; }
}
