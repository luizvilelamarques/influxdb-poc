package br.com.in2code.influxdb.influxdbpoc;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 1: Docker suba um influxdb: 'docker run --rm -d -p 8083:8083 -p 8086:8086 --expose 8090 --expose 8099 --name influxdb tutum/influxdb' 
 *    Nao precisa ter o docker local, usei o docker play [https://labs.play-with-docker.com]
 * 
 * 2: Copie a url de acesso ao banco influxdb
 * 
 * 3: execute a aplicacao: [http://localhost:8080/influxdb/api/teste?cliente=luiz || http://localhost:8080/influxdb/api/teste?cliente=marcos]
 * 
 * 
 * 
 * plus: visualizar os dados com grafana.
 * 
 * 		docker run -d -p 3000:3000  --link influxdb:influxdb  --name grafana  grafana/grafana
  
 * @author Luiz
 */
@SpringBootApplication
public class SpringBootDemoApplication {

	/**
	 * dados de aceso obtidos no passo 2
	 */
	public static final String URL = "http://ip172-18-0-35-bja15svdqii000d8ka40-8086.direct.labs.play-with-docker.com/";
	public static final String USERNAME = "root";
	public static final String PWD = "root";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	/**
	 * Cria e popula um banco influxdb, na pratica nao teremos isso
	 * 
	 * @return
	 * @throws Throwable 
	 */
	@Bean(name = "luiz")
	public InfluxDB createInfluxDBLuiz() throws Throwable {
		return popular("luiz");
	}

	/**
	 * Cria e popula um banco influxdb, na pratica nao teremos isso
	 * 
	 * @return
	 * @throws Throwable 
	 */
	@Bean(name = "marcos")
	public InfluxDB createInfluxDBMarcos() throws Throwable {
		return popular("marcos");
	}
	
	private InfluxDB popular(String dbName) {
		InfluxDB db = InfluxDBFactory.connect(URL, USERNAME, PWD);
		//deleta se ja existir
		db.query(new Query("DROP DATABASE " + dbName));
		db.query(new Query("CREATE DATABASE " + dbName));

		String rpName = "cotacoes";
		db.query(new Query(
				"CREATE RETENTION POLICY " + rpName + " ON " + dbName + " DURATION 30h REPLICATION 2 DEFAULT"));
		
		
		Calendar start = Calendar.getInstance();
		start.add(Calendar.MINUTE, -300);
		Calendar end = Calendar.getInstance();
		Random gerador = new Random();
		
		for (Calendar date = start; date.before(end); date.add(Calendar.MINUTE, 1)) {
			Point cotacao = Point.measurement("moeda")
	                .time(date.getTime().getTime(), TimeUnit.MILLISECONDS)
	                .addField("valor", gerador.nextInt(100))
	                .build();
			db.write(dbName, rpName, cotacao);
		}
		return db;
	}
}