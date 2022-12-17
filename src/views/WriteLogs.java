package views;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Anaclita
 */
public class WriteLogs {

    static String filePath = "./activity_logs.txt";
    static File file = new File(filePath);
    static FileWriter fr = null;

    public static void writeLog(String logs) {
        try {
            // Below constructor argument decides whether to append or override
            fr = new FileWriter(file, true);
            fr.write(logs + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
