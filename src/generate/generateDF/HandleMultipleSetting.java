package generate.generateDF;

import java.io.File;

/**
 * 从这里处理多个文件夹或者文件的情况
 */
public class HandleMultipleSetting {

    //文件类型生成器
    private GenerateFByType generateFByType;

    /**
     * 以{@willHandlePath}为基准，以逗号分隔为界定标准。
     * 如果有的如下所示的写法
     * file1,file2
     * 则生成文件file1，file2
     * 如果这样写
     * file1
     * 则生成文件夹及文件 file1
     * 如果这样写
     * file1,
     * 则生成文件file1，没有生成文件夹。
     * 逗号后边多个文件对应{@tempName}{@type}{@stuffix},如果少则取最后的，如果多则省略。
     *
     * @param willHandlePath
     * @param absolutePath
     * @param tempName
     * @param type
     * @param javaPackagePathAdd
     * @param stuffix
     * @Param increasePath
     */
    public void handleMutipleSetting(String willHandlePath, String absolutePath, String[] tempName, String[] type, String javaPackagePathAdd, String[] stuffix
            , String increasePath) {
        // 判断有没有逗号
        int hasSeparator = willHandlePath.indexOf(",");
        if (hasSeparator != -1) {
            String[] splitWillHandlePath = willHandlePath.split(",");
            for (int i = 0; i < splitWillHandlePath.length; i++) {
                String nowTempName = getNowStr(tempName, i);
                String nowType = getNowStr(type, i);
                String nowStuffix = getNowStr(stuffix, i);


                choiceGenerateType(willHandlePath, absolutePath, nowTempName, nowType, javaPackagePathAdd, nowStuffix, increasePath);
            }
        } else {
            String nowTempName = getNowStr(tempName, 0);
            String nowType = getNowStr(type, 0);
            String nowStuffix = getNowStr(stuffix, 0);
            //生成上级的文件夹
            GenerateDF.makeDir(absolutePath + File.separator + willHandlePath);
            absolutePath += File.separator + willHandlePath;
            choiceGenerateType(willHandlePath, absolutePath, nowTempName, nowType, javaPackagePathAdd, nowStuffix, increasePath);
        }
    }

    /**
     * 获得当前的 循环次数的 名称，如果超过了数组的最大数，则取数组的最后一个
     *
     * @param str
     * @param i
     * @return
     */
    private String getNowStr(String[] str, int i) {
        String nowTempName = "";
        if (i > str.length) {
            nowTempName = str[str.length];
        } else {
            nowTempName = str[i];
        }
        return nowTempName;
    }

    /**
     * 根据文件类型，决定采用哪个文件生成器
     *
     * @param absolutePath
     * @param tempName
     * @param type
     * @param javaPackagePathAdd
     * @param stuffix
     */
    private void choiceGenerateType(String nowPath, String absolutePath, String tempName, String type, String javaPackagePathAdd, String stuffix, String increasePath) {
        if (type.equals("java")) {
            generateFByType = new GenerateForJava();
        } else {
            System.err.println("当前没有匹配的处理器，请检查文件类型是否正确。如果检查无误还是有这个错误，请编写类继承GenerateFByType接口,当前文件类型：" + type);
            return;
        }
        generateFByType.GenerateFByType(nowPath, absolutePath, tempName, javaPackagePathAdd, stuffix, increasePath);
    }
}
