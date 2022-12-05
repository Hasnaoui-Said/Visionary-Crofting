package com.retro.visionarycrofting.services.implementation;

import com.retro.visionarycrofting.entities.CallForProposal;
import com.retro.visionarycrofting.entities.Product;
import com.retro.visionarycrofting.entities.Stock;
import com.retro.visionarycrofting.enumeration.CallForProposalStatus;
import com.retro.visionarycrofting.exception.ApiRequestException;
import com.retro.visionarycrofting.repositories.StockDao;
import com.retro.visionarycrofting.services.CallForProposalService;
import com.retro.visionarycrofting.services.ProductService;
import com.retro.visionarycrofting.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockDao stockDao;
    @Autowired
    ProductService productService;
    @Autowired
    CallForProposalService callForProposalService;

    public List<Stock> findAllByAddress(String address) {
        List<Stock> allByAddress = stockDao.findAllByAddress(address);
        if (allByAddress.size() == 0)
            throw new ApiRequestException("Stocks was not found for parameters {address=" + address + "}");
        return allByAddress;
    }

    public Stock findByName(String name) {
        Stock find = stockDao.findByName(name);
        if (find == null)
            throw new ApiRequestException("Stock was not found for parameters {name=" + name + "}");
        return find;
    }

    public Stock findByEmail(String email) {
        Stock byEmail = stockDao.findByEmail(email);
        if (byEmail == null)
            throw new ApiRequestException("Stock was not found for parameters {email=" + email + "}");
        return byEmail;
    }

    @Transactional
    public int deleteByName(String name) {
        Stock byName = this.findByName(name);
        if (byName == null)
            throw new ApiRequestException("Can't delete!! Stock was not found for parameters {name=" + name + "}");
        return stockDao.deleteByName(name);
    }

    @Transactional
    public int deleteByEmail(String email) {
        Stock byName = this.findByName(email);
        if (byName == null)
            throw new ApiRequestException("Can't delete!! Stock was not found for parameters {email=" + email + "}");
        int i = stockDao.deleteByEmail(email);
        if (i != 1)
            throw new ApiRequestException("Error in deleting Stock with parameters {email=" + email + "}");
        return i;
    }

    public List<Stock> findAll() {
        List<Stock> all = stockDao.findAll();
        if (all.size() == 0)
            throw new ApiRequestException("stocks empty!!");
        return all;
    }

    @Deprecated
    public Stock getOne(Long aLong) {
        Stock one = stockDao.getOne(aLong);
        if (one == null)
            throw new ApiRequestException("Stock was not found for parameters {id=" + aLong + "}");
        return one;
    }

    public Stock save(Stock s) {
        if (stockDao.findByName(s.getName()) != null)
            throw new ApiRequestException("Stock already exist whit this parameters {name=" + s.getName() + "}");
        if (stockDao.findByEmail(s.getEmail()) != null)
            throw new ApiRequestException("Stock already exist whit this parameters {email=" + s.getEmail() + "}");
        Stock stock = stockDao.save(s);
        return stock;
    }

    public Stock update(Stock stock) {
        Stock byName = stockDao.findByName(stock.getName());
        Stock byEmail = stockDao.findByEmail(stock.getEmail());
        Stock stockUpdate = (byEmail != null) ? byEmail : byName;
        if (stockUpdate == null)
            throw new ApiRequestException("Can't update stock whit this parameters {name=" + stock.getName() + ", email=" + stock.getEmail() + "}");
        if (stockUpdate != null) {
            String address = stock.getAddress();
            if (!address.equals("")) {
                stockUpdate.setAddress(address);
            }
            String password = stock.getPassword();
            if (!password.equals("")) {
                stockUpdate.setPassword(password);
            }
            String phone = stock.getPhone();
            if (!phone.equals("")) {
                stockUpdate.setPhone(phone);
            }
        }
        Stock save = stockDao.save(stockUpdate);
        if (save == null)
            throw new ApiRequestException("Error!! Stock whit this parameters {name=" + stock.getName() + ", email=" + stock.getEmail() + "}");
        return save;
    }

    @Override
    public Stock findById(Long id) {
        Stock stock = stockDao.findById(id).get();
        if (stock == null)
            throw new ApiRequestException("Stock whit this parameters {name=" + stock.getName() + ", email=" + stock.getEmail() + "}");
        return stock;
    }
    @Override
    public CallForProposal validApellOffer(String call) {
        CallForProposal offer = callForProposalService.findByRef(call);
        if (offer == null)
            throw new ApiRequestException("Offer whit this parameters {ref=" + offer.getRef() + "}");
        Product product = productService.findByRef(offer.getRefProduct());
        if (offer.getStatus() == CallForProposalStatus.Open)
            throw new ApiRequestException("Offer whit this parameters {ref=" + offer.getRef() + "} is already opened");
        if (offer.getStatus() == CallForProposalStatus.Close)
            throw new ApiRequestException("Offer whit this parameters {ref=" + offer.getRef() + "} is already closed");
        product.setQuantity(product.getQuantity() + offer.getQuantity());
        offer.setStatus(CallForProposalStatus.Close);
        return offer;
    }
    @Override
    public CallForProposal createApellOffer(CallForProposal call) {
        return callForProposalService.addNewCallForProposal(call);
    }
    @Override
    public CallForProposal closeApellOffer(String ref) {
        //return callForProposalService.;
        return null;
    }

}
