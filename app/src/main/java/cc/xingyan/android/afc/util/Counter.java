package cc.xingyan.android.afc.util;

/**
 * Created by San on 12/1/15.
 */
public abstract class Counter<T> {
    private int count;

    public synchronized void step(T data) {
        onStep(++count, data);
    }

    public abstract void onStep(int count, T data);
}
