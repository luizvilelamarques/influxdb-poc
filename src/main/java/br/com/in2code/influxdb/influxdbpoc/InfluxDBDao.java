package br.com.in2code.influxdb.influxdbpoc;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Repository;

import br.com.in2code.influxdb.influxdbpoc.bean.CotacaoMoeda;

@Repository
public class InfluxDBDao {

	private InfluxDB _connect(String dbName) {
		return InfluxDBFactory.connect(SpringBootDemoApplication.URL, SpringBootDemoApplication.USERNAME,
				SpringBootDemoApplication.PWD);
	}

	public List<CotacaoMoeda> teste(String dbName) {
		InfluxDB influxDB = _connect(dbName);
		QueryResult queryResult = influxDB.query(new Query("SELECT * FROM moeda", dbName));
		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
		List<CotacaoMoeda> list = resultMapper.toPOJO(queryResult, CotacaoMoeda.class);
		return list;
	}
}