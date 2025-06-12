package controller;

import dao.SerializadorDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Animal;
import model.TipoAnimal;

public class AnimalController {

    private List<Animal> animais;

    public AnimalController(List<Animal> animais) {
        this.animais = animais;
    }

    private int buscarIdAnimal() {
        if (animais.isEmpty()) {
            return 1;
        }
        return animais.stream()
                .mapToInt(Animal::getId)
                .max()
                .orElse(0) + 1;
    }

    public void criarAnimal(String nome, int idade, String habitat, String alimentacao, TipoAnimal tipoAnimal) throws IllegalArgumentException {
        if (tipoAnimal == null) {
            throw new IllegalArgumentException("Tipo de animal não pode ser nulo.");
        }
        int novoId = buscarIdAnimal();
        Animal animal = Animal.criarAnimal(novoId, nome, idade, habitat, alimentacao, tipoAnimal);
        animais.add(animal);
        LogTxt.registrarLog("Cadastro animal (" + tipoAnimal.getNomeExibicao() + "): " + animal.getNome() + " (ID: " + animal.getId() + ")");
    }

    public Animal buscarAnimalPorId(int id) {
        for (Animal a : animais) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public boolean removerAnimal(int idAnimal) {
        int tamanhoAntes = animais.size();
        animais = animais.stream()
                         .filter(a -> a.getId() != idAnimal)
                         .collect(Collectors.toList());
        boolean removido = animais.size() < tamanhoAntes;
        if (removido) {
            LogTxt.registrarLog("Animal removido com ID: " + idAnimal);
        }
        return removido;
    }

    public boolean alterarAnimal(int idAnimal, String novoNome, int novaIdade, String novoHabitat, String novaAlimentacao, TipoAnimal novoTipoAnimal) {
        Optional<Animal> animalOptional = animais.stream()
            .filter(a -> a.getId() == idAnimal)
            .findFirst();
        if (animalOptional.isPresent()) {
            Animal animalParaAlterar = animalOptional.get();
            try {
                animalParaAlterar.setNome(novoNome);
                animalParaAlterar.setIdade(novaIdade);
                animalParaAlterar.setHabitat(novoHabitat);
                animalParaAlterar.setAlimentacao(novaAlimentacao);
                animalParaAlterar.setTipoAnimal(novoTipoAnimal);
                LogTxt.registrarLog("Animal alterado: " + animalParaAlterar.getNome() + " (ID: " + animalParaAlterar.getId() + ") para tipo " + novoTipoAnimal.getNomeExibicao());
                return true;
            } catch (IllegalArgumentException e) {
                System.err.println("Erro ao alterar animal: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public List<String> listarAnimais() {
        return animais.stream()
                      .map(Animal::toString)
                      .collect(Collectors.toList());
    }

    public String alimentarAnimal(int idAnimal) {
        Animal animalEncontrado = buscarAnimalPorId(idAnimal);
        if (animalEncontrado != null) {
            String mensagem = animalEncontrado.alimentar();
            LogTxt.registrarLog("Animal alimentado: " + animalEncontrado.getNome());
            return mensagem;
        }
        return "Animal com ID " + idAnimal + " não encontrado.";
    }

    public String alimentarAnimal(int idAnimal, String tipoAlimentoExtra) {
        Animal animalEncontrado = buscarAnimalPorId(idAnimal);
        if (animalEncontrado != null) {
            String mensagem = animalEncontrado.alimentar();
            mensagem += " Além da dieta normal, foi oferecido: " + tipoAlimentoExtra + ".";
            LogTxt.registrarLog("Animal alimentado (com extra): " + animalEncontrado.getNome() + " com " + tipoAlimentoExtra);
            return mensagem;
        }
        return "Animal com ID " + idAnimal + " não encontrado.";
    }

    public void salvar() throws IOException {
        SerializadorDao.salvarAnimais(animais);
    }

    public void carregar() throws IOException, ClassNotFoundException {
        List<Animal> animaisCarregados = SerializadorDao.carregarAnimais();
        this.animais = new ArrayList<>(animaisCarregados);
    }
}