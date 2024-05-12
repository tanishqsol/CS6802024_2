package umbcs680.hw6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class FileSystemTest {
    private static FileSystem fileSystem;
    private static Directory root;
    private static LocalDateTime time;

    @BeforeAll
    public static void HW6_setUp() {
        fileSystem = FileSystem.getFileSystem();
        root = new Directory(null,"directory",0, time);
        fileSystem.appendRootDir(root);
    }

    @Test
    public void getFileSystemTest() {
        assertNotNull(FileSystem.getFileSystem());
    }

    @Test
    public void test_singleton() {
        FileSystem anotherFS = FileSystem.getFileSystem();
        assertSame(fileSystem, anotherFS);
    }

    @Test
    public void test_addRootDir() {
        Directory root = new Directory(null,"directory",0, time);

        fileSystem.appendRootDir(root);
        assertTrue(fileSystem.getRootDirs().contains(root));
    }
}