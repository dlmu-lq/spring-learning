package top.itlq.spring.exampleBeans.extension;

public class RunningService {
    public String server;
    public String state;
    public Integer minutes;
    public Integer type;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        System.out.println("设置属性server:" + server);
        this.server = server;
    }

    public void init(){
        System.out.println("init 回调");
    }

    @Override
    public String toString(){
        return "server:" + server + ";minutes:" + minutes + ";type:" + type + ";state:" + state;
    }
}
