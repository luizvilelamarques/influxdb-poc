"# influxdb-poc" 

SpringBoot produzindo e consumindo dados do Influxdb.

docker, influxdb, grafana.

1: Docker suba um influxdb: 'docker run --rm -d -p 8083:8083 -p 8086:8086 --expose 8090 --expose 8099 --name influxdb tutum/influxdb'
    Nao precisa ter o docker local, usei o docker play [https://labs.play-with-docker.com]
    
2: Copie a url de acesso ao banco influxdb

3: execute a aplicacao: [http://localhost:8080/influxdb/api/teste?cliente=luiz || http://localhost:8080/influxdb/api/teste?cliente=marcos]

plus: visualizar os dados com grafana. [docker run -d -p 3000:3000  --link influxdb:influxdb  --name grafana  grafana/grafana]
