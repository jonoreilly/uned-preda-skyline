
public class Traza {
    private final long inicio;

    public Traza() {
        this.inicio = System.currentTimeMillis();

        System.out.println("");
        System.out.println("TRAZA INICIADA:");
    }

    private long tiempoTranscurrido() {
        long ahora = System.currentTimeMillis();

        return ahora - inicio;
    }

    public void registrar(String mensaje) {
        System.out.println(tiempoTranscurrido() + " ms - " + mensaje);
    }

    public void terminar() {
        System.out.println("TRAZA TERMINADA: " + tiempoTranscurrido() + " ms transcurridos");
        System.out.println("");
    }
}
