package io.mersys.basqar.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
//import io.mersys.basqar.document.Order;
import io.mersys.basqar.document.Order;
import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.document.type.RoleEnum;
import org.springframework.core.Ordered;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.List;

@ChangeLog
@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE)
public class ChangeLogs {

    @ChangeSet(order = "000001", id = "000001_add_phone_and active_and_roles_fields_to_user", author = "system")
    public void addPhoneAndActiveAndRolesFieldsToUser(MongoTemplate template) {
        final List<User> users = template.find(new Query(), User.class);
        users.stream().forEach(u -> {
            u.setPhone(u.getPhone() == null ? "+77758888888" : u.getPhone());
            u.setRoles(u.getRoles() == null ? Arrays.asList(RoleEnum.ADMIN) : u.getRoles());
            u.setActive(u.getActive() == null ? true : u.getActive());
            template.save(u, "user");
        });
    }

    @ChangeSet(order = "000002", id = "000002_add_manufacture_calcel_to_orders", author = "system")
    public void addManufactureCancelToOrders(MongoTemplate template) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("manufactureCancel").exists(false));
//		final List<Order> orders = template.find(query, Order.class);
//		orders.stream().forEach(o -> {
//			o.setManufactureCancel(false);
//			template.save(o);
//		});

        template.updateMulti(new Query().addCriteria(Criteria.where("manufactureCancel").exists(false)), new Update().set("manufactureCancel", false),
                Order.class);


    }

}