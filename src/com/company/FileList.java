package com.company;

import java.util.LinkedList;
import java.util.List;

public class FileList {

    private List<String> recentFiles;

    private final int limit;

    private boolean locked;

    public FileList(int limit) {
        this.recentFiles = new LinkedList<>();
        this.limit = limit;
        this.locked = false;
    }
    public List<String> getRecent() {
        return recentFiles;
    }

    public void setRecent(List<String> fileList){
        recentFiles = fileList;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    public int getLimit() {
        return limit;
    }

    public void empty(){
        setRecent(new LinkedList<>());
    }

    public void openFile(String fileName) {
        if (!locked) {
            if (getRecent().size() == limit) {
                recentFiles.remove(getRecent().size() - 1);
            }
            recentFiles.remove(fileName);
            recentFiles.add(0, fileName);
        }
    }
}
