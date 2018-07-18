package com.summerzhou.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.*;

/**
 * 这里没有使用flume等日志搜集工具进行数据采集，所以在初始化时首先产生日志数据
 */
public class RandomSpout extends BaseRichSpout {
    private Map<String,Values> ackMap = new HashMap<String, Values>();
    private List<String> logList = new ArrayList<String>();
    private SpoutOutputCollector collector;
    private Random random;
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        //产生日志数据  格式：appId$$$$$日志内容
        logList.add("1$$$$$error: Caused by: java.lang.NoClassDefFoundError: com/starit/gejie/dao/SysNameDao");
        logList.add("2$$$$$java.sql.SQLException: You have an error in your SQL syntax;");
        logList.add("1$$$$$error Unable to connect to any of the specified MySQL hosts.");
        logList.add("1$$$$$error:Servlet.service() for servlet action threw exception java.lang.NullPointerException");
        logList.add("1$$$$$error:Exception in thread main java.lang.ArrayIndexOutOfBoundsException: 2");
        logList.add("1$$$$$error:NoSuchMethodError: com/starit/.");
        logList.add("2$$$$$error:java.lang.NoClassDefFoundError: org/coffeesweet/test01/Test01");
        logList.add("1$$$$$error:java.lang.NoClassDefFoundError: org/coffeesweet/test01/Test01");
        logList.add("1$$$$$error:Java.lang.IllegalStateException");
        logList.add("1$$$$$error:Java.lang.IllegalMonitorStateException");
        logList.add("1$$$$$error:Java.lang.NegativeArraySizeException");
        logList.add("2$$$$$error:java.sql.SQLException: You have an error in your SQL syntax;");
        logList.add("1$$$$$error:Java.lang.TypeNotPresentException ");
        logList.add("1$$$$$error:Java.lang.UnsupprotedOperationException ");
        logList.add("1$$$$$error Java.lang.IndexOutOfBoundsException");
        logList.add("1$$$$$error Java.lang.ClassNotFoundException");
        logList.add("2$$$$$error java.lang.ExceptionInInitializerError ");
        logList.add("1$$$$$error:java.lang.IncompatibleClassChangeError ");
        logList.add("1$$$$$error:java.lang.LinkageError ");
        logList.add("1$$$$$error:java.lang.OutOfMemoryError ");
        logList.add("1$$$$$error java.lang.StackOverflowError");
        logList.add("2$$$$$error: java.lang.UnsupportedClassVersionError");
        logList.add("1$$$$$error java.lang.ClassCastException");
        logList.add("1$$$$$error: java.lang.CloneNotSupportedException");
        logList.add("1$$$$$error: java.lang.EnumConstantNotPresentException ");
        logList.add("1$$$$$error java.lang.IllegalMonitorStateException ");
        logList.add("2$$$$$error java.lang.IllegalStateException ");
        logList.add("1$$$$$error java.lang.IndexOutOfBoundsException ");
        logList.add("1$$$$$error java.lang.NumberFormatException ");
        logList.add("1$$$$$error java.lang.RuntimeException ");
        logList.add("1$$$$$error java.lang.TypeNotPresentException ");
        logList.add("2$$$$$error MetaSpout.java:9: variable i might not have been initialized");
        logList.add("1$$$$$error MyEvaluator.java:1: class Test1 is public, should be declared in a file named Test1.java ");
        logList.add("1$$$$$error Main.java:5: cannot find symbol ");
        logList.add("1$$$$$error NoClassDefFoundError: asa wrong name: ASA ");
        logList.add("1$$$$$error Test1.java:54: 'void' type not allowed here");
        logList.add("2$$$$$error Test5.java:8: missing return statement");
        logList.add("1$$$$$error:Next.java:66: cannot find symbol ");
        logList.add("1$$$$$error symbol  : method createTempFile(java.lang.String,java.lang.String,java.lang.String) ");
        logList.add("1$$$$$error invalid method declaration; return type required");
        logList.add("1$$$$$error array required, but java.lang.String found");
        logList.add("2$$$$$error Exception in thread main java.lang.NumberFormatException: null 20. .");
        logList.add("1$$$$$error non-static method cannot be referenced from a static context");
        logList.add("1$$$$$error Main.java:5: non-static method fun1() cannot be referenced from a static context");
        logList.add("1$$$$$error continue outside of  loop");
        logList.add("1$$$$$error MyAbstract.java:6: missing method body, or declare abstract");
        logList.add("2$$$$$error Main.java:6: Myabstract is abstract; cannot be instantiated");
        logList.add("1$$$$$error MyInterface.java:2: interface methods cannot have body ");
        logList.add("1$$$$$error Myabstract is abstract; cannot be instantiated");
        logList.add("1$$$$$error asa.java:3: modifier static not allowed here");
        logList.add("1$$$$$error possible loss of precision  found: long required:byte  var=varlong");
        logList.add("2$$$$$error  java.lang.NegativeArraySizeException ");
        logList.add("1$$$$$error java.lang.ArithmeticException:  by zero");
        logList.add("1$$$$$error java.lang.ArithmeticException");
        logList.add("1$$$$$error java.lang.ArrayIndexOutOfBoundsException");
        logList.add("1$$$$$error java.lang.ClassNotFoundException");
        logList.add("2$$$$$error java.lang.IllegalArgumentException");
        logList.add("1$$$$$error fatal error C1010: unexpected end of file while looking for precompiled header directive");
        logList.add("1$$$$$error fatal error C1083: Cannot open include file: R…….h: No such file or directory");
        logList.add("1$$$$$error C2011:C……clas type redefinition");
        logList.add("1$$$$$error C2018: unknown character 0xa3");
        logList.add("2$$$$$error C2057: expected constant expression");
        logList.add("1$$$$$error C2065: IDD_MYDIALOG : undeclared identifier IDD_MYDIALOG");
        logList.add("1$$$$$error C2082: redefinition of formal parameter bReset");
        logList.add("1$$$$$error C2143: syntax error: missing : before  ");
        logList.add("1$$$$$error C2146: syntax error : missing ';' before identifier dc");
        logList.add("2$$$$$error C2196: case value '69' already used");
        logList.add("1$$$$$error C2509: 'OnTimer' : member function not declared in 'CHelloView'");
        logList.add("1$$$$$error C2555: 'B::f1': overriding virtual function differs from 'A::f1' only by return type or calling convention");
        logList.add("1$$$$$error C2511: 'reset': overloaded member function 'void (int)' not found in 'B'");
        logList.add("1$$$$$error C2660: 'SetTimer' : function does not take 2 parameters");
        logList.add("2$$$$$error warning C4035: 'f……': no return value");
        logList.add("1$$$$$error warning C4553: '= =' : operator has no effect; did you intend '='");
        logList.add("1$$$$$error C4716: 'CMyApp::InitInstance' : must return a value");
        logList.add("1$$$$$error LINK : fatal error LNK1168: cannot open Debug/P1.exe for writing");
        logList.add("1$$$$$error LNK2001: unresolved external symbol public: virtual _ _thiscall C (void)");
        logList.add("2$$$$$error java.lang.IllegalArgumentException: Path index.jsp does not start with");
        logList.add("1$$$$$error org.apache.struts.action.ActionServlet.process(ActionServlet.java:148");
        logList.add("1$$$$$error org.apache.jasper.JasperException: Exception in JSP");
        logList.add("1$$$$$error The server encountered an internal error () that prevented it from fulfilling this request");
        logList.add("1$$$$$error org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467");
        logList.add("2$$$$$error javax.servlet.http.HttpServlet.service(HttpServlet.java:803)");
        logList.add("1$$$$$error javax.servlet.jsp.JspException: Cannot find message resources under key org.apache.struts.action.MESSAGE");
        logList.add("1$$$$$error Stacktrace:  org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467)");
        logList.add("1$$$$$error javax.servlet.ServletException: Cannot find bean org.apache.struts.taglib.html.BEAN in any scope");
        logList.add("1$$$$$error no data found");
        logList.add("2$$$$$error exception in thread main org.hibernate.MappingException: Unknown entity:.");
        logList.add("1$$$$$error using namespace std;");
        logList.add("1$$$$$error C2065: 'cout' : undeclared identifier");
        logList.add("1$$$$$error main already defined in aaa.obj");
        logList.add("1$$$$$error syntax error : missing ';' before '}'");
        logList.add("2$$$$$error cout : undeclared identifier");
        logList.add("1$$$$$error weblogic.servlet.internal.WebAppServletContext$ServletInvocationAction.run(WebAp ");
        logList.add("1$$$$$error Caused by: java.lang.reflect.InvocationTargetException");
        logList.add("1$$$$$error Caused by: java.lang.NoClassDefFoundError: com/starit/gejie/dao/SysNameDao");
        logList.add("1$$$$$error at com.starit.gejie.Util.Trans.BL_getSysNamesByType(Trans.java:220)");
    }

    public void nextTuple() {
        random = new Random();
        //随机从list中取出日志数据，构成Tuple
        String log = logList.get(random.nextInt(logList.size()));
        //生成messageId
        String messageId = UUID.randomUUID().toString().replace("-","");
        Values values = new Values(log);
        //将发送的数据放入map中，进行ack-fail
        ackMap.put(messageId,values);
        collector.emit(values,messageId);
        //限制发送的速度
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("logMessage"));
    }
    //ack-fail机制，失败了重发
    @Override
    public void ack(Object msgId) {
        //发送成功，则将msgId对应的数据移除
        if(ackMap.containsKey(msgId)){
            ackMap.remove(msgId);
        }
    }

    @Override
    public void fail(Object msgId) {
        //如果失败，则从map中取出对应的数据重新发送
        Values values = ackMap.get(msgId);
        collector.emit(values,msgId);
    }
}
