JSON-Maker


**[주요 개발 기간]** 2024/04/06 ~ 2024/04/08

**[목표]**

* 수능 영어 LLM Dataset 수집을 위하여 제작.
* 웹에서 입력하면 json 방식으로 서버에 전송하고, json 데이터 그대로 파일로 저장

**[문제 유형 리스트]**

1. 글의 내용 불일치

2. 글의 목적

3. 글의 요지

4. 글의 제목

5. 글의 주제

6. 나머지 넷과 다른 대상

7. 문맥상 낱말의 쓰임이 적절하지 않은 것

8. 문맥에 맞는 낱말 선택

9. 문장이 들어가기에 가장 적절한 곳

10. 밑줄친 문장이 의미하는 바

11. 빈칸 A, B에 들어갈 말

12. 빈칸에 들어갈 말

13. 심경

14. 심경 변화

15. 안내문 내용 불일치

16. 안내문 내용 일치

17. 어법

18. 요약문에 들어갈 A, B 빈칸

19. 이어질 글의 순서

20. 인물에 관한 내용 불일치

21. 표의 내용 불일치

22. 필자가 주장하는 바

23. 흐름과 관계 없는 문장

24. A에 이어질 내용의 순서


**[2024 K-SAT Sample 1]**

19. 다음 글에 드러난 David의 심경 변화로 가장 적절한 것은?

    David was starting a new job in Vancouver, and he was
    waiting for his bus. He kept looking back and forth between
    his watch and the direction the bus would come from. He
    thought, “My bus isn’t here yet. I can’t be late on my first
    day.” David couldn’t feel at ease. When he looked up again,
    he saw a different bus coming that was going right to his
    work. The bus stopped in front of him and opened its door. He
    got on the bus thinking, “Phew! Luckily, this bus came just in
    time so I won’t be late.” He leaned back on an unoccupied seat
    in the bus and took a deep breath, finally able to relax.

    ① nervous → relieved 

    ② lonely → hopeful

    ③ pleased → confused

    ④ indifferent → delighted

    ⑤ bored → thrilled

**[2024 K-SAT Sample 2]**

[36～37] 주어진 글 다음에 이어질 글의 순서로 가장 적절한
것을 고르시오.

36. Negotiation can be defined as an attempt to explore and
    reconcile conflicting positions in order to reach an
    acceptable outcome.
    (A) Areas of difference can and do frequently remain, and
    will perhaps be the subject of future negotiations, or
    indeed remain irreconcilable. In those instances in which
    the parties have highly antagonistic or polarised relations,
    the process is likely to be dominated by the exposition,
    very often in public, of the areas of conflict.
    (B) In these and sometimes other forms of negotiation,
    negotiation serves functions other than reconciling
    conflicting interests. These will include delay, publicity,
    diverting attention or seeking intelligence about the other
    party and its negotiating position.
    (C) Whatever the nature of the outcome, which may actually
    favour one party more than another, the purpose of
    negotiation is the identification of areas of common interest
    and conflict. In this sense, depending on the intentions of
    the parties, the areas of common interest may be clarified,
    refined and given negotiated form and substance.
    
    *reconcile: 화해시키다 **antagonistic: 적대적인
    ***exposition: 설명
    
    ① (A) － (C) － (B)
    
    ② (B) －(A) － (C)
    
    ③ (B) － (C) － (A)
    
    ④ (C) －(A) － (B)
    
    ⑤ (C) － (B) － (A)


**[JSON Form]**

Dataset의 최종본에 해당하는 JSON Format. 수집의 용이함을 위해 아래에서 간소화 하였다.

    {
        "task_type":"문제 유형 입력",
        "definition":"프롬프트",
        "instance":[{"질문" : "문제의 유형에 해당하는 질문",
        "원문" : "문제를 풀어서 원래 정답인 글 입력",
        "본문" : "문제에 있는 본문 복붙",
        "보기" : "문제의 보기",
        "답" : "문제의 정답"
        }, {"질문" : "문제의 유형에 해당하는 질문",
        "원문" : "문제를 풀어서 원래 정답인 글 입력",
        "본문" : "문제에 있는 본문 복붙",
        "보기" : "문제의 보기",
        "답" : "문제의 정답"
        }, {"질문" : "문제의 유형에 해당하는 질문",
        "원문" : "문제를 풀어서 원래 정답인 글 입력",
        "본문" : "문제에 있는 본문 복붙",
        "보기" : "문제의 보기",
        "답" : "문제의 정답"
        }]
    }


**[JSON Simple Form]**

따라서 Dataset의 Instance에 해당하는 문제 부분만 추출하기 위한 Format을 따로 제작하였다.

    {
        "문제 유형 정의":[{
        "id" : "1",
        "질문" : "문제의 유형에 해당하는 질문",
        "원문" : "문제를 풀어서 원래 정답인 글 입력",
        "본문" : "문제에 있는 본문 복붙",
        "보기" : "문제의 보기",
        "답" : "문제의 정답"
        }, {
        "id" : "2",
        "질문" : "문제의 유형에 해당하는 질문",
        "원문" : "문제를 풀어서 원래 정답인 글 입력",
        "본문" : "문제에 있는 본문 복붙",
        "보기" : "문제의 보기",
        "답" : "문제의 정답"
        }, {
        "id" : "3",
        "질문" : "문제의 유형에 해당하는 질문",
        "원문" : "문제를 풀어서 원래 정답인 글 입력",
        "본문" : "문제에 있는 본문 복붙",
        "보기" : "문제의 보기",
        "답" : "문제의 정답"
        }]
    }

**[Input Format]**

삭제를 위한 식별 ID : Long

문제 유형 : String

질문 : String

본문 : String

보기 : String

정답 : String

**[실행 화면]**

[!Result](https://github.com/LeeJU0912/json-saver/blob/master/screenshot.png)

**[History]**

2024/4/7 

    First Commit

2024/4/8

    Dataset을 ConcurrentHashMap 대신 LinkedHashMap을 사용하여 순서 유지. (개인이 각각 별도의 LinkedHashMap을 사용하기 때문에 동시성 보장 필요 없음.)
    key값 한글로 치환.

2024/4/9

    json delete 지원. (문제 제출 번호로 Map 탐색 후 삭제)
    json 포맷 변경
    ConcurrentMultiHashMap을 ConcurrentHashMap<String, LinkedHashSet>으로 임의로 구현
    objectMapper.writerWithDefaultPrettyPrinter() 사용하여 JSON 포맷 이쁘게 만들기.
    stringBuilder "\n" 삽입 (앞쪽에 추가하는 경우 현재 문자열 뒤로 가서 반복 탐색 주의)
    
    +minor fix
    지웠을 때 Sequence 감소 추가
    삭제 실패시 메시지 추가

2024/4/10

    +minor fix
    공백 입력시 무시 추가

2024/05/09

    다운로드 로직 간소화
    상대경로 도입


+ ### **추가 예정**

  타임리프 국제화

  validation 추가

