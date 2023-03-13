#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
#include <cmath>
#include <string>
using namespace std;

#define MAX_N 21
#define INF 1e9
int dp[MAX_N][MAX_N];
int dp2[MAX_N][MAX_N];

int N;

int main() {
    ios::sync_with_stdio(false); 
    cin.tie(0); 
    cout.tie(0);


    cin >> N;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            int data; 
            cin >> data;
            dp[i][j] = data; 
            dp2[i][j] = data; 
    }

    for (int m = 0; m < N; m++) {
        for (int s = 0; s < N; s++) {
            for (int e = 0; e < N; e++) {
                if (s == m || e == m) continue;
                if (dp[s][e] == dp[s][m] + dp[m][e]) {
                    dp2[s][e] = 0;
                }
                else if (dp[s][e] > dp[s][m] + dp[m][e]) {
                    cout << -1 << "\n";
                    return 0;
                }

            }
        }
    }
    int total = 0;

    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
            total += dp2[i][j];
        }
    }
    cout << total << "\n";


}