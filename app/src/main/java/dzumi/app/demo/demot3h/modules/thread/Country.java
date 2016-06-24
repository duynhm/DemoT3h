package dzumi.app.demo.demot3h.modules.thread;

/**
 * Created by Dzumi on 6/24/2016.
 */
public class Country {
    String name;
    int flag;

    public Country() {
    }

    public Country(int flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
