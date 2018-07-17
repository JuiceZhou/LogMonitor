package com.summerzhou.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import com.summerzhou.domain.Record;
import com.summerzhou.utils.MonitorUtils;
import org.apache.log4j.Logger;

/**
 * 保存record至DB，最后一步bolt
 */
public class SaveRecord2DB extends BaseBasicBolt {
    private static Logger logger = Logger.getLogger(SaveRecord2DB.class);
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        Record record = (Record) input.getValueByField("record");
        MonitorUtils.saveRecord(record);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
