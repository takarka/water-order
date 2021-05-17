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

import java.time.LocalDate;

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
import io.mersys.basqar.document.Sample;
import io.mersys.basqar.document.type.SampleType;
import io.mersys.basqar.service.SampleService;
import io.mersys.basqar.service.dto.SampleDTO;
import io.mersys.basqar.service.dto.SampleSearchDTO;
import io.mersys.basqar.service.mapper.SampleMapper;
import io.mersys.basqar.web.rest.impl.SampleResourceImpl;
import io.mersys.basqar.web.rest.util.TestUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SuApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
        "feign.hystrix.enabled=false"})
@ActiveProfiles("test")
@DisplayName("Sample Resouce API test ...")
@DirtiesContext
public class SampleResourceTest {

    private static final String UPDATED_SAMPLE_NAME = "Updated name";
    private static final String BASE_API = "/api/sample";
    private static final String DEFAULT_SAMPLE_NAME = "Sample Name";

    private Sample document;

    private MockMvc restSampleMockMvc;

    @Autowired
    private SampleMapper mapper;

    @Autowired
    private SampleService service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MongoTemplate template;

    @BeforeEach
    @DisplayName("Sample test init ...")
    public void initTest() {
        document = createDocument();
    }

    @BeforeEach
    @DisplayName("Sample test setup ...")
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final SampleResource resource = new SampleResourceImpl(service);
        this.restSampleMockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                // .setControllerAdvice(exceptionTranslator)
                .setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @AfterEach
    @DisplayName("Sample test down ...")
    public void tearDown() {
        template.getMongoDbFactory().getDb().drop();
    }

    private Sample createDocument() {
        Sample doc = new Sample();
        doc.setName(DEFAULT_SAMPLE_NAME);
        doc.setBirthDate(LocalDate.now());
        doc.setType(SampleType.TYPE1);
        return doc;
    }

    private void prepareDocument() {
        template.save(document);
    }

    @Test
    @DisplayName("Get all samples test ...")
    public void findAll() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restSampleMockMvc.perform(get(BASE_API));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].type").value(hasItem("" + document.getType())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Search samples test ...")
    public void search() throws Exception {
        // GIVEN
        prepareDocument();
        SampleSearchDTO searchDTO = new SampleSearchDTO();
        searchDTO.setName(document.getName().substring(0, 2));
        searchDTO.setShortName("");
        searchDTO.setType(SampleType.TYPE1);

        // WHEN
        ResultActions perform = restSampleMockMvc.perform(post(BASE_API + "/search")
                .contentType(TestUtil.API_VERSION1_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(searchDTO)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId())))
                .andExpect(jsonPath("$.[*].type").value(hasItem("" + document.getType())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(document.getName())));
    }

    @Test
    @DisplayName("Get sample by id test ...")
    public void find() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restSampleMockMvc.perform(get(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.type").value("" + document.getType()))
                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Create sample test ...")
    public void create() throws Exception {
        // GIVEN
        final SampleDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restSampleMockMvc.perform(post(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.type").value("" + SampleType.TYPE1))

                .andExpect(jsonPath("$.name").value(document.getName()));
    }

    @Test
    @DisplayName("Update sample test ...")
    public void update() throws Exception {
        // GIVEN
        prepareDocument();
        document.setName(UPDATED_SAMPLE_NAME);
        document.setType(SampleType.TYPE2);
        final SampleDTO dto = mapper.toDto(document);

        // WHEN
        ResultActions perform = restSampleMockMvc.perform(put(BASE_API).contentType(TestUtil.API_VERSION1_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)));

        // THEN
        perform.andExpect(status().isOk()).andExpect(content().contentType(TestUtil.API_VERSION1_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(document.getId()))
                .andExpect(jsonPath("$.type").value("" + SampleType.TYPE2))
                .andExpect(jsonPath("$.name").value(UPDATED_SAMPLE_NAME));
    }

    @Test
    @DisplayName("Delete sample test ...")
    public void remove() throws Exception {
        // GIVEN
        prepareDocument();

        // WHEN
        ResultActions perform = restSampleMockMvc.perform(delete(BASE_API + "/" + document.getId()));

        // THEN
        perform.andExpect(status().isNoContent()).andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist());
    }

}
