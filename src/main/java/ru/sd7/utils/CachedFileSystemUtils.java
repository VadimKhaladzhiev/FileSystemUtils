package ru.sd7.utils;

import ru.sd7.core.FileSystemUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachedFileSystemUtils {
    private Map<String, CasheResult> results = new HashMap<>();
    private FileSystemUtils utils = new FileSystemUtils();

    public List<String> getDir(String path) throws Exception{
        List<String> result;
        if(results.containsKey(path)){
            result = results.get(path).result;
        }
        else{
            result = utils.getDir(path);
            results.put(path, new CasheResult(result));
        }
        return result;
    }

    private class CasheResult {
        List<String> result;

        CasheResult(List<String> result) {
            this.result = result;
        }
    }
}
