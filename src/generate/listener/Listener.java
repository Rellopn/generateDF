package generate.listener;

/**
 * 监听接口
 */
public interface Listener{
    // 程序启动时
    void started();

    // 准备中
    void parper();

    // 准备完成
    void parpered();

    // 结束
    void finshed();
}
