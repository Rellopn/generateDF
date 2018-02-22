package generate.generateDF;

import generate.resourceLoad.ConfigField;
import generate.util.FileUtil;

import java.io.File;

/**
 * 生成java类型 的文件
 */
public class GenerateForJava implements GenerateFByType {

    /**
     * 具体 生成的方法
     *
     * @param absolutePath       路径
     * @param tempName           模板名称
     * @param javaPackagePathAdd 类头上的包名
     * @param stuffix            后缀
     * @Param increasePath       增加的包后缀
     */
    @Override
    public void GenerateFByType(String nowName, String absolutePath, String tempName, String javaPackagePathAdd, String stuffix, String increasePath) {
        ConfigField cf = ConfigField.getConfield();
        HandlerImpl handler = new HandlerImpl(nowName, javaPackagePathAdd, increasePath, cf.getCustom());

        String getTempStr = FileUtil.File2String(System.getProperty("user.dir") + File.separator + "src" + File.separator + "template" + File.separator + tempName);
        String parse = new Parse(handler).parse(getTempStr);
        // 路径到这一层只是文件夹的路径，所以要在路径下不同的文件类型
        // 因为是 java文件，首字母大写
        String transFileName = nowName.substring(0, 1).toLowerCase() + nowName.substring(1)+stuffix+".java";
        String totalPath=absolutePath+File.separator+transFileName;
        FileUtil.String2File(parse,totalPath);
    }
}
