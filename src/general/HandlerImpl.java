package general;

import java.util.Map;

public class HandlerImpl implements TokenHandler {
    String nowMapName;
    String thisPath;
    String importPath;

    public void setImportPath(String importPath) {
        this.importPath = importPath;
    }

    public void setThisPath(String thisPath) {
        this.thisPath = thisPath;
    }

    public HandlerImpl(String nowMapName) {
        this.nowMapName = nowMapName;
        this.thisPath = "";
    }

    @Override
    public String handleToken(String content) {
        Map load = LoadSetting.loadTemplate();
        if (content.equals("importPath")){
            return this.importPath;
        }
        if (content.equals("nowPath")) {
            return thisPath;
        }
        if (load.get(content) != null) {
            return (String) load.get(content);
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
}
