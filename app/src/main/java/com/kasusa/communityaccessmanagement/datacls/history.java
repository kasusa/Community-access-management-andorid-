package com.kasusa.communityaccessmanagement.datacls;

public class history {
    public String citizen_id = "";
    public String xiaoqu_name = "";
    public String action_time = "";
    public Long xiaoqu_id = 0L;
    public boolean in  = false;
    public boolean out  = false;

    public history(String citizen_id, String xiaoqu_name, String action_time, Long xiaoqu_id, boolean in, boolean out) {
        this.citizen_id = citizen_id;
        this.xiaoqu_name = xiaoqu_name;
        this.action_time = action_time.substring(0,16);//把时间的秒去掉
        this.xiaoqu_id = xiaoqu_id;
        this.in = in;
        this.out = out;
    }

    @Override
    public String toString() {
        return "history{" +
                "citizen_id='" + citizen_id + '\'' +
                ", xiaoqu_name='" + xiaoqu_name + '\'' +
                ", action_time='" + action_time + '\'' +
                ", xiaoqu_id=" + xiaoqu_id +
                ", in=" + in +
                ", out=" + out +
                '}';
    }
}
