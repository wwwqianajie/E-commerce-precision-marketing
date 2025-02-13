package com.example.demo.hbase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.ResultScanner;
public class tb_province_order {
    private Configuration configuration;
    private Connection connection;
    public tb_province_order() throws IOException{
        this.configuration = HBaseConfiguration.create();
        this.configuration.set("hbase.zookeeper.quorum", "192.168.10.105");
        this.configuration.set("hbase.zookeeper.property.clientPort", "2181");
        this.connection = ConnectionFactory.createConnection(configuration);
    }
    public List<String> getDataFromTable(String tableName) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        List<String> results = new ArrayList<>();

        for (Result result : scanner) {
            String value = Bytes.toString(result.getValue(Bytes.toBytes("pv_id"), Bytes.toBytes("province_name")));
            String value1 = Bytes.toString(result.getValue(Bytes.toBytes("pv_id"), Bytes.toBytes("count")));
            results.add(value);
            results.add(value1);
        }
        for (String result : results) {
            System.out.println(result);
        }

        return results;
    }
}
