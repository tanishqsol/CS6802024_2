package umbcs680.hw6;

import java.time.LocalDateTime;

public class testFixtureInitializer {
    private static final LocalDateTime time = LocalDateTime.now();


    public static FileSystem createFS() {
        FileSystem fs = FileSystem.getFileSystem();
        // Create root directory
        Directory root = new Directory(null, "root", 0, time);
        fs.appendRootDir(root);

        Directory home = new Directory(root, "home", 0, time);
        Directory windows_C = new Directory(home, "windows_C", 0, time);
        Directory windows_D = new Directory(home, "windows_D", 0, time);
        Directory windows_E = new Directory(home, "windows_E", 0, time);

        File build_xml = new File(windows_C, "build.xml", 4,time);
        File readme = new File(root, "readme.md", 4, time);
        File ivy_xml = new File(windows_D, "ivy.xml", 6, time);
        File gitIgnore = new File(windows_E, ".gitignore", 1, time);


        return fs;
    }
    public static LocalDateTime getTime() {
        return time;
    }
}
