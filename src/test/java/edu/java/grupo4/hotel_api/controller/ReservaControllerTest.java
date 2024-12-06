package edu.java.grupo4.hotel_api.controller;

import edu.ada.grupo4.hotel_api.HotelApiApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = HotelApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservaControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    public void testSalvarReserva(){
        given()
                .body(
                        """
                            {
                                "nomeCliente": "João",
                                "numeroReserva": 1,
                                "cidadeOrigemCliente": "São Paulo",
                                "ufOrigemCliente": "SP",
                                "dataInicioReserva": "2021-10-01",
                                "dataFimReserva": "2021-10-10"
                            }
                                """
                ).contentType("application/json")
                .header("Authorization", "Basic amF2YXdlYjp0ZXN0ZTEyMw==")
                .when()
                .post("/reservas")
                .then()
                .statusCode(200)
                .body("nomeCliente", equalTo("João"));

    }
}
