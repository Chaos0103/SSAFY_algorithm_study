#include <iostream>
#include <algorithm>
using namespace std;

int n;
int p[1001];
int sum[1001];

void solution() {

	int ans = 0;
	for (int i = 1; i <= n; i++) {
		ans += sum[i];
	}
	cout << ans;
}

void input() {
	cin >> n;

	for (int i = 1; i <= n; i++) {
		cin >> p[i];
	}

	sort(p + 1, p + n + 1);

	for (int i = 1; i <= n; i++) {
		cout << p[i] << "  ";
	}
	cout << "\n";
	for (int i = 1; i <= n; i++) {
		sum[i] = sum[i - 1] + p[i];
		cout << "sum: " << sum[i];
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}