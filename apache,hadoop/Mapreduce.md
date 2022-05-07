https://www.youtube.com/watch?v=Jx9rjPTWYPQ&list=PL9mhQYIlKEheGLT1V_PEby_I9pOXr1o3r&index=5&ab_channel=SKplanetTacademy

large cluster에서 data 처리 위한 알고리즘

hadoop mapreduce는 이 알고리즘을 소프트웨어로 구현한 구현체

key-value 구조가 알고리즘의 핵심

모든 문제를 해결하기에 적합하지는 않을수도있음


map function: (key1, value1) -> (key2, value2)


reduce function: (key2, List of value2) - > (key3, value3)


job에대한 구동 관리는 하둡이 알아서함( 개발자는 비즈니스 로직 구현에 집중)

맵리듀스 구동방식

Local






