package org.example;
import java.util.Scanner;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.FileOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class App {

    public static void main(String[] args) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("Books.xml");

            // Вызываем метод для добавления новой книги
            addNewBook(document);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Функция добавления новой книги и записи результата в файл
    private static void addNewBook(Document document) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getDocumentElement();
        String titleName;
        String Author;
        String dateName;
        String publisherName;
        String price;
        Scanner console = new Scanner(System.in);
        System.out.println("Название книги");
        titleName = console.nextLine();
        System.out.println("Автор");
        Author= console.nextLine();
        System.out.println("Дата");
        dateName= console.nextLine();
        System.out.println("Издатель");
        publisherName= console.nextLine();
        System.out.println("Цена");
        price= console.nextLine();


        // Создаем новую книгу по элементам
        // Сама книга <Book>
        Element book = document.createElement("Книга");
        // <Title>
        Element title = document.createElement("Название");
        // Устанавливаем значение текста внутри тега
        title.setTextContent(titleName);
        // <Author>
        Element author = document.createElement("Автор");
        author.setTextContent(Author);
        // <Date>
        Element date = document.createElement("Дата");
        date.setTextContent(dateName);
        // <ISBN>
      //  Element isbn = document.createElement("ISBN");
        //isbn.setTextContent("0-06-999999-9");
        // <Publisher>
        Element publisher = document.createElement("Издатель");
        publisher.setTextContent(publisherName);
        // <Cost>
        Element cost = document.createElement("Цена");
        cost.setTextContent(price);
        // Устанавливаем атрибут
        cost.setAttribute("Валюта", "RUB");

        // Добавляем внутренние элементы книги в элемент <Book>
        book.appendChild(title);
        book.appendChild(author);
        book.appendChild(date);
      //  book.appendChild(isbn);
        book.appendChild(publisher);
        book.appendChild(cost);
        // Добавляем книгу в корневой элемент
        root.appendChild(book);

        // Записываем XML в файл
        writeDocument(document);
        readAllBooks();
    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("Books.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
   private  static void readAllBooks(){
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("Books.xml");

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of books:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    }
