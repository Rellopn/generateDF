package Generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NameAndPath {

    ArrayList<String> templateName;
    ArrayList<String> generalPath;
    public NameAndPath(ArrayList<String> templateName,ArrayList<String> generalPath){
        this.templateName=templateName;
        this.generalPath=generalPath;
    }

    /**
     * get path and name
     * @return
     */
    public Map<String,String> getNameAndPath(){
        HashMap nameAndPath=new HashMap();
        for (int i = 0; i < generalPath.size(); i++) {
            nameAndPath.put(generalPath.get(i),templateName.get(i));
        }
        return nameAndPath;
    }
}
