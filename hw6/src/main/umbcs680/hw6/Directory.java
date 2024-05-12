package umbcs680.hw6;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Directory extends FSElement {

    private LinkedList<FSElement> children = new LinkedList<FSElement>();
    private LinkedList<Directory> directoryList = new LinkedList<Directory>();


    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if (parent != null) {
            parent.appendChild(this);
        }
    }

    public void appendChild(FSElement child) {
        children.add(child);
    }

    public List<FSElement> getChildren() {
        return children;
    }

    public int countChildren() {
        return children.size();
    }
    public LinkedList<Directory> getSubDirectories()
    {
        for (FSElement e : getChildren()) {
            if (e instanceof Directory)
                directoryList.add((Directory) e);
        }
        return directoryList;
    }


    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<>();
        for (FSElement element : children) {
            if (!element.isDirectory()) {
                files.add((File) element);
            }
        }
        return files;
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement element : children) {
            totalSize += element.getSize();
            if (element.isDirectory()) {
                totalSize += ((Directory) element).getTotalSize();
            }
        }
        return totalSize;
    }

    public Directory findDir(String name) {
        for (FSElement element : this.children) {
            if (element instanceof Directory && element.getName().equals(name)) {
                return (Directory)element;
            }
        }
        return null;
    }

    public File findFile(String name) {
        for (FSElement element : this.children) {
            if (element instanceof File && element.getName().equals(name)) {
                return (File)element;
            }
        }
        return null;
    }
    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isFile() {
        return false;
    }

}

