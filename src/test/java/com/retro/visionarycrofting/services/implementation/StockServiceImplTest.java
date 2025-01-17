package com.retro.visionarycrofting.services.implementation;

import com.retro.visionarycrofting.entities.Stock;
import com.retro.visionarycrofting.repositories.StockDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class StockServiceImplTest {

    @Autowired
    private StockDao stockService;
    @Test
    void findByEmail() {
        Stock st = new Stock();
        st.setEmail("test@gmail.com");
        st.setName("name");
        Stock expe = stockService.save(st);
        assertThat(expe).isEqualTo("test@gmail.com");
    }
}