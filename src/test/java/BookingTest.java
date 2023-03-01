
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.google.common.collect.Range.greaterThan;
import static io.restassured.RestAssured.given;


public class BookingTest {
    @Test
    public void verifyBookingAuth(){
        given().
                header("Content-Type", "application/json").
                body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}").
                when().
                post("https://restful-booker.herokuapp.com/auth").
                then().
                statusCode(200).
                body("token", Matchers.not(Matchers.emptyString()));
    }
    @Test
    public void verifyNewBooking(){
        Integer bookingID = given().
                header("Content-Type", "application/json").
                header("Accept","application/json").
                body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}").
                when().
                post("https://restful-booker.herokuapp.com/booking").
                then().
                statusCode(200).
                body("token", Matchers.not(Matchers.emptyString())).extract().response().path("bookingid");
        System.out.println(bookingID);
        given().
                header("Content-Type", "application/json").
                header("Accept","application/json").
                auth().basic("admin", "password123").
                body("{\n" +
                        "    \"firstname\": \"Jim\",\n" +
                        "    \"lastname\": \"Brown\",\n" +
                        "    \"totalprice\": 900,\n" +
                        "    \"depositpaid\": true,\n" +
                        "    \"bookingdates\": {\n" +
                        "        \"checkin\": \"2018-01-01\",\n" +
                        "        \"checkout\": \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\": \"Breakfast\"\n" +
                        "}").
                when().
                get(String.format("https://restful-booker.herokuapp.com/booking/%d",bookingID)).
                then().statusCode(200);
    }
}