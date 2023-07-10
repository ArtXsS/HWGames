package src;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> directory = new ArrayList<>(Arrays.asList("res/",
                "res/", "src/",
                "savegames/", "temp/",
                "src/main", "src/test",
                "res/drawables", "res/vectors",
                "res/icons"));
        ArrayList<String> files = new ArrayList<>(Arrays.asList("C://Games//src//main//Main.java",
                "C://Games//src//main//Utils.java",
                "C://Games//temp//temp.txt"));

        for (int i = 0; i < directory.size() && i < files.size(); i++) {
            createDirectory(directory.get(i));
            createFile(files.get(i));
        }

        //Задание 2
        GameProgress gameProgress = new GameProgress(95, 10, 5, 300.42);
        savegame("C://Games//savegames//save3.dat", gameProgress);
        zipFiles("C://Games//savegames//GameProgress.zip", "C://Games//savegames//save3.dat");
        deleteFile("C://Games//savegames//save3.dat");
    }

    public static void createDirectory (String url) {
        File file = new File(url);
        try {
            if (file.mkdir()) {
                StringBuilder sb = new StringBuilder();
                sb.append(file);
                String text = sb.toString();
                FileWriter writer = new FileWriter("temp/temp.txt");
                writer.write(text);
                writer.append("\nВсе файлы были созданы успешно!");
                writer.flush();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createFile (String url) {
        File file = new File(url);
        try {
            if (file.createNewFile()) {
                StringBuilder sb = new StringBuilder();
                sb.append(file);
                String text = sb.toString();
                FileWriter writer = new FileWriter("temp/temp.txt");
                writer.write(text);
                writer.append("\nВсе файлы были созданы успешно!");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //задание 2
    public static void savegame(String url, GameProgress gameProgress) {
        try(FileOutputStream fos = new FileOutputStream(url);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String url, String file) {
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(url));
            FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry = new ZipEntry("packed_game.txt");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteFile(String url) {
        File save = new File(url);
        save.delete();
    }
}
