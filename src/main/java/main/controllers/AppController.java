package main.controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import main.dto.PageResource;
import main.dto.PaymentDTO;
import main.entity.Payment;
import main.entity.User;
import main.service.PaymentService;
import main.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@Api(value = "paymentsgateway", description = "Payments related operations")
public class AppController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserAuthService userService;

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getIndrx() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @RequestMapping(value = "/payments")
    public ModelAndView allPayments() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paymentsList", paymentService.getAllPayments());
        modelAndView.setViewName("payments");
        return modelAndView;
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    public ModelAndView addPayment(@ModelAttribute("newPayment") PaymentDTO newPayment) {
        User user = userService.getUser(newPayment.getUserId());

        paymentService.makePayment(user, newPayment);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:payments");
        return modelAndView;
    }

    @RequestMapping(value = "/makepayment", method = RequestMethod.GET)
    public ModelAndView addPayment() {
        ModelAndView modelAndView = new ModelAndView();
        PaymentDTO paymentDTO = new PaymentDTO();
        modelAndView.addObject("newPayment", paymentDTO);
        modelAndView.setViewName("makepayment");
        return modelAndView;
    }


    @ApiOperation(value = "A list of all payments", response = PageResource.class)
    @RequestMapping(value = "/api/paymentz", method = RequestMethod.GET
            , produces = {"application/json"})
    public PageResource<Payment> paymentz(Pageable pageable
//            ,@RequestParam int page
//            ,@RequestParam int size
    ) {
        Page<Payment> pageN
                = paymentService.findAll(pageable);
        for (Payment payment : pageN) {
            Link selfLink = linkTo(methodOn(AppController.class)
                    .getPayment(payment.getPaymentId())).slash(payment.getId()).withSelfRel();
            payment.add(selfLink);
        }
        return new PageResource<Payment>(pageN, "page", "size");
    }


    /**
     * 1.1 Get payment by id
     *
     * @param id paymen ID
     * @return Payment
     */
    @ApiOperation(value = "Payment with specified id", response = Payment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved payment"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(
            value = "/api/payments/{id}"
            , method = RequestMethod.GET
            , headers = {"Accept=application/json"})
    // HttpEntity Represents an HTTP request or response entity, consisting of headers and body.
    // ResponseEntity это Extension of HttpEntity that adds a HttpStatus status code
    public ResponseEntity<Payment> getPayment(@PathVariable("id") int id) {
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            Link selfLink = linkTo(methodOn(AppController.class)
                    .getPayment(payment.getPaymentId())).slash(payment.getId()).withSelfRel();
            payment.add(selfLink);
            return new ResponseEntity<Payment>(payment, HttpStatus.OK);
        }
        return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }



    @RequestMapping(
            value = "/api/payments/{userId}/{id}"
            , method = RequestMethod.GET
            , headers = {"Accept=application/json"}
    )
    public ResponseEntity<Payment> getPaymentByIdCustomerId
    (@PathVariable("userId") int userId, @PathVariable("id") int id) {
        User customer = userService.getUser(userId);
        if (customer != null) {
            Payment payment = paymentService.getPayment(id, userId);
            if (payment != null) {
                return new ResponseEntity<Payment>(payment, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }



    @RequestMapping(
            value = "/api/paymentsall/{userId}"
            , method = RequestMethod.GET
            , headers = {"Accept=application/json"})
    public ResponseEntity<List<Payment>> getPaymentsList(@PathVariable("userId") int userId) {
        User customer = userService.getUser(userId);
        if (customer != null) {
            List<Payment> paymentList = paymentService.getCustomerPayments(customer);
            return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Payment>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 2.1 Create payment of customerID
     *
     * @param paymentDTO json like {"customerId":"1","paymentDate":"11-11-2011","channel":"fignya","paymentAmount":"21"}
     * @return payment id, -1 if wrong
     */
    @RequestMapping(value = "/api/paymentdtocreate", method = RequestMethod.POST
            , headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Integer createPaymentDto(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.create(paymentDTO);
    }

    @RequestMapping(value = "/api/paymentcreate", method = RequestMethod.POST
            , headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Integer createPayment(@RequestBody Payment payment) {
        return paymentService.create(payment);
    }

    /**
     * 3.1 Update payment by id
     *
     * @param id         payment ID
     * @param newPayment data to update. json like {"customerId":"1","paymentDate":"11-11-2011","channel":"fignya","paymentAmount":"21"}
     * @return "Payment " + id + " updated"
     */
    @RequestMapping(value = "/api/paymentupdate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") int id
            , @RequestBody Payment newPayment) {
        Payment payment = paymentService.getPayment(id);
        if (payment == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        paymentService.updatePayment(payment, newPayment);
        return new ResponseEntity<String>("Payment " + id + " updated", HttpStatus.OK);
    }

    /**
     * 4.1 Delete payment by id
     *
     * @param id id of payment
     * @return "Payment " + id + " deleted"
     */
    @RequestMapping(value = "/api/paymentdelete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {

        Payment payment = paymentService.getPayment(id);
        if (payment == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        paymentService.deletePayment(id);
        return new ResponseEntity<String>("Payment " + id + " deleted", HttpStatus.OK);
    }
}
