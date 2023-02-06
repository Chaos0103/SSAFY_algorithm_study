#include <iostream>

using namespace std;

int n;
int arr[10001];
int dp[10001];

void solution() {

	for (int i = 1; i <= n; i++) {
		dp[i] = arr[i];
		for (int j = 1; j <= i / 2; j++) {
			dp[i] = max(dp[i], dp[j] + dp[i - j]);
		}
	}

	cout << dp[n];
}

void input() {
	cin >> n;

	for (int i = 1; i <= n; i++) {
		cin >> arr[i];
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}