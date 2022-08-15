package com.amazon.ata.kindlepublishingservice.publishing;

import java.util.LinkedList;
import java.util.Queue;

public class BookPublishingRequestManager {

    private Queue<BookPublishRequest> queue;

    public BookPublishingRequestManager() {
        queue = new LinkedList<>();
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
