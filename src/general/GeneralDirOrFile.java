package general;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * 根据传入参数生成包及文件的类
 *
 * @author sr
 * 2017/10/3
 */
public class GeneralDirOrFile {

    NameAndPath nameAndPath;
    public GeneralDirOrFile(NameAndPath nameAndPath) {
        this.nameAndPath = nameAndPath;
    }

    /**
     * 建立文件夹及文件
     * @param waitForGeneral list本层到结构
     * @param path 生成文件到绝对路径
     * @param tempName 找哪一个temp
     * @param type 生成到文件类型
     * @param genrealPath java的基础包地址
     * @param willAddPath java基础包地址后面的每层的地址，根据每一层的名字叠加
     * @throws IOException
     */
    public void deepSearchAndMake(ArrayList waitForGeneral, String path,String tempName,String type,String genrealPath,String willAddPath) throws IOException {

        boolean hasOneMap = false;
        for (int i = 0; i < waitForGeneral.size(); i++) {
            if (waitForGeneral.get(i) instanceof Map) {
                hasOneMap = true;
            }
        }
        if (hasOneMap) {
            for (int i = 0; i < waitForGeneral.size(); i++) {

                if (waitForGeneral.get(i) instanceof Map) {
                    String addPath = path + "/" + ((Map) waitForGeneral.get(i)).keySet().iterator().next();
                    System.out.println("建立文件夹：" + addPath);
                    File f=new File(addPath);
                    if (!f.exists()){
                        f.mkdir();
                    }
                    for (Object s : ((Map) waitForGeneral.get(i)).values()) {
                        //获取到这一层目录到名字，因为只有一个名字，直接取第一个就好类。
                        String nowPack="";
                        for(Object b:((Map) waitForGeneral.get(i)).keySet()){
                            nowPack=(String) b;
                        }
                        System.err.println(((ArrayList) s));
                        deepSearchAndMake((ArrayList) s, addPath,tempName,type,genrealPath,genrealPath+"."+willAddPath+nowPack);
                    }
                } else {
                    System.out.println("建立文件夹 文件：" + path + "/"+waitForGeneral.get(i));
                    File f=new File(path + "/"+waitForGeneral.get(i));
                    f.mkdir();
                    HandlerImpl handler=new HandlerImpl((String) waitForGeneral.get(i));;
                    if (willAddPath.equals("")){
                        handler.setThisPath(genrealPath+"."+waitForGeneral.get(i));
                    }else {
                        handler.setThisPath(willAddPath+"."+waitForGeneral.get(i));
                    }


                    Parse ss = new Parse(handler);
                    FileReader f1 = new FileReader(new File(System.getProperty("user.dir")+"/src/template/"+tempName));
                    int j = f1.read();
                    String s="";
                    while (j != -1) {
                        s+=((char) j);
                        j = f1.read();
                    }
                    String parse = ss.parse(s);
                    writeTelplate(path,"/"+(String) waitForGeneral.get(i),"."+type,parse);
                    f1.close();

                }
            }
        } else {
            for (int i = 0; i < waitForGeneral.size(); i++) {
                String addPath = path + "/" + waitForGeneral.get(i);
                System.out.println("建立文件夹及文件：" + addPath);
                File f=new File(addPath);
                f.mkdir();
                HandlerImpl handler = new HandlerImpl((String) waitForGeneral.get(i));
                if (willAddPath.equals("")){
                    handler.setThisPath(genrealPath+"."+waitForGeneral.get(i));
                }else {
                    handler.setThisPath(willAddPath+"."+waitForGeneral.get(i));
                }
                Parse ss = new Parse(handler);
                FileReader f1 = new FileReader(new File(System.getProperty("user.dir")+"/src/template/"+tempName));
                int j = f1.read();
                String s="";
                while (j != -1) {
                    s+=((char) j);
                    j = f1.read();
                }
                String parse = ss.parse(s);
                writeTelplate(path,"/"+(String) waitForGeneral.get(i),"."+type,parse);
                f1.close();

            }
            return;
        }
    }

    /**
     * 写文件
     * @param path 文件路径
     * @param tempName 文件名
     * @param type 文件类型
     * @param inputString 文件内容
     * @throws IOException
     */
    public void writeTelplate(String path,String tempName,String type,String inputString) throws IOException {
        FileWriter fw = new FileWriter(path+tempName+File.separator+tempName+type, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(inputString);
        bw.close();
        fw.close();
    }
}
