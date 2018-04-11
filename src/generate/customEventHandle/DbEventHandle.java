package generate.customEventHandle;

import generate.customObserver.CustomObserver;
import generate.resourceLoad.ConfigField;

public class DbEventHandle implements CustomObserver {
    @Override
    public void update(String eventMessage) {
        if (eventMessage.equals("parpered")){
            //如果是 parpered 说明 文件准备完成 得到属性
            ConfigField confield = ConfigField.getConfield();

        }
    }
}