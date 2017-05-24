package io.trakerr.client;

import java.lang.management.ManagementFactory;

class CpuUsageTracker {
    public static CpuUsageTracker CPU_USAGE_TRACKER = new CpuUsageTracker(1000);

    private CpuUsageTrackerThread cpuTracker;

    private CpuUsageTracker(long cpuRecentTimeMillis) {
        try {
            cpuTracker = new CpuUsageTrackerThread(cpuRecentTimeMillis);
            cpuTracker.start();
        } catch (Exception e) {
            // ignored
        }
    }

    public void shutdown() {
        cpuTracker.interrupt();
    }

    public int getCurrentCpuUsage() {
        return cpuTracker == null ? null : cpuTracker.currentCpuPercentUse;
    }

    public int getCurrentMemUsage() {
        return cpuTracker == null ? null : (int) Math.round(((double) (cpuTracker.sunmsbean.getTotalPhysicalMemorySize() - cpuTracker.sunmsbean.getFreeSwapSpaceSize()) / cpuTracker.sunmsbean.getTotalPhysicalMemorySize()) * 100);
    }

    private static class CpuUsageTrackerThread extends Thread {
        private volatile int currentCpuPercentUse;

        private com.sun.management.OperatingSystemMXBean sunmsbean;
        private long cpuRecentTimeMillis;

        public CpuUsageTrackerThread(long cpuRecentTimeMillis) throws Exception {
            this.cpuRecentTimeMillis = cpuRecentTimeMillis;
            try {
                sunmsbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                sunmsbean.getSystemCpuLoad();//May need to gauge util on first event by starting the performance watch.
            } catch (Exception e) {
                throw new Exception("Not implemented: " + e.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                while(true) {
                    Thread.sleep(cpuRecentTimeMillis);
                    currentCpuPercentUse = (int) Math.round(sunmsbean.getSystemCpuLoad() * 100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
