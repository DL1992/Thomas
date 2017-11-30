package IO;

import java.util.HashMap;
import java.util.Map;

public class PostingIO {

    private Map<String,String> termPathMap;

    public PostingIO(){
        initialize();
    }

    private void initialize() {
        createPostingFolders();
        createPostingMap();
    }

    private void createPostingFolders() {
    }

    private void createPostingMap() {
        Map<String,String> termPathMap = new HashMap<>();
        String Path = ;
        for(char alphabet = 'a'; alphabet <='z'; alphabet ++ )
        {
            if(alphabet>='a' &&  alphabet <='e'){termPathMap.put(alphabet,path+"\\")};
            if(alphabet>='f' &&  alphabet <='j'){termPathMap.put(alphabet,path+"\\")};
            if(alphabet>='k' &&  alphabet <='o'){termPathMap.put(alphabet,path+"\\")};
            if(alphabet>='p' &&  alphabet <='t'){termPathMap.put(alphabet,path+"\\")};
            if(alphabet>='u' &&  alphabet <='z'){termPathMap.put(alphabet,path+"\\")};
        }
        for (int i = 0; i <= 9; i++) {
            termPathMap.put(i,path+"\\")
        }
    }
}
