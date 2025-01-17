package com.retro.visionarycrofting.ressources;

import com.retro.visionarycrofting.entities.CallForProposal;
import com.retro.visionarycrofting.entities.Stock;
import com.retro.visionarycrofting.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/stock")
public class StockWs {
    @Autowired
    StockService stockService;

    @GetMapping("/address/{address}")
    public List<Stock> findAllByAddress(@PathVariable String address) {
        return stockService.findAllByAddress(address);
    }

    @GetMapping("/name/{name}")
    public Stock findByName(@PathVariable String name) {
        return stockService.findByName(name);
    }

    @GetMapping("/email/{email}")
    public Stock findByEmail(@PathVariable String email) {
        return stockService.findByEmail(email);
    }

    @DeleteMapping("/name/{name}")
    public int deleteByName(@PathVariable String name) {
        return stockService.deleteByName(name);
    }

    @DeleteMapping("/email/{email}")
    public int deleteByEmail(@PathVariable String email) {
        return stockService.deleteByEmail(email);
    }

    @GetMapping("/")
    public List<Stock> findAll() {
        //throw new ApiRequestException("Can't found Stock !!");
        return stockService.findAll();
    }

    @GetMapping("/one/{id}")
    public Stock getOne(@PathVariable Long id) {
        return stockService.getOne(id);
    }

    @PostMapping("/")
    public Stock save(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @PutMapping("/update")
    public Stock update(@RequestBody Stock stock) {
        return stockService.update(stock);
    }

    @GetMapping("/id/{id}")
    public Stock findById(@PathVariable Long id) {
        return stockService.findById(id);
    }

    @PutMapping("/offer/valid/ref{ref}")
    public CallForProposal validApellOffer(String ref) {
        return stockService.validApellOffer(ref);
    }

    @PutMapping("/offer/add")
    public CallForProposal createApellOffer(@RequestBody CallForProposal call) {
        return stockService.createApellOffer(call);
    }

    @PutMapping("/offer/close/ref{ref}")
    public CallForProposal closeApellOffer(@PathVariable String ref) {
        return stockService.closeApellOffer(ref);
    }
}
