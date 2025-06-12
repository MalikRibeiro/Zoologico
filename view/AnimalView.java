package view;

import controller.AnimalController;
import controller.TratadorController;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import model.Animal;
import model.Endereco; 
import model.TipoAnimal; 
import model.Tratador;

public class AnimalView {

    private Scanner scanner;
    private AnimalController animalController;
    private TratadorController tratadorController;

    public AnimalView() {
        this.scanner = new Scanner(System.in);
        this.animalController = new AnimalController(new ArrayList<Animal>());
        this.tratadorController = new TratadorController(new ArrayList<Tratador>());

        try {
            animalController.carregar();
            tratadorController.carregar();
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados iniciais: " + e.getMessage());
        }
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- MENU PRINCIPAL DO ZOOLÓGICO ---");
            System.out.println("1. Gerenciar Animais");
            System.out.println("2. Gerenciar Tratadores");
            System.out.println("3. Realizar pre-carga de dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    menuAnimais();
                    break;
                case 2:
                    menuTratadores();
                    break;
                case 3:
                    preCarga();
                    break;
                case 0:
                    try {
                        animalController.salvar();
                        tratadorController.salvar();
                        System.out.println("Dados salvos. Saindo do sistema. Obrigado!");
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar dados ao sair: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private void menuAnimais() {
        int opcao;
        do {
            System.out.println("\n--- MENU DE ANIMAIS ---");
            System.out.println("1. Cadastrar Animal");
            System.out.println("2. Listar Animais");
            System.out.println("3. Alterar Animal");
            System.out.println("4. Remover Animal");
            System.out.println("5. Alimentar Animal (Padrão)");
            System.out.println("6. Alimentar Animal (Com Extra)");
            System.out.println("7. Exibir Comportamento Especial");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarAnimal();
                    break;
                case 2:
                    listarAnimais();
                    break;
                case 3:
                    alterarAnimal();
                    break;
                case 4:
                    removerAnimal();
                    break;
                case 5:
                    alimentarAnimalPadrao();
                    break;
                case 6:
                    alimentarAnimalComExtra();
                    break;
                case 7:
                    exibirComportamentoEspecial();
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void menuTratadores() {
        int opcao;
        do {
            System.out.println("\n--- MENU DE TRATADORES ---");
            System.out.println("1. Cadastrar Tratador");
            System.out.println("2. Listar Tratadores");
            System.out.println("3. Alterar Tratador");
            System.out.println("4. Remover Tratador"); 
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarTratador();
                    break;
                case 2:
                    listarTratadores();
                    break;
                case 3:
                    alterarTratador(); 
                    break;
                case 4:
                    removerTratador(); 
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    
    private void cadastrarAnimal() {
        System.out.println("\n--- CADASTRO DE ANIMAL ---");

        System.out.print("Nome: ");
        scanner.nextLine(); 
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInteiro();

        System.out.print("Habitat: ");
        scanner.nextLine();
        String habitat = scanner.nextLine();

        System.out.print("Alimentação: ");
        String alimentacao = scanner.nextLine();

        
        System.out.println("Tipos de Animais disponíveis:");
        for (TipoAnimal tipo : TipoAnimal.values()) {
            System.out.println(tipo.getId() + " - " + tipo.getNomeExibicao());
        }

        
        System.out.print("Digite o número correspondente ao tipo do animal: ");
        int tipoId = lerInteiro();

        try {
            TipoAnimal tipoAnimal = TipoAnimal.fromId(tipoId); 
            animalController.criarAnimal(nome, idade, habitat, alimentacao, tipoAnimal);
            System.out.println("Animal cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar animal: " + e.getMessage());
        }
    }

    private void listarAnimais() {
        System.out.println("\n--- LISTAGEM DE ANIMAIS ---");
        List<String> animaisListados = animalController.listarAnimais();
        if (animaisListados.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            for (String animal : animaisListados) {
                System.out.println(animal);
            }
        }
    }

    private void alterarAnimal() {
        System.out.println("\n--- ALTERAR ANIMAL ---");
        System.out.print("ID do animal para alterar: ");
        int id = lerInteiro();

        System.out.print("Novo Nome: ");
        scanner.nextLine(); 
        String novoNome = scanner.nextLine();

        System.out.print("Nova Idade: ");
        int novaIdade = lerInteiro();

        System.out.print("Novo Habitat: ");
        scanner.nextLine();
        String novoHabitat = scanner.nextLine();

        System.out.print("Nova Alimentação: ");
        String novaAlimentacao = scanner.nextLine();

        
        System.out.println("Tipos de Animais disponíveis:");
        for (TipoAnimal tipo : TipoAnimal.values()) {
            System.out.println(tipo.getId() + " - " + tipo.getNomeExibicao());
        }

        System.out.print("Digite o número correspondente ao novo tipo do animal: ");
        int novoTipoId = lerInteiro();

        try {
            TipoAnimal novoTipoAnimal = TipoAnimal.fromId(novoTipoId);
            if (animalController.alterarAnimal(id, novoNome, novaIdade, novoHabitat, novaAlimentacao, novoTipoAnimal)) {
                System.out.println("Animal alterado com sucesso!");
            } else {
                System.out.println("Falha ao alterar animal. Verifique o ID ou os dados informados.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao alterar animal: " + e.getMessage());
        }
    }

    private void removerAnimal() {
        System.out.println("\n--- REMOVER ANIMAL ---");
        System.out.print("ID do animal para remover: ");
        int id = lerInteiro();

        if (animalController.removerAnimal(id)) {
            System.out.println("Animal removido com sucesso!");
        } else {
            System.out.println("Falha ao remover animal. Verifique o ID.");
        }
    }

    private void alimentarAnimalPadrao() {
        System.out.println("\n--- ALIMENTAR ANIMAL (PADRÃO) ---");
        System.out.print("ID do animal para alimentar: ");
        int id = lerInteiro();
        String resultado = animalController.alimentarAnimal(id);
        System.out.println(resultado);
    }

    private void alimentarAnimalComExtra() {
        System.out.println("\n--- ALIMENTAR ANIMAL (COM EXTRA) ---");
        System.out.print("ID do animal para alimentar: ");
        int id = lerInteiro();

        System.out.print("Qual alimento extra será oferecido? ");
        scanner.nextLine();
        String alimentoExtra = scanner.nextLine();

        String resultado = animalController.alimentarAnimal(id, alimentoExtra);
        System.out.println(resultado);
    }

    private void exibirComportamentoEspecial() {
        System.out.println("\n--- EXIBIR COMPORTAMENTO ESPECIAL DO ANIMAL ---");
        System.out.print("ID do animal para ver o comportamento especial: ");
        int id = lerInteiro();

        Animal animal = animalController.buscarAnimalPorId(id);

        if (animal != null) {
            System.out.println("\nComportamento de " + animal.getNome() + " (Tipo: " + animal.getTipoAnimal().getNomeExibicao() + "):");
            System.out.println("- Ação Especial: " + animal.realizarAcaoEspecial());
            System.out.println("- Comportamento Único da Espécie: " + animal.descreverComportamentoUnico());
            System.out.println("- Som Característico: " + animal.emitirSom());
        } else {
            System.out.println("Animal com ID " + id + " não encontrado.");
        }
    }

    
    private void cadastrarTratador() {
        System.out.println("\n--- CADASTRO DE TRATADOR ---");
        System.out.print("Nome: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        System.out.print("Rua do Endereço: ");
        String rua = scanner.nextLine();

        System.out.print("Número do Endereço: ");
        String numero = scanner.nextLine();

        try {
            Endereco endereco = new Endereco(rua, numero);
            tratadorController.criarTratador(nome, especialidade, endereco);
            System.out.println("Tratador cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar tratador: " + e.getMessage());
        }
    }

    private void listarTratadores() {
        System.out.println("\n--- LISTAGEM DE TRATADORES ---");
        List<String> tratadoresListados = tratadorController.listarTratadores();
        if (tratadoresListados.isEmpty()) {
            System.out.println("Nenhum tratador cadastrado.");
        } else {
            for (String tratador : tratadoresListados) {
                System.out.println(tratador);
            }
        }
    }

    private void alterarTratador() {
        System.out.println("\n--- ALTERAR TRATADOR ---");
        System.out.print("ID do tratador para alterar: ");
        int id = lerInteiro();

        System.out.print("Novo Nome: ");
        scanner.nextLine();
        String novoNome = scanner.nextLine();

        System.out.print("Nova Especialidade: ");
        String novaEspecialidade = scanner.nextLine();

        System.out.print("Nova Rua do Endereço: ");
        String novaRua = scanner.nextLine();

        System.out.print("Novo Número do Endereço: ");
        String novoNumero = scanner.nextLine();

        try {
            Endereco novoEndereco = new Endereco(novaRua, novoNumero);
            if (tratadorController.alterarTratador(id, novoNome, novaEspecialidade, novoEndereco)) {
                System.out.println("Tratador alterado com sucesso!");
            } else {
                System.out.println("Falha ao alterar tratador. Verifique o ID ou os dados informados.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao alterar tratador: " + e.getMessage());
        }
    }

    private void removerTratador() {
        System.out.println("\n--- REMOVER TRATADOR ---");
        System.out.print("ID do tratador para remover: ");
        int id = lerInteiro();

        if (tratadorController.removerTratador(id)) {
            System.out.println("Tratador removido com sucesso!");
        } else {
            System.out.println("Falha ao remover tratador. Verifique o ID.");
        }
    }

    
    private int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        return valor;
    }

    private void preCarga() {
        System.out.println("\n--- REALIZANDO PRÉ-CARGA DE DADOS ---");

        
        Endereco end1 = new Endereco("Rua das Palmeiras", "100");
        Endereco end2 = new Endereco("Avenida dos Leões", "200");

        tratadorController.criarTratador("Carlos Silva", "Mamíferos", end1);
        tratadorController.criarTratador("Fernanda Oliveira", "Répteis", end2);

       
        animalController.criarAnimal("Leão", 5, "Savana", "Carne", TipoAnimal.MAMIFERO);
        animalController.criarAnimal("Papagaio", 2, "Floresta", "Frutas", TipoAnimal.AVE);
        animalController.criarAnimal("Jacaré", 7, "Pântano", "Peixes", TipoAnimal.REPTIL);

        System.out.println("Pré-carga concluída com sucesso!");
    }
}
