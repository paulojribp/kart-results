package eu.paulo.kart.entities;

public class Pilot {

    int numero;

    String nome;

    public Pilot(String numeroString, String nome) {
        this.numero =
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                '}';
    }
}
