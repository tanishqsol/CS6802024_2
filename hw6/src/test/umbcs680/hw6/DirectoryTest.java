package umbcs680.hw6;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;


import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {
    private static FileSystem fs;
    private static Directory root, home, windows_C, windows_D, windows_E;
    private static File build_xml, readme, ivy_xml, gitIgnore;
    private static LocalDateTime time;





    @BeforeAll
    public static void setUpFs() {
        fs = testFixtureInitializer.createFS();

        root = fs.getRootDirs().get(0);
        home = root.findDir("home");
        time = testFixtureInitializer.getTime();

        windows_C = home.findDir("windows_C");
        windows_D = home.findDir("windows_D");
        windows_E = home.findDir("windows_E");
        build_xml = windows_C.findFile("build.xml");
        readme = root.findFile("readme.md");
        ivy_xml = windows_D.findFile("ivy.xml");
        gitIgnore = windows_E.findFile(".gitignore");
    }

    @AfterEach
    public void tearDown() {
        removeChild(windows_C, "bin");
        removeChild(windows_D, "test1");
    }

    private String[] dirToStringArray(Directory dir) {
        String parentName = null;
        Directory parent = dir.getParent();
        if (parent != null) {
            parentName = parent.getName();
        }
        String[] dirInfo = {dir.getName(), Integer.toString(dir.getSize()),parentName};
        return dirInfo;
    }

    private void removeChild(Directory directory, String name) {
        Iterator<FSElement> iterator = directory.getChildren().iterator();
        while (iterator.hasNext()) {
            FSElement element = iterator.next();
            if (element.getName().equals(name)) {
                iterator.remove();
            }
        }
    }
    @Test
    public void testIsDirectory() {
        assertTrue(root.isDirectory());
        assertTrue(home.isDirectory());
        assertTrue(windows_C.isDirectory());
        assertTrue(windows_E.isDirectory());
        assertTrue(windows_E.isDirectory());
        assertFalse(readme.isDirectory());
        assertFalse(gitIgnore.isDirectory());
        assertFalse(build_xml.isDirectory());
        assertFalse(ivy_xml.isDirectory());
        assertFalse(readme.isDirectory());
    }

    @Test
    public void testIsFile() {
        assertFalse(root.isFile());
        assertFalse(home.isFile());
        assertFalse(windows_C.isFile());
        assertFalse(windows_E.isFile());
        assertFalse(windows_E.isFile());
        assertTrue(readme.isFile());
        assertTrue(gitIgnore.isFile());
        assertTrue(build_xml.isFile());
        assertTrue(ivy_xml.isFile());
        assertTrue(readme.isFile());
    }

    @Test
    public void testDirectoryEqualityRoot() {
        String[] expected1 = { "root","0",null };
        Directory actual1 = root;
        assertArrayEquals(expected1, dirToStringArray(actual1));

    }
    @Test
    public void verifyDirectoryEqualityHome()
    {
        String[] expected2 = { "home","0","root" };
        Directory actual2 = home;
        assertArrayEquals(expected2, dirToStringArray(actual2));


    }
    @Test
    public void verifyDirectoryEqualityWindowsC()
    {
        String[] expected3 = { "windows_C","0","home" };
        Directory actual3 = windows_C;
        assertArrayEquals(expected3, dirToStringArray(actual3));

    }
    @Test
    public void verifyDirectoryEqualityWindowsD()
    {
        String[] expected4 = { "windows_D","0","home" };
        Directory actual4 = windows_D;
        assertArrayEquals(expected4, dirToStringArray(actual4));

    }
    @Test
    public void verifyDirectoryEqualityWindowsE()
    {
        String[] expected5 = { "windows_E","0","home" };
        Directory actual5 = windows_E;
        assertArrayEquals(expected5, dirToStringArray(actual5));

    }


    @Test
    public void nameEqualityTest(){
        assertEquals("home",root.getChildren().get(0).getName());
        assertEquals("windows_C",home.getChildren().get(0).getName());
        assertEquals("windows_D",home.getChildren().get(1).getName());
        assertEquals("windows_E",home.getChildren().get(2).getName());

    }
    @Test
    public void sizeEqualityTest(){
        assertEquals(0,(windows_C.getSize()+windows_D.getSize()+windows_E.getSize()));//size of dirs is 0
        assertEquals(4,windows_C.getChildren().get(0).getSize());
        assertEquals(6,windows_D.getChildren().get(0).getSize());
        assertEquals(1,windows_E.getChildren().get(0).getSize());

    }

    @Test
    public void timeEqualityTest(){
        assertEquals(time,root.getCreationTime());
        assertEquals(time,windows_C.getCreationTime());
        assertEquals(time,windows_D.getCreationTime());
        assertEquals(time,windows_E.getCreationTime());

    }

    @Test
    public void testCountChildren() {
        File bin = new File(windows_C, "bin", 0, time);
        assertEquals(2, root.getChildren().size()); //home and readme
        assertEquals(2, windows_C.getChildren().size()); // one file(bin) and one subdirectory(home)
        assertEquals(1, windows_D.getChildren().size()); // one file(bin) and one subdirectory(home)
        assertEquals(1, windows_E.getChildren().size()); // one file(bin) and one subdirectory(home)

    }

    @Test
    public void testAddChild() {
        File bin = new File(windows_C, "bin", 0, time);
        File test1 = new File(windows_D, "test1", 3, time);
        root.appendChild(bin);
        root.appendChild(test1);
        assertEquals(2, windows_C.countChildren()); //2 files -> test1 and bin (added in this test)
    }

    @Test
    public void getSubDirectories() {
        LinkedList<Directory> homeDirs = home.getSubDirectories();
        assertEquals(3,homeDirs.size()); //all the windows C,D,E
        assertEquals("home", root.getSubDirectories().getFirst().getName()); //root has only one subdirectory -> home
    }

    @Test
    public void getFiles() {
        LinkedList<File> windowsCFiles = windows_C.getFiles();
        System.out.println("windows c"+windowsCFiles);
        assertEquals(1,windowsCFiles.size() );
        assertEquals("build.xml",windowsCFiles.get(0).getName());
        assertTrue (windowsCFiles.contains(build_xml));
        assertTrue (windowsCFiles.contains(build_xml));

    }

    @Test
    public void getTotalSize() {
        assertEquals(15,root.getTotalSize()); //total size of files 6+4+4+1
    }

    @Test
    public void getCreationTime(){
        assertEquals(windows_C.getCreationTime(),time);
    }




}