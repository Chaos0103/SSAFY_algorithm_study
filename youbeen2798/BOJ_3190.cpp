#include <iostream>
#include <set>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int n, k, l;
long current_time = 0; //현재 시간

int current_head_row = 1; //현재 머리 행
int current_head_col = 1; //현재 머리 열

int current_dir = 0; //현재 방향
set<pair<int, int>> apples; //사과 위치
queue<pair<int, char>> move_info; //뱀의 방향 변환 정보
vector<pair<int, int>> snakes; //뱀 위치 정보

void input() {
    cin >> n >> k; //보드의 크기, 사과의 개수

    for (int i = 0; i < k; i++) {
        int row, col;
        cin >> row >> col;
        apples.insert({ row, col });
    }

    cin >> l; //뱀의 방향 변환 횟수

    for (int i = 0; i < l; i++) {
        int sec;
        char dir;
        cin >> sec >> dir;
        move_info.push({ sec, dir });
    }
}

void move() {

    switch (current_dir) {
    case 0: //동
        current_head_col++;
        break;
    case 1: //남
        current_head_row++;
        break;
    case 2: //서
        current_head_col--;
        break;
    case 3: //북
        current_head_row--;
        break;
    }

    //범위를 넘어버리면
    if (current_head_row < 1 || current_head_col < 1 || n < current_head_row || n < current_head_col) {
        cout << current_time;
        exit(0);
    }
    //자기 몸에 닿아버리면
    pair<int, int> now = { current_head_row, current_head_col };
    if (find(snakes.begin(), snakes.end(), now) != snakes.end()) {
        cout << current_time;
        exit(0);
    }

    snakes.push_back(now);

    //움직인 곳에 사과가 없다면 꼬리 빼기
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