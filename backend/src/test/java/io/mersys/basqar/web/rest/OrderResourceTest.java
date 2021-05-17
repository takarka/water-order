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

import io.mersys.basqar.document.auth.AuthProvider;
import io.mersys.basqar.document.auth.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.mersys.basqar.SuApplication;
import io.mersys.basqar.document.Order;
import io.mersys.basqar.service.OrderService;
import io.mersys.basqar.service.dto.OrderDTO;
import io.mersys.basqar.service.dto.OrderSearchDTO;
import io.mersys.basqar.service.mapper.OrderMapper;
import io.mersys.basqar.web.rest.impl.OrderResourceImpl;
import io.mersys.basqar.web.rest.util.TestUtil;

import javax.validation.constraints.NotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SuApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
        "feign.hystrix.enabled=false"})
@ActiveProfiles("test")
@DisplayName("Order Resouce API test ...")
@DirtiesContext
public class OrderResourceTest {

    private static final String UPDATED_ORDER_NAME = "Updated name";
    private static final String BASE_API = "/api/order";
    private static final String DEFAULT_ORDER_NAME = "Order Name";

    private Order document;

    private MockMvc restOrderMockMvc;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderService service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MongoTemplate template;

    @BeforeEach
    @DisplayName("Order test init ...")
    public void initTest() {
        document = createDocument();
    }

    @BeforeEach
    @DisplayName("Order test setup ...")
    public void setup() {
        MockitoAnnotations.initMocks(this);

        User user = User.builder()
                .name("user")
                .email("email@email.com")
                .phone("+707707777")
                .emailVerified(true)
                .provider(AuthProvider.local)
                .build()
                ;
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        final OrderResource resource = new OrderResourceImpl(service);
        this.restOrderMockMvc = MockMvcBuilders.standaloneSetup(resource)

                .setCustomArgumentResolvers(pageableArgumentResolver)
                // .setControllerAdvice(exceptionTranslator)
                .setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @AfterEach
    @DisplayName("Order test down ...")
    public void tearDown() {
        template.getMongoDbFactory().getDb().drop();
    }

    private Order createDocument() {
        Order doc = new Order();
        doc.setOrderNumber(DEFAULT_ORDER_NAME);

//        doc.setUser(user);
        return doc;
    }

    private void prepareDocument() {
        User user = template.save(document.getUser());
        document.setUser(user);
        template.save(document);
    }

    @Test
    @DisplayName("Get all Orders test ...")
    public void findAll() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restOrderMockMvc.perform(get(BASE_API));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(document.getOrderNumber())));
    }

    @Test
    @DisplayName("Get all Orders of CurrentUser test ...")
    public void findAllOfCurrentUser() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restOrderMockMvc.perform(get(BASE_API));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(document.getOrderNumber())));
    }

    @Test
    @DisplayName("Search Orders test ...")
    public void search() throws Exception {
        // GIVEN
        prepareDocument();
        OrderSearchDTO searchDTO = new OrderSearchDTO();
        searchDTO.setOrderNumber(document.getOrderNumber().substring(0, 2));


        // WHEN
        ResultActions perform = restOrderMockMvc.perform(post(BASE_API + "/search")
                .contentType(TestUtil.API_VERSION1_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(searchDTO)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(document.getOrderNumber())));
    }

    @Test
    @DisplayName("Get Order by id test ...")
    public void find() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restOrderMockMvc.perform(get(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.orderNumber").value(document.getOrderNumber()));
    }

    @Test
    @DisplayName("Create Order test ...")
    public void create() throws Exception {
        // GIVEN
        final OrderDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restOrderMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.orderNumber").value(document.getOrderNumber()));
    }

    @Test
    @DisplayName("Update Order test ...")
    public void update() throws Exception {
        // GIVEN
        prepareDocument();
        document.setOrderNumber(UPDATED_ORDER_NAME);
        final OrderDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restOrderMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.orderNumber").value(UPDATED_ORDER_NAME));
    }

    @Test
    @DisplayName("Delete Order test ...")
    public void remove() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restOrderMockMvc.perform(delete(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist());
    }

}
