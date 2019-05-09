package br.com.in2code.influxdb.influxdbpoc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.in2code.influxdb.influxdbpoc.bean.CotacaoMoeda;

@RestController
@RequestMapping(path = "/influxdb")
public class InfluxController {

	@Autowired
    private InfluxDBDao influxDBDao;
	
    @GetMapping(path="/api/teste", produces = "application/json")
    @ResponseBody
    public List<CotacaoMoeda> cotacoes(@RequestParam String cliente)
    {
        return influxDBDao.teste(cliente);
    }
}