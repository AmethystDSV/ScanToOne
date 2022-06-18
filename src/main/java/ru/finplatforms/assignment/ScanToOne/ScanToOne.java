package ru.finplatforms.assignment.ScanToOne;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class ScanToOne {
    public static void main(String[] args) {
        System.out.println("---------------------");
        System.out.println("-----Scan To One-----");
        System.out.println("---------------------");

        new ScanToOne().run(System.in, System.out);
    }

    public void run(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(in);
        out.println("Введите путь к папке для сканирования файлов: ");
        String path = sc.nextLine();

        File rootFolder = new File(path);
        if(!rootFolder.isDirectory()){
            System.out.println("This is not a directory!");
        }

        ArrayList<File> filesList = new ArrayList<>();
        Stack<File> directoriesList = new Stack<>();
        directoriesList.push(rootFolder);

        while (!directoriesList.empty()) {
            File folder = directoriesList.pop();
            File[] fileFromFolder = folder.listFiles();
            for (File f : fileFromFolder) {
                if (f.getName().endsWith(".txt")) {
                    filesList.add(f);
                } else {
                    if (f.isDirectory()) {
                        directoriesList.push(f);
                    }
                }
            }
        }

        //Collections.sort(filesList);
        ArrayList<File> sortedFilesList = (ArrayList) filesList.stream()
                .sorted((f1,f2) -> (f1.getName()).compareTo(f2.getName()))
                .collect(Collectors.toList());

        out.println("Введите путь к файлу для записи: ");
        String outputFile = sc.nextLine();
        File userFile = new File(outputFile);

        for (File f: sortedFilesList ) {
            String fileContent = "";
            try {
                fileContent = FileOperations.readFile(f);
            } catch (Exception e) {
                out.println("! Файл не был прочитан! ");
            }

            try {
                FileOperations.writeToFile(userFile, fileContent);
            } catch (Exception e) {
                out.println("! Ошибка при попытке записи! ");
            }

        }

    }

}
