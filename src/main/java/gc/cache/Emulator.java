package gc.cache;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    private static DirFileCache dirFileCache;

    public static final int SELECT_FOLDER = 1;
    public static final int LOAD_FILE = 2;
    public static final int GET_FILE = 3;

    public static final String SELECT = "Выберите меню:";
    public static final String MENU = """ 
                Введите 1, чтобы указать директорию.
                Введите 2, чтобы загрузить файл.
                Введите 3, чтобы получить файл.
                Введите 4 или любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
    }

    private static void start(Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (SELECT_FOLDER == userChoice) {
                System.out.println("Введите желаемую директорию:");
                String cachingDir = scanner.nextLine();
                dirFileCache = checkDir(cachingDir);
            } else if (LOAD_FILE == userChoice) {
                System.out.println("Введите имя файла, который хотите загрузить:");
                String fileName = scanner.nextLine();
                dirFileCache.get(fileName);
                System.out.println("Файл загружен");
            } else if (GET_FILE == userChoice) {
                System.out.println("Введите имя файла, который хотите получить:");
                String fileName = scanner.nextLine();
                System.out.println(dirFileCache.get(fileName));
            }  else {
                run = false;
                System.out.println("---ВЫХОД---");
            }
        }
    }

    private static DirFileCache checkDir(String cachingDir) {
        File file = Paths.get(cachingDir).toFile();
        if (file.isFile()) {
            throw new IllegalArgumentException("Not a directory");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("Not exist");
        }
        return new DirFileCache(cachingDir);
    }
}
