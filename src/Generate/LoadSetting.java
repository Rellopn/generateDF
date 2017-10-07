package Generate;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * loading .yaml setting.
 * @author sr
 * 2017/10/3
 */
public class LoadSetting {

    //加载完成的结构
    public Map loaded;

    //生成文件及文件夹的路径
    public ArrayList generalPath;

    //数据结构，生成文件及文件夹的名字。
    public ArrayList  generalDirOrFile;

    //
    public ArrayList templateName;

    //类型
    public ArrayList templateType;
    //构造器生成加载完的数据结构
    public LoadSetting() throws IOException {
    }

    /**
     * 加载模版成
     * @return
     * @throws IOException
     */
    public static Map<String, String> loadTemplate(){
        Map load=new HashMap();
        try {

            InputStream resourceAsStream = LoadSetting.class.getResourceAsStream("/resource/setting.yaml");
            Yaml y = new Yaml();
            load= (Map) y.load(resourceAsStream);

        }catch (Exception e){
            e.fillInStackTrace();
        }
        return load;
    }

    /**
     * 获得生成的文件夹路径
     */
    public void getGeneralPath(){
        this.loaded=loadTemplate();
        this.generalPath=(ArrayList)this.loaded.get("generalAbsPath");
    }

    /**
     * 获得生成的文件夹或者文件
     */
    public void getGeneralDirOrFile(){
        this.loaded=loadTemplate();
        this.generalDirOrFile=(ArrayList)this.loaded.get("tempName");
    }
    /**
     * 获得生成的文件夹或者文件
     */
    public void getTempName(){
        this.loaded=loadTemplate();
        this.templateName=(ArrayList)this.loaded.get("templateName");
    }

    /**
     * 获得生成的文件夹或者文件
     */
    public void getTempType(){
        this.loaded=loadTemplate();
        this.templateType=(ArrayList)this.loaded.get("templateType");
    }
}
