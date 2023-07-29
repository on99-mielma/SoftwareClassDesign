package com.tothecloud.offices.utils;

public class SnowflakeUtils {
    //开始时间
    private final static long START_TIME = 16538551000L;

    //序列号位数
    private final static int SEQUENCE_BIT = 12;

    //服务器位数
    private final static int SERVER_BIT = 5;

    //机器位数
    private final static int MACHINE_BIT = 5;

    //时间左移位数
    private final static int TIME_LEFT_BIT = SEQUENCE_BIT + SERVER_BIT + MACHINE_BIT;

    //服务器左移位数
    private final static int SEVER_LEFT_BIT = SEQUENCE_BIT + SERVER_BIT;

    //时间左移位数
    private final static int MACHINE_LEFT_BIT = SEQUENCE_BIT;

    //序列号最大值
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    //服务器id默认值
    private final static long serverId = 1;

    //机器id默认值
    private final static long machineId = 1;

    //序列号默认值
    private static long sequence = 0L;

    //上次时间戳默认值
    private static long lastTime = -1L;

    /**
     * 返回63位的分布式id(不返回标识位）
     * 时间戳（41位） + 服务器id(5位） + 机器id(5位） + 序列号（12位）
     *
     * @return 63位的分布式id
     */
    public static synchronized long generateId() {
        long currentTime = getCurrentTime();
        //当前时间小于上次时间，抛出异常
        if (currentTime < lastTime) {
            throw new RuntimeException("current time is error");
        }

        //同一个时间戳，序列号加1
        if (currentTime == lastTime) {
            sequence = sequence + 1;
            //当前时间的序列号已满，取下个时间的序列号
            if (sequence >= MAX_SEQUENCE) {
                currentTime = getNextTime();
            }
        } else {
            //当前时间大于上次时间，序列号置为0
            sequence = 0L;
        }
        //修改上次时间
        lastTime = currentTime;

        return (currentTime - START_TIME) << TIME_LEFT_BIT
                | serverId <<  SEVER_LEFT_BIT
                | machineId << MACHINE_LEFT_BIT
                | sequence;
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取下个时间戳
     *
     * @return 下个时间戳
     */
    private static long getNextTime() {
        long currentTime = getCurrentTime();
        while (currentTime == lastTime) {
            currentTime = getCurrentTime();
        }
        return currentTime;
    }
}
