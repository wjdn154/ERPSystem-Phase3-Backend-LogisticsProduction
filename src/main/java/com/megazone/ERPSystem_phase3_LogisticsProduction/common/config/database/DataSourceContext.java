package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.database;

public class DataSourceContext {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCurrentDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getCurrentDataSource() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
