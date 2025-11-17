import java.util.Objects;

public class Avion {
    String numero;

    public Avion() {
    }

    public Avion(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "numero='" + numero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Avion avion = (Avion) o;
        return Objects.equals(numero, avion.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }
}
