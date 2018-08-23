package com.demo.rest.test;

import java.util.Collection;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.pojo.Customer;
import com.demo.rest.service.ServiceImpl;

@RestController
public class Demo {

	@Autowired
	private ServiceImpl service; // = new ServiceImpl();

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST/* , consumes = "application/json" */)
	public void addCustomer(@RequestBody Customer customer) {
		System.out.println(customer);
		service.addCustomer(customer);
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET/* , produces = MediaType.ALL_VALUE */)
	public Collection<Customer> viewAllCustomers() {
		for (Customer customer : service.viewAllCustomers()) {

			Link link = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteCustomer(customer.getCustomerId()))
					.withSelfRel();
			/* Resource resource = new Resource(customer, link); */
			customer.add(link);

		}

		return service.viewAllCustomers();
	}

	@RequestMapping(value = "/customer/update", method = RequestMethod.PUT/* , consumes = "application/json" */)
	public void updateCustomer(int id, @RequestBody Customer customer) {
		System.out.println(customer);
		service.updateCustomer(customer);
	}

	@RequestMapping(value = "/customer/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@RequestParam(value = "customerId") int customerId) {
		service.deleteCustomer(customerId);
		return new ResponseEntity<String>("Entity Deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/searchById", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerById(int customerId) {
		Customer cust = null;
		Resource<Customer> resource = null;

		for (Customer customer : service.viewAllCustomers()) {
			if (customer.getCustomerId() == customerId) {
				resource = new Resource<Customer>(customer);

				// Link link = new Link("http://localhost:8080/customers");
				// ControllerLinkBuilder link =
				// ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).viewAllCustomers());
				// resource.add(link.withRel("All Customers"));

				resource.add(ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteCustomer(customerId))
						.withSelfRel());
				cust = (Customer) customer;
				break;

			}
		}

		if (cust == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);

	}

}
