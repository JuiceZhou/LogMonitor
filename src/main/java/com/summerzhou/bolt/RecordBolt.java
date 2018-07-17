package com.summerzhou.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.mchange.v2.beans.BeansUtils;
import com.summerzhou.domain.Message;
import com.summerzhou.domain.Record;
import com.summerzhou.utils.MonitorUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;


/**
 * 将触发的信息放入数据库中储存
 */
public class RecordBolt extends BaseBasicBolt {
    private static Logger logger = Logger.getLogger(RecordBolt.class);
    public void execute(Tuple input, BasicOutputCollector collector) {
        //从输出中获取Message和appId
        String appid = (String) input.getValueByField("appid");
        Message message = (Message) input.getValueByField("message");
        //将触发规则的消息进行通知
        //MonitorUtils.notify(appid,message);
        Record record = new Record();
        //获取record
        try {
            BeanUtils.copyProperties(record,message);
            collector.emit(new Values(record));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("record"));
    }
}
