package com.amazon.ata.kindlepublishingservice.converters;

import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;

import java.util.ArrayList;
import java.util.List;

public class PublishingStatusConverter {

    public static List<PublishingStatusRecord> toPublishingStatusHistory(List<PublishingStatusItem> items) {

        List<PublishingStatusRecord> history = new ArrayList<>();

        for (PublishingStatusItem item : items) {
            history.add(PublishingStatusRecord.builder()
                    .withBookId(item.getBookId())
                    .withStatusMessage(item.getStatusMessage())
                    .withStatus(item.getStatus().toString())
                    .build());
        }

        return history;

    }
}
