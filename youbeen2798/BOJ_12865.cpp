#include <iostream>
#define MAX 101
using namespace std;

int n, k;

int value[MAX];
int weight[MAX];
int dp[MAX][100001];


int solution() {

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= k; j++) {
			if (weight[i] <= j) {
				dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
			}
			else {
				dp[i][j] = dp[i - 1][j];
			}
		}
	}

	return dp[n][k];
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> k;

	for (int i = 1; i <= n; i++) {
		cin >> weight[i] >> value[i];
	}

	cout << solution();

}