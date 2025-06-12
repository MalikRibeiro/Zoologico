package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogTxt {
    private static final String LOG_FILE = "log.txt";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void registrarLog(String mensagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(formatter);
            String log = String.format("[%s] %s", timestamp, mensagem);
            writer.write(log);
            writer.newLine(); 
        } catch (IOException e) {
            System.err.println("Falha crítica ao registrar log no arquivo " + LOG_FILE + ": " + e.getMessage());
        }
    }

    private LogTxt() {} 
}