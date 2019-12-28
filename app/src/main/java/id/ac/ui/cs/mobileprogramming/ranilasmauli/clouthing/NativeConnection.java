package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

public class NativeConnection {

    static {
        System.loadLibrary("discount-operation");
    }
    public native int calculate(int price, int discount);

}
