package com.summerzhou.utils;

import com.summerzhou.dao.LogMoniterDao;
import com.summerzhou.domain.*;
import com.summerzhou.email.EmailInfo;
import com.summerzhou.email.EmailUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 工具类
 */
public class MonitorUtils {
    private static Logger logger = Logger.getLogger(MonitorUtils.class);
    //定时更新的控制标志
    private static boolean flag = false;
    //构建一个map，结构为<appid,对应应用的RuleList>
    private static Map<String,List<Rule>> ruleMap;
    //储存所有的App信息
    private static List<App> appList;
    //构建一个map，结构为<appid,对应负责的UserList>
    private static Map<String,List<User>> userMap;
    //储存所有的User
    private static List<User> userList;
    //连接数据库，获得所有的信息
    static {
        loadData();
    }

    /**
     * 对日志进行切分
     * @param input
     * @return
     */
    public static String[] splitInput(String input){
        //切分日志
        String[] fields = input.split("\\$\\$\\$\\$\\$");
        return fields;
    }

    /**
     * 根据输入的参数判定是否合理，如果合理则封装Message
     * @param fields
     * @return
     */
    public static Message getMessage(String[] fields) {
        if(fields == null){
            return null;
        }
        if(fields.length != 2)
            return null;
        //isBlank(String):1.先判断String是否为null或者String.length == 0 2.在判断String中每个字符是否为空字符
        if(StringUtils.isBlank(fields[0]) || StringUtils.isBlank(fields[1])){
            return null;
        }
        //封装Message
        Message message = new Message();
        message.setAppId(fields[0]);
        message.setMessageInfo(fields[1]);
        return message;
    }

    /**
     * 判断message是否触发rule
     * @param message
     * @return
     */
    public static boolean trigger(Message message) {
        //判定ruleMap是否有数据
        if(ruleMap == null){
            //加载数据
            loadData();
        }
        //根据message的appid从数据库中取出需要满足的rule: List<Rule>
        String appId = message.getAppId();
        String msgInfo = message.getMessageInfo();
        //从ruleMap中获取对应的ruleList
        List<Rule> ruleList = ruleMap.get(appId);
        for(Rule rule : ruleList){
            //判断所属的消息中是否含有rule的关键字
            if(msgInfo.contains(rule.getKeyword())){
                //message进行进一步封装
                message.setRuleId(rule.getId()+"");
                message.setKeyWord(rule.getKeyword());
                return true;
            }
        }
        return false;
    }

    /**
     * 加载数据模型
     */
    public static synchronized void loadData() {
        if(ruleMap == null){
            loadRuleMap();
        }
        if(userMap == null){
            loadUserMap();
        }
        if(userList == null){
            loadUserList();
        }
        if(appList == null){
            loadAppList();
        }
    }


    /**
     * 获取List<App>
     */
    private static void loadAppList() {
        appList = new LogMoniterDao().getAllApps();
    }

    /**
     * 获取List<User>
     */
    private static void loadUserList() {
        userList = new LogMoniterDao().getAllUsers();
    }

    /**
     * 从数据库获得所有的User信息，进行封装
     */
    private static void loadUserMap() {
        userMap = new HashMap<>();
        LogMoniterDao dao = new LogMoniterDao();
        List<User> userList = dao.getAllUsers();
        //封装userMap
        for(User user : userList){
            Integer appId = user.getId();
            List<User> userListValue = userMap.get(appId);
            if(userListValue == null){
                userListValue = new ArrayList<>();
            }
            userListValue.add(user);
            userMap.put(appId+"",userListValue);
        }
    }

    /**
     * 从数据库中获取所有的Rule信息，进行封装
     */
    private static void loadRuleMap() {
        ruleMap = new HashMap<>();
        LogMoniterDao dao = new LogMoniterDao();
        //获取所有的Rule
        List<Rule> ruleList = dao.getAllRules();
        //循环，对于每个rule，获取其appid，判断是否有对应的value，如果没有则创建
        for(Rule rule : ruleList){
            Integer appId = rule.getAppId();
            List<Rule> ruleListValue = ruleMap.get(appId);
            if(ruleListValue == null){
                //如果为空，创建
                ruleListValue = new ArrayList<>();
            }
            ruleListValue.add(rule);
            ruleMap.put(appId.toString(),ruleListValue);
        }
    }

    /**
     * 告警模块，用来给对应人员发送短信和邮件
     * @param appid
     * @param message
     */
    public static void notify(String appid,Message message) {
        List<User> userList = userMap.get(appid);
        //给每个对应的user发送邮件和短信，方便抄送
        if(sendEmail(userList,appid,message)){
            message.setIsEmail(1);
        }
        if(sendMessage(userList,appid,message)){
            message.setIsPhone(1);
        }

    }

    /**
     * 给指定user发短信
     * @param user
     * @param appid
     * @param message
     * @return
     */
    private static boolean sendMessage(List<User> user, String appid, Message message) {
        return true;
    }

    /**
     * 给指定user发送邮件
     *
     * @param appid
     * @param message
     * @return
     */
    private static boolean sendEmail(List<User> userList, String appid, Message message) {
        //指定该message的emailList
        List<String> receiverList = new ArrayList<>();
        for(User user : userList){
            receiverList.add(user.getEmail());
        }
        //指定message的appName
        for(App app : appList){
            if(appid.trim().equals(app.getId().toString())){
                message.setAppName(app.getName());
                break;
            }
        }
        //发送邮件
        if(receiverList.size() >= 1) {
            String content = "软件【" + message.getAppName() + "】在" + DateUtils.getDate() + "触发了规则" + message.getRuleId() + ",日志内容为："
                    + message.getMessageInfo();
            //构建邮件内容对象
            EmailInfo info = new EmailInfo("日志监控平台",content,receiverList,null);
            return EmailUtils.sendEmail(info);
        }
        return false;
    }

    /**
     * 保存record到DB
     * @param record
     */
    public static void saveRecord(Record record) {
        new LogMoniterDao().save(record);
    }

    /**
     * 定时更新规则:每十分钟更新一次(即每十分钟就从数据库重新提取数据封装到成员变量中)
     * 设置一个boolean作为标记，第一个进入synchronized方法的线程执行更新操作，然后将boolean设置为false，这样避免了
     * 其他线程拿到锁后进行更新
     */
    public static void updateRule() {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = simpleDateFormat.format(date);
        //获取当前时间分钟数
        Integer minute = Integer.valueOf(currentTime.split(":")[1]);
        //判断
        if(minute % 10 == 0){
            //每十分钟进入同步
            ruleReload();
        }else {
            //未达同步条件时设置标识为true
            flag = true;
        }
    }

    /**
     * 同步方法
     */
    public static synchronized void ruleReload() {
        /**
         * thread 1 --> flag = true --> reload --> flag = false
         * thread 2 --> flag = false
         * thread 3 --> flag = false
         */
        if(flag){
            long start = System.currentTimeMillis();
            loadRuleMap();
            loadUserMap();
            loadAppList();
            loadUserList();
            flag = false;
            logger.info("文件配置完成，当前时间："+new Date()+"耗时："+(System.currentTimeMillis()-start));
        }
    }

    public static void main(String[] args) {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = simpleDateFormat.format(date);
        //获取当前时间分钟数
        String minute = currentTime.split(":")[1];
        System.out.println(minute);
    }
}
