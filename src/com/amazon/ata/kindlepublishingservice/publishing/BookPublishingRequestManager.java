package com.amazon.ata.kindlepublishingservice.publishing;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class BookPublishingRequestManager {

    private final Queue<BookPublishRequest> queue;

    public BookPublishingRequestManager() {
        queue = new ConcurrentLinkedQueue<>();
    }

    public boolean addBookPublishRequest(BookPublishRequest request) {
        try {
            queue.add(request);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public BookPublishRequest getBookPublishRequestToProcess() {
        return queue.poll();
    }
}
