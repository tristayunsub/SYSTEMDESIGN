https://jaemunbro.medium.com/gcp-dataflow-apache-beam-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0-a4f5f09b98d1

https://medium.com/@kiseon_twt/gcp-apache-beam-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-8737122b276b

아파치 빔의 구조?

![0_IFT73j17fPJUmMyr](https://user-images.githubusercontent.com/75001605/168922501-1389e4a3-f409-4bf8-946e-2d5f61cc0c8e.png)


pcollections, ptransforms? 


pardo는 map에가까움

windowing 방식.. 어렵다.

Windowing은 PCollection을 타임스탬프나 어떤 기준 요소로 잘라서 보는 것을 말한다. 

어떤 PCollection을 논리적 윈도우로 잘라볼 수 있다. 각 요소들은 하나 혹은 그 이상의 윈도우에 속해서 GroupByKey 또는 Combine등의 Aggregation연산 등에 활용될 수 있다.

Windowing 방식에는 세가지가 있다:

1. Fixed time windows

2. Sliding time windows

3. Session windows


