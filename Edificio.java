
public class Edificio {
    public final int inicio;
    public final int fin;
    public final int altura;

    public Edificio(int inicio, int fin, int altura) {
        this.inicio = inicio;
        this.fin = fin;
        this.altura = altura;
    }

    public String toString() {
        return "{ inicio: " + inicio + ", fin: " + fin + ", altura: " + altura + " }";
    }
}
