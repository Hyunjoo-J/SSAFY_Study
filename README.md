## Algorithm
|    알고리즘    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: |
|백트래킹|4|4|4|4|0|
|그래프 탐색|24|19|15|20|0|
|이분 탐색|7|6|4|6|0|
|브루트포스|9|9|4|9|0|
|자료구조|9|6|4|9|0|
|다익스트라|7|5|3|5|0|
|분할정복|4|3|2|3|0|
|다이나믹 프로그래밍|22|17|14|20|0|
|플로이드 워셜|1|1|1|1|0|
|그리디|11|10|6|9|0|
|구현|14|8|4|11|0|
|KMP|3|3|2|2|0|
|LCA(최소 공통 조상)|5|2|0|3|0|
|수학|1|1|0|1|0|
|MST(최소 스패닝 트리)|2|2|2|2|0|
|누적합|3|3|3|3|0|
|세그먼트 트리|5|3|0|3|1|
|문자열|4|1|0|4|0|
|위상정렬|4|4|2|3|0|
|트리|3|1|0|3|0|
|트라이|2|0|0|1|0|
|투포인터|6|6|4|5|0|
|유니온 파인드|2|0|0|1|0|
| **합계** | **152**|**114**|**74**|**128**|**1**|

<br>

## Problems
<details>
<summary>

#### 백트래킹

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/14888">14888    |     <a href="BackTracking/p14888_연산자끼워넣기">연산자끼워넣기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/14889">14889    |     <a href="BackTracking/p14889_스타트와링크">스타트와링크    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/15661">15661    |     <a href="BackTracking/p15661_링크와스타트">링크와스타트    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1759">1759    |     <a href="BackTracking/p1759_암호만들기">암호만들기    | ✔ |✔ |✔ |✔ |❌ |
</details>

<details>
<summary>

#### 그래프 탐색

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1012">1012    |     <a href="BFS_DFS/p1012_유기농배추">유기농배추    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/11724">11724    |     <a href="BFS_DFS/p11724_연결요소의개수">연결요소의개수    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/1189">1189    |     <a href="BFS_DFS/p1189_컴백홈">컴백홈    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/12851">12851    |     <a href="BFS_DFS/p12851_숨바꼭질2">숨바꼭질2    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/13549">13549    |     <a href="BFS_DFS/p13549_숨바꼭질3">숨바꼭질3    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/14502">14502    |     <a href="BFS_DFS/p14502_연구소">연구소    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/14940">14940    |     <a href="BFS_DFS/p14940_쉬운최단거리">쉬운최단거리    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/16197">16197    |     <a href="BFS_DFS/p16197_두동전">두동전    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/16236">16236    |     <a href="BFS_DFS/p16236_아기상어">아기상어    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/16946">16946    |     <a href="BFS_DFS/p16946_벽부수고이동하기4">벽부수고이동하기4    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/16953">16953    |     <a href="BFS_DFS/p16953_AtoB">AtoB    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1697">1697    |     <a href="BFS_DFS/p1697_숨바꼭질">숨바꼭질    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1707">1707    |     <a href="BFS_DFS/p1707_이분그래프">이분그래프    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/17129">17129    |     <a href="BFS_DFS/p17129_윌리암슨수액빨이딱따구리가정보섬에올라온이유">윌리암슨수액빨이딱따구리가정보섬에올라온이유    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/18405">18405    |     <a href="BFS_DFS/p18405_경쟁적전염">경쟁적전염    | ✔ |✔ |❌ |❌ |❌ |
|    <a href="http://boj.kr/1939">1939    |     <a href="BFS_DFS/p1939_중량제한">중량제한    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/2206">2206    |     <a href="BFS_DFS/p2206_벽부수고이동하기">벽부수고이동하기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2251">2251    |     <a href="BFS_DFS/p2251_물통">물통    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2310">2310    |     <a href="BFS_DFS/p2310_어드벤처게임">어드벤처게임    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2468">2468    |     <a href="BFS_DFS/p2468_안전영역">안전영역    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2583">2583    |     <a href="BFS_DFS/p2583_영역구하기">영역구하기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2606">2606    |     <a href="BFS_DFS/p2606_바이러스">바이러스    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2644">2644    |     <a href="BFS_DFS/p2644_촌수계산">촌수계산    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/7562">7562    |     <a href="BFS_DFS/p7562_나이트의이동">나이트의이동    | ✔ |✔ |❌ |❌ |❌ |
</details>

<details>
<summary>

#### 이분 탐색

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/2110">2110    |     <a href="BinarySearch/p2110_공유기설치">공유기설치    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2467">2467    |     <a href="BinarySearch/p2467_용액">용액    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/2473">2473    |     <a href="BinarySearch/p2473_세용액">세용액    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/2512">2512    |     <a href="BinarySearch/p2512_예산">예산    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2805">2805    |     <a href="BinarySearch/p2805_나무자르기">나무자르기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/4001">4001    |     <a href="BinarySearch/p4001_미노타우르스미궁">미노타우르스미궁    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/7453">7453    |     <a href="BinarySearch/p7453_합이0인네정수">합이0인네정수    | ✔ |✔ |✔ |✔ |❌ |
</details>

<details>
<summary>

#### 브루트포스

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1107">1107    |     <a href="Bruteforce/p1107_리모컨">리모컨    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1182">1182    |     <a href="Bruteforce/p1182_부분수열의합">부분수열의합    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/15683">15683    |     <a href="Bruteforce/p15683_감시">감시    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1747">1747    |     <a href="Bruteforce/p1747_소수and팰린드롬">소수and팰린드롬    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/18429">18429    |     <a href="Bruteforce/p18429_근손실">근손실    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/20529">20529    |     <a href="Bruteforce/p20529_가장가까운세사람의심리적거리">가장가까운세사람의심리적거리    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2304">2304    |     <a href="Bruteforce/p2304_창고다각형">창고다각형    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/27172">27172    |     <a href="Bruteforce/p27172_수나누기게임">수나누기게임    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/3085">3085    |     <a href="Bruteforce/p3085_사탕게임">사탕게임    | ✔ |✔ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 자료구조

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/11286">11286    |     <a href="DataStructure/p11286_절댓값힙">절댓값힙    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1202">1202    |     <a href="DataStructure/p1202_보석도둑">보석도둑    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/13335">13335    |     <a href="DataStructure/p13335_트럭">트럭    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1406">1406    |     <a href="DataStructure/p1406_에디터">에디터    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1918">1918    |     <a href="DataStructure/p1918_후위표기식">후위표기식    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1927">1927    |     <a href="DataStructure/p1927_최소힙">최소힙    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1991">1991    |     <a href="DataStructure/p1991_트리순회">트리순회    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/23309">23309    |     <a href="DataStructure/p23309_철도공사">철도공사    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/5397">5397    |     <a href="DataStructure/p5397_키로거">키로거    | ✔ |✔ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 다익스트라

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/10282">10282    |     <a href="Dijkstra/p10282_해킹">해킹    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/11779">11779    |     <a href="Dijkstra/p11779_최소비용구하기2">최소비용구하기2    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1238">1238    |     <a href="Dijkstra/p1238_파티">파티    | ✔ |✔ |❌ |❌ |❌ |
|    <a href="http://boj.kr/1446">1446    |     <a href="Dijkstra/p1446_지름길">지름길    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/17270">17270    |     <a href="Dijkstra/p17270_연예인은힘들어">연예인은힘들어    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/1753">1753    |     <a href="Dijkstra/p1753_최단경로">최단경로    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/9370">9370    |     <a href="Dijkstra/p9370_미확인도착지">미확인도착지    | ✔ |❌ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 분할정복

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/10830">10830    |     <a href="DivideAndConquer/p10830_행렬제곱">행렬제곱    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/11444">11444    |     <a href="DivideAndConquer/p11444_피보나치수6">피보나치수6    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1493">1493    |     <a href="DivideAndConquer/p1493_박스채우기">박스채우기    | ✔ |✔ |❌ |❌ |❌ |
|    <a href="http://boj.kr/2630">2630    |     <a href="DivideAndConquer/p2630_색종이만들기">색종이만들기    | ✔ |✔ |✔ |✔ |❌ |
</details>

<details>
<summary>

#### 다이나믹 프로그래밍

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/10844">10844    |     <a href="DynamicProgramming/p10844_쉬운계단수">쉬운계단수    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/11048">11048    |     <a href="DynamicProgramming/p11048_이동하기">이동하기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/11049">11049    |     <a href="DynamicProgramming/p11049_행렬곱셈순서">행렬곱셈순서    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/11060">11060    |     <a href="DynamicProgramming/p11060_점프점프">점프점프    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/11066">11066    |     <a href="DynamicProgramming/p11066_파일합치기">파일합치기    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/1149">1149    |     <a href="DynamicProgramming/p1149_RGB거리">RGB거리    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/12920">12920    |     <a href="DynamicProgramming/p12920_평범한배낭2">평범한배낭2    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/14501">14501    |     <a href="DynamicProgramming/p14501_퇴사">퇴사    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1520">1520    |     <a href="DynamicProgramming/p1520_내리막길">내리막길    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/15486">15486    |     <a href="DynamicProgramming/p15486_퇴사2">퇴사2    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/17404">17404    |     <a href="DynamicProgramming/p17404_RGB거리2">RGB거리2    | ✔ |❌ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1912">1912    |     <a href="DynamicProgramming/p1912_연속합">연속합    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1932">1932    |     <a href="DynamicProgramming/p1932_정수삼각형">정수삼각형    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/20303">20303    |     <a href="DynamicProgramming/p20303_할로윈의양아치">할로윈의양아치    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/2293">2293    |     <a href="DynamicProgramming/p2293_동전1">동전1    | ✔ |❌ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2302">2302    |     <a href="DynamicProgramming/p2302_극장좌석">극장좌석    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/2342">2342    |     <a href="DynamicProgramming/p2342_DanceDanceRevolution">DanceDanceRevolution    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/2533">2533    |     <a href="DynamicProgramming/p2533_사회망서비스">사회망서비스    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2579">2579    |     <a href="DynamicProgramming/p2579_계단오르기">계단오르기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/7579">7579    |     <a href="DynamicProgramming/p7579_앱">앱    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/9095">9095    |     <a href="DynamicProgramming/p9095_123더하기">123더하기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/9252">9252    |     <a href="DynamicProgramming/p9252_LCS2">LCS2    | ✔ |✔ |✔ |✔ |❌ |
</details>

<details>
<summary>

#### 플로이드 워셜

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1389">1389    |     <a href="FloydWarshall/p1389_케빈베이컨의6단계법칙">케빈베이컨의6단계법칙    | ✔ |✔ |✔ |✔ |❌ |
</details>

<details>
<summary>

#### 그리디

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/11000">11000    |     <a href="Greedy/p11000_강의실배정">강의실배정    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/11501">11501    |     <a href="Greedy/p11501_주식">주식    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1541">1541    |     <a href="Greedy/p1541_잃어버린괄호">잃어버린괄호    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/15903">15903    |     <a href="Greedy/p15903_카드합체놀이">카드합체놀이    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/16496">16496    |     <a href="Greedy/p16496_큰수만들기">큰수만들기    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/1700">1700    |     <a href="Greedy/p1700_멀티탭스케줄링">멀티탭스케줄링    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1715">1715    |     <a href="Greedy/p1715_카드정렬하기">카드정렬하기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1946">1946    |     <a href="Greedy/p1946_신입사원">신입사원    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2138">2138    |     <a href="Greedy/p2138_전구와스위치">전구와스위치    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2785">2785    |     <a href="Greedy/p2785_체인">체인    | ✔ |✔ |❌ |❌ |❌ |
|    <a href="http://boj.kr/2885">2885    |     <a href="Greedy/p2885_초콜릿식사">초콜릿식사    | ✔ |✔ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 구현

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/12100">12100    |     <a href="Implematation/p12100_2048Easy">2048Easy    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/14503">14503    |     <a href="Implematation/p14503_로봇청소기">로봇청소기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/16637">16637    |     <a href="Implematation/p16637_괄호추가하기">괄호추가하기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/17136">17136    |     <a href="Implematation/p17136_색종이붙이기">색종이붙이기    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1713">1713    |     <a href="Implematation/p1713_후보추천하기">후보추천하기    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/17780">17780    |     <a href="Implematation/p17780_새로운게임">새로운게임    | ✔ |❌ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1800">1800    |     <a href="Implematation/p1800_인터넷설치">인터넷설치    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/19236">19236    |     <a href="Implematation/p19236_청소년상어">청소년상어    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/20006">20006    |     <a href="Implematation/p20006_랭킹전대기열">랭킹전대기열    | ✔ |✔ |❌ |❌ |❌ |
|    <a href="http://boj.kr/20056">20056    |     <a href="Implematation/p20056_마법사상어와파이어볼">마법사상어와파이어볼    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/20057">20057    |     <a href="Implematation/p20057_마법사상어와토네이도">마법사상어와토네이도    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/20058">20058    |     <a href="Implematation/p20058_마법사상어와파이어스톰">마법사상어와파이어스톰    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/23289">23289    |     <a href="Implematation/p23289_온풍기안녕">온풍기안녕    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/2632">2632    |     <a href="Implematation/p2632_피자판매">피자판매    | ✔ |❌ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### KMP

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/11585">11585    |     <a href="KMP/p11585_속타는저녁메뉴">속타는저녁메뉴    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/1305">1305    |     <a href="KMP/p1305_광고">광고    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/7575">7575    |     <a href="KMP/p7575_바이러스">바이러스    | ✔ |✔ |❌ |❌ |❌ |
</details>

<details>
<summary>

#### LCA(최소 공통 조상)

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/11437">11437    |     <a href="LCA/p11437_LCA">LCA    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/11438">11438    |     <a href="LCA/p11438_LCA2">LCA2    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/15481">15481    |     <a href="LCA/p15481_그래프와MST">그래프와MST    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/1626">1626    |     <a href="LCA/p1626_두번째로작은스패닝트리">두번째로작은스패닝트리    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/3176">3176    |     <a href="LCA/p3176_도로네트워크">도로네트워크    | ✔ |❌ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 수학

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/2166">2166    |     <a href="Math/p2166_다각형의넓이">다각형의넓이    | ✔ |✔ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### MST(최소 스패닝 트리)

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/14950">14950    |     <a href="MST/p14950_정복자">정복자    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/16398">16398    |     <a href="MST/p16398_행성연결">행성연결    | ✔ |✔ |✔ |✔ |❌ |
</details>

<details>
<summary>

#### 누적합

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/14476">14476    |     <a href="PrefixSum/p14476_최대공약수하나빼기">최대공약수하나빼기    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/25682">25682    |     <a href="PrefixSum/p25682_체스판다시칠하기2">체스판다시칠하기2    | ✔ |✔ |✔ |✔ |❌ |
|    프로그래머스LV3    |     <a href="PrefixSum/프로그래머스LV3_파괴되지않은건물">파괴되지않은건물    | ✔ |✔ |✔ |✔ |❌ |
</details>

<details>
<summary>

#### 세그먼트 트리

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/10167">10167    |     <a href="SegmentTree/p10167_금광">금광    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/18196">18196    |     <a href="SegmentTree/p18196_정기모임">정기모임    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/2243">2243    |     <a href="SegmentTree/p2243_사탕상자">사탕상자    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/2357">2357    |     <a href="SegmentTree/p2357_최소값과최댓값">최소값과최댓값    | ✔ |✔ |❌ |✔ |✔ |
|    <a href="http://boj.kr/2517">2517    |     <a href="SegmentTree/p2517_달리기">달리기    | ✔ |✔ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 문자열

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1294">1294    |     <a href="String/p1294_문자열장식">문자열장식    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/20920">20920    |     <a href="String/p20920_영단어암기는괴로워">영단어암기는괴로워    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/9177">9177    |     <a href="String/p9177_단어섞기">단어섞기    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/9935">9935    |     <a href="String/p9935_문자열폭발">문자열폭발    | ✔ |❌ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 위상정렬

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1005">1005    |     <a href="TopologySort/p1005_ACMCraft">ACMCraft    | ✔ |✔ |❌ |❌ |❌ |
|    <a href="http://boj.kr/2056">2056    |     <a href="TopologySort/p2056_작업">작업    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2623">2623    |     <a href="TopologySort/p2623_음악프로그램">음악프로그램    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/5021">5021    |     <a href="TopologySort/p5021_왕위계승">왕위계승    | ✔ |✔ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 트리

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1167">1167    |     <a href="Tree/p1167_트리의지름">트리의지름    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/1967">1967    |     <a href="Tree/p1967_트리의지름">트리의지름    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/5639">5639    |     <a href="Tree/p5639_이진검색트리">이진검색트리    | ✔ |❌ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 트라이

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/5670">5670    |     <a href="Trie/p5670_휴대폰자판">휴대폰자판    | ✔ |❌ |❌ |❌ |❌ |
|    <a href="http://boj.kr/9202">9202    |     <a href="Trie/p9202_Boggle">Boggle    | ✔ |❌ |❌ |✔ |❌ |
</details>

<details>
<summary>

#### 투포인터

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1806">1806    |     <a href="TwoPointer/p1806_부분합">부분합    | ✔ |✔ |❌ |✔ |❌ |
|    <a href="http://boj.kr/20922">20922    |     <a href="TwoPointer/p20922_겹치는건싫어">겹치는건싫어    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/22857">22857    |     <a href="TwoPointer/p22857_가장긴짝수연속한부분수열small">가장긴짝수연속한부분수열small    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/22862">22862    |     <a href="TwoPointer/p22862_가장긴짝수연속한부분수열large">가장긴짝수연속한부분수열large    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2531">2531    |     <a href="TwoPointer/p2531_회전초밥">회전초밥    | ✔ |✔ |✔ |✔ |❌ |
|    <a href="http://boj.kr/2842">2842    |     <a href="TwoPointer/p2842_집배원한상덕">집배원한상덕    | ✔ |✔ |❌ |❌ |❌ |
</details>

<details>
<summary>

#### 유니온 파인드

</summary>

    
|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 | 이동훈 |
| :-------------: | :----: | :----: | :----: | :----: | :----: | :----: |
|    <a href="http://boj.kr/1043">1043    |     <a href="UnionFind/p1043_거짓말">거짓말    | ✔ |❌ |❌ |✔ |❌ |
|    <a href="http://boj.kr/20040">20040    |     <a href="UnionFind/p20040_사이클게임">사이클게임    | ✔ |❌ |❌ |❌ |❌ |
</details>
