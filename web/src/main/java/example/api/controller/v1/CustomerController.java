package example.api.controller.v1;

import example.mail.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Customer controller.
 */
@RestController
@RequestMapping("/v1/customer")
@Api(value = "Customer API")
public class CustomerController {

    /**
     * The Logger.
     */
    @Autowired
    private Logger logger;

    /**
     * The Mail service.
     */
    @Autowired
    private MailService mailService;

    /**
     * Guest endpoint response entity.
     *
     * @return the response entity
     */
    @ApiOperation(value = "Test endpoint for guest", response = String.class)
    @GetMapping("/guest")
    public ResponseEntity<String> guestEndpoint() {
        logger.info("Entering in guest endpoint");

        // test mail library
        mailService.sendEmail(null, "content here");

        return ResponseEntity.ok("Hello from customer api - GUEST");
    }

    /**
     * Admin endpoint response entity.
     *
     * @return the response entity
     */
    @ApiOperation(value = "Test endpoint for admin", response = String.class)
    @GetMapping("/admin")
    public ResponseEntity<String> adminEndpoint() {
        logger.info("Entering in admin endpoint");
        return ResponseEntity.ok("Hello from customer api - ADMIN");
    }

}
