package com.mercadolibre.demo.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import com.mercadolibre.demo.dto.response.DueDateDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.InboundOrderRepository;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.repository.ProductRepository;

class ProductServiceTest {
    ProductRepository mockProduct = Mockito.mock(ProductRepository.class);
    InboundOrderRepository mockInboundOrder = Mockito.mock(InboundOrderRepository.class);
    ProductService productService = new ProductService(mockProduct, mockInboundOrder);

    @Test
    void testSaveProductWithSuccess() {

        ProductDTO dto = new ProductDTO();
        dto.setName("Abacate Breda");
        dto.setDescription("Abacate com casca verde vibrante e  sabor adocicado");

        Product product = productService.convertProductToDTO(dto);
        Mockito.when(mockProduct.save(Mockito.any(Product.class))).thenReturn(productService.convertProductToDTO(dto));
        mockProduct.save(productService.save(dto));

        assertEquals("Abacate Breda", product.getName());
        assertEquals("Abacate com casca verde vibrante e  sabor adocicado", product.getDescription());

        assertNotNull(product.getName());
        assertNotNull(product.getDescription());
    }

    @Test
    void testListProductWithSuccess() {

        List<Product> list = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laranja Lima");
        product1.setDescription("Laranja azedinha rica em vitamina c");
        list.add(product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Abacate Breda");
        product2.setDescription("Abacate com casca verde vibrante e  sabor adocicado");
        list.add(product2);

        Mockito.when(mockProduct.findAll()).thenReturn(list);
        List<Product> listAll = mockProduct.findAll();
        productService.list();

        assertNotNull(list);

        assertTrue(listAll.contains(product1));
        assertTrue(listAll.contains(product2));
    }

    @Test
    void testFindById() {
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");

        list.add(product);
        mockProduct.findAll();
        Mockito.when(mockProduct.findById(Mockito.any(Long.class))).thenReturn(Optional.of(list.stream().findAny().get()));
        mockProduct.findById(1L);

        assertEquals("Laranja Lima", product.getName());
        assertEquals("Laranja azedinha rica em vitamina c", product.getDescription());

        assertNotNull(product.getName());
        assertNotNull(product.getDescription());
    }

    @Test
    void testUpdateProductWithSuccess() throws Exception {
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        list.add(product);

        ProductDTO dto = new ProductDTO();
        dto.setName("Manga Tommy");
        dto.setDescription("Manga resistente e com maior durabilidade");

        Mockito.when(mockProduct.findById(Mockito.any(Long.class))).thenReturn(Optional.of(list.stream().findAny().get()));
        Mockito.when(mockProduct.saveAndFlush(product)).thenReturn(product);

        product = productService.convertProductToDTO(dto);
        product.setId(1L);
        productService.update(dto, mockProduct.findById(1L).get().getId());

        assertEquals("Manga Tommy", product.getName());
        assertEquals("Manga resistente e com maior durabilidade", product.getDescription());

        assertNotNull(product.getName());
        assertNotNull(product.getDescription());
    }

    @Test
    void testUpdateProductNoSuccess() {
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        list.add(product);

        ProductDTO dto = new ProductDTO();
        dto.setName("Manga Tommy");
        dto.setDescription("Manga resistente e com maior durabilidade");

        Mockito.when(mockProduct.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
        Mockito.when(mockProduct.saveAndFlush(product)).thenReturn(product);

        product = productService.convertProductToDTO(dto);
        product.setId(1L);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            productService.update(dto, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
    }

    @Test
    void deleteProductWithSuccess() throws Exception {

        Product product = new Product();
        product.setId(1L);

        Mockito.when(mockProduct.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(product));

        productService.delete(1L);

        Mockito.verify(mockProduct).deleteById(1L);
    }
    @Test
    void deleteProductNoSuccess() throws Exception {

        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        list.add(product);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            productService.delete( 2L);
        });

        assertEquals("Id não cadastrado",exceptionThatWasThrown.getMessage());
    }
    @Test
    void testDueDate(){

        List<InboundOrder> inboundOrderList = new ArrayList<>();

        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");

        Seller seller = new Seller();
        seller.setIdseller(1l);

        SalesAd salesAd = new SalesAd();
        salesAd.setId(1L);
        salesAd.setProduct(product);
        salesAd.setSeller(seller);

        Section section1 = new Section();
        section1.setIdSection(1l);
        section1.setCategory("Frios");
        section1.setCapacity(2000L);

        BatchStock batchStock = new BatchStock();
        batchStock.setDueDate(LocalDate.now());
        batchStock.setIdBatchNumber(1L);
        batchStock.setIdSalesAd(salesAd);
        batchStock.setCurrentQuantity(300L);

        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setId(1L);
        inboundOrder.setBatchStock(batchStock);
        inboundOrder.setSection(section1);
        inboundOrder.setOrderDate(LocalDate.now());
        inboundOrderList.add(inboundOrder);

        Mockito.when(mockInboundOrder.findAll()).thenReturn(inboundOrderList);
        List<DueDateDTO> result = productService.dueDate(58L,1L);

        assertNotNull(result);
        assertEquals("Frios",result.get(0).getProductTypeID());
    }
    @Test
    void testDueDateCustom() throws Exception {
        List<InboundOrder> inboundOrderList = new ArrayList<>();

        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");

        Seller seller = new Seller();
        seller.setIdseller(1l);

        SalesAd salesAd = new SalesAd();
        salesAd.setId(1L);
        salesAd.setProduct(product);
        salesAd.setSeller(seller);

        Section section1 = new Section();
        section1.setIdSection(1l);
        section1.setCategory("Frios");
        section1.setCapacity(2000L);


        BatchStock batchStock = new BatchStock();
        batchStock.setDueDate(LocalDate.now());
        batchStock.setIdBatchNumber(2L);
        batchStock.setIdSalesAd(salesAd);
        batchStock.setCurrentQuantity(300L);

        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setId(1L);
        inboundOrder.setBatchStock(batchStock);
        inboundOrder.setSection(section1);
        inboundOrder.setOrderDate(LocalDate.of(2021,11,20));
        inboundOrderList.add(inboundOrder);

        inboundOrderList.add(inboundOrder);

        Mockito.when(mockInboundOrder.findAll()).thenReturn(inboundOrderList);

        List<DueDateDTO> resultAsc = productService.dueDateCustom(58L,"Frios","asc");
        List<DueDateDTO> resultDesc = productService.dueDateCustom(20L,"Frios","desc");

        assertNotNull(resultAsc);
        assertNotNull(resultDesc);
        assertEquals("Frios",resultAsc.get(0).getProductTypeID());
        assertEquals("Laranja Lima",resultDesc.get(0).getProductId());
    }

    @Test
    void testFailDueDateCustom() throws Exception {
        List<InboundOrder> inboundOrderList = new ArrayList<>();

        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");

        Seller seller = new Seller();
        seller.setIdseller(1l);

        SalesAd salesAd = new SalesAd();
        salesAd.setId(1L);
        salesAd.setProduct(product);
        salesAd.setSeller(seller);

        Section section1 = new Section();
        section1.setIdSection(1l);
        section1.setCategory("Frios");
        section1.setCapacity(2000L);

        BatchStock batchStock = new BatchStock();
        batchStock.setDueDate(LocalDate.now());
        batchStock.setIdBatchNumber(2L);
        batchStock.setIdSalesAd(salesAd);
        batchStock.setCurrentQuantity(300L);

        BatchStock batchStock2 = new BatchStock();
        batchStock2.setDueDate(LocalDate.now());
        batchStock2.setIdBatchNumber(3L);
        batchStock2.setIdSalesAd(salesAd);
        batchStock2.setCurrentQuantity(300L);

        BatchStock batchStock3 = new BatchStock();
        batchStock3.setDueDate(LocalDate.now());
        batchStock3.setIdBatchNumber(1L);
        batchStock3.setIdSalesAd(salesAd);
        batchStock3.setCurrentQuantity(300L);

        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setId(1L);
        inboundOrder.setBatchStock(batchStock);
        inboundOrder.setSection(section1);
        inboundOrder.setOrderDate(LocalDate.of(2021,11,20));
        inboundOrderList.add(inboundOrder);

        inboundOrderList.add(inboundOrder);

        Mockito.when(mockInboundOrder.findAll()).thenReturn(inboundOrderList);
        List<DueDateDTO> resultAsc = productService.dueDateCustom(58L,"Frios","asc");
        List<DueDateDTO> resultDesc = productService.dueDateCustom(20L,"Frios","desc");

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            productService.dueDateCustom(10L,"cres", "Frios");
        });

        assertEquals(exceptionThatWasThrown.getMessage(), "Escreva asc para ascendente ou desc para descendente");
    }
}
