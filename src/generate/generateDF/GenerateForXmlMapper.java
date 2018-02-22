package generate.generateDF;

import generate.resourceLoad.ConfigField;
import generate.util.FileUtil;

import java.io.File;

public class GenerateForXmlMapper implements GenerateFByType {
    @Override
    public void GenerateFByType(String nowName, String absolutePath, String tempName, String javaPackagePathAdd, String stuffix, String increasePath) {
        ConfigField cf = ConfigField.getConfield();
        HandlerImpl handler = new HandlerImpl(nowName, javaPackagePathAdd, increasePath, cf.getCustom());

        String getTempStr = FileUtil.File2String(System.getProperty("user.dir") + File.separator + "src" + File.separator + "template" + File.separator + tempName);
        String parse = new Parse(handler).parse(getTempStr);
        // 路径到这一层只是文件夹的路径，所以要在路径下不同的文件类型
        // 因为是 java文件，首字母大写
        String transFileName = nowName.substring(0, 1).toLowerCase() + nowName.substring(1) + stuffix + ".xml";
        String totalPath = absolutePath + File.separator + transFileName;
        FileUtil.String2File(parse, totalPath);
    }
}
