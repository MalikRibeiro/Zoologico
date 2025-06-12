package model;

import java.io.Serializable;

public abstract class Caracteristicas implements Serializable {
    protected String habitat;
    protected String alimentacao;

    public Caracteristicas() {
       
    }

    public Caracteristicas(String habitat, String alimentacao) {
        if (habitat == null || habitat.trim().isEmpty()) {
            throw new IllegalArgumentException("Habitat não pode ser nulo ou vazio.");
        }
        if (alimentacao == null || alimentacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Alimentação não pode ser nula ou vazia.");
        }
        this.habitat = habitat.trim();
        this.alimentacao = alimentacao.trim();
    }

    public String getHabitat() {
        return habitat;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setHabitat(String habitat) {
        if (habitat == null || habitat.trim().isEmpty()) {
            throw new IllegalArgumentException("Habitat não pode ser nulo ou vazio.");
        }
        this.habitat = habitat.trim();
    }

    public void setAlimentacao(String alimentacao) {
        if (alimentacao == null || alimentacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Alimentação não pode ser nula ou vazia.");
        }
        this.alimentacao = alimentacao.trim();
    }

    
    public abstract String descreverComportamentoUnico();

    public String emitirSom() {
        return "Som genérico de animal do zoológico!";
    }

    public String alimentar() {
        return "Animal alimentado!";
    }

    @Override
    public String toString() {
        return "Habitat: " + habitat + ", Alimentação: " + alimentacao;
    }
}