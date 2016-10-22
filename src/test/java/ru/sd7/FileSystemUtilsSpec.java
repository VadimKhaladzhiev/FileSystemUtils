package ru.sd7;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileSystemUtilsSpec {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private FileSystemUtils fileSystemUtils;

    private String folder = "test_folder";

    @Before
    public final void before(){
        fileSystemUtils = new FileSystemUtils(folder);
        createFolder();
        createTmpFile();
    }

    private void createTmpFile() {
        try {
            new File(folder+"/"+"tmp.txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public final void after(){
        removeFolder();
    }

    @Test
    public void whenGetPathThenPathNotNull(){
        assertNotEquals(null, fileSystemUtils.getPath());
    }

    @Test
    public void whenPathNotExistsThenRuntimeException() throws Exception{
        fileSystemUtils = new FileSystemUtils(null);
        exception.expect(Exception.class);
        fileSystemUtils.dir();
    }

    @Test
    public void whenFolderNotExistsThenRuntimeException() throws Exception{
        fileSystemUtils = new FileSystemUtils("not_exists_folder");
        exception.expect(Exception.class);
        fileSystemUtils.dir();
    }

    @Test
    public void whenDirThenResultNotNull(){
        List<String> list = Collections.emptyList();
        try {
            list = fileSystemUtils.dir();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

    @Test
    public void whenDirIsEmptyThenResultEmpty(){
        emptyFolder();
        List<String> list = Collections.emptyList();
        try {
            list = fileSystemUtils.dir();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        Assert.assertEquals(list.isEmpty(), true);
    }

    @Test
    public void whenDirResultis1(){
        List<String> list = Collections.emptyList();
        try {
            list = fileSystemUtils.dir();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        Assert.assertEquals(1, list.size());
    }

    private void createFolder() {
        File file = new File(folder);
        if (!file.exists()) file.mkdir();
    }

    private void removeFolder() {
        File file = new File(folder);
        emptyFolder();
        file.delete();
    }

    private void emptyFolder() {
        File file = new File(folder);
        String[]entries = file.list();
        for(String s: entries){
            File currentFile = new File(file.getPath(),s);
            currentFile.delete();
        }
    }

}
