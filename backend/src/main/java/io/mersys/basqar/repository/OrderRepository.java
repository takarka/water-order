package io.mersys.basqar.repository;

import io.mersys.basqar.document.Order;
import io.mersys.basqar.repository.base.CrudRepository;
import io.mersys.basqar.repository.base.Repository;

public interface OrderRepository extends Repository, CrudRepository<Order>, CustomizedOrderRepository {

}
