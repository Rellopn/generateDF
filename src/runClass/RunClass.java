package runClass;

import general.General;
import general.GeneralDirOrFile;
import general.LoadSetting;
import general.NameAndPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class RunClass {
    public void generalStart() throws IOException {

        //加载setting
        LoadSetting loadSetting = new LoadSetting();



        //获得生成的文件夹或者文件的名字
        loadSetting.getGeneralDirOrFile();
        loadSetting.getGeneralPath();
        loadSetting.getTempName();
        loadSetting.getTempType();
        //加载生成文件的类
        GeneralDirOrFile generalDirOrFile = new GeneralDirOrFile(new NameAndPath(loadSetting.templateName, loadSetting.generalPath));
        NameAndPath nameAndPath = new NameAndPath(loadSetting.templateName, loadSetting.generalPath);
        Map<String, String> nameAndPath1 = nameAndPath.getNameAndPath();
        Iterator<String> iterator = nameAndPath1.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            String[] s = nameAndPath1.get(next).split(",");
            //生成文件夹或者文件
            generalDirOrFile.deepSearchAndMake(loadSetting.generalDirOrFile,next ,s[0],s[1],s[2],"");
        }
    }

    public static void main(String[] args) throws IOException {
        new RunClass().generalStart();
    }
}
