package cn.tws.controller;

import cn.tws.entity.Product;
import cn.tws.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    public static final long PRODUCT_ID = 123L;
    private ProductController productController = new ProductController();

    @Mock
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        setField(productController, "productService", productService);
    }

    @Test
    public void shouldDelegateToProductServiceToGetProduct() {
        when(productService.getProduct(PRODUCT_ID)).thenReturn(new Product());

        productController.getProductById("123");

        verify(productService).getProduct(PRODUCT_ID);
    }
}