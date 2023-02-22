#include <iostream>
#include <deque>
#include <set>

using namespace std;

int t;
int n, k;
string st;
deque<char> dq;

void solution() {

	dq.clear();

	for (int i = 0; i < st.size(); i++) {
		dq.push_back(st[i]);
	}

	for (int i = 0; i < k; i++) {
		char c = dq.back();
		dq.pop_back();
		dq.push_front(c);
	}

	set<string> dq2;

	for (int i = 0; i < k; i += 3) {
		string st;
		for (int j = i; j <= i + 2; j++) {
			st += dq[j];
		}
		dq2.insert(st);
	}

	for (auto i : dq2) {
		cout << "i: " << i << "\n";
	}
}
void input() {
	cin >> t;

	for (int tc = 1; tc <= t; tc++) {
		cin >> n >> k;
		cin >> st;
		solution();
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}