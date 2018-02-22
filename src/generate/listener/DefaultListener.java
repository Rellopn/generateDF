package generate.listener;

import generate.customObserver.CuseomObserverable;
import generate.customObserver.CustomObserver;

import java.util.List;

public class DefaultListener implements Listener,CuseomObserverable {

    public List<CustomObserver> observers;

    private String eventMessage;
    @Override
    public void started() {
        System.out.println("项目启动");
        this.eventMessage="started";
        notifyObserver();
    }

    @Override
    public void parper() {
        System.out.println("准备");
        this.eventMessage="parper";
        notifyObserver();
    }

    @Override
    public void parpered() {
        System.out.println("准备完成");
        this.eventMessage="parpered";
        notifyObserver();
    }

    @Override
    public void finshed() {
        System.out.println("结束");
        this.eventMessage="fished";
        notifyObserver();
    }

    @Override
    public void registerObserver(CustomObserver o) {

    }

    @Override
    public void removeObserver(CuseomObserverable o) {

    }

    @Override
    public void notifyObserver() {
        for (int i=0;i<observers.size();i++){
            observers.get(i).update(eventMessage);
        }
    }
}
