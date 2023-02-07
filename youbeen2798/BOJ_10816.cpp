#include <iostream>
#include <algorithm>
using namespace std;

int n, m;
int arr[500001];

int lower_idx(int target, int len) {
	int st = 0;
	int en = len;

	while (st < en) {
		int mid = (st + en) / 2;
		if (arr[mid] >= target) {
			en = mid;
		}
		else {
			st = mid + 1;
		}
	}
	return st;
}

int upper_idx(int target, int len) {
	int st = 0;
	int en = len;

	while (st < en) {
		int mid = (st + en) / 2;
		if (arr[mid] > target) {
			en = mid;
		}
		else {
			st = mid + 1;
		}
	}
	return st;
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	sort(arr, arr + n);

	cin >> m;

	while (m--) {
		int k;
		cin >> k;

		cout << upper_idx(k, n) - lower_idx(k, n) << " ";
	}
}