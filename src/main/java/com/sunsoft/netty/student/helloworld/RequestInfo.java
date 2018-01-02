package com.sunsoft.netty.student.helloworld;

import java.io.Serializable;
import java.util.Map;

public class RequestInfo implements Serializable {

    private String ip;
    private String data;
    private Map<String, Object> cpuPercMap;
    private Map<String, Object> memoryMap;
    private byte[] zipData;


    public RequestInfo() {
    }

    //.. other field
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map<String, Object> getCpuPercMap() {
        return cpuPercMap;
    }

    public void setCpuPercMap(Map<String, Object> cpuPercMap) {
        this.cpuPercMap = cpuPercMap;
    }

    public Map<String, Object> getMemoryMap() {
        return memoryMap;
    }

    public void setMemoryMap(Map<String, Object> memoryMap) {
        this.memoryMap = memoryMap;
    }

    public byte[] getZipData() {
        return zipData;
    }

    public void setZipData(byte[] zipData) {
        this.zipData = zipData;
    }
}