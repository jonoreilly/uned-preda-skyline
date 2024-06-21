public class Parametros {
    public boolean quiereAyuda = false;
    public boolean quiereTraza = false;
    public boolean quiereGrafica = false;
    public String ficheroEntrada = null;
    public String ficheroSalida = null;

    public Parametros(String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            quiereAyuda = true;
            return;
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                    quiereAyuda = true;
                    return;

                case "-t":
                    quiereTraza = true;
                    break;

                case "-g":
                    quiereGrafica = true;
                    break;

                default:
                    ficheroEntrada = args[i];
                    i++;
                    if (i < args.length) {
                        ficheroSalida = args[i];
                    }
                    return;
            }
        }
    }

    public static void printAyuda() {
        System.out.println("");
        System.out.println("SINTAXIS: skyline [-h][-t][-g] [fichero entrada] [fichero salida]");
        System.out.println("    -h                  Muestra esta ayuda");
        System.out.println("    -t                  Traza cada llamada recursiva y sus parámetros");
        System.out.println("    -g                  Dibuja la grafica del skyline en la consola");
        System.out.println("    [fichero entrada]   Conjunto de edificios de la ciudad, en formato \"x1 x2 h\"");
        System.out.println(
                "    [fichero salida]    Secuencia que representa el skyline de la ciudad, en formato \"x y\"");
        System.out.println("");
        System.out.println("COMBINACIONES POSIBLES:");
        System.out.println("    skyline -h");
        System.out.println("    skyline [fichero entrada]");
        System.out.println("    skyline [fichero entrada] [fichero salida]");
        System.out.println("    skyline -t [fichero entrada]");
        System.out.println("    skyline -t [fichero entrada] [fichero salida]");
        System.out.println("    skyline -g [fichero entrada]");
        System.out.println("    skyline -g [fichero entrada] [fichero salida]");
        System.out.println("    skyline -g -t [fichero entrada]");
        System.out.println("    skyline -g -t [fichero entrada] [fichero salida]");
        System.out.println("");
    }
}