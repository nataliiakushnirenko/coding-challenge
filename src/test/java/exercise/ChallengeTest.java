package exercise;

import com.ebay.challenge.service.ApplicationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

class ChallengeTest {

    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        applicationService = new ApplicationService();
        applicationService.populateDatabase();
    }

    @Test
    void orderByUserTest() {
        List<String> userIds = List.of("5", "4", "3", "2", "1");

        SortedSet<Map<String, String>> result = applicationService.getOrderedTable("users", "user_id");

        Assertions.assertThat(result)
                .extracting(i -> i.get("user_id"))
                .isEqualTo(userIds);

    }

    @Test
    void orderByPurchaseTest() {
        List<String> purchaseIds = List.of("9", "7", "6", "5", "4", "3", "2", "1");

        SortedSet<Map<String, String>> result = applicationService.getOrderedTable("purchases", "ad_id");

        Assertions.assertThat(result)
                .extracting(i -> i.get("ad_id"))
                .isEqualTo(purchaseIds);

    }

}
