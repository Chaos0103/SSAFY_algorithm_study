#include <string>
#include <iostream>
#include <vector>

using namespace std;

//Ǫ�� �߿� signal: aborted (core dumped) �̷� ������ ���� �ذ��ؾ� ��
//�� ���� �ذ��ϸ� Ǯ�� ����!
string mini_solution(string s, int length) {

    vector<string> v;

    int idx;
    for (idx = 0; idx < length - 1; idx += length) {

        string tmp = s.substr(idx, idx + length);
        v.push_back(tmp);
    }
}
int solution(string s) {
    int answer = 0;

    for (int i = 1; i < s.size(); i++) {
        mini_solution(s, i);
    }
    return answer;
}