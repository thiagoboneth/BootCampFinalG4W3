package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.BuyerDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.repository.BuyerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuyerServiceTest {
    BuyerRepository mock = Mockito.mock(BuyerRepository.class);
    BuyerService buyerService = new BuyerService(mock);

    @Test
    void testSaveProductWithSuccess() {

        BuyerDTO dto = new BuyerDTO();
        dto.setName("Tsunade");
        dto.setLastname("Senju");

        Buyer buyer = buyerService.convertObjectBuyer(dto);
        when(mock.save(Mockito.any(Buyer.class))).thenReturn(buyerService.convertObjectBuyer(dto));
        mock.save(buyerService.save(dto));

        assertEquals("Tsunade", buyer.getName());
        assertEquals("Senju", buyer.getLastName());

        assertNotNull(buyer.getName());
        assertNotNull(buyer.getLastName());
    }


    @Test
    public void testListProductWithSuccess() {

        List<Buyer> list = new ArrayList<Buyer>();
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("Kabuto");
        buyer.setLastName("Yakushi");

        Buyer product2 = new Buyer();
        buyer.setIdBuyer(2L);
        buyer.setName("Madara");
        buyer.setLastName("Uchiha");

        list.add(buyer);
        list.add(product2);
        when(mock.findAll()).thenReturn(list);
        List <Buyer> listAll = mock.findAll();
        buyerService.list();
        assertNotNull(list);
        assertTrue(listAll.contains(buyer));
        assertTrue(listAll.contains(product2));
    }
    @Test
    public void testFindById() {
        Long id = 1L;
        List<Buyer> list = new ArrayList<Buyer>();
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(id);
        buyer.setName("Gaara");
        buyer.setLastName("Da Areia");

        list.add(buyer);
        mock.findAll();
        when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        mock.findById(id);

        assertEquals("Gaara", buyer.getName());
        assertEquals("Da Areia", buyer.getLastName());

        assertNotNull(buyer.getName());
        assertNotNull(buyer.getLastName());
    }

    @Test
    public void testUpdateProductWithSuccess() throws Exception {
        Long id = 1L;
        List<Buyer> list = new ArrayList<Buyer>();
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(id);
        buyer.setName("Sakura");
        buyer.setLastName("Haruno");
        list.add(buyer);

        BuyerDTO dto = new BuyerDTO();
        dto.setName("Sakura");
        dto.setLastname("Uchiha");

        when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        when(mock.saveAndFlush(buyer)).thenReturn(buyer);

        buyer = buyerService.convertObjectBuyer(dto);
        buyer.setIdBuyer(id);
        buyerService.update(dto,mock.findById(id).get().getIdBuyer());

        assertEquals("Sakura", buyer.getName());
        assertEquals("Uchiha", buyer.getLastName());
        assertNotNull(buyer.getName());
        assertNotNull(buyer.getLastName());

    }
    @Test
    public void testUpdateProductNoSuccess() throws Exception {
        Long id = 1L;
        List<Buyer> list = new ArrayList<Buyer>();
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(id);
        buyer.setName("Ino");
        buyer.setLastName("Yamanaka");
        list.add(buyer);

        BuyerDTO dto = new BuyerDTO();
        dto.setName("Ino");
        dto.setLastname("Uzumaki");

        when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        when(mock.saveAndFlush(buyer)).thenReturn(buyer);

        buyer = buyerService.convertObjectBuyer(dto);
        buyer.setIdBuyer(id);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            buyerService.update(dto, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));

    }
    @Test
    void deleteProductWithSuccess() {
        List<Buyer> list = new ArrayList<Buyer>();

        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("Zabuza");
        buyer.setLastName("Momochi");
        list.add(buyer);
        buyerService.delete(1L);

        verify(mock).deleteById(1L);

    }
}
