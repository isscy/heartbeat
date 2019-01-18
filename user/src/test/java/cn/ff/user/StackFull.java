package cn.ff.user;

public class StackFull {
    private int callCount = 0;
    private String userName = null;

    public StackFull() {
    }

    public StackFull(String userName) {
        this.userName = userName;
    }

    public void say() {
        System.out.println(this.userName + ": 调用次数" + this.callCount);
    }
}
