package cn.tws.service;

import cn.tws.entity.Product;
import cn.tws.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    public static final long PRODUCT_ID = 123L;
    private ProductService productService = new ProductServiceImpl();

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        setField(productService, "productRepository", productRepository);
    }

    @Test
    public void shouldGetProductById() {
        Product product = new Product();
        product.setDescription("description");
        product.setId(PRODUCT_ID);
        product.setName("name");
        when(productRepository.findById(PRODUCT_ID)).thenReturn(of(product));
        Product result = productService.getProduct(PRODUCT_ID);

        verify(productRepository).findById(PRODUCT_ID);

        assertThat(result.getDescription(), is("description"));
        assertThat(result.getId(), is(PRODUCT_ID));
        assertThat(result.getName(), is("name"));
    }

    @Test
    public void shouldReturnNullIfProductRepositoryReturnsNull() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(empty());

        assertNull(productService.getProduct(PRODUCT_ID));
    }
}