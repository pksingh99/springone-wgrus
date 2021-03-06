package org.wgrus.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wgrus.Order;

@Controller
@RequestMapping(value="/orders")
public class OrderController {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public OrderController(MongoDbFactory mongoDbFactory) {
		this.mongoTemplate = new MongoTemplate(mongoDbFactory);
	}

	@RequestMapping(method=RequestMethod.GET)
	public String orders(Map<String, Object> model) {
		List<Order> orders = this.mongoTemplate.findAll(Order.class, "orders");
		model.put("orders", orders);
		return "orders";
	}

}
