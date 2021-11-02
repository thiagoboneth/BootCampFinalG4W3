package com.mercadolibre.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.ProductRepository;


public class ProductServiceTest {

    ProductRepository mock = Mockito.mock(ProductRepository.class);
    ProductService productService = new ProductService(mock);

    @Test
    void testSaveProductWithSuccess() {

        ProductDTO dto = new ProductDTO();
        dto.setName("Abacate Breda");
        dto.setDescription("Abacate com casca verde vibrante e  sabor adocicado");

        Product product = productService.convertProductToDTO(dto);
        Mockito.when(mock.save(Mockito.any(Product.class))).thenReturn(productService.convertProductToDTO(dto));
        mock.save(productService.save(dto));

        assertEquals("Abacate Breda", product.getName());
        assertEquals("Abacate com casca verde vibrante e  sabor adocicado", product.getDescription());

        assertNotNull(product.getName());
        assertNotNull(product.getDescription());
    }


    @Test
    public void testListProductWithSuccess() {

        List<Product> list = new ArrayList<Product>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");


        Product product2 = new Product();
        product.setId(2L);
        product.setName("Abacate Breda");
        product.setDescription("Abacate com casca verde vibrante e  sabor adocicado");

        list.add(product);
        list.add(product2)
        ;
        Mockito.when(mock.findAll()).thenReturn(list);
        List <Product> listAll = mock.findAll();
        productService.list();
        assertNotNull(list);
        assertTrue(listAll.contains(product));
        assertTrue(listAll.contains(product2));

    }
    @Test
    public void testFindById() {
        Long id = 1L;
        List<Product> list = new ArrayList<Product>();
        Product product = new Product();
        product.setId(id);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");

        list.add(product);
        mock.findAll();
        Mockito.when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        mock.findById(id);

        assertEquals("Laranja Lima", product.getName());
        assertEquals("Laranja azedinha rica em vitamina c", product.getDescription());

        assertNotNull(product.getName());
        assertNotNull(product.getDescription());
    }

    @Test
    public void testUpdateProductWithSuccess() throws Exception {
        Long id = 1L;
        List<Product> list = new ArrayList<Product>();
        Product product = new Product();
        product.setId(id);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        list.add(product);

        ProductDTO dto = new ProductDTO();
        dto.setName("Manga Tommy");
        dto.setDescription("Manga resistente e com maior durabilidade");

        Mockito.when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        Mockito.when(mock.saveAndFlush(product)).thenReturn(product);

        product = productService.convertProductToDTO(dto);
        product.setId(id);
        productService.update(dto,mock.findById(id).get().getId());

        assertEquals("Manga Tommy", product.getName());
        assertEquals("Manga resistente e com maior durabilidade", product.getDescription());
        assertNotNull(product.getName());
        assertNotNull(product.getDescription());

    }
    @Test
    public void testUpdateProductNoSuccess() {
        Long id = 1L;
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setId(id);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        list.add(product);

        ProductDTO dto = new ProductDTO();
        dto.setName("Manga Tommy");
        dto.setDescription("Manga resistente e com maior durabilidade");

        Mockito.when(mock.findById(id)).thenReturn(Optional.of(list.stream().findAny().get()));
        Mockito.when(mock.saveAndFlush(product)).thenReturn(product);

        product = productService.convertProductToDTO(dto);
        product.setId(id);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            productService.update(dto, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));

    }
    @Test
    void deleteProductWithSuccess() {
        List<Product> list = new ArrayList<Product>();

        Product product = new Product();
        product.setId(1L);
        product.setName("Laranja Lima");
        product.setDescription("Laranja azedinha rica em vitamina c");
        list.add(product);
        productService.delete(1L);

        Mockito.verify(mock).deleteById(1L);

    }
}
