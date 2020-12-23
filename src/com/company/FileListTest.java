package com.company;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileListTest {


    /**
     * On first application execution it should have an empty list of recent files.
     */
    @Test
    public void shouldReturnEmptyList(){
        FileList fileList = new FileList();
        assertEquals(fileList.getRecent(), emptyList());
    }

    @Test
    public void openFileShouldAddToRecentFileList() {
        FileList fileList = new FileList();
        FileList.openFile("FileA");
        assertEquals(fileList.getRecent(), asList("FileA"));
    }
}