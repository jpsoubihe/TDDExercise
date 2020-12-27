package com.company;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileListTest {

    private static final String FILE_A= "FileA";
    private static final String FILE_B = "FileB";
    private static final String FILE_C = "FileC";

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
        fileList.openFile(FILE_A);
        assertEquals(fileList.getRecent(), Collections.singletonList(FILE_A));
    }

    @Test
    public void openMultipleFilesShouldStoreMostRecentInTop() {
        FileList fileList = new FileList();
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        assertEquals(asList(FILE_C, FILE_B, FILE_A), fileList.getRecent());
    }

    @Test
    public void shouldNotContainDuplicates(){
        FileList fileList = new FileList();
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_A);
        assertEquals(asList(FILE_A, FILE_B), fileList.getRecent());
    }

    @Test
    public void shouldNotPassListLimit() {
        int limit = 2;
        FileList fileList = new FileList(2);
        assertEquals(2, fileList.limit);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        assertEquals(limit, fileList.getRecent().size());
    }

    @Test
    public void shouldRemoveCaseFullList() {
        int limit = 2;
        FileList fileList = new FileList(2);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        assertEquals(asList(FILE_C,FILE_B), fileList.getRecent());
    }

    @Test
    public void shouldRemoveOldestInCaseFullList() {
        int limit = 2;
        FileList fileList = new FileList(2);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        fileList.openFile(FILE_A);
        assertEquals(asList(FILE_A,FILE_C), fileList.getRecent());
    }
}