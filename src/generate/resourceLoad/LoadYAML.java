package generate.resourceLoad;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;

/**
 * 这个类加载.yaml配置文件
 */
public class LoadYAML {

    /**
     * 加载yaml资源
     * @return
     */
    public Map loadFromYaml(){
        Map load=new HashMap();

        try {
            InputStream resourceAsStream = LoadYAML.class.getResourceAsStream("../../resource/setting1.yaml");
            Yaml y = new Yaml();
            load= (Map) y.load(resourceAsStream);

        }catch (Exception e){
            e.fillInStackTrace();
        }
        return load;
    }

}
