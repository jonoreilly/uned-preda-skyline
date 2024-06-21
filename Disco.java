import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Disco {
    public static List<Edificio> leerEdificios(String fichero) throws Exception {
        try (BufferedReader ficheroEntrada = new BufferedReader(
                new InputStreamReader(new FileInputStream(fichero), "utf-8"))) {

            Iterator<String> iterador = ficheroEntrada.lines().iterator();
            List<Edificio> edificios = new ArrayList<Edificio>();

            while (iterador.hasNext()) {
                String linea = iterador.next();
                Edificio edificio = Conversor.deLineaDelFicheroDeEntradaAEdificio(linea);
                edificios.add(edificio);
            }

            return edificios;
        }
    }

    public static void guardarSkyline(Skyline skyline, String fichero) throws Exception {
        try (BufferedWriter ficheroSalida = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fichero), "utf-8"))) {
            ficheroSalida.write(Conversor.deSkylineAFicheroDeSalida(skyline));
        }
    }
}
