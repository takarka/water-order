package io.mersys.basqar.web.rest;

import static io.mersys.basqar.web.rest.util.TestUtil.createFormattingConversionService;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.mersys.basqar.SuApplication;
import io.mersys.basqar.document.Product;
import io.mersys.basqar.document.ProductCategory;
import io.mersys.basqar.service.ProductService;
import io.mersys.basqar.service.dto.ProductDTO;
import io.mersys.basqar.service.dto.ProductSearchDTO;
import io.mersys.basqar.service.mapper.ProductMapper;
import io.mersys.basqar.web.rest.impl.ProductResourceImpl;
import io.mersys.basqar.web.rest.util.TestUtil;

@SuppressWarnings("all")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SuApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
        "feign.hystrix.enabled=false"})
@ActiveProfiles("test")
@DisplayName("Product Resouce API test ...")
@DirtiesContext
public class ProductResourceTest {

    private static final String UPDATED_PRODUCT_NAME = "Updated name";
    private static final String BASE_API = "/api/product";
    private static final String DEFAULT_PRODUCT_NAME = "Product Name";

    private Product document;

    private MockMvc restProductMockMvc;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductService service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MongoTemplate template;

    @BeforeEach
    @DisplayName("Product test init ...")
    public void initTest() {
        document = createDocument();
    }

    @BeforeEach
    @DisplayName("Product test setup ...")
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final ProductResource resource = new ProductResourceImpl(service);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                // .setControllerAdvice(exceptionTranslator)
                .setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @AfterEach
    @DisplayName("Product test down ...")
    public void tearDown() {
        template.getMongoDbFactory().getDb().drop();
    }

    private Product createDocument() {
        ProductCategory productCategory = ProductCategoryResourceTest.createDocument();
        productCategory = template.save(productCategory);
        Product doc = new Product();
        doc.setProductCategory(productCategory);
        doc.setPrice(BigDecimal.ZERO);
        doc.setName(DEFAULT_PRODUCT_NAME);
        return doc;
    }

    private void prepareDocument() {
        ProductCategory productCategory = template.save(document.getProductCategory());
        document.setProductCategory(productCategory);
        template.save(document);
    }

    @Test
    @DisplayName("Get all Products test ...")
    public void findAll() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restProductMockMvc.perform(get(BASE_API));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Search Products test ...")
    public void search() throws Exception {
        // GIVEN
        prepareDocument();
        ProductSearchDTO searchDTO = new ProductSearchDTO();
        searchDTO.setName(document.getName().substring(0, 2));


        // WHEN
        ResultActions perform = restProductMockMvc.perform(post(BASE_API + "/search")
                .contentType(TestUtil.API_VERSION1_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(searchDTO)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Get Product by id test ...")
    public void find() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restProductMockMvc.perform(get(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Create Product test ...")
    public void create() throws Exception {
        // GIVEN
        final ProductDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restProductMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Update Product test ...")
    public void update() throws Exception {
        // GIVEN
        prepareDocument();
        document.setName(UPDATED_PRODUCT_NAME);
        final ProductDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restProductMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.name").value(UPDATED_PRODUCT_NAME));
    }

    @Test
    @DisplayName("Delete Product test ...")
    public void remove() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restProductMockMvc.perform(delete(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist());
    }

}
