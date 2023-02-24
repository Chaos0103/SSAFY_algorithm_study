#include <iostream>
#include <deque>
#include <queue>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int t;
int n, k;
string st;
deque<char> dq;
vector<string> v;
vector<int> nums;

int string_to_int(string st) {

	long num = 0;
	int idx = 1;
	int one_side_size = n / 4;
	for (int i = 0; i < st.size(); i++) {
		char c = st[i];
		if ('0' <= c && c <= '9') {
			num += (c - '0') * pow(16, one_side_size - idx++);
			continue;
		}
		num += (c - 'A' + 10) * pow(16, one_side_size - idx++);
	}
	return num;
}
void put_s(deque<char> dq) {

	int one_side_size = n / 4;

	for (int i = 0; i < dq.size(); i += one_side_size) {
		string st;
		int num = 0;
		int idx = 1;
		for (int j = i; j < i + one_side_size; j++) {
			st += dq[j];
		}
		v.push_back(st);
	}
}
int solution() {

	v.clear();
	dq.clear();
	nums.clear();

	for (int i = 0; i < st.size(); i++) {
		dq.push_back(st[i]);
	}

	put_s(dq);
	for (int i = 0; i < k; i++) {
		char c = dq.back();
		dq.pop_back();
		dq.push_front(c);
		put_s(dq);
	}

	sort(v.rbegin(), v.rend());
	v.erase(unique(v.begin(), v.end()), v.end());

	for (int i = 0; i < v.size(); i++) {
		nums.push_back(string_to_int(v[i]));
	}

	return nums[k - 1];
}
void input() {
	cin >> t;

	for (int tc = 1; tc <= t; tc++) {
		cin >> n >> k;
		cin >> st;
		cout << "#" << tc << " " << solution() << "\n";
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
}

