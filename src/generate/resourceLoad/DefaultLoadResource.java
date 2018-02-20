package generate.resourceLoad;

import java.util.*;

/**
 * 默认的加载配置的方式是从.yaml中
 */
public class DefaultLoadResource implements ResourceLoad {

    // 加载后的map
    private Map loadResource;

    @Override
    public Map loadResource(String loadfrom) {
        //如果加载方式是从本地的话，那么从.yaml中加载
        if (loadfrom.equals("LOCAL_SETTING")) {
            loadResource = new LoadYAML().loadFromYaml();
        }
        return loadResource;
    }

    /**
     * 设置 读取配置文件加载到内存中。
     * @param cf
     * @throws Exception
     */
    @Override
    public void handleConfigCenter(ConfigField cf) throws Exception {
        ArrayList tempName = (ArrayList) this.loadResource.get("tempName");
        ArrayList generalAbsPath = (ArrayList) this.loadResource.get("generalAbsPath");
        ArrayList templateName = (ArrayList) this.loadResource.get("templateName");
        ArrayList fileType = (ArrayList) this.loadResource.get("fileType");
        ArrayList stuffix = (ArrayList) this.loadResource.get("stuffix");
        ArrayList packageName = (ArrayList) this.loadResource.get("packageName");

        cf.setAll(this.loadResource);
        cf.setDFName(tempName);
        cf.setGeneralAbsPath(generalAbsPath);
        cf.setTempName(getTemp(templateName));
        cf.setCustom(getCustomerFileds(this.loadResource));
        cf.setStuffix(getTemp(stuffix));
        cf.setFileStuffix(getTemp(fileType));
        cf.setPackageName(getTemp(packageName));
    }

    /**
     * 获得对应属性的数据结构
     *
     * @param template
     * @return
     * @throws Exception
     */
    private List<String[]> getTemp(ArrayList template) throws Exception {
        ArrayList<String[]> rest = new ArrayList<>();
        for (int i=0;i<template.size();i++){
            String aTempName = (String) template.get(i);
            String[] splitTempName = aTempName.split(",");

            rest.add(splitTempName);
        }
        return rest;
    }

    /**
     * 得到自定义字段
     * @param all
     * @return
     */
    private Map<String,String> getCustomerFileds(Map all){
        HashMap copyObj = new HashMap<>();
        copyObj.putAll(all);
        copyObj.remove("tempName");
        copyObj.remove("generalAbsPath");
        copyObj.remove("templateName");
        copyObj.remove("fileType");
        copyObj.remove("stuffix");
        copyObj.remove("packageName");
        return copyObj;
    }
}
