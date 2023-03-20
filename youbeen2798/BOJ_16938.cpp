#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, l, r, x;
int problem[16];
bool selected[16];
int answer = 0;
vector<int> selectedProblems;

int MAXP = 10e6 + 1;
int MINP = -1;


void SearchProblem(int curIndex, int problemCount, int sum) {
    if (problemCount >= 2) {
        int minLevel = MAXP;
        int maxLevel = MINP;

        for (int i = 0; i < selectedProblems.size(); i++) {
            minLevel = min(minLevel, selectedProblems[i]);
            maxLevel = max(maxLevel, selectedProblems[i]);
        }
        int problemDist = maxLevel - minLevel;

        if (l <= sum && sum <= r && problemDist >= x) {
            answer++;
        }
    }

    if (sum > r) return;

    for (int i = curIndex; i < n; i++) {
        if (selected[i]) continue;

        selected[i] = true;
        selectedProblems.push_back(problem[i]);
        SearchProblem(i, problemCount + 1, sum + problem[i]);
        selectedProblems.pop_back();
        selected[i] = false;
    }
}


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> l >> r >> x;

    for (int i = 0; i < n; i++)
        cin >> problem[i];


    SearchProblem(0, 0, 0);


    cout << answer << endl;
}