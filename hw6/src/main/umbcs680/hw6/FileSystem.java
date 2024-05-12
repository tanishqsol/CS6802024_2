package umbcs680.hw6;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class FileSystem {
    private static FileSystem instance = null;
    private LinkedList<Directory> rootDirs;
    private Directory root;




    private FileSystem() {
        rootDirs = new LinkedList<Directory>();
        this.root = new Directory(null, "root", 0, LocalDateTime.now());

    }
    public static FileSystem getFileSystem() {
        if (instance == null) {
            instance = new FileSystem();
        }
        return instance;
    }

    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }
    public void appendRootDir(Directory dir) {
        rootDirs.add(dir);
    }
}