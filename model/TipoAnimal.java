package model;

public enum TipoAnimal {
    MAMIFERO(1, "Mamífero") {
        @Override
        public String descreverComportamentoUnico(String nome) {
            return nome + " é um mamífero e possui pelos ou cabelos.";
        }

        @Override
        public String realizarAcaoEspecial(String nome) {
            return nome + " brinca e interage socialmente com frequência.";
        }

        @Override
        public String emitirSom() {
            return "Som de mamífero: rugido, latido ou miado!";
        }
    },
    AVE(2, "Ave") {
        @Override
        public String descreverComportamentoUnico(String nome) {
            return nome + " é uma ave e geralmente possui asas e penas.";
        }

        @Override
        public String realizarAcaoEspecial(String nome) {
            return nome + " canta melodias ou demonstra um voo acrobático.";
        }

        @Override
        public String emitirSom() {
            return "Piu-piu ou grasnado de ave!";
        }
    },
    REPTIL(3, "Réptil") {
        @Override
        public String descreverComportamentoUnico(String nome) {
            return nome + " é um réptil e tem pele escamosa.";
        }

        @Override
        public String realizarAcaoEspecial(String nome) {
            return nome + " se camufla perfeitamente em seu ambiente.";
        }

        @Override
        public String emitirSom() {
            return "Sibilos ou sons de rastejar!";
        }
    },
    PEIXE(4, "Peixe") {
        @Override
        public String descreverComportamentoUnico(String nome) {
            return nome + " é um peixe e vive na água, respirando por guelras.";
        }

        @Override
        public String realizarAcaoEspecial(String nome) {
            return nome + " exibe padrões de nado complexos.";
        }

        @Override
        public String emitirSom() {
            return "Sons subaquáticos!";
        }
    },
    ANFIBIO(5, "Anfíbio") {
        @Override
        public String descreverComportamentoUnico(String nome) {
            return nome + " é um anfíbio e pode viver tanto na água quanto na terra.";
        }

        @Override
        public String realizarAcaoEspecial(String nome) {
            return nome + " realiza uma transição entre ambientes aquáticos e terrestres.";
        }

        @Override
        public String emitirSom() {
            return "Crocitar de sapo ou rã!";
        }
    },
    INVERTEBRADO(6, "Invertebrado") {
        @Override
        public String descreverComportamentoUnico(String nome) {
            return nome + " é um invertebrado, sem esqueleto interno.";
        }

        @Override
        public String realizarAcaoEspecial(String nome) {
            return nome + " demonstra habilidades de adaptação ou caça peculiares.";
        }

        @Override
        public String emitirSom() {
            return "Zumbidos ou farfalhar!";
        }
    };

    private final int id;
    private final String nomeExibicao;

    TipoAnimal(int id, String nomeExibicao) {
        this.id = id;
        this.nomeExibicao = nomeExibicao;
    }

    public int getId() {
        return id;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public abstract String descreverComportamentoUnico(String nome);

    public abstract String realizarAcaoEspecial(String nome);

    public abstract String emitirSom();

    public static TipoAnimal fromId(int id) {
        for (TipoAnimal ta : TipoAnimal.values()) {
            if (ta.getId() == id) {
                System.out.println("Tipo selecionado: ID = " + ta.getId() + ", Nome = " + ta.getNomeExibicao());
                return ta;
            }
        }
        System.err.println("ID inválido: " + id + ". Usando tipo padrão: ID = " + MAMIFERO.getId() + ", Nome = " + MAMIFERO.getNomeExibicao());
        return MAMIFERO;
    }
}
