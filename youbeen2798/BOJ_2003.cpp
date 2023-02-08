#include <iostream>

using namespace std;

int n, m;
int arr[10001];
int sum[10001];
int ans = 0;

//4 2
//1 1 1 1
//1 2 3 4
void solution() {

	for (int i = 0; i < n; i++) {
		//0 ~ 3
		for (int j = i + 1; j <= n; j++) {
			if (sum[j] - sum[i] == m) {
				ans++;
			}
		}
	}

	cout << ans;
}

void input() {
	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		cin >> arr[i];
		sum[i] = sum[i - 1] + arr[i];
	}
}
int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}