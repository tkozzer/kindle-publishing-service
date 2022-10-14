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
- [Class diagram](https://github.com/tkozzer/kindle-publishing-service/commit/ebf2c5773f8884d96c348a0090f64dd3f8b51f90#diff-8e45af3c592ef506bb6673fb9702eb1a4cce0130ce4e856d8f523d193f69128c)   
- [Sequence diagram](https://github.com/tkozzer/kindle-publishing-service/commit/2b6ec889b05ce1c922cbe60a20c6764520d7c4fc#diff-aea40ad0c50002ac2e2f4143b21dc78c68b3c5684d0e3910b985db92ede2996e)
- [CatalogDao softDelete](https://github.com/tkozzer/kindle-publishing-service/commit/6742e110dc13c4f14733654aaa4b9d699f1ffc21#diff-1e0d63a2b6ec3ceaca2b52a387c966bccffb587c829fa5528b1954afdb4b8845)
- [CatalogDaoTest softDelete tests](https://github.com/tkozzer/kindle-publishing-service/commit/6742e110dc13c4f14733654aaa4b9d699f1ffc21#diff-101a5c43cb20a76f37648f7c038defcaf90e6744e15962264f98568d5478654b)

### [Mastery Task 2: Submit to the process](tasks/MasteryTask02.md)

My Contributions:
- [BookPublishingRequestManger](https://github.com/tkozzer/kindle-publishing-service/commit/7463eb04651e424b57ed611e80c8e5a5255a204f#diff-03019c93ce076a291c5c91318ec1d046db00ff2c524cd65d1a32be9ec1f188bf)  
- [SubmitBookForPublishingActivity](https://github.com/tkozzer/kindle-publishing-service/commit/7463eb04651e424b57ed611e80c8e5a5255a204f#diff-f5d5e5e40d2b9c62c349e30f034f19af340d04d87ac52949a2b7a3df1ee6495f)
- [SubmitBookForPublishingActivityTest](https://github.com/tkozzer/kindle-publishing-service/commit/7463eb04651e424b57ed611e80c8e5a5255a204f?diff=split#diff-5d3ecb8756b310976ecf99fc27c1726d9aadefa2ef765a799cdfeeeb08233db5)
- [CatalogDao](https://github.com/tkozzer/kindle-publishing-service/commit/7463eb04651e424b57ed611e80c8e5a5255a204f?diff=split#diff-1e0d63a2b6ec3ceaca2b52a387c966bccffb587c829fa5528b1954afdb4b8845)

### [Mastery Task 3: Query, query on the wall, don’t load one, get them all!](tasks/MasteryTask03.md)

My Contributions:
- [PublishingStatusDao](https://github.com/tkozzer/kindle-publishing-service/commit/88e226f107139260867090eaff2beb2b421a74c7#diff-080d2cc0751fec25e3e85585253976ced2b787c2d1a81c7be0d36bd8d833ba36)
- [GetPublishingStatusActivity](https://github.com/tkozzer/kindle-publishing-service/commit/88e226f107139260867090eaff2beb2b421a74c7#diff-586e4556aa411ec8f844d7e4c434958616ae02970efed5659c1acf1c255a9bfd)
- [PublishingStatusConverter](https://github.com/tkozzer/kindle-publishing-service/commit/88e226f107139260867090eaff2beb2b421a74c7#diff-c6d7787376d27247d54ec482799a321334dcf4503664e05e5a120d760266656e)

### [Mastery Task 4: Make a run(nable) for it](tasks/MasteryTask04.md)

My Contributions:

- [CatalogDao](https://github.com/tkozzer/kindle-publishing-service/commit/468f0eb3992eba52261efc54619edbcb275a3281#diff-1e0d63a2b6ec3ceaca2b52a387c966bccffb587c829fa5528b1954afdb4b8845)
- [BookPublishTask (Runnable)](https://github.com/tkozzer/kindle-publishing-service/commit/468f0eb3992eba52261efc54619edbcb275a3281#diff-ea9c8983608e995deef7ac61cbefe364fc1a1a124813b25861b1ee8e2aadccce)
- [BookPublishingRequestManager(Updated for Thread Safety)](https://github.com/tkozzer/kindle-publishing-service/commit/468f0eb3992eba52261efc54619edbcb275a3281#diff-03019c93ce076a291c5c91318ec1d046db00ff2c524cd65d1a32be9ec1f188bf)

&nbsp;

*note: This is a project adapted from Amazon Technical Academy by Bloomtech.*

