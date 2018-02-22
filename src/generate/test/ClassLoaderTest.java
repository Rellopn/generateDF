package generate.test;

public class ClassLoaderTest {
    public static void main(String[] args) {
        //generate.test.Test
        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/sr/Documents/demoDir");
        try {
            Class<?> c = diskClassLoader.loadClass("generate.test.Test");

            if (c != null) {
                Object o = c.newInstance();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
