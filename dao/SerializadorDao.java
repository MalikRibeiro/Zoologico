package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Animal;
import model.Tratador;

public class SerializadorDao {
    private static final String CAMINHO = "dados";

    public static void salvarAnimais(List<Animal> animais) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs(); 

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/Animais.ser"))) {
            oos.writeObject(animais);
        }
    }

    public static void salvarTratadores(List<Tratador> tratadores) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs(); 

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/Tratadores.ser"))) {
            oos.writeObject(tratadores);
        }
    }

    @SuppressWarnings("unchecked") 
    public static List<Animal> carregarAnimais() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/Animais.ser");
        if (!arquivo.exists()) {
            return new ArrayList<>(); 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Animal>) ois.readObject();
        }
    }

    @SuppressWarnings("unchecked") 
    public static List<Tratador> carregarTratadores() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/Tratadores.ser"); 
        if (!arquivo.exists()) {
            return new ArrayList<>(); 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Tratador>) ois.readObject();
        }
    }
}