package com.summerzhou.email;


import javax.print.DocFlavor;
import java.util.List;
import java.util.Properties;

public class EmailInfo {
    private String smtpHost;//发送邮件的服务器
    private String mailServerPort = "25";
    private String username;//发送邮件的用户名
    private String password;//发送邮件的密码
    private String fromAddress;//发件人的地址
    private String toAddress;//收件人的地址
    private String ccAddress;//抄送人的地址
    private String fromUserName = "日志监控平台";//发件人在邮件中显示的名字
    private String mailTitle;//邮件主题
    private String mailContent;//邮件内容
    private Properties properties;//邮件会话属性
    private boolean isAuto = true; //是否开启身份验证

    public EmailInfo(String mailTitle, String mailContent, List<String> receiverList, List<String> ccList) {
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.toAddress = listToString(receiverList);
        this.ccAddress = ccList == null?"":listToString(ccList);
        this.smtpHost = EmailConstant.SERVER_HOST;
        this.username = EmailConstant.USER;
        this.password = EmailConstant.PWD;
        this.fromAddress = EmailConstant.FROM_ADDRESS;
    }

    /**
     * 将List转变为String1,String2....
     * @param receiverList
     * @return
     */
    private synchronized String listToString(List<String> receiverList) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < receiverList.size();i++){
            if(i == receiverList.size() - 1)
                sb.append(receiverList.get(i));
            else
                sb.append(receiverList.get(i)+",");
        }
        return sb.toString();
    }


    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host",this.smtpHost);
        properties.setProperty("mail.smtp.auth",isAuto?"true":"false");
        properties.put("mail.smtp.port", this.mailServerPort);
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    @Override
    public String toString() {
        return "EmailInfo{" +
                "smtpHost='" + smtpHost + '\'' +
                ", mailServerPort='" + mailServerPort + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", ccAddress='" + ccAddress + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", mailTitle='" + mailTitle + '\'' +
                ", mailContent='" + mailContent + '\'' +
                ", properties=" + properties +
                ", isAuto=" + isAuto +
                '}';
    }
}
