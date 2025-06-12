package model;

public interface ComportamentoEspecial {
    
    String realizarAcaoEspecial();
    
    
    default String obterDescricaoGeral() {
        return "Este ser vivo pode realizar uma ação especial.";
    }
}