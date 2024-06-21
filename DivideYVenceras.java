import java.util.List;
import java.util.Iterator;

public class DivideYVenceras {
    public static Skyline producirSkylineConDyD(List<Edificio> edificios, Parametros parametros, Traza traza) {
        // Registrar llamada
        if (parametros.quiereTraza) {
            traza.registrar("Llamada a producirSkylineConDyD con:"
                    + "\n    edificios: " + edificios);
        }

        if (edificios.size() == 0) {
            // Crear skyline vacío
            Skyline skyline = new Skyline();

            return skyline;
        } else if (edificios.size() == 1) {
            // Crear skyline con 1 edificio
            Edificio edificio = edificios.get(0);

            Coordenada inicio = new Coordenada(edificio.inicio, edificio.altura);
            Coordenada fin = new Coordenada(edificio.fin, 0);

            Skyline skyline = new Skyline();

            skyline.addCoordenada(inicio);
            skyline.addCoordenada(fin);

            // Registrar resultado
            if (parametros.quiereTraza) {
                traza.registrar("Respuesta de producirSkylineConDyD con:"
                        + "\n    edificios: " + edificios
                        + "\n    resultado: " + Conversor.deSkylineAJson(skyline));
            }

            return skyline;
        } else {
            // DIVIDIR: Crear 2 skylines recursivamente con la mitad de los edificios
            int medio = edificios.size() / 2;

            List<Edificio> edificios1 = edificios.subList(0, medio);
            List<Edificio> edificios2 = edificios.subList(medio, edificios.size());

            Skyline skyline1 = producirSkylineConDyD(edificios1, parametros, traza);
            Skyline skyline2 = producirSkylineConDyD(edificios2, parametros, traza);

            // VENCER: Combinar los dos skylines y devolver el resultado
            Skyline skyline = combinarSkylines(skyline1, skyline2, parametros, traza);

            // Registrar resultado
            if (parametros.quiereTraza) {
                traza.registrar("Respuesta de producirSkylineConDyD con:"
                        + "\n    edificios: " + edificios
                        + "\n    resultado: " + Conversor.deSkylineAJson(skyline));
            }

            return skyline;
        }
    }

    private static Skyline combinarSkylines(Skyline skyline1, Skyline skyline2, Parametros parametros, Traza traza) {
        // Registrar llamada
        if (parametros.quiereTraza) {
            traza.registrar("Llamada a combinarSkylines con:"
                    + "\n    skyline 1: " + Conversor.deSkylineAJson(skyline1)
                    + "\n    skyline 2:  " + Conversor.deSkylineAJson(skyline2));
        }

        Skyline skyline = new Skyline();

        Iterator<Coordenada> iterador1 = skyline1.getIterator();
        Iterator<Coordenada> iterador2 = skyline2.getIterator();

        // Coordenada actual del nuevo Skyline
        Coordenada actual = new Coordenada(0, 0);

        // Coordenada actual de los Skyline parametros
        Coordenada actual1 = new Coordenada(0, 0);
        Coordenada actual2 = new Coordenada(0, 0);

        // Coordenada siguiente de los Skyline parametros
        Coordenada siguiente1 = new Coordenada(0, 0);
        Coordenada siguiente2 = new Coordenada(0, 0);

        while (siguiente1 != null || siguiente2 != null) {
            // Actualizar el puntero de la coordenada actual del Skyline 1
            // si pasamos a la siguiente coordenada del Skyline 1

            // Pasamos a la siguiente coordenada del Skyline 1
            // si el Skyline 2 se ha acabado,
            // o si la siguiente coordenada del Skyline 1 está más cerca
            // que la siguiente del Skyline 2

            // Si los dos están igual de cerca, actualizamos los dos
            // pero solo añadiremos el más alto al Skyline

            // Debido a la estructura de este algoritmo, el orden siempre se mantiene
            // actual1 <= actual < siguiente1
            // actual2 <= actual < siguiente2

            Boolean debemosActualizar1 = siguiente1 != null && (siguiente2 == null || siguiente1.x <= siguiente2.x);
            Boolean debemosActualizar2 = siguiente2 != null && (siguiente1 == null || siguiente2.x <= siguiente1.x);

            if (debemosActualizar1) {
                Coordenada next = null;

                if (iterador1.hasNext()) {
                    next = iterador1.next();
                }

                actual1 = siguiente1;
                siguiente1 = next;
            }

            if (debemosActualizar2) {
                Coordenada next = null;

                if (iterador2.hasNext()) {
                    next = iterador2.next();
                }

                actual2 = siguiente2;
                siguiente2 = next;
            }

            Coordenada nuevaCoordenada = masAltaOReciente(actual1, actual2);

            // Si la nueva coordenada es diferente a la actual, añadirla al Skyline
            if (nuevaCoordenada.y != actual.y) {
                actual = nuevaCoordenada;
                skyline.addCoordenada(nuevaCoordenada);
            }
        }

        // Registrar resultado
        if (parametros.quiereTraza) {
            traza.registrar("Respuesta de combinarSkylines con:"
                    + "\n    skyline 1: " + Conversor.deSkylineAJson(skyline1)
                    + "\n    skyline 2:  " + Conversor.deSkylineAJson(skyline2)
                    + "\n    resultado: " + Conversor.deSkylineAJson(skyline));
        }

        return skyline;
    }

    private static Coordenada masAltaOReciente(Coordenada a, Coordenada b) {
        if (a.y > b.y) {
            return a;
        } else if (a.y < b.y) {
            return b;
        } else {
            if (a.x > b.x) {
                return a;
            } else {
                return b;
            }
        }
    }
}
