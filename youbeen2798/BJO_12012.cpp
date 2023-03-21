#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n;
int arr[1000001];
vector<int> v;

void binary_search(int num) {
	int low = 0, high = v.size() - 1, mid;
	int ret = 1000000007;
	while (low <= high) {
		mid = (low + high) / 2;
		int here_num = v[mid];
		if (here_num >= num) {
			if (ret > mid)
				ret = mid;
			high = mid - 1;
		}
		else
			low = mid + 1;
	}

	v[ret] = num;
}

void solution() {
	//v = 10 20
	v.push_back(arr[0]);

	for (int i = 1; i < n; i++) {
		for (int j = 0; j < v.size(); j++) {
			if (v.back() < arr[i]) { 
				v.push_back(arr[i]);
				continue;
			}
			binary_search(arr[i]);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	solution();
	cout << v.size();

}