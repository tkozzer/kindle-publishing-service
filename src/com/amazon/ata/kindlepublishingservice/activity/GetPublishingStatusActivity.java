package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.converters.PublishingStatusConverter;
import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatus;
import com.amazon.ata.kindlepublishingservice.models.requests.GetPublishingStatusRequest;
import com.amazon.ata.kindlepublishingservice.models.response.GetPublishingStatusResponse;
import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetPublishingStatusActivity for the ATACurriculumKindlePublishingService's
 * RemoveBookFromCatalog API
 *
 * This API allows the client to get the publishing status history
 */
public class GetPublishingStatusActivity {

    private PublishingStatusDao publishingStatusDao;

    /**
     * Instantiates a new GetPublishingStatusActivity object
     *
     * @param publishingStatusDao PublishingStatusDao to access the Catalog table.
     */
    @Inject
    public GetPublishingStatusActivity(PublishingStatusDao publishingStatusDao) {
        this.publishingStatusDao = publishingStatusDao;
    }

    /**
     * Retrieves all the publishing status records for a given publishingStatusId
     * *
     * @param publishingStatusRequest A request that contains the publishingStatusId
     * @return GetPublishingStatusResponse Response object that contains a history of
     *          publishing statuses
     */
    public GetPublishingStatusResponse execute(GetPublishingStatusRequest publishingStatusRequest) {

        List<PublishingStatusItem> items = publishingStatusDao.getPublishStatusList(publishingStatusRequest.getPublishingRecordId());

        return GetPublishingStatusResponse.builder()
                .withPublishingStatusHistory(PublishingStatusConverter.toPublishingStatusHistory(items))
                .build();
    }
}
