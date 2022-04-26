Grokking the System Design Interview

1. Introduction
About this Module


System Design Interviews: A step by step guide



2. Summarized System Design Problems
Designing a URL Shortening service like TinyURL

Designing Pastebin

Designing Instagram

Designing Dropbox

Designing Facebook Messenger

Designing Twitter

Designing Youtube or Netflix

Designing Typeahead Suggestion

Designing an API Rate Limiter

Designing Twitter Search

Designing a Web Crawler

Designing Facebook’s Newsfeed

Designing Yelp or Nearby Friends

Designing Uber backend

Designing Ticketmaster




**3. Dynamo: How To Design a Key-Value Store**


Dynamo: Introduction

High-level Architecture

Data Partitioning

Replication

Vector Clocks and Conflicting Data

The Life of Dynamo’s put() & get() Operations

Anti-entropy Through Merkle Trees

Gossip Protocol

Dynamo Characteristics and Criticism

Summary: Dynamo

Quiz: Dynamo

Mock Interview: Dynamo

***4. Cassandra: How to Design a Wide-Column NoSQL Database ***
Cassandra: Introduction

High-level Architecture

Replication

Cassandra Consistency Levels

Gossiper

Anatomy of Cassandra's Write Operation

Anatomy of Cassandra's Read Operation

Compaction

Tombstones

Summary: Cassandra

Quiz: Cassandra

Mock Interview: Cassandra

***5. Kafka: How to Design a Distributed Messaging System ***


Messaging Systems: Introduction

Kafka: Introduction

High-level Architecture

Kafka: Deep Dive

Consumer Groups

Kafka Workflow

Role of ZooKeeper

Controller Broker

Kafka Delivery Semantics

Kafka Characteristics

Summary: Kafka

Quiz: Kafka

Mock Interview: Kafka

***6. Chubby: How to Design a Distributed Locking Service ***
Chubby: Introduction

High-level Architecture

Design Rationale

How Chubby Works

File, Directories, and Handles

Locks, Sequencers, and Lock-delays

Sessions and Events

Master Election and Chubby Events

Caching

Database

Scaling Chubby

Summary: Chubby

Quiz: Chubby

Mock Interview: Chubby

***7. GFS: How to Design a Distributed File System***


Google File System: Introduction

High-level Architecture

Single Master and Large Chunk Size

Metadata

Master Operations

Anatomy of a Read Operation

Anatomy of a Write Operation

Anatomy of an Append Operation

GFS Consistency Model and Snapshotting

Fault Tolerance, High Availability, and Data Integrity

Garbage Collection

Criticism on GFS

Summary: GFS

Quiz: GFS

Mock Interview: GFS

***8. HDFS: How to Design a Distributed File System***


Hadoop Distributed File System: Introduction

High-level Architecture

Deep Dive

Anatomy of a Read Operation

Anatomy of a Write Operation

Data Integrity & Caching

Fault Tolerance

HDFS High Availability (HA)

HDFS Characteristics

Summary: HDFS

Quiz: HDFS

Mock Interview: HDFS

***9. BigTable: How to Design a Wide-Column Storage System ***


BigTable: Introduction

BigTable Data Model

System APIs

Partitioning and High-level Architecture

SSTable

GFS and Chubby

Bigtable Components

Working with Tablets

The Life of BigTable's Read & Write Operations

Fault Tolerance and Compaction

BigTable Refinements

BigTable Characteristics

Summary: BigTable

Quiz: BigTable

Mock Interview: BigTable

***10. System Design Patterns *** 

Introduction: System Design Patterns

1. Bloom Filters

2. Consistent Hashing

3. Quorum

4. Leader and Follower

5. Write-ahead Log

6. Segmented Log

7. High-Water Mark

8. Lease

9. Heartbeat

10. Gossip Protocol

11. Phi Accrual Failure Detection

12. Split Brain

13. Fencing

14. Checksum

15. Vector Clocks

16. CAP Theorem

17. PACELC Theorem

18. Hinted Handoff

19. Read Repair

20. Merkle Trees

***11. Glossary of System Design Basics ***
System Design Basics

Key Characteristics of Distributed Systems

Load Balancing

Caching

Data Partitioning

Indexes

Proxies

Redundancy and Replication

SQL vs. NoSQL

Consistent Hashing (New)

Long-Polling vs WebSockets vs Server-Sent Events

12. Final Assessments
Quiz I

Quiz II
