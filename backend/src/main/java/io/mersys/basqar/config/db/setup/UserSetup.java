package io.mersys.basqar.config.db.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import io.mersys.basqar.config.db.CollectionSetup;
import io.mersys.basqar.document.auth.User;


public class UserSetup implements CollectionSetup {
    private final Logger log = LoggerFactory.getLogger(UserSetup.class);

    private final MongoTemplate template;

    public UserSetup(MongoTemplate template) {
        this.template = template;
    }

    /*
     * Use this method for init collection and first use
     */
    @Override
    public void setup() {
//		final long count = template.getCollection("user").countDocuments();
//		if (count == 0) {
//			// Find first school record from db
//			final User doc = new User();
//			// This record must be DELETED (Not for use)
//			doc.setEmail("admin@admin");
//			doc.setName("admin");
//			doc.setPassword("$2a$10$0UBqUKUYepggt1Ajs/F9c.DCCj3Bx583XRPz6Z.AWataSA8LJ5un6");
//			template.save(doc);
//			log.info("Defaul admin user created");
//
//		} else if (count > 0) {
//			updateOldAdminPassword();
//		}
//		log.info("User collection setup finished ...");
    }

    private void updateOldAdminPassword() {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("admin")).addCriteria(Criteria.where("password").is("admin"));
        Update update = new Update();
        update.set("password", "$2a$10$0UBqUKUYepggt1Ajs/F9c.DCCj3Bx583XRPz6Z.AWataSA8LJ5un6");
        update.set("accountNonLocked", true);
        template.updateMulti(query, update, User.class);
    }

}
