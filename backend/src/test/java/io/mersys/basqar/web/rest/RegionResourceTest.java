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
import io.mersys.basqar.document.Region;
import io.mersys.basqar.service.RegionService;
import io.mersys.basqar.service.dto.RegionDTO;
import io.mersys.basqar.service.dto.RegionSearchDTO;
import io.mersys.basqar.service.mapper.RegionMapper;
import io.mersys.basqar.web.rest.impl.RegionResourceImpl;
import io.mersys.basqar.web.rest.util.TestUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SuApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
        "feign.hystrix.enabled=false"})
@ActiveProfiles("test")
@DisplayName("Region Resouce API test ...")
@DirtiesContext
public class RegionResourceTest {

    private static final String UPDATED_REGION_NAME = "Updated name";
    private static final String BASE_API = "/api/region";
    private static final String DEFAULT_REGION_NAME = "Region Name";

    private Region document;

    private MockMvc restRegionMockMvc;

    @Autowired
    private RegionMapper mapper;

    @Autowired
    private RegionService service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MongoTemplate template;

    @BeforeEach
    @DisplayName("Region test init ...")
    public void initTest() {
        document = createDocument();
    }

    @BeforeEach
    @DisplayName("Region test setup ...")
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final RegionResource resource = new RegionResourceImpl(service);
        this.restRegionMockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                // .setControllerAdvice(exceptionTranslator)
                .setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @AfterEach
    @DisplayName("Region test down ...")
    public void tearDown() {
        template.getMongoDbFactory().getDb().drop();
    }

    private Region createDocument() {
        Region doc = new Region();
        doc.setName(DEFAULT_REGION_NAME);
        return doc;
    }

    private void prepareDocument() {
        template.save(document);
    }

    @Test
    @DisplayName("Get all Regions test ...")
    public void findAll() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restRegionMockMvc.perform(get(BASE_API));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Search Regions test ...")
    public void search() throws Exception {
        // GIVEN
        prepareDocument();
        RegionSearchDTO searchDTO = new RegionSearchDTO();
        searchDTO.setName(document.getName().substring(0, 2));


        // WHEN
        ResultActions perform = restRegionMockMvc.perform(post(BASE_API + "/search")
                .contentType(TestUtil.API_VERSION1_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(searchDTO)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Get Region by id test ...")
    public void find() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restRegionMockMvc.perform(get(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Create Region test ...")
    public void create() throws Exception {
        // GIVEN
        final RegionDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restRegionMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Update Region test ...")
    public void update() throws Exception {
        // GIVEN
        prepareDocument();
        document.setName(UPDATED_REGION_NAME);
        final RegionDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restRegionMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.name").value(UPDATED_REGION_NAME));
    }

    @Test
    @DisplayName("Delete Region test ...")
    public void remove() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restRegionMockMvc.perform(delete(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist());
    }

}
