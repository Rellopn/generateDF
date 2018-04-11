package generate.generateDF;

import generate.resourceLoad.ConfigField;

import java.io.File;
import java.util.*;

/**
 * 生成文件的类
 */
public class GenerateDF {
    // 配置属性
    private ConfigField cf = null;
    private HandleMultipleSetting handleMultipleSetting=null;

    public GenerateDF(ConfigField cf) {
        this.cf = cf;
        this.handleMultipleSetting=new HandleMultipleSetting();
    }

    public void runGenerateDf(){
        ArrayList dfName = this.cf.getDFName();
        // 得到路径的个数
        ArrayList generalAbsPath = this.cf.getGeneralAbsPath();
        List<String[]> tempName = this.cf.getTempName();
        List<String[]> fileStuffix = this.cf.getFileStuffix();
        List<String[]> packageName = this.cf.getPackageName();
        List<String[]> stuffix = this.cf.getStuffix();
        // 开始 生成，循环路径，对每个路径的文件进行生成
        for (int i=0;i<generalAbsPath.size();i++){
            String nowAbsPath= (String) generalAbsPath.get(i);
            deepSearchAndMake(dfName,nowAbsPath,"",tempName.get(i),fileStuffix.get(i),packageName.get(i)[0],stuffix.get(i));
        }
    }

    /**
     * 深度搜索，并生成文件夹及文件
     * @param nowDeepDirOrFile      当前深度的文件夹或者文件名
     * @param absolutePath          要生成文件的绝对路径+生成到当前层级的路径
     * @Param nowDeep               累加的名字，以.分隔
     * @param tempName              模板名称
     * @param type                  文件类型
     * @param javaPackagePathAdd    递归的时候每一层加上的package，也就是生成java文件最上面的package
     */
    //这里 注意下。加一个表示符号生成相应的文件
    public void deepSearchAndMake (List nowDeepDirOrFile,String absolutePath,String nowDeep,String[] tempName,String[] type,String javaPackagePathAdd,String[] stuffix){
            for (int i=0;i<nowDeepDirOrFile.size();i++){
                Object willHandlePath = nowDeepDirOrFile.get(i);
                HashMap hashMap=null;
                //如果是map的话
                if (willHandlePath instanceof Map){
                    hashMap = (HashMap)willHandlePath;
                }else {
//                    System.out.println("到底层了，最后一层是，将要生成文件夹及文件："+(String)willHandlePath);
//                    System.out.println("文件夹及文件生成的路径是"+absolutePath+ File.separator+(String) willHandlePath);
//                    System.out.println("package是 "+javaPackagePathAdd+"."+(String) willHandlePath);
                    makeDir(absolutePath);
                    handleMultipleSetting.handleMutipleSetting((String) willHandlePath,absolutePath,tempName,type,javaPackagePathAdd,stuffix,nowDeep);
                    continue;
                }

                Iterator iterator = hashMap.keySet().iterator();
                while (iterator.hasNext()){
                    String theNext = (String)iterator.next();
//                    System.out.println("将要生成文件夹"+theNext);
//                    System.out.println("文件夹生成的路径是"+absolutePath+ File.separator+theNext);
//                    System.out.println("package是 "+javaPackagePathAdd+"."+theNext);
                    //当前目录的层级
                    String addNowDeep=nowDeep+"."+theNext;
                    // 绝对路径+当前层级的目录
                    String addPath=absolutePath+File.separator+theNext;
                    // java每一层增加的包名
                    String addPackage=javaPackagePathAdd+"."+theNext;
                    // 下一次遍历的list
                    List nextList = (List) hashMap.get(theNext);
                    //创建文件夹
                    makeDir(absolutePath);
                    deepSearchAndMake(nextList,addPath,addNowDeep,tempName,type,addPackage,stuffix);
                }
            }
    }

    /**
     * 创建文件夹
     * @param path
     */
    static void makeDir(String path){
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
    }
}
