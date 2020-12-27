package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FileList {

    private List<String> recentFiles;

    private int limit;

    public FileList(int limit) {
        this.recentFiles = new LinkedList<>();
        this.limit = limit;
    }
    public List<String> getRecent(){
        return recentFiles;
    }

    public int getLimit(){
        return limit;
    }

    public void openFile(String fileName) {
        if(getRecent().size() == limit){
            recentFiles.remove(getRecent().size() - 1);
        }
        recentFiles.remove(fileName);
        recentFiles.add(0, fileName);
    }
}
