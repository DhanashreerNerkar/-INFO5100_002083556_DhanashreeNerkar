package xml;

import model.Book;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.List;

public class XMLParser {
    private Document doc;

    public void loadFromXML(String xmlData) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(new ByteArrayInputStream(xmlData.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        Element bookElem = doc.createElement("Book");

        Element titleElem = doc.createElement("title");
        titleElem.setTextContent(book.getTitle());
        bookElem.appendChild(titleElem);

        Element yearElem = doc.createElement("publishedYear");
        yearElem.setTextContent(String.valueOf(book.getPublishedYear()));
        bookElem.appendChild(yearElem);

        Element pagesElem = doc.createElement("numberOfPages");
        pagesElem.setTextContent(String.valueOf(book.getNumberOfPages()));
        bookElem.appendChild(pagesElem);

        Element authorsElem = doc.createElement("authors");
        for (String author : book.getAuthors()) {
            Element authorElem = doc.createElement("author");
            authorElem.setTextContent(author);
            authorsElem.appendChild(authorElem);
        }
        bookElem.appendChild(authorsElem);

        doc.getDocumentElement().appendChild(bookElem);
    }

    public void printXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
