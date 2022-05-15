import com.udacity.timezones.client.ChargeUserApiHttpClient;
import com.udacity.timezones.model.TicketItem;
import com.udacity.timezones.service.ChargeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChargeServiceTest {


    //The declare the class under test
    // The actual instance of the interface(class) been instantiated so that we can mock it
    ChargeUserApiHttpClient chargeUserApiHttpClient;

    //We declare the dependency class as a mock class
    @Mock
    private ChargeService chargeService;

    //Initializing our chargeService with the Mock and @BeforeEach method
    @BeforeEach
    void init(){
        chargeService = new ChargeService(chargeUserApiHttpClient);
    }

    @Test
    void chargeService_Verifies_Amount_Charged(){

        chargeService.chargeUser(
                "Chizzy",
                List.of(
                        new TicketItem("Milk", new BigDecimal(15), new BigDecimal(2)),
                        new TicketItem("Soda", new BigDecimal(13), new BigDecimal(3))

                ),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(0.3)
        );

        /**
         * When we use the verify() method, after we call the first parameter (which is the mock client), we add a dot(.)
         * then call the method we want to verify passing the correct values
         * the eq() (i.e equals matcher) is used to verify that the userId and the amount match
         * the values we expect
         */
        verify(chargeUserApiHttpClient).charge(eq("Chizzy"), eq(new BigDecimal("33.7")));

//         boolean amountCharged = chargeService.chargeUser(expectedReturnId, expectedReturnItem, tip, discount);
//        Assertions.assertEquals(amountCharged, chargeService.chargeUser(expectedReturnId, expectedReturnItem, tip, discount));
    }
}
