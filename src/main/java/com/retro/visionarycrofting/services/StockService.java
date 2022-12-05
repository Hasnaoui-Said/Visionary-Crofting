package com.retro.visionarycrofting.services;

import com.retro.visionarycrofting.entities.CallForProposal;
import com.retro.visionarycrofting.entities.Stock;
import java.util.List;

public interface StockService {

    public List<Stock> findAllByAddress(String address);

    public Stock findByName(String name);

    public Stock findByEmail(String email);

    public int deleteByName(String name);

    public int deleteByEmail(String email);

    public List<Stock> findAll();

    public Stock getOne(Long aLong);

    public Stock save(Stock s);
    public Stock update(Stock stock);

    Stock findById(Long id);

    CallForProposal validApellOffer(String call);

    CallForProposal createApellOffer(CallForProposal call);

    CallForProposal closeApellOffer(String ref);
}
