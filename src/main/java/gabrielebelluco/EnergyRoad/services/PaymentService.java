package gabrielebelluco.EnergyRoad.services;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private final BraintreeGateway gateway;

    public PaymentService(BraintreeGateway gateway) {
        this.gateway = gateway;
    }

    public String generateClientToken() {
        return gateway.clientToken().generate();
    }

    public String processPayment(String paymentMethodNonce, BigDecimal amount) {
        TransactionRequest request = new TransactionRequest()
                .amount(amount)
                .paymentMethodNonce(paymentMethodNonce)
                .options()
                .submitForSettlement(true)
                .done();
        Result<Transaction> result = gateway.transaction().sale(request);
        if (result.isSuccess()) {
            return result.getTarget().getId();
        } else {
            throw new IllegalArgumentException("Pagamento fallito: " + result.getMessage());
        }
    }
}