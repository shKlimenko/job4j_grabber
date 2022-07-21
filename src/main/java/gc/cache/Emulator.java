package gc.cache;

import java.io.File;
import java.nio.file.Paths;

public class Emulator {
    public static void main(String[] args) {
        DirFileCache dirFileCache = checkDir("C:/projects/cache/");
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

    public static void putCache(DirFileCache dirFileCache, String key, String value) {
        dirFileCache.put(key, value);
    }

    public static String loadCache(DirFileCache dirFileCache, String key) {
        return dirFileCache.get(key);
    }
}
