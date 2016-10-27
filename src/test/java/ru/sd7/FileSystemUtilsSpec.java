package ru.sd7;

import org.junit.*;
import org.junit.rules.ExpectedException;
import ru.sd7.core.FileSystemUtils;

import java.io.File;
import java.io.IOException;

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
        fileSystemUtils.getDir();
    }

    @Test
    public void whenFolderNotExistsThenRuntimeException() throws Exception{
        fileSystemUtils = new FileSystemUtils("not_exists_folder");
        exception.expect(Exception.class);
        fileSystemUtils.getDir();
    }

    @Test
    public void whenDirThenResultNotNull() throws Exception{
        Assert.assertNotNull(fileSystemUtils.getDir());
    }

    @Test
    public void whenDirIsEmptyThenResultEmpty() throws Exception{
        emptyFolder();
        Assert.assertEquals(fileSystemUtils.getDir().isEmpty(), true);
    }

    @Test
    public void whenDirResultis1() throws Exception{
        Assert.assertEquals(1, fileSystemUtils.getDir().size());
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
