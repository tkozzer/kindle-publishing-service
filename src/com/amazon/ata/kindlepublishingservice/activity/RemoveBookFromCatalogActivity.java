package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.models.requests.RemoveBookFromCatalogRequest;
import com.amazon.ata.kindlepublishingservice.models.response.RemoveBookFromCatalogResponse;
import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;

/**
 * Implementation of the RemoveBookFromCatalogActivity for the ATACurriculumKindlePublishingService's
 * RemoveBookFromCatalog API
 *
 * This API allows the client to remove a book.
 */

public class RemoveBookFromCatalogActivity {
    private CatalogDao catalogDao;

    /**
     * Instantiates a new RemoveBookFromCatalog object
     *
     * @param catalogDao CatalogDao to access the Catalog table.
     */
    @Inject
    RemoveBookFromCatalogActivity(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    /**
     * Sets the book to inactive which persists the data, but functionally deletes the book.
     *
     * @param removeBookFromCatalogRequest Request object containing the book ID associated with book to remove
     * @return RemoveBookFromCatalogResponse An empty response
     */
    public RemoveBookFromCatalogResponse execute(RemoveBookFromCatalogRequest removeBookFromCatalogRequest) {
        catalogDao.softDelete(removeBookFromCatalogRequest.getBookId());
        return new RemoveBookFromCatalogResponse();
    }
}
