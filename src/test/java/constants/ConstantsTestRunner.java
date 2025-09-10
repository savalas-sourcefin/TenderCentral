package constants;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ConstantsTestRunner {

    private void sendToTeams(Results results, String testSuite) {
        try {
            String webhookUrl = "https://sourcefincoza.webhook.office.com/webhookb2/70e70573-6f5c-4cfd-82b3-5a5b9a949c17@b3016264-0791-4d59-85fe-0fe19036c055/IncomingWebhook/5d4d62d1a5054936990c4d533cde1b5f/c7d899b5-cf72-4077-9e1d-ae510f5b5a7e/V2RgcqsV8Laa2rNdYNNLAMjWVLAUNSPBtu17706RHr7Q41";
            String payload = buildTeamsPayload(results, testSuite);

            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create(webhookUrl))
                    .header("Content-Type", "application/json")
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            System.out.println("✅ Teams notification sent for: " + testSuite);

        } catch (Exception e) {
            System.out.println("❌ Failed to send Teams notification: " + e.getMessage());
        }
    }

    private String buildTeamsPayload(Results results, String testSuite) {
        String color = results.getFailCount() > 0 ? "FF0000" : "00FF00";
        String status = results.getFailCount() > 0 ? "❌ FAILED" : "✅ PASSED";

        // Use available methods - adjust based on your Karate version
        int totalTests = results.getFeaturesTotal();
        int failedTests = results.getFailCount();
        int passedTests = totalTests - failedTests;
        double duration = results.getElapsedTime() / 1000.0;

        return String.format("""
            {
                "@type": "MessageCard",
                "@context": "https://schema.org/extensions",
                "summary": "%s Test Results",
                "themeColor": "%s",
                "sections": [{
                    "activityTitle": "🧪 %s API Tests",
                    "activitySubtitle": "Status: %s",
                    "facts": [
                        {"name": "📊 Total Features", "value": "%d"},
                        {"name": "✅ Passed", "value": "%d"},
                        {"name": "❌ Failed", "value": "%d"},
                        {"name": "⏱️ Duration", "value": "%.2f seconds"}
                    ]
                }]
            }
            """,
                testSuite, color, testSuite, status, totalTests, passedTests, failedTests, duration
        );
    }

    private void printResults(Results results, String testSuite) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 " + testSuite + " Test Results:");
        System.out.println("=".repeat(50));
        System.out.println("Features: " + results.getFeaturesTotal());
        System.out.println("Failed: " + results.getFailCount());
        System.out.println("Duration: " + String.format("%.2f", results.getElapsedTime() / 1000.0) + "s");
        System.out.println("=".repeat(50));
    }

    @Test
    @DisplayName("🚀 All Constants APIs Test Suite")
    void runAllConstantsTests() {
        System.out.println("🚀 Running ALL Constants API Tests...");

        Results results = Runner.path(
                "classpath:constants/provinces",
                        "classpath:constants/departments",
                        "classpath:constants/categories"
                )
                .outputCucumberJson(true)
                .outputJunitXml(true)
                .parallel(3);

        printResults(results, "All Constants");
        sendToTeams(results, "All Constants");

//        assertEquals(0, results.getFailCount(),
//                results.getFailCount() + " test(s) failed");
    }

    @Test
    @DisplayName("🌍 Provinces API Tests")
    void runProvincesTests() {
        System.out.println("🌍 Running Provinces API Tests...");

        Results results = Runner.path("Provinces")
                .outputCucumberJson(true)
                .parallel(1);

        printResults(results, "Provinces");
        sendToTeams(results, "Provinces");

//        assertEquals(0, results.getFailCount(), "Provinces tests failed");
    }

    @Test
    @DisplayName("🏢 Departments API Tests")
    void runDepartmentsTests() {
        System.out.println("🏢 Running Departments API Tests...");

        Results results = Runner.path("Departments")
                .outputCucumberJson(true)
                .parallel(1);

        printResults(results, "Departments");
        sendToTeams(results, "Departments");

//        assertEquals(0, results.getFailCount(), "Departments tests failed");
    }

    @Test
    @DisplayName("📂 Categories API Tests")
    void runCategoriesTests() {
        System.out.println("📂 Running Categories API Tests...");

        Results results = Runner.path("Categories")
                .outputCucumberJson(true)
                .parallel(1);

        printResults(results, "Categories");
        sendToTeams(results, "Categories");

//        assertEquals(0, results.getFailCount(), "Categories tests failed");
    }
}