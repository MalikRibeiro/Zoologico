package model;

import java.io.Serializable;

public class Animal extends Caracteristicas implements Serializable, ComportamentoEspecial {

    private static final long serialVersionUID = 1L;

    protected int id;
    protected String nome;
    protected int idade;
    private TipoAnimal tipoAnimal;

    public Animal() {
        super(); 
        this.tipoAnimal = TipoAnimal.MAMIFERO; 
    }

    private Animal(int id, String nome, int idade, String habitat, String alimentacao, TipoAnimal tipoAnimal) {
        super(habitat, alimentacao); 

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do animal deve ser informado.");
        }
        if (idade < 0) {
            throw new IllegalArgumentException("Idade inválida.");
        }
        if (tipoAnimal == null) {
            throw new IllegalArgumentException("Tipo de animal deve ser informado.");
        }

        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tipoAnimal = tipoAnimal;
    }

    
    public static Animal criarAnimal(int id, String nome, int idade, String habitat, String alimentacao, TipoAnimal tipoAnimal) {
        return new Animal(id, nome, idade, habitat, alimentacao, tipoAnimal);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    } 

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do animal não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade do animal não pode ser negativa.");
        }
        this.idade = idade;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        if (tipoAnimal == null) {
            throw new IllegalArgumentException("Tipo de animal não pode ser nulo.");
        }
        this.tipoAnimal = tipoAnimal;
    }
    

    public String realizarAcaoEspecial() {
        return tipoAnimal.realizarAcaoEspecial(this.nome);
    }

    public String descreverComportamentoUnico() {
        return tipoAnimal.descreverComportamentoUnico(this.nome);
    }

    @Override
    public String emitirSom() {
        return tipoAnimal.emitirSom();
    }

    @Override
    public String alimentar() {
        return nome + " sendo alimentado com " + alimentacao + ".";
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Idade: " + idade
                + " | Tipo: " + tipoAnimal.getNomeExibicao()
                + " | Habitat: " + habitat + " | Alimentação: " + alimentacao
                + " | Comportamento Único: " + descreverComportamentoUnico();
    }
}
