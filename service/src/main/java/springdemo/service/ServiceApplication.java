package springdemo.service;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}


@Controller
@ResponseBody
class CustomerHttpController {

    private final CustomerRepository customerRepository;
    private final ObservationRegistry registry;

    public CustomerHttpController(CustomerRepository customerRepository, ObservationRegistry registry) {
        this.customerRepository = customerRepository;
        this.registry = registry;
    }

    @GetMapping("/customers/{name}")
    Iterable<Customer> customersByName(@PathVariable String name) {
        Assert.state(Character.isUpperCase(name.charAt(0)), "the name must start with a capital letter!");
        return Observation
                .createNotStarted("by-name", this.registry)
                .observe(() -> customerRepository.findByName(name));
//        return this.customerRepository.findByName(name);
    }

    @GetMapping("/customers")
    Iterable<Customer> customers() {
        return this.customerRepository.findAll();
    }
}

@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler
    ProblemDetail handle(IllegalStateException ise, HttpServletRequest request) {
        request.getHeaderNames().asIterator()
                .forEachRemaining(System.out::println);
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
        pd.setDetail(ise.getLocalizedMessage());
        return pd;
    }
}

interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Iterable<Customer> findByName(String name);
}

// look ma, no Lombok!
record Customer(@Id Integer id, String name) {}
