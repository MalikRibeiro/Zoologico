package model;

import java.io.Serializable;

public class Endereco implements Serializable {

    private String rua;
    private String numero; 

    public Endereco(String rua, String numero) {
        if (rua == null || rua.trim().isEmpty()) {
            throw new IllegalArgumentException("A rua não pode ser nula ou vazia.");
        }
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("O número não pode ser nulo ou vazio.");
        }
        
        this.rua = rua.trim();
        this.numero = numero.trim();
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Rua: " + rua + ", Número: " + numero;
    }
}