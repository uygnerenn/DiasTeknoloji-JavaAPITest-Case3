package Apı;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

    public class BookingTests extends BaseTest {

        private static String token;
        private static int bookingId;

        @Test(priority = 1)
        public void createToken() {
            Map<String, String> authDetails = new HashMap<>();
            authDetails.put("username", "admin");
            authDetails.put("password", "password123");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(authDetails)
                            .when()
                            .post("/auth")
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

            token = response.jsonPath().getString("token");
            System.out.println(" Token: " + token);
            response.prettyPrint();
        }
        @Test(priority = 2)
        public void createBooking() {
            Map<String, Object> bookingDetails = new HashMap<>();
            bookingDetails.put("firstname", "Dias");
            bookingDetails.put("lastname", "Tek");
            bookingDetails.put("totalprice", 777);
            bookingDetails.put("depositpaid", true);

            Map<String, String> bookingDates = new HashMap<>();
            bookingDates.put("checkin", "2024-06-20");
            bookingDates.put("checkout", "2024-06-25");

            bookingDetails.put("bookingdates", bookingDates);
            bookingDetails.put("additionalneeds", "Coding");

            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .body(bookingDetails)
                            .when()
                            .post("/booking")
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();
            response.prettyPrint();

            bookingId = response.jsonPath().getInt("bookingid");
            System.out.println(" Created Booking ID: " + bookingId);
        }
        @Test(priority = 3)
        public void updateBooking() {
            Map<String, Object> updatedDetails = new HashMap<>();
            updatedDetails.put("firstname", "DİAS");
            updatedDetails.put("lastname", "TEK:");
            updatedDetails.put("totalprice", 999);
            updatedDetails.put("depositpaid", false);

            Map<String, String> bookingDates = new HashMap<>();
            bookingDates.put("checkin", "2024-07-01");
            bookingDates.put("checkout", "2024-07-05");

            updatedDetails.put("bookingdates", bookingDates);
            updatedDetails.put("additionalneeds", "CodCod");

            given()
                    .contentType(ContentType.JSON)
                    .header("Cookie", "token=" + token)
                    .body(updatedDetails)
                    .when()
                    .put("/booking/" + bookingId)
                    .then()
                    .statusCode(200);



            System.out.println(" Booking ID " + bookingId + " successfully updated.");
        }
        @Test(priority = 4)
        public void GetBooking() {
            Response getResponse =
                    given()
                            .when()
                            .get("/booking/" + bookingId)
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

            System.out.println(getResponse.asPrettyString());
        }

        @Test(priority = 5)
        public void partialUpdateBooking() {
            Map<String, Object> partialUpdateDetails = new HashMap<>();
            partialUpdateDetails.put("firstname", "James");
            partialUpdateDetails.put("additionalneeds", "Dinner");

            Response patchResponse =
                    given()
                            .contentType(ContentType.JSON)
                            .header("Cookie", "token=" + token)
                            .body(partialUpdateDetails)
                            .when()
                            .patch("/booking/" + bookingId)
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

            patchResponse.prettyPrint();

            System.out.println("🔄 Booking ID " + bookingId + " partially updated.");
        }
        @Test(priority = 6)
        public void verifyPartialUpdatedBooking() {
            Response getResponse =
                    given()
                            .when()
                            .get("/booking/" + bookingId)
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

            System.out.println(getResponse.asPrettyString());
        }
        @Test(priority = 7)
        public void deleteBooking() {
            given()
                    .header("Cookie", "token=" + token)
                    .when()
                    .delete("/booking/" + bookingId)
                    .then()
                    .statusCode(201);

            System.out.println("🗑 Booking ID " + bookingId + " successfully deleted.");
        }
        @Test(priority = 8)
        public void healthCheck() {
            Response response =
                    given()
                            .when()
                            .get("/ping")
                            .then()
                            .statusCode(201) // A
                            .extract()
                            .response();

            response.prettyPrint();

            System.out.println(" API Health Check Passed!");
        }
        @Test(priority = 9)
        public void getBookingsIds() {
            Response response =
                    given()
                            .when()
                            .get("/booking")
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();
            response.prettyPrint();
            System.out.println(" Booking List: " + response.asString());
        }


    }








