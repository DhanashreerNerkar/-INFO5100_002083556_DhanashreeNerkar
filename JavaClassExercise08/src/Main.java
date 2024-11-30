import json.JSONParser;
import model.Book;
import model.BookShelf;
import xml.XMLParser;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        XMLParser xmlParser=new XMLParser();
        JSONParser jsonParser=new JSONParser();

        //Sample XML
        String sampleXML= """
                <BookShelf>
                     <Book>
                            <title>Book Title 1</title>
                            <publishedYear>2021</publishedYear>
                            <numberOfPages>300</numberOfPages>
                            <authors>
                                    <author>Author 1</author>
                                    <author>Author 2</author>
                            </authors>
                     </Book>
                     <Book>
                            <title>Book Title 2</title>
                            <publishedYear>2020</publishedYear>
                            <numberOfPages>450</numberOfPages>
                            <authors>
                                    <author>Author 3</author>
                            </authors>
                     </Book>
                </BookShelf>
                """;
        String sampleJSON = """
            {
                "BookShelf": {
                    "Book": [
                        {
                            "title": "Book Title 1",
                            "publishedYear": 2021,
                            "numberOfPages": 300,
                            "authors": ["Author 1", "Author 2"]
                        },
                        {
                            "title": "Book Title 2",
                            "publishedYear": 2020,
                            "numberOfPages": 450,
                            "authors": ["Author 3"]
                        }
                    ]
                }
            }
        """;

        // Parse and print XML
        System.out.println("Initial XML:");
        xmlParser.loadFromXML(sampleXML);
        xmlParser.printXML();

        // Add new Book to XML
        xmlParser.addBook(new Book("Book Title 3", 2023, 200, Arrays.asList("Author 4")));
        System.out.println("Updated XML:");
        xmlParser.printXML();

        // Parse and print JSON
        System.out.println("Initial JSON:");
        jsonParser.loadFromJSON(sampleJSON);
        jsonParser.printJSON();

        // Add new Book to JSON
        jsonParser.addBook(new Book("Book Title 3", 2023, 200, Arrays.asList("Author 4")));
        System.out.println("Updated JSON:");
        jsonParser.printJSON();
    }
}