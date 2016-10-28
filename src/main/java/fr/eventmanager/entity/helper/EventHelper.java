package fr.eventmanager.entity.helper;

import fr.eventmanager.core.security.SecurityService;
import fr.eventmanager.entity.Address;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Clément Garbay
 */
public class EventHelper {

    public static Event build(HttpServletRequest request) {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        String maxTicketsStr = request.getParameter("maxTickets");
        Integer maxTickets = maxTicketsStr.isEmpty() ? null : Integer.parseInt(maxTicketsStr);

        String priceStr = request.getParameter("price");
        Double price = priceStr.isEmpty() ? null : Double.parseDouble(priceStr);

        Date date = null;
        try {
            String dateStr = request.getParameter("date_day") + "-" + request.getParameter("date_hour");
            date = new SimpleDateFormat("yyyy-MM-dd-HH:mm").parse(dateStr);
        } catch (ParseException ignored) {}

        String addressName = request.getParameter("address_name");
        String addressCity = request.getParameter("address_city");
        String addressZipCodeStr = request.getParameter("address_zipCode");
        Integer addressZipCode = addressZipCodeStr.isEmpty() ? null : Integer.parseInt(addressZipCodeStr);
        String addressCountry = "France"; // TODO
        Address address = new Address(addressName, addressCity, addressZipCode, addressCountry);

        User owner = SecurityService.getLoggedUser(request);

        return new Event(title, description, date, address, maxTickets, price, owner);
    }
}
