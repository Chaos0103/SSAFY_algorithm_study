#include <iostream>
#include <algorithm>

using namespace std;

int n;
int arr[100001];
int x;
int ans = 0;

void input() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	sort(arr, arr + n);

	cin >> x;
}

void solution() {

	int start = 0;
	int endd = n - 1;

	while (start < endd) {
		int sum = arr[start] + arr[endd];

		if (sum == x) {
			ans++;
			start++;
			endd--;
		}
		else if (sum < x) {
			start++;
		}
		else {
			endd--;
		}
	}
	cout << ans;
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}