package generate.resourceLoad;

import java.util.Map;

public interface ResourceLoad {
    Map loadResource(String loadFrom);
    void handleConfigCenter(ConfigField cf) throws Exception;
}
