package Generate;

/**
 * 解析#{}中的字符串并替换
 * @author sr
 * 2017/10/3
 *
 */
public class Parse{

    /**
     * 开始符
     * 关闭符
     * handler 处理器
     */
    String openToken;
    String closeToken;
    TokenHandler handler;

    public Parse(TokenHandler handler) {
        this.openToken = "#{";
        this.closeToken = "}";
        this.handler=handler;
    }

    /**
     * 解析字符串，并替换其中的变量
     * 参考org.apache.ibatis.parsing.GenericTokenParser
     * @param text
     * @return
     */
    public String parse(String text) {
        StringBuilder builder = new StringBuilder();
        if (text != null && text.length() > 0) {
            char[] src = text.toCharArray();
            int offset = 0;
            int start = text.indexOf(openToken, offset);
            //这里是循环解析参数.
            while (start > -1) {
                int end = text.indexOf(closeToken, start);
                if (end == -1) {
                    builder.append(src, offset, src.length - offset);
                    offset = src.length;
                } else {
                    builder.append(src, offset, start - offset);
                    offset = start + openToken.length();
                    String content = new String(src, offset, end - offset);
                    //得到一对大括号里的字符串后，调用handler.handleToken,替换变量
                    builder.append(handler.handleToken(content));
                    offset = end + closeToken.length();
                }

                start = text.indexOf(openToken, offset);
            }
            if (offset < src.length) {
                builder.append(src, offset, src.length - offset);
            }
        }
        return builder.toString();
    }

}
