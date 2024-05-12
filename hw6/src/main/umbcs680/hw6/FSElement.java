package umbcs680.hw6;

import java.time.LocalDateTime;

public abstract class FSElement {
    private String name;
    private int size;
    private LocalDateTime creationTime;
    private Directory parent;


    FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }


    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Directory getParent()
    {
        return parent;
    }

    public abstract boolean isDirectory();
    public abstract boolean isFile();

}