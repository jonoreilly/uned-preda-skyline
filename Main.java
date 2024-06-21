import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main2(String[] args) throws Exception {
        Parametros parametros = new Parametros(args);

        if (parametros.quiereAyuda) {
            Parametros.printAyuda();
            return;
        }

        List<Edificio> edificios = Disco.leerEdificios(parametros.ficheroEntrada);

        Traza traza = new Traza();

        Skyline skyline = DivideYVenceras.producirSkylineConDyD(edificios, parametros, traza);

        traza.terminar();

        if (parametros.ficheroSalida == null) {
            System.out.println(Conversor.deSkylineAFicheroDeSalida(skyline));
        } else {
            Disco.guardarSkyline(skyline, parametros.ficheroSalida);
        }

        if (parametros.quiereGrafica) {
            System.out.println(Conversor.deSkylineAGrafica(skyline));
        }
    }
}
