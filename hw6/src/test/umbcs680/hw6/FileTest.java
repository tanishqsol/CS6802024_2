package umbcs680.hw6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static umbcs680.hw6.DirectoryTest.*;

import java.time.LocalDateTime;

public class FileTest {
    private static FileSystem fs;

    private static File build_xml,readme,ivy_xml,gitIgnore;
    private static Directory root, home,windows_C, windows_D, windows_E;
    private static LocalDateTime time;

    @BeforeAll
    public static void setUpFS() {
        fs = testFixtureInitializer.createFS();

        root = fs.getRootDirs().get(0);
        home = root.findDir("home");
        time = testFixtureInitializer.getTime();

        windows_C = home.findDir("windows_C");
        windows_D = home.findDir("windows_D");
        windows_E = home.findDir("windows_E");
        build_xml = windows_C.findFile("build.xml");
        readme = home.findFile("readme.md");
        ivy_xml = windows_D.findFile("ivy.xml");
        gitIgnore = windows_E.findFile(".gitignore");

    }

    private String[] fileToStringArray(File f)
    {
        String parentName = null;
        Directory parent = f.getParent();
        if (parent != null) {
            parentName = parent.getName();
        }
        String[] dirInfo = {f.getName(), Integer.toString(f.getSize()),parentName};
        return dirInfo;
    }



@Test
public void verifyFileEqualityBuildXml(){
    String[] expected = { "build.xml","4","windows_C" };
    File actual = build_xml;
    assertArrayEquals(expected, fileToStringArray(actual));
}
    @Test
    public void verifyFileEqualityReadme(){
        String[] expected3 = { "readme.md","4","home" };
        File actual3 = readme;
        assertArrayEquals(expected3, fileToStringArray(actual3));
    }
    @Test
    public void verifyFileEqualityIvyXml(){
        String[] expected3 = { "ivy.xml","6","windows_D" };
        File actual3 = ivy_xml;
        assertArrayEquals(expected3, fileToStringArray(actual3));
    }
    @Test
    public void verifyFileEqualityGitIgnore(){
        String[] expected3 = { ".gitignore","1","windows_E" };
        File actual3 = gitIgnore;
        assertArrayEquals(expected3, fileToStringArray(actual3));
    }

    @Test
    public void testGetName() {
        assertEquals("build.xml", build_xml.getName());
        assertEquals("ivy.xml", ivy_xml.getName());
        assertEquals(".gitignore", gitIgnore.getName());
        assertEquals("readme.md", readme.getName());
    }

    @Test
    public void testGetSize() {
        assertEquals(4, build_xml.getSize());
        assertEquals(6, ivy_xml.getSize());
        assertEquals(1, gitIgnore.getSize());
        assertEquals(4, readme.getSize());
    }

    @Test
    public void testGetCreationTime() {
        assertEquals(time, build_xml.getCreationTime());
        assertEquals(time, ivy_xml.getCreationTime());
        assertEquals(time, gitIgnore.getCreationTime());
        assertEquals(time, readme.getCreationTime());


    }
}
