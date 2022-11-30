package com.retro.visionarycrofting.services;

import com.retro.visionarycrofting.entities.Product;
import com.retro.visionarycrofting.entities.Stock;
import com.retro.visionarycrofting.repositories.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockService {
    @Autowired
    StockDao stockDao;
    @Autowired
    ProductService productService;

    public List<Stock> findAllByAddress(String address) {
        return stockDao.findAllByAddress(address);
    }

    public Stock findByName(String name) {
        return stockDao.findByName(name);
    }

    public Stock findByEmail(String email) {
        return stockDao.findByEmail(email);
    }

    @Transactional
    public int deleteByName(String name) {
        return stockDao.deleteByName(name);
    }

    @Transactional
    public int deleteByEmail(String email) {
        return stockDao.deleteByEmail(email);
    }

    public List<Stock> findAll() {
        return stockDao.findAll();
    }

    @Deprecated
    public Stock getOne(Long aLong) {
        return stockDao.getOne(aLong);
    }

    public Stock save(Stock s) {
        if (this.findByName(s.getName()) != null) return null;
        if (this.findByEmail(s.getEmail()) != null) return null;
        Stock stock = stockDao.save(s);
        for (Product product: s.getProducts()) {
            productService.save(product, stock.getName());
        }
        return stock;
    }
    public Stock update(Stock stock) {
        if (this.findByName(stock.getName()) == null) return null;
        if (this.findByEmail(stock.getEmail()) == null) return null;
        if (this.getOne(stock.getId()) == null) return null;
        return stockDao.save(stock);
    }
}
