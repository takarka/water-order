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
import io.mersys.basqar.document.City;
import io.mersys.basqar.service.dto.CityDTO;
import io.mersys.basqar.service.dto.CitySearchDTO;
import io.mersys.basqar.service.impl.CityServiceImpl;
import io.mersys.basqar.service.mapper.CityMapper;
import io.mersys.basqar.web.rest.impl.CityResourceImpl;
import io.mersys.basqar.web.rest.util.TestUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SuApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
        "feign.hystrix.enabled=false"})
@ActiveProfiles("test")
@DisplayName("City Resouce API test ...")
@DirtiesContext
class CityResourceTest {

    private static final String UPDATED_CITY_NAME = "Updated name";
    private static final String BASE_API = "/api/city";
    private static final String DEFAULT_CITY_NAME = "City Name";

    private City document;

    private MockMvc restCityMockMvc;

    @Autowired
    private CityMapper mapper;

    @Autowired
    private CityServiceImpl service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MongoTemplate template;

    @BeforeEach
    @DisplayName("City test init ...")
    public void initTest() {
        document = createDocument();
    }

    @BeforeEach
    @DisplayName("City test setup ...")
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final CityResourceImpl resource = new CityResourceImpl(service);
        this.restCityMockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                // .setControllerAdvice(exceptionTranslator)
                .setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @AfterEach
    @DisplayName("City test down ...")
    public void tearDown() {
        template.getMongoDbFactory().getDb().drop();
    }

    private City createDocument() {
        City doc = new City();
        doc.setName(DEFAULT_CITY_NAME);
        return doc;
    }

    private void prepareDocument() {
        template.save(document);
    }

    @Test
    @DisplayName("Get all Cities test ...")
    public void findAll() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restCityMockMvc.perform(get(BASE_API));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Search Cities test ...")
    public void search() throws Exception {
        // GIVEN
        prepareDocument();
        CitySearchDTO searchDTO = new CitySearchDTO();
        searchDTO.setName(document.getName().substring(0, 2));


        // WHEN
        ResultActions perform = restCityMockMvc.perform(post(BASE_API + "/search")
                .contentType(TestUtil.API_VERSION1_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(searchDTO)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Get City by id test ...")
    public void find() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restCityMockMvc.perform(get(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Create City test ...")
    public void create() throws Exception {
        // GIVEN
        final CityDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restCityMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Update City test ...")
    public void update() throws Exception {
        // GIVEN
        prepareDocument();
        document.setName(UPDATED_CITY_NAME);
        final CityDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restCityMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.name").value(UPDATED_CITY_NAME));
    }

    @Test
    @DisplayName("Delete City test ...")
    public void remove() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restCityMockMvc.perform(delete(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist());
    }

}
