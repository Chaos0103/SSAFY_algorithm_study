#include <iostream>

using namespace std;

int n, k;
int arr[100001];
long long sum[100001]; 

void solution() {

	long long ans = -9999999;

	for (int i = k; i <= n; i++) {
		long tmp_ans = sum[i] - sum[i - k];

		if (tmp_ans > ans) {
			ans = tmp_ans;
		}
	}

	cout << ans;
}

void input() {
	cin >> n >> k;

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