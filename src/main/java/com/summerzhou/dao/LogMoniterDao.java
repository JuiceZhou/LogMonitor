package com.summerzhou.dao;

import com.summerzhou.domain.App;
import com.summerzhou.domain.Record;
import com.summerzhou.domain.Rule;
import com.summerzhou.domain.User;
import com.summerzhou.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * 使用dbUtils和mysql进行数据交互
 */
public class LogMoniterDao {
    private static Logger logger = Logger.getLogger(LogMoniterDao.class);
    private QueryRunner queryRunner;
    public LogMoniterDao(){
        queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    }

    public List<Rule> getAllRules() {
        String sql = "select * from log_monitor_rule";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Rule.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Record record) {
        String sql = "insert into log_monitor_rule_record(appId,ruleId,isEmail,isPhone,noticeInfo) " +
                "values(?,?,?,?,?) ";
        try {
            queryRunner.update(sql,record.getAppId(),record.getRuleId(),record.getIsEmail(),record.getIsPhone()
                            ,record.getMessageInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from log_monitor_user";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) throws SQLException {
        LogMoniterDao dao = new LogMoniterDao();
        List<User> allUsers = dao.getAllUsers();
        for(User user : allUsers)
            System.out.println(user);
    }

    public List<App> getAllApps() {
        String sql = "select * from log_monitor_app";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(App.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
