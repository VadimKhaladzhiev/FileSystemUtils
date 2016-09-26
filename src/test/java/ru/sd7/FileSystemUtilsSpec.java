package ru.sd7;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
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
    public void whenPathNotExistsThenRuntimeException(){
        fileSystemUtils = new FileSystemUtils(null);
        exception.expect(RuntimeException.class);
        fileSystemUtils.dir();
    }

    @Test
    public void whenFolderNotExistsThenRuntimeException(){
        fileSystemUtils = new FileSystemUtils("not_exists_folder");
        exception.expect(RuntimeException.class);
        fileSystemUtils.dir();
    }

    @Test
    public void whenDirThenResultNotNull(){
        Assert.assertNotNull(fileSystemUtils.dir());
    }

    @Test
    public void whenDirIsEmptyThenResultEmpty(){
        emptyFolder();
        Assert.assertEquals(fileSystemUtils.dir().isEmpty(), true);
    }

    @Test
    public void whenDirResultis1(){
        List<String> list = fileSystemUtils.dir();
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
