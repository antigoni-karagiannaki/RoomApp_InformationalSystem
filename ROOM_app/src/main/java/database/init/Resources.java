/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

/**
 *
 * @author antig
 */
public class Resources {

    static String room1 = "{ "
            + "    \"id_room\": \"1\", "
            + "    \"room_name\": \"room1\", "
            + "    \"floor\": \"3\", "
            + "    \"capacity\": \"100\", "
            + "    \"address\": \"Heraklion\", "
            + "    \"type\": \"univercity\", "
            + "    \"facilities\": \"projector\\r\\nwhite board\\r\\nA/c\", "
            + "    \"id_r\": \"1\", "
            + "    \"status\": \"0\", "
            + "    \"price\": \"100\" }";
    static String room2 = "{ "
            + "    \"id_room\": \"2\", "
            + "    \"room_name\": \"room2\", "
            + "    \"floor\": \"0\", "
            + "    \"capacity\": \"500\", "
            + "    \"address\": \"Rethymno\", "
            + "    \"type\": \"wedding\", "
            + "    \"facilities\": \"A/c\", "
            + "    \"id_r\": \"1\", "
            + "    \"status\": \"1\", "
            + "    \"price\": \"500\" "
            + "}";
    static String room3 = "{ "
            + "    \"id_room\": \"3\", "
            + "    \"room_name\": \"room3\", "
            + "    \"floor\": \"0\", "
            + "    \"capacity\": \"800\", "
            + "    \"address\": \"Chania\", "
            + "    \"type\": \"wedding\", "
            + "    \"facilities\": \"A/c\\r\\nProjector\", "
            + "    \"id_r\": \"1\", "
            + "    \"status\": \"0\", "
            + "    \"price\": \"800\" "
            + "}";

    static String room4 = "{ "
            + "    \"id_room\": \"4\", "
            + "    \"room_name\": \"room4\", "
            + "    \"floor\": \"3\", "
            + "    \"capacity\": \"150\", "
            + "    \"address\": \"Chania\", "
            + "    \"type\": \"univercity\", "
            + "    \"facilities\": \"Projector\\r\\nWhite board\", "
            + "    \"id_r\": \"1\", "
            + "    \"status\": \"0\", "
            + "    \"price\": \"150\" "
            + "}";
    static String room5 = "{ "
            + "    \"id_room\": \"5\", "
            + "    \"room_name\": \"room5\", "
            + "    \"floor\": \"6\", "
            + "    \"capacity\": \"1000\", "
            + "    \"address\": \"Rethymno\", "
            + "    \"type\": \"Party\", "
            + "    \"facilities\": \"A/c\\r\\nProjector\\r\\nBar\", "
            + "    \"id_r\": \"1\", "
            + "    \"status\": \"0\", "
            + "    \"price\": \"950\" "
            + "}";
    static String reservation1 = ""
            + "{  "
            + "    \"extra_facilities\": \"\",  "
            + "    \"price\": \"100\",  "
            + "    \"id_r\": \"1\",  "
            + "    \"time\": \"11:59\",  "
            + "    \"reservation_date\": \"2022-05-11\",  "
            + "    \"application_date\": \"2023-05-10\",  "
            + "    \"payment_method\": \"card\",  "
            + "    \"id_a\": \"1\",  "
            + "    \"id_e\": \"1\"  "
            + "}";
    static String employee1 = "{   "
            + "    \"id_e\": \"1\",   "
            + "    \"firstname\": \"Andria\",   "
            + "    \"password\": \"andria12\",   "
            + "    \"home_address\": \"evans 29\",   "
            + "    \"username\": \"andria33\",   "
            + "    \"email\": \"csd4482@csd.uoc.gr\",   "
            + "    \"date_of_birth\": \"2001-07-13\",   "
            + "    \"lastname\": \"Panayi\",   "
            + "    \"phone_number\": \"6912345678\"   "
            + "}";

    static String employee2 = "{   "
            + "    \"id_e\": \"2\",   "
            + "    \"firstname\": \"Antigoni\",   "
            + "    \"password\": \"antigoni34\",   "
            + "    \"home_address\": \"irakleio 123\",   "
            + "    \"username\": \"antigoni77\",   "
            + "    \"email\": \"csd4422@csd.uoc.gr\",   "
            + "    \"date_of_birth\": \"2001-01-09\",   "
            + "    \"lastname\": \"Karagianni\",   "
            + "    \"phone_number\": \"6921345978\"   "
            + "}";
    static String employee3 = "{   "
            + "    \"id_e\": \"3\",   "
            + "    \"firstname\": \"Lampros\",   "
            + "    \"password\": \"lampos123\",   "
            + "    \"home_address\": \"leoforos ikaron 10\",   "
            + "    \"username\": \"lamp88\",   "
            + "    \"email\": \"csd4827@csd.uoc.gr\",   "
            + "    \"date_of_birth\": \"1991-01-09\",   "
            + "    \"lastname\": \"Pitsilos\",   "
            + "    \"phone_number\": \"6925659788\"   "
            + "}";

    static String admin = "{   "
            + "    \"username\": \"admin123\",   "
            + "    \"password\": \"admin123\",   "
            + "    \"id_a\": \"1\",   "
            + "    \"home_address\": \"Leoforos Kalokairinou 196\",   "
            + "    \"firstname\": \"John\",   "
            + "    \"lastname\": \"Papa\",   "
            + "    \"date_of_birth\": \"1993-01-12\",   "
            + "    \"email\": \"csd4455@csd.uoc.gr\",   "
            + "    \"phone_number\": \"6947483643\",   "
            + "    \"IBAN\": \"1234567891234567\"   "
            + "}";

}
