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
        FileList fileList = new FileList(1);
        assertEquals(fileList.getRecent(), emptyList());
    }

    @Test
    public void openFileShouldAddToRecentFileList() {
        int limit = 1;
        FileList fileList = new FileList(limit);
        fileList.openFile(FILE_A);
        assertEquals(fileList.getRecent(), Collections.singletonList(FILE_A));
    }

    @Test
    public void openMultipleFilesShouldStoreMostRecentInTop() {
        int limit = 5;
        FileList fileList = new FileList(limit);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        assertEquals(asList(FILE_C, FILE_B, FILE_A), fileList.getRecent());
    }

    @Test
    public void shouldNotContainDuplicates(){
        int limit = 3;
        FileList fileList = new FileList(limit);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_A);
        assertEquals(asList(FILE_A, FILE_B), fileList.getRecent());
    }

    @Test
    public void shouldNotPassListLimit() {
        int limit = 2;
        FileList fileList = new FileList(limit);
        assertEquals(limit, fileList.getLimit());
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        assertEquals(limit, fileList.getRecent().size());
    }

    @Test
    public void shouldRemoveCaseFullList() {
        int limit = 2;
        FileList fileList = new FileList(limit);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        assertEquals(asList(FILE_C,FILE_B), fileList.getRecent());
    }

    @Test
    public void shouldRemoveOldestInCaseFullList() {
        int limit = 2;
        FileList fileList = new FileList(limit);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        fileList.openFile(FILE_A);
        assertEquals(asList(FILE_A,FILE_C), fileList.getRecent());
    }

    @Test
    public void shouldEmptyFullListAtAnytime() {
        int limit = 3;
        FileList fileList = new FileList(limit);
        fileList.empty();
        assertEquals(emptyList(), fileList.getRecent());
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        fileList.openFile(FILE_C);
        fileList.openFile(FILE_A);
        assertEquals(asList(FILE_A,FILE_C,FILE_B), fileList.getRecent());
        fileList.empty();
        assertEquals(emptyList(), fileList.getRecent());
    }

    @Test
    public void shouldEmptyEmptyListAtAnytime() {
        int limit = 3;
        FileList fileList = new FileList(limit);
        fileList.empty();
        assertEquals(emptyList(), fileList.getRecent());
    }

    @Test
    public void shouldNotUpdateListIfItsLocked() {
        int limit = 3;
        FileList fileList = new FileList(limit);
        fileList.openFile(FILE_A);
        fileList.openFile(FILE_B);
        assertEquals(asList(FILE_B,FILE_A), fileList.getRecent());
        fileList.lock();
        fileList.openFile(FILE_C);
        assertEquals(asList(FILE_B,FILE_A), fileList.getRecent());
    }

    @Test
    public void shouldUpdateListAfterUnlock() {
        int limit = 3;
        FileList fileList = new FileList(limit);
        fileList.openFile(FILE_A);
        fileList.lock();
        fileList.openFile(FILE_B);
        assertEquals(asList(FILE_A), fileList.getRecent());
        fileList.unlock();
        fileList.openFile(FILE_B);
        assertEquals(asList(FILE_B,FILE_A), fileList.getRecent());
    }


}