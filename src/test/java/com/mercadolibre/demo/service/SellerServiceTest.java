package com.mercadolibre.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mercadolibre.demo.repository.SalesAdRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.SellerDTO;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.SellerRepository;


public class SellerServiceTest {

    SellerRepository mock = Mockito.mock(SellerRepository.class);
    private SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
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
    public void testFindAllSeller() {
        List<Seller> list = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Kakashi");
        seller.setLastname("Sensei");

        list.add(seller);
        mock.findAll();
        when(mock.findAll()).thenReturn(list);
        sellerService.list();

        assertEquals("Kakashi", list.get(0).getName());
        assertEquals("Sensei", list.get(0).getLastname());

        assertNotNull(list);
    }
    @Test
    public void testFindSellerById() {

        List<Seller> list = new ArrayList<>();
        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Kakashi");
        seller.setLastname("Sensei");
        list.add(seller);

        when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
        mock.findById(1L);

        assertNotNull(mock.findById(1L));

        assertEquals("Kakashi", mock.findById(1l).get().getName());
        assertEquals("Sensei", mock.findById(1l).get().getLastname());
    }

    @Test
    public void testUpdateProductWithSuccess() throws Exception {

        List<Seller> list = new ArrayList<>();
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
    public void testUpdateProductNoSuccess(){

        List<Seller> list = new ArrayList<>();
        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Hinata");
        seller.setLastname("Hyuga");
        list.add(seller);

        SellerDTO dto = new SellerDTO();
        dto.setName("Hinata");
        dto.setLastname("Uchiha");

        when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
        when(mock.saveAndFlush(seller)).thenReturn(seller);

        seller = sellerService.convertSellerDTO(dto);
        seller.setIdseller(1L);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            sellerService.update(dto, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));

    }
    @Test
    void deleteProductWithSuccess() throws Exception {

        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Orochimaru");
        seller.setLastname("Sannin");

        Mockito.when(mock.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(seller));

        sellerService.delete(1L);

        Mockito.verify(mock).deleteById(1L);
    }

    @Test
    void deleteProductWithFail() throws Exception {

        Seller seller = new Seller();
        seller.setIdseller(1L);
        seller.setName("Orochimaru");
        seller.setLastname("Sannin");

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            sellerService.delete( 1L);
        });

        assertEquals("Id não cadastrado",exceptionThatWasThrown.getMessage());
    }
}
