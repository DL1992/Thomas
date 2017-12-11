package algorithms;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String,String> cacheMap;

    public Cache(Map<String, String> cacheMap) {
        this.cacheMap = cacheMap;
    }

    public Cache(){
        this.cacheMap= new HashMap<>();
    }

    public void saveCache(String path){
        try {
            ObjectOutputStream osTermDic = new ObjectOutputStream(new FileOutputStream(new File(path)));
            osTermDic.writeObject(cacheMap);
            osTermDic.flush();
            osTermDic.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTermDic(String path){
        ObjectInputStream oisTermDic;
        try {
            oisTermDic = new ObjectInputStream(new FileInputStream(new File(path)));
            cacheMap=(HashMap<String,String>)oisTermDic.readObject();
            oisTermDic.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
