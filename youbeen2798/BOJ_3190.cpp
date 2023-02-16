#include <iostream>
#include <set>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int n, k, l;
long current_time = 0; //���� �ð�

int current_head_row = 1; //���� �Ӹ� ��
int current_head_col = 1; //���� �Ӹ� ��

int current_dir = 0; //���� ����
set<pair<int, int>> apples; //��� ��ġ
queue<pair<int, char>> move_info; //���� ���� ��ȯ ����
vector<pair<int, int>> snakes; //�� ��ġ ����

void input() {
    cin >> n >> k; //������ ũ��, ����� ����

    for (int i = 0; i < k; i++) {
        int row, col;
        cin >> row >> col;
        apples.insert({ row, col });
    }

    cin >> l; //���� ���� ��ȯ Ƚ��

    for (int i = 0; i < l; i++) {
        int sec;
        char dir;
        cin >> sec >> dir;
        move_info.push({ sec, dir });
    }
}

void move() {

    switch (current_dir) {
    case 0: //��
        current_head_col++;
        break;
    case 1: //��
        current_head_row++;
        break;
    case 2: //��
        current_head_col--;
        break;
    case 3: //��
        current_head_row--;
        break;
    }

    //������ �Ѿ������
    if (current_head_row < 1 || current_head_col < 1 || n < current_head_row || n < current_head_col) {
        cout << current_time;
        exit(0);
    }
    //�ڱ� ���� ��ƹ�����
    pair<int, int> now = { current_head_row, current_head_col };
    if (find(snakes.begin(), snakes.end(), now) != snakes.end()) {
        cout << current_time;
        exit(0);
    }

    snakes.push_back(now);

    //������ ���� ����� ���ٸ� ���� ����
    if (apples.find(now) == apples.end()) {
        snakes.erase(snakes.begin());
    }
    else {
        apples.erase(now);
    }
}

void change_dir() {

    if (move_info.front().first == current_time) {
        if (move_info.front().second == 'L') {
            current_dir--;
            current_dir = (current_dir + 4) % 4;
            move_info.pop();
        }
        else if (move_info.front().second == 'D') {
            current_dir++;
            current_dir = (current_dir + 4) % 4;
            move_info.pop();
        }
        
    }
}
void solution() {

    snakes.push_back({ 1,1 });
    while (true) {
        current_time++;
        move();
        change_dir();
    }

}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    input();
    solution();
}