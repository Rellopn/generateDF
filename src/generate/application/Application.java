package generate.application;

import generate.customObserver.CustomObserver;
import generate.generateDF.GenerateDF;
import generate.listener.DefaultListener;
import generate.resourceLoad.ConfigField;
import generate.resourceLoad.DefaultLoadResource;
import generate.resourceLoad.ResourceLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 整个项目在此类初始化
 */
public class Application {

    //判断是不是java文件。java文件要求输入当前的包名。
    public boolean isJavaFile = true;

    //判断当前环境是不是从web启动的
    public boolean webEnvironment = true;

    private final ConfigField cf = ConfigField.getConfield();
    //从哪里加载setting。

    //LOACL_SETTING:本地setting文件,LOCAL_CLASS:java方式配置，REMOTE:网络.默认本地
    public final String LOAD_FROM = "LOCAL_SETTING";

    public DefaultListener listener = null;

    public Application() {
        InitApplication();
    }

    //初始化项目，目前只想有一个监听器。listener
    public void InitApplication() {
        //加载 listener
        getListener();
        run();
    }


    public void run() {
        listener.started();
        //加载配置
        ResourceLoad resourceLoad = new DefaultLoadResource();
        resourceLoad.loadResource(LOAD_FROM);
        listener.parper();
        try {
            resourceLoad.handleConfigCenter(cf);
            System.out.println("log：加载配置成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("log：加载配置失败");
        }
        listener.parpered();
        //开始创建文件及文件夹
        GenerateDF generateDF = new GenerateDF(cf);
        generateDF.runGenerateDf();
        listener.finshed();
    }

    /**
     * 获得所有的事件监听器
     */
    private void getListener() {
        this.listener = new DefaultListener();
        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "generate1" + File.separator + "customEventHandle");
        String[] list = file.list();
        List<CustomObserver> customObservers = new ArrayList<>();
        try {
            for (int i = 0; i < list.length; i++) {
                this.getClass().getClassLoader();
                Class<?> aClass = Class.forName("generate.customEventHandle." + list[i].split("\\.")[0]);
                Object o = aClass.newInstance();
                customObservers.add((CustomObserver) o);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.listener.observers = customObservers;
    }

    public static void main(String[] args) {
//        new Application();
        ClassLoader classLoader = Application.class.getClassLoader();
        System.out.println(classLoader.getParent());
    }
}
