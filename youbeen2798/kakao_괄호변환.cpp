#include <string>
#include <vector>
#include <stack>

using namespace std;

//���ذ� �ȵǴ� �κ� : �ùٸ� ���ڰ� �ƴ� �� u�� v�� �ڸ��� ������ �𸣰���
//���� �ٽ� õõ�� Ǯ� ����

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