package gc.cache;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите желаемую директорию:");
        String cachingDir = scanner.nextLine();
        DirFileCache dirFileCache = checkDir(cachingDir);

        System.out.println("Введите имя файла:");
        String fileName = scanner.nextLine();
        System.out.println(dirFileCache.get(fileName));

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
