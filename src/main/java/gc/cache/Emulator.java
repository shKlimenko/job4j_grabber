package gc.cache;

import java.io.File;
import java.nio.file.Paths;

public class Emulator {
    public static void main(String[] args) {
        DirFileCache dirFileCache = checkDir(".\\src\\main\\java\\gc\\cache\\");

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
