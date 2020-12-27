package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FileList {

    private List<String> recentFiles;

    public FileList() {
        this.recentFiles = new LinkedList<>();
    }
    public List<String> getRecent(){
        return recentFiles;
    }

    public void openFile(String fileName) {
        recentFiles.remove(fileName);
        recentFiles.add(0, fileName);
    }
}
