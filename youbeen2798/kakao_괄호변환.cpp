#include <string>
#include <vector>
#include <stack>

using namespace std;

//이해가 안되는 부분 : 올바른 문자가 아닐 때 u와 v로 자르는 기준을 모르겠음
//오늘 다시 천천히 풀어볼 예정

string solution(string p) {

    stack <char> s;

    for (int i = 0; i < p.size(); i++) {

        if (p[i] == '(') {
            s.push(p[i]);
            continue;
        }
        s.pop();
    }

    if (s.empty()) {
        return p;
    }
    string answer = "";
    return answer;
}