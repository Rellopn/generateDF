package generate.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 字符串写进文件中去
 */
public class FileUtil {

    /**
     * string 转 file
     * @param text
     * @param path
     */
    public static void String2File(String text, String path) {
        FileChannel channel = null;
        try {
            channel = new FileOutputStream(path).getChannel();
            ByteBuffer encode = Charset.forName("utf-8").encode(text);
            channel.write(encode);
            channel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * file 转string
     * @param path
     * @return
     */
    public static String File2String(String path) {
        FileChannel channel = null;
        StringBuilder sb = new StringBuilder();
        Charset charset = Charset.forName("utf-8");
        try {
            channel = new FileInputStream(path).getChannel();
            ByteBuffer buff = ByteBuffer.allocate(50000);

            while (channel.read(buff)!=-1){
                buff.flip();
                CharBuffer decode = charset.decode(buff);
                sb.append(decode);
                buff.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
