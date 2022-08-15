package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.publishing.BookPublishingRequestManager;
import com.amazon.ata.recommendationsservice.types.BookGenre;
import com.amazon.ata.kindlepublishingservice.models.requests.SubmitBookForPublishingRequest;
import com.amazon.ata.kindlepublishingservice.models.response.SubmitBookForPublishingResponse;
import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;
import com.amazon.ata.kindlepublishingservice.publishing.BookPublishRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubmitBookForPublishingActivityTest {

    @Mock
    private PublishingStatusDao publishingStatusDao;

    @Mock
    private CatalogDao catalogDao;

    @Mock
    private BookPublishingRequestManager manager;

    @InjectMocks
    private SubmitBookForPublishingActivity activity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute_bookIdInRequest_bookQueuedForPublishing() {
        // GIVEN
        SubmitBookForPublishingRequest request = SubmitBookForPublishingRequest.builder()
                .withAuthor("Author")
                .withTitle("Title")
                .withBookId("book.123")
                .withGenre(BookGenre.FANTASY.name())
                .build();

        PublishingStatusItem item = new PublishingStatusItem();
        item.setPublishingRecordId("publishing.123");
        // KindlePublishingUtils generates a random publishing status ID for us
        when(publishingStatusDao.setPublishingStatus(anyString(),
                eq(PublishingRecordStatus.QUEUED),
                eq(request.getBookId()))).thenReturn(item);

        // WHEN
        SubmitBookForPublishingResponse response = activity.execute(request);

        // THEN
        verify(catalogDao).validateBookExists(request.getBookId());
        assertEquals("publishing.123", response.getPublishingRecordId(), "Expected response to return a publishing" +
                "record id.");
    }

    @Test
    public void execute_noBookIdInRequest_bookQueuedForPublishing() {
        // GIVEN
        SubmitBookForPublishingRequest request = SubmitBookForPublishingRequest.builder()
                .withAuthor("Author")
                .withTitle("Title")
                .withGenre(BookGenre.FANTASY.name())
                .build();

        PublishingStatusItem item = new PublishingStatusItem();
        item.setPublishingRecordId("publishing.123");
        when(publishingStatusDao.setPublishingStatus(anyString(),
                eq(PublishingRecordStatus.QUEUED),
                isNull())).thenReturn(item);

        // WHEN
        SubmitBookForPublishingResponse response = activity.execute(request);

        // THEN
        verify(catalogDao, never()).validateBookExists(anyString());
        assertEquals("publishing.123", response.getPublishingRecordId(), "Expected response to return a publishing" +
                "record id.");
    }

    @Test
    public void execute_validBookIdButInactive_bookQueuedForPublishing() {
        // GIVEN
        SubmitBookForPublishingRequest request = SubmitBookForPublishingRequest.builder()
                .withAuthor("Author")
                .withTitle("Title")
                .withBookId("book.456")
                .withGenre(BookGenre.FANTASY.name())
                .build();

        PublishingStatusItem item = new PublishingStatusItem();
        item.setPublishingRecordId("publishing.123");
        when(publishingStatusDao.setPublishingStatus(anyString(),
                eq(PublishingRecordStatus.QUEUED),
                eq(request.getBookId()))).thenReturn(item);

        // WHEN
        SubmitBookForPublishingResponse response = activity.execute(request);

        // THEN
        verify(catalogDao, only()).validateBookExists(request.getBookId());
        assertEquals("publishing.123", response.getPublishingRecordId(), "Expected response to return a publishing" +
                "record id.");

    }

    @Test
    public void execute_inValidBookId_throwsBookNotFoundException() {
        // GIVEN
        SubmitBookForPublishingRequest request = SubmitBookForPublishingRequest.builder()
                .withAuthor("Author")
                .withTitle("Title")
                .withBookId("invalid")
                .withGenre(BookGenre.FANTASY.name())
                .build();

        PublishingStatusItem item = new PublishingStatusItem();
        item.setPublishingRecordId("publishing.123");
        doThrow(BookNotFoundException.class).when(catalogDao).validateBookExists(request.getBookId());
        when(publishingStatusDao.setPublishingStatus(anyString(),
                eq(PublishingRecordStatus.QUEUED),
                eq(request.getBookId()))).thenReturn(item);

        // THEN // WHEN
        assertThrows(BookNotFoundException.class, () -> activity.execute(request));

    }
}
