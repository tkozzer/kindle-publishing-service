# Kindle Publishing Service

## The Problem: Amazon Kindle Publishing

The Amazon Kindle store provides millions of ebooks to our customers. The process of publishing an
ebook to the kindle catalog is currently an extremely manual process, which causes a long wait time
to add a book to the catalog.

As a member of the Amazon Kindle team, you will be launching a new service that allows our
publishing department to convert books into a digital format.

The overview, architecture, and implementation are covered in the [design document here](DESIGN_DOCUMENT.md). Almost all major pieces of software at Amazon first go through an intensive design
review to answer the question "Are we building the right thing for our customer?".

Carefully read the design document and refer back to it while working on the tasks.

## Project Mastery Tasks

#### Under each mastery task I have linked to parts of the project that are my code.  
### [Mastery Task 1: Killing me softly](tasks/MasteryTask01.md)

My Contributions:   
- [Class diagram](src/resources/mastery-task1-kindle-publishing-CD.puml)  
- [Sequence diagram](src/resources/mastery-task1-remove-book-SD.puml)  
- [Soft Delete](src/com/amazon/ata/kindlepublishingservice/dao/CatalogDao.java)  

### [Mastery Task 2: Submit to the process](tasks/MasteryTask02.md)

My Contributions:
- [BookPublishingRequestManger](src/com/amazon/ata/kindlepublishingservice/publishing/BookPublishingRequestManager.java)  
- [SubmitBookForPublishingActivity](src/com/amazon/ata/kindlepublishingservice/activity/SubmitBookForPublishingActivity.java) 

### [Mastery Task 3: Query, query on the wall, don’t load one, get them all!](tasks/MasteryTask03.md)

My Contributions:
- [DynamoDB Query](src/com/amazon/ata/kindlepublishingservice/dao/PublishingStatusDao.java)
- [GetPublishingStatus](src/com/amazon/ata/kindlepublishingservice/activity/GetPublishingStatusActivity.java) 

### [Mastery Task 4: Make a run(nable) for it](tasks/MasteryTask04.md)

My Contributions:
- [BookPublishTask (Runnable)](src/com/amazon/ata/kindlepublishingservice/publishing/BookPublishTask.java)
- [BookPublishingRequestManager(Updated for Thread Safety)](src/com/amazon/ata/kindlepublishingservice/publishing/BookPublishingRequestManager.java)
- [BookPublishRequest(Updated for Thread Safety)](src/com/amazon/ata/kindlepublishingservice/publishing/BookPublishRequest.java)

&nbsp;

*note: This is a project adapted from Amazon Technical Academy by Bloomtech.*

