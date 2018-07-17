package com.summerzhou.topologyMain;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.summerzhou.bolt.FilterBolt;
import com.summerzhou.bolt.RecordBolt;
import com.summerzhou.bolt.SaveRecord2DB;
import com.summerzhou.spout.RandomSpout;

public class LogMonitorTopologyMain {
    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        //设置Bolt和Spout
        builder.setSpout("logSpout",new RandomSpout(),2);
        builder.setBolt("filterBolt",new FilterBolt(),2).shuffleGrouping("logSpout");
        //根据appId进行分组
        builder.setBolt("recordBolt",new RecordBolt(),2).fieldsGrouping("filterBolt",new Fields("appid"));
        builder.setBolt("saveMessageBolt",new SaveRecord2DB(),2).shuffleGrouping("recordBolt");

        Config config = new Config();
        config.setNumWorkers(2);
        config.setDebug(true);

        //本地和集群模式
        if(args.length > 0 && args != null){
            //集群
            try {
                StormSubmitter.submitTopology(args[0],config,builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        }else{
            //本地
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("logMonitor",config,builder.createTopology());
        }

    }
}
