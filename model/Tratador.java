package model;

import java.io.Serializable;

public class Tratador implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String nome;
    protected String especialidade;
    private Endereco endereco;

    private Tratador(int id, String nome, String especialidade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    public static Tratador criarTratador(int id, String nome, String especialidade, Endereco endereco) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        if (especialidade == null || especialidade.isEmpty()) throw new IllegalArgumentException("Especialidade inválida.");
        if (endereco == null) throw new IllegalArgumentException("Endereço inválido.");
        return new Tratador(id, nome, especialidade, endereco);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    
    public Endereco getEndereco() { 
        return endereco;
    }

    @Override
    public String toString() {
        String enderecoInfo = (this.endereco != null) ? this.endereco.toString() : "Endereço não informado";
        return "ID: " + id +
               ", Nome: " + nome +
               ", Especialidade: " + especialidade +
               ", " + enderecoInfo; 
    }
}
