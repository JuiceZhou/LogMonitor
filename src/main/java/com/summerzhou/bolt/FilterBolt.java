package com.summerzhou.bolt;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.summerzhou.domain.Message;
import com.summerzhou.utils.MonitorUtils;
import org.apache.log4j.Logger;

import java.util.Map;
//继承BaseBasicBolt不需要自己手动实现ack-fail，继承BaseRichBolt需要自己实现
public class FilterBolt extends BaseBasicBolt {
    private static Logger logger = Logger.getLogger(FilterBolt.class);
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        //获取当前日志输入
        String str = input.getString(0);
        //进行切分，第一个元素是appid，第二个元素是日志具体内容
        String[] fields = MonitorUtils.splitInput(str);
        //对日志进行判断，封装
        Message message = MonitorUtils.getMessage(fields);
        //判断message是否封装成功
        if(message == null){
            //未成功，直接返回，不向下一个bolt发送数据
            return;
        }
        //判断messages是否触发了rule
        if(MonitorUtils.trigger(message)){
            //如果触发，则发射数据
            collector.emit(new Values(fields[0],message));
        }
        //定时更新规则信息
        MonitorUtils.updateRule();
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("appid","message"));
    }
}
