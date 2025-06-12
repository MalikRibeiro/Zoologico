package controller;

import dao.SerializadorDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Endereco;
import model.Tratador;

public class TratadorController {

    private List<Tratador> tratadores;

  

    public TratadorController(List<Tratador> tratadores) {
        this.tratadores = tratadores;
    }

    private int buscarIdTratador() {
        if (tratadores.isEmpty()) {
            return 1;
        }
        return tratadores.stream()
                .mapToInt(Tratador::getId)
                .max()
                .orElse(0) + 1;
    }
   
    public void criarTratador(String nome, String especialidade, Endereco endereco) throws IllegalArgumentException {
        Tratador tratador = Tratador.criarTratador(buscarIdTratador(), nome, especialidade, endereco);
        tratadores.add(tratador);
        LogTxt.registrarLog("Cadastro tratador: " + tratador.getNome() + " (ID: " + tratador.getId() + ")");
    }

    
    public boolean removerTratador(int idTratador) {
        Tratador tratadorParaRemover = null;
        for (Tratador t : tratadores) {
            if (t.getId() == idTratador) {
                tratadorParaRemover = t;
                break;
            }
        }
        if (tratadorParaRemover != null) {
            tratadores.remove(tratadorParaRemover);
            LogTxt.registrarLog("Tratador removido: " + tratadorParaRemover.getNome() + " (ID: " + tratadorParaRemover.getId() + ")");
            return true;
        }
        return false;
    }

    
    public boolean alterarTratador(int idTratador, String novoNome, String novaEspecialidade, Endereco novoEndereco) {
        Tratador tratadorParaAlterar = null;
        for (Tratador t : tratadores) {
            if (t.getId() == idTratador) {
                tratadorParaAlterar = t;
                break;
            }
        }
        if (tratadorParaAlterar != null) {
            
            Tratador tratadorAtualizado = Tratador.criarTratador(idTratador, novoNome, novaEspecialidade, novoEndereco);
            int index = tratadores.indexOf(tratadorParaAlterar);
            if (index != -1) {
                tratadores.set(index, tratadorAtualizado);
                LogTxt.registrarLog("Tratador alterado: " + tratadorAtualizado.getNome() + " (ID: " + tratadorAtualizado.getId() + ")");
                return true;
            }
            return false;
        }
        return false;
    }


    public List<String> listarTratadores() {
        List<String> lista = new ArrayList<>();
        for (Tratador tratador : tratadores) {
            lista.add(tratador.toString());
        }
        return lista;
    }

    public void salvar() throws IOException {
        SerializadorDao.salvarTratadores(tratadores);
    }
    
    public void carregar() throws IOException, ClassNotFoundException {
        List<Tratador> tratadoresCarregados = SerializadorDao.carregarTratadores();
        this.tratadores = new ArrayList<>(tratadoresCarregados); 
    }

    
    public Tratador buscarTratadorPorId(int id) {
        for (Tratador t : tratadores) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}