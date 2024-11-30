package json;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.List;
import com.google.gson.*;
import model.Book;

public class JSONParser {
    private JsonObject jsonObj;

    public void loadFromJSON(String jsonData) {
        JsonParser parser = new JsonParser();
        jsonObj = parser.parse(jsonData).getAsJsonObject();
    }

    public void addBook(Book book) {
        JsonArray books = jsonObj.getAsJsonObject("BookShelf").getAsJsonArray("Book");

        JsonObject newBook = new JsonObject();
        newBook.addProperty("title", book.getTitle());
        newBook.addProperty("publishedYear", book.getPublishedYear());
        newBook.addProperty("numberOfPages", book.getNumberOfPages());

        JsonArray authorsArray = new JsonArray();
        for (String author : book.getAuthors()) {
            authorsArray.add(author);
        }
        newBook.add("authors", authorsArray);

        books.add(newBook);
    }

    public void printJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(jsonObj));
    }
}
