package vn.techmaster;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book hello() {
        Book book = new Book("Dế mèn phiêu lưu ký", "Tô Hoài");
        return book;
    }
}

class Book {
    public String title;
    public String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}