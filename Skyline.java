import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Skyline {
  private List<Coordenada> coordenadas = new ArrayList<Coordenada>();

  public void addCoordenada(Coordenada coordenada) {
    this.coordenadas.add(coordenada);
  }

  public Iterator<Coordenada> getIterator() {
    return this.coordenadas.iterator();
  }

  public String toString() {
    return coordenadas.toString();
  }
}
