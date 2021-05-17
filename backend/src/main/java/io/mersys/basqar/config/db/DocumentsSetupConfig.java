package io.mersys.basqar.config.db;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.mersys.basqar.config.db.setup.UserSetup;


@Configuration
public class DocumentsSetupConfig {
    private final Logger log = LoggerFactory.getLogger(DocumentsSetupConfig.class);
    @Autowired
    private MongoTemplate template;

    @PostConstruct
    public void setupCollections() {
        // USERS
        final UserSetup userSetup = new UserSetup(template);
        userSetup.setup();

    }

}
