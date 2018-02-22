package generate.generateDF;

import java.util.Map;

public class HandlerImpl implements TokenHandler {
    String nowMapName;
    String thisPath;
    String importPath;
    Map customerFiled;

    public HandlerImpl(String nowMapName, String thisPath, String importPath, Map customerFiled) {
        this.nowMapName = nowMapName;
        this.thisPath = thisPath;
        this.importPath = importPath;
        this.customerFiled = customerFiled;
    }

    @Override
    public String handleToken(String content) {
        //如果是java 的importPath 也就是 当前包名的话
        if (content.equals("importPath")) {
            return this.importPath;
        }
        // 当前路径
        if (content.equals("nowPath")) {
            return thisPath;
        }
        //遍历自定义字段，符合就替换
        String traverseCustomerFiled = traverseCustomerFiled(content);
        if (!traverseCustomerFiled .equals("")) {
            return traverseCustomerFiled;
        }
        if (content.indexOf("L") != -1) {
            String upContentFirst = nowMapName.substring(0, 1).toLowerCase() + nowMapName.substring(1);
            return upContentFirst;
        }
        if (content.indexOf("U") != -1) {
            String upContentFirst = nowMapName.substring(0, 1).toUpperCase() + nowMapName.substring(1);
            return upContentFirst;
        }
        return nowMapName;
    }

    /**
     * 遍历 自定义字段，相符的话，取自定义的值
     * @param content
     * @return
     */
    private String traverseCustomerFiled(String content) {
        final StringBuilder res = new StringBuilder("");
        this.customerFiled.forEach((k, v) -> {
            if (k.equals(content)) {
                res.append((String) v);
            }
        });
        return res.toString();
    }
}
