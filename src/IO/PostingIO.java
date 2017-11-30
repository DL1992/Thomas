package IO;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostingIO {
    String path;

    private Map<Character,String> termPathMap;

    public PostingIO(String path){
        this.path=path;
        initialize(path);
    }

    private void initialize(String path) {
        createPostingFolders(path);
        createPostingMap(path);
    }

    private void createPostingFolders(String path) {
        String postingPath = path+ "\\Posting";
        new File(postingPath).mkdirs();
        new File(postingPath + "\\A-E").mkdirs();
        new File(postingPath + "\\F-J").mkdirs();
        new File(postingPath + "\\K-O").mkdirs();
        new File(postingPath + "\\P-T").mkdirs();
        new File(postingPath + "\\U-Z").mkdirs();
        new File(postingPath + "\\0-9").mkdirs();
    }

    private void createPostingMap(String path) {
        Map<Character,String> termPathMap = new HashMap<>();
        for(char alphabet = 'a'; alphabet <='z'; alphabet ++ )
        {
            if(alphabet>='a' &&  alphabet <='e')
                termPathMap.put(alphabet, path + "\\A-E");
            if(alphabet>='f' &&  alphabet <='j')
                termPathMap.put(alphabet, path+"\\F-J");
            if(alphabet>='k' &&  alphabet <='o')
                termPathMap.put(alphabet, path+"\\K-O");
            if(alphabet>='p' &&  alphabet <='t')
                termPathMap.put(alphabet, path+"\\P-T");
            if(alphabet>='u' &&  alphabet <='z')
                termPathMap.put(alphabet, path+"\\U-Z");
        }
        for (char i = '0'; i <= '9'; i++) {
            termPathMap.put(i,path+"\\0-9");
        }
        this.termPathMap=termPathMap;
    }

    public void createPostingFile(Map<String,List<String>> tempPosting){


    }
}
