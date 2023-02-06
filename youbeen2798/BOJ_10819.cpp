#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int n;
int ans = 0;

vector<int> v;

void input() {
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		v.push_back(num);
	}
}

void solution() {
	
	sort(v.begin(), v.end());

	do {
		int tmp_ans = 0;
		for (int i = 0; i < v.size() - 1; i++) {
			tmp_ans += abs(v[i + 1] - v[i]);
//			cout << v[i] << " ";
		}
		ans = max(tmp_ans, ans);
//		cout << "\n";
	} while (next_permutation(v.begin(), v.end()));

	cout << ans;
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}