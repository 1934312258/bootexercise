package com.kevin.os;

import java.io.Serializable;

/**
 * @author kevin
 * @date 2019-10-17 22:20
 * @description todo
 **/
public class OsBean implements Serializable {
    public String ip;
    public Double cpu;
    public long usedMemorySize;
    public long usableMemorySize;
    public String pid;
    public long lastUpdateTime;

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public long getUsedMemorySize() {
        return usedMemorySize;
    }

    public void setUsedMemorySize(long usedMemorySize) {
        this.usedMemorySize = usedMemorySize;
    }

    public long getUsableMemorySize() {
        return usableMemorySize;
    }

    public void setUsableMemorySize(long usableMemorySize) {
        this.usableMemorySize = usableMemorySize;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "OsBean{" +
                "ip='" + ip + '\'' +
                ", cpu=" + cpu +
                ", usedMemorySize" + usedMemorySize +
                ", usabledMemorySize" + usableMemorySize +
                ", pid='" + pid + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                "}";
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
