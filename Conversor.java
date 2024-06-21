import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Conversor {

    // #### EDIFICIO ####

    public static Edificio deLineaDelFicheroDeEntradaAEdificio(String linea) {
        String[] valores = linea.split(" ");

        int inicio = Integer.parseInt(valores[0]);
        int fin = Integer.parseInt(valores[1]);
        int altura = Integer.parseInt(valores[2]);

        Edificio edificio = new Edificio(inicio, fin, altura);

        return edificio;
    }

    // #### COORDENADA ####

    public static String deCoordenadaALineaDeFicheroDeSalida(Coordenada coordenada) {
        return coordenada.x + " " + coordenada.y;
    }

    public static String deCoordenadaAJson(Coordenada coordenada) {
        return "{ x: " + coordenada.x + ", y: " + coordenada.y + " }";
    }

    // #### SKYLINE ####

    public static String deSkylineAFicheroDeSalida(Skyline skyline) {
        Iterator<Coordenada> iterador = skyline.getIterator();
        String resultado = "";

        while (iterador.hasNext()) {
            Coordenada coordenada = iterador.next();
            String linea = deCoordenadaALineaDeFicheroDeSalida(coordenada);
            resultado += linea;

            if (iterador.hasNext()) {
                resultado += "\n";
            }
        }

        return resultado;
    }

    public static String deSkylineAJson(Skyline skyline) {
        Iterator<Coordenada> iterador = skyline.getIterator();
        String resultado = "[";

        while (iterador.hasNext()) {
            Coordenada coordenada = iterador.next();
            resultado += deCoordenadaAJson(coordenada);

            if (iterador.hasNext()) {
                resultado += ", ";
            }
        }

        resultado += "]";

        return resultado;
    }

    private static int alturaDeSkylineEn(Skyline skyline, int x) {
        Iterator<Coordenada> iterador = skyline.getIterator();
        int altura = 0;

        while (iterador.hasNext()) {
            Coordenada siguiente = iterador.next();

            if (siguiente.x > x) {
                break;
            }

            altura = siguiente.y;
        }

        return altura;
    }

    public static String deSkylineAGrafica(Skyline skyline) {
        int anchura = 0;
        int altura = 0;

        Iterator<Coordenada> iterador = skyline.getIterator();

        while (iterador.hasNext()) {
            Coordenada coordenada = iterador.next();

            if (coordenada.x > anchura) {
                anchura = coordenada.x;
            }

            if (coordenada.y > altura) {
                altura = coordenada.y;
            }
        }

        String resultado = "";

        for (int columna = 0; columna < anchura + 5; columna++) {
            resultado += "_";
        }

        resultado += "\n";

        for (int fila = altura + 5; fila > 0; fila--) {
            resultado += "|";
            for (int columna = 0; columna < anchura + 5; columna++) {
                if (alturaDeSkylineEn(skyline, columna) >= fila) {
                    resultado += "H";
                } else {
                    resultado += " ";
                }
            }
            resultado += "|\n";
        }

        for (int columna = 0; columna < anchura + 5; columna++) {
            resultado += "_";
        }

        resultado += "\n";

        return resultado;
    }
}
