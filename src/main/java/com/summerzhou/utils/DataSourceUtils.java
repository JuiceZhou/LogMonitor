package com.summerzhou.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

/**
 * 使用单例模式构建c3poDataSource
 */
public class DataSourceUtils {
    private static Logger logger = Logger.getLogger(DataSourceUtils.class);
    private static ComboPooledDataSource dataSource;

    public static DataSource getDataSource(){
        if(dataSource == null){
            dataSource = new ComboPooledDataSource();
            //使用c3p0.properties作为配置文件加载datasource;
        }
        return dataSource;
    }
}
