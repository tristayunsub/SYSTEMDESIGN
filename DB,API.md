https://tech.kakao.com/2016/01/29/opensource-1-s2graph/

https://www.youtube.com/watch?v=mge4cLqcpxU&t=7s&ab_channel=Hanib
준비방법

graph database?
https://www.youtube.com/watch?v=hnpzNAPiC0E&ab_channel=InfoQ
scacaling infrastructure

https://www.educative.io/courses/grokking-the-system-design-interview/m2ygV4E81AR : url shortening


https://www.youtube.com/watch?v=VJpfO6KdyWE&ab_channel=Exponent


https://www.youtube.com/watch?v=MbjObHmDbZo&ab_channel=Geek%27sLesson

Jamie Pearcey
11개월 전(수정됨)
I am not sure that a relational database is the best choice here. There are often relationships in data but sometimes you need to determine the right trade offs in terms of scalability. Though I appreciate that a relational model can be designed to work at such scale (read replicas, sharing etc), 
I would consider using NoSQL solution for the media metadata api, and a graph database for the activity feed.

Nice pace of the mock interview. However, the diagram can actually fit most of the system. Shouldn't we focus more on how to fan-out photos,
generate news feed, etc, which are more specific to "designing Instagram"? I wonder if we only talk about the high-level things and teaching the interviewers 
those basic architectural concepts (read-write, load balancer, s3, CDN) 
as this video does can really ace the interview.

I see that in so many videos on YouTube. 0 specifics about the actual system, just generic LB, DB schema, object storage stuff. 
I really wonder if interviews are buying this….


they're not. You won't get any reputable offer with details YouTube videos provide. These are just for the views.


Liran elisha this would not be sufficient — scalability is not one dimensional. You can read the actual story about how Instagram originally did it at High Scalability.
There is a cost to using NoSQL — schema on load versus schema on read. If you care about data integrity and operational familiarity,
it’s hard to beat SQL. These are considerable trade-offs worth sharing and discussing during an interview. 
It’s not blatantly obvious why some hand-wavy NoSQL solution is more scalable. Which part? 
I don’t doubt that there is a NoSQL solution that makes some bottleneck in the system much more performant, 
but rather we should go with a choice and be able to defend it and also discuss alternatives and their trade-offs

https://www.youtube.com/watch?v=DSGsa0pu8-k&list=PLQ3oJb1lRYO2_tcYFeu9NEpWX5_0xnWNw&index=4&ab_channel=SuccessinTech
여기 맛집이네


The re addition of the empty space to the stack is an O(n) operation which kinda sucks but may be a reasonable tradeoff.
A minheap would be better suited to get the best overall performance while sacrificing the entry performance.  
Otherwise very good video, nicely explained. Especially the part about practicalities of interviews.



Deepak Nayak
4년 전(수정됨)
Very nice detailed video. Thanks for the detailed explanation.
But using stack only for tracking how many spots we are left with us is an overkill. Just use counters of any integer type. Increment it as you fill up and decrement it as spaces are free. If you are going to use the stack for a different purpose in future scalable design then that’s a different scenario. As of now stack DS doesn’t make much sense here.
For coders out there, Guys coding in C++, can just use std::map instead of writing a hashmap DS, unlike java which has HashMap as a inbuilt container.
One mote better powerful DS i can think of is using nested maps which can contain so many information at once. e.g 
std::map<car_license <spot_type, spot_number>. We can nest it as much as possible. Searching operation is log(n) in maps.
If database can be used then we are much relaxed as you told. Just query and get the result.
Overall very cool video.

