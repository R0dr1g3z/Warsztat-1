package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import pl.coderslab.utiks.ConsoleColors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) throws Exception {
        Console();
    }

    public static String[][] readFile() throws IOException {
        Path path = Paths.get("tasks.csv");
        int counterFile = 0;
        int counterFile1 = 0;
        Scanner scanner = new Scanner(path);
        Scanner scanner1 = new Scanner(path);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] split = s.split(",");
            counterFile1 = split.length;
            counterFile++;
        }
        String[][] tab = new String[counterFile][counterFile1];
        for (int i = 0; i < tab.length; i++) {
            String s = scanner1.nextLine();
            tab[i][0] = s;
        }
        return tab;
    }

    public static void Console() throws Exception {
        String[][] tasks = readFile();
        Scanner scanner = new Scanner(System.in);
        start();
        while (scanner.hasNextLine()) {
            switch (scanner.nextLine()) {
                case "add":
                    tasks = add(scanner, tasks);
                    break;
                case "remove":
                    tasks = remove(scanner, tasks);
                    break;
                case "list":
                    list(tasks);
                    break;
                case "exit":
                    exit(tasks);
                default:
                    System.out.println(ConsoleColors.RED + "Zla komenda");
            }
        }
    }

    public static void start() {
        System.out.println(ConsoleColors.YELLOW + "Wpisz komende");
        System.out.println(ConsoleColors.GREEN + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    public static String[][] add(Scanner scanner, String[][] tasks) {
        System.out.println(ConsoleColors.GREEN + "Podaj taska");
        String task = scanner.nextLine() + ", ";
        System.out.println("Podaj date taska");
        String taskData = scanner.nextLine() + ", ";
        System.out.println("Czy twoj task jest wazny: true/false");
        String taskimp = scanner.nextLine();
        String quest = task + taskData + taskimp;
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = quest;
        System.out.println("Task zostal dodany");
        return tasks;
    }

    public static String[][] remove(Scanner scanner, String[][] tasks) {
        System.out.println("Podaj number tablicy do usuniecia");
        while (scanner.hasNextLine()) {
            String taskdel = scanner.nextLine();
            try {
                int taskdelint = Integer.parseInt(taskdel);
                if (taskdelint >= 0) {
                    tasks = ArrayUtils.remove(tasks, taskdelint);
                    System.out.println("Tablica numer " + taskdelint + " zostala usunieta");
                    break;
                } else {
                    System.out.println("Zla liczba. Podaj liczbe wieksza lub rowna 0");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Zla lizcba. Podaj liczbe wieksza lub rowna 0");
            }
        }
        return tasks;
    }

    public static void list(String[][] tasks) {
        System.out.println(ConsoleColors.YELLOW + "Lista taskow");
        System.out.println(ConsoleColors.GREEN);
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(i + " : " + tasks[i][0]);
        }
    }

    public static void exit(String[][] tasks) throws IOException {
        Path path = Paths.get("tasks.csv");
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            strings.add(tasks[i][0]);
        }
        Files.write(path, strings);
        System.out.println(ConsoleColors.RED + "Do zobaczenia");
        System.exit(0);
    }
}
