package general;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class General {

    Map load;
    public General(Map load,String nowMapName){
        this.load=load;
    }
//    public Map<String,String> readTemplate(String path,String templateName) throws IOException {
//        Parse ss = new Parse(new HandlerImpl());
//        String[] split = (String[]) this.load.get("templateName");
//        String property = System.getProperty("user.dir");
//        Map<String, String> templateAndTemplateName=new HashMap<>();
//        //循环读入替换
//        for (int i = 0; i < split.length; i++) {
//            FileReader f = new FileReader(new File(property+"/src/template/"+split[i]));
//            int j = f.read();
//            String s="";
//            while (j != -1) {
//                s+=((char) j);
//                j = f.read();
//            }
//            String parse = ss.parse(s);
//            templateAndTemplateName.put(split[i],parse);
//            f.close();
//        }
//        return templateAndTemplateName;
//    }



    public static void main(String[] args) {
        String a="bbbb";
//        a.replaceFirst("^\\S","S");
        String gg=a.substring(0, 1).toUpperCase()+a.substring(1);
        System.out.println(gg);

    }
}
