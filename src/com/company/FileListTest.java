package com.company;

import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class FilesTest {


    /**
     * On first application execution it should have an empty list of recent files.
     */
    @Test
    public void shouldReturnEmptyList(){
        Files files = new Files();
        assertEquals(files.getRecent, emptyList());
    }
}