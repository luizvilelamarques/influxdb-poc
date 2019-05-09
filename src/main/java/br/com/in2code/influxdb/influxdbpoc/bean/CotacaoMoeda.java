package br.com.in2code.influxdb.influxdbpoc.bean;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "moeda")
public class CotacaoMoeda {
	@Column(name = "time")
	private Instant time;
	@Column(name = "valor")
	private Double valor;
	
	public Instant getTime() {
		return time;
	}
	public void setTime(Instant time) {
		this.time = time;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
