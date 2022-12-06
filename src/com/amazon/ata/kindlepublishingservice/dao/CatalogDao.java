package com.amazon.ata.kindlepublishingservice.dao;

import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;

import com.amazon.ata.kindlepublishingservice.publishing.KindleFormattedBook;
import com.amazon.ata.kindlepublishingservice.utils.KindlePublishingUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import javax.inject.Inject;

public class CatalogDao {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a new CatalogDao object.
     *
     * @param dynamoDbMapper The {@link DynamoDBMapper} used to interact with the catalog table.
     */
    @Inject
    public CatalogDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the latest version of the book from the catalog corresponding to the specified book id.
     * Throws a BookNotFoundException if the latest version is not active or no version is found.
     * @param bookId Id associated with the book.
     * @return The corresponding CatalogItem from the catalog table.
     */
    public CatalogItemVersion getBookFromCatalog(String bookId) {
        CatalogItemVersion book = getLatestVersionOfBook(bookId);

        if (book == null || book.isInactive()) {
            throw new BookNotFoundException(String.format("No book found for id: %s", bookId));
        }

        return book;
    }

    /**
     * Gets the latest version of the book and set the 'inactive' to true which will functionally delete
     * the book from the catalog;
     * Throws a BookNotFoundException if the latest version is not active or no version is found.
     * @param bookId Id associated with the book
     */
    public void softDelete(String bookId) {
        CatalogItemVersion book = getBookFromCatalog(bookId);
        book.setInactive(true);
        dynamoDbMapper.save(book);
    }

    // Returns null if no version exists for the provided bookId
    private CatalogItemVersion getLatestVersionOfBook(String bookId) {
        CatalogItemVersion book = new CatalogItemVersion();
        book.setBookId(bookId);

        DynamoDBQueryExpression<CatalogItemVersion> queryExpression = new DynamoDBQueryExpression()
            .withHashKeyValues(book)
            .withScanIndexForward(false)
            .withLimit(1);

        List<CatalogItemVersion> results = dynamoDbMapper.query(CatalogItemVersion.class, queryExpression);
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    public void addOrUpdateBook(CatalogItemVersion book) {
        dynamoDbMapper.save(book);
    }

    public CatalogItemVersion validateBookExists(String bookId) {
        CatalogItemVersion book = getLatestVersionOfBook(bookId);

        if (book == null) {
            throw new BookNotFoundException(String.format("No book found for id: %s", bookId));
        }
        return book;
    }

    public CatalogItemVersion createOrUpdateBook(KindleFormattedBook book) {
        String bookId = book.getBookId();
        CatalogItemVersion existingBookItem = null;
        // If book doesn't exist this will throw a BookNotFoundException immediately
        if (bookId != null) {
            existingBookItem = validateBookExists(bookId);
        }
        CatalogItemVersion newBook = new CatalogItemVersion();
        newBook.setInactive(false);
        newBook.setAuthor(book.getAuthor());
        newBook.setGenre(book.getGenre());
        newBook.setText(book.getText());
        newBook.setTitle(book.getTitle());
        if (bookId == null) {
            newBook.setVersion(1);
            newBook.setBookId(KindlePublishingUtils.generateBookId());
            addOrUpdateBook(newBook);
        } else {
            newBook.setBookId(bookId);
            newBook.setVersion(existingBookItem.getVersion() + 1);
            softDelete(existingBookItem.getBookId());
            addOrUpdateBook(newBook);
        }
        return newBook;
    }
}
