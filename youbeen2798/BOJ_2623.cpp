#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n, m; // 가수 수, 보조 pd 수
int entrance[1001];
vector<int> singer[1001];
vector<int> singer_order;
queue<int> q;
int number_of_singer;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        cin >> number_of_singer;

        vector<int> this_case;

        while (number_of_singer--) {
            int singer_number;
            cin >> singer_number;
            this_case.push_back(singer_number);
        }

        for (int j = 0; j < this_case.size(); j++) {
            for (int k = j + 1; k < this_case.size(); k++) {
                // 후순위 가수들을 저장
                singer[this_case[j]].push_back(this_case[k]);
                entrance[this_case[k]]++;
            }
        }
    }

    for (int i = 1; i <= n; i++) {
        if (entrance[i] == 0)
            q.push(i);
    }


    while (!q.empty()) {
        int cur_singer = q.front();
        q.pop();
        singer_order.push_back(cur_singer);

        for (int i = 0; i < singer[cur_singer].size(); i++) {
            int next_singer = singer[cur_singer][i];
            entrance[next_singer]--;
            if (entrance[next_singer] == 0)
                q.push(next_singer);
        }
    }

    if (singer_order.size() != n)
        cout << "0\n";
    else {
        for (int i = 0; i < singer_order.size(); i++) {
            cout << singer_order[i] << '\n';
        }
    }
    return 0;
}