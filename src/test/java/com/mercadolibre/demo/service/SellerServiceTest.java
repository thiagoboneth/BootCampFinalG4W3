package com.mercadolibre.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.SellerDTO;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.SellerRepository;


public class SellerServiceTest {

    SellerRepository mock = Mockito.mock(SellerRepository.class);
    SellerService sellerService = new SellerService(mock);

    @Test
    void testSaveProductWithSuccess() {

        SellerDTO dto = new SellerDTO();
        dto.setName("Itachi");
        dto.setLastname("Uchiha");

        Seller Seller = sellerService.convertSellerDTO(dto);
        when(mock.save(Mockito.any(Seller.class))).thenReturn(sellerService.convertSellerDTO(dto));
        mock.save(sellerService.save(dto));

        assertEquals("Itachi", Seller.getName());
        assertEquals("Uchiha", Seller.getLastname());

        assertNotNull(Seller.getName());
        assertNotNull(Seller.getLastname());
    }


    @Test
    public void testListProductWithSuccess() {

        List<Seller> list = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Rock");
        seller.setLastname("Lee");


        Seller seller2 = new Seller();
        seller2.setIdseller(2L);
        seller2.setName("Sasuke");
        seller2.setLastname("Uchiha");

        list.add(seller);
        list.add(seller2);
        when(mock.findAll()).thenReturn(list);
        List <Seller> listAll = mock.findAll();

        assertNotNull(list);
        assertTrue(listAll.contains(seller));
        assertTrue(listAll.contains(seller2));

    }
    @Test
    public void testFindById() {
        Long id = 1L;
        List<Seller> list = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setIdseller(id);
        seller.setName("Kakashi");
        seller.setLastname("Sensei");

        list.add(seller);
        mock.findAll();
        when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        mock.findById(id);

        assertEquals("Kakashi", seller.getName());
        assertEquals("Sensei", seller.getLastname());

        assertNotNull(seller.getName());
        assertNotNull(seller.getLastname());
    }

    @Test
    public void testUpdateProductWithSuccess() throws Exception {
        List<Seller> list = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Naruto");
        seller.setLastname("Uzumaki");
        list.add(seller);

        SellerDTO dto = new SellerDTO();
        dto.setName("Naruto");
        dto.setLastname("Hokage");

        when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
        when(mock.saveAndFlush(seller)).thenReturn(seller);

        seller = sellerService.convertSellerDTO(dto);
        seller.setIdseller(1L);
        sellerService.update(dto,mock.findById(1L).get().getIdseller());

        assertEquals("Naruto", seller.getName());
        assertEquals("Hokage", seller.getLastname());
        assertNotNull(seller.getName());
        assertNotNull(seller.getLastname());

    }
    @Test
    public void testUpdateProductNoSuccess() throws Exception {
        Long id = 1L;
        List<Seller> list = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setIdseller(id);
        seller.setName("Hinata");
        seller.setLastname("Hyuga");
        list.add(seller);

        SellerDTO dto = new SellerDTO();
        dto.setName("Hinata");
        dto.setLastname("Uchiha");

        when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        when(mock.saveAndFlush(seller)).thenReturn(seller);

        seller = sellerService.convertSellerDTO(dto);
        seller.setIdseller(id);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            sellerService.update(dto, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));

    }
    @Test
    void deleteProductWithSuccess() {
        List<Seller> list = new ArrayList<Seller>();

        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Orochimaru");
        seller.setLastname("Sannin");
        list.add(seller);
        sellerService.delete(1L);

        verify(mock).deleteById(1L);

    }
}
