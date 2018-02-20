package generate.resourceLoad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加载配置文件到内存中的属性
 */
public class ConfigField {

    volatile private static ConfigField cf = null;

    private ConfigField() {
    }

    /**
     * 线程安全的单例
     *
     * @return
     */
    public static ConfigField getConfield() {
        if (cf != null) {

        } else {
            synchronized (ConfigField.class) {
                if (cf == null) {
                    cf = new ConfigField();
                }
            }
        }
        return cf;
    }

    //加载的所有配置
    private Map all;

    // 生成文件的list对象
    private ArrayList DFName;

    // generalAbsPath 生成文件的路径
    private ArrayList generalAbsPath;

    //每一个路径对应的setting
    private ArrayList<HashMap> pathsetting;

    //对应 的模板名称，如果一个文件夹下面有多个文件的话，数量要和这里的string[]对应起来。
    private List<String[]> tempName;

    // 文件的后缀 对应模板个数
    private List<String[]> fileStuffix;

    // 后缀，生成文件的后缀
    private List<String[]> stuffix;

    // 如果是java文件的话，需要定义最上一级的包名
    private List<String[]> packageName;

    // 自定义字段
    private Map<String,String> custom;

    public Map getAll() {
        return all;
    }

    public void setAll(Map all) {
        this.all = all;
    }

    public ArrayList getDFName() {
        return DFName;
    }

    public void setDFName(ArrayList DFName) {
        this.DFName = DFName;
    }

    public ArrayList getGeneralAbsPath() {
        return generalAbsPath;
    }

    public void setGeneralAbsPath(ArrayList generalAbsPath) {
        this.generalAbsPath = generalAbsPath;
    }

    public ArrayList<HashMap> getPathsetting() {
        return pathsetting;
    }

    public void setPathsetting(ArrayList<HashMap> pathsetting) {
        this.pathsetting = pathsetting;
    }

    public List<String[]> getTempName() {
        return tempName;
    }

    public void setTempName(List<String[]> tempName) {
        this.tempName = tempName;
    }

    public List<String[]> getFileStuffix() {
        return fileStuffix;
    }

    public void setFileStuffix(List<String[]> fileStuffix) {
        this.fileStuffix = fileStuffix;
    }

    public List<String[]> getStuffix() {
        return stuffix;
    }

    public void setStuffix(List<String[]> stuffix) {
        this.stuffix = stuffix;
    }

    public List<String[]> getPackageName() {
        return packageName;
    }

    public void setPackageName(List<String[]> packageName) {
        this.packageName = packageName;
    }

    public Map<String, String> getCustom() {
        return custom;
    }

    public void setCustom(Map<String, String> custom) {
        this.custom = custom;
    }
}
