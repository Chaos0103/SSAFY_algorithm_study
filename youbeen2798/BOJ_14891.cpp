#include <iostream>
#include <deque>
#include <vector>

using namespace std;

deque <char> dq[4];

int k;

void real_rotate(int num, int dir) {


    if (dir == 1) {
        //시계 방향이라면
        int back = dq[num].back();
        dq[num].pop_back();
        dq[num].push_front(back);
    }
    else if (dir == -1) {
        //반시계방향이라면
        int front = dq[num].front();
        dq[num].pop_front();
        dq[num].push_back(front);
    }
}
void rotate(int num, int dir) {

    vector<pair<int, int>> move_orders;//이동 순서

    move_orders.push_back({ num - 1, dir });

    if (num == 1) {

        for (int i = 0; i <= 2; i++) {
            if (dq[i][2] != dq[i + 1][6]) {
                dir *= -1;
                move_orders.push_back({ i + 1, dir });
                continue;
            }
            break;
        }
    }
    else if (num == 2) {
        if (dq[0][2] != dq[1][6]) { //0번과 1번이 맞물리면
            move_orders.push_back({ 0, dir * -1 });
        }
        //2번부터 4번
        for (int i = 1; i <= 2; i++) {
            if (dq[i][2] != dq[i + 1][6]) {
                dir *= -1;
                move_orders.push_back({ i + 1, dir });
                continue;
            }
            break;
        }
    }
    else if (num == 3) {

        //3번과 4번이 맞물리면
        if (dq[2][2] != dq[3][6]) {
            move_orders.push_back({ 3, dir * -1 });
        }
        for (int i = 2; i > 0; i--) {
            if (dq[i][6] != dq[i - 1][2]) {
                dir *= -1;
                move_orders.push_back({ i - 1, dir });
                continue;
            }
            break;
        }
    }

    else if (num == 4) {

        for (int i = 3; i > 0; i--) {
            if (dq[i][6] != dq[i - 1][2]) {
                dir *= -1;
                move_orders.push_back({ i - 1, dir });
                continue;
            }
            break;
        }
    }


    for (int i = 0; i < move_orders.size(); i++) {
        //     cout << move_orders[i].first << " , " << move_orders[i].second << "\n";
        real_rotate(move_orders[i].first, move_orders[i].second);
    }
}


void input() {
    for (int i = 0; i < 4; i++) {
        string input;
        cin >> input;

        for (int j = 0; j < 8; j++) {
            dq[i].push_back(input[j]);
        }
    }

    cin >> k;

    for (int i = 0; i < k; i++) {
        {
            int num, dir;
            cin >> num >> dir;

            rotate(num, dir);
        }
    }
}



void output() {

    int ans = 0;

    int tmp_num = 1;
    for (int i = 0; i < 4; i++) {
        if (dq[i][0] == '1') { //S극이면
            ans += tmp_num;
            //       cout << "ans: " << ans << " tmp_num: " << tmp_num << "\n";
        }
        tmp_num *= 2;
    }
    cout << ans;

}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    input();
    output();

}
