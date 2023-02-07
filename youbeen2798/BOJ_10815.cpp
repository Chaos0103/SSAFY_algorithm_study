#include <iostream>
#include <set>
using namespace std;

int n;
set<int> s;
int m;

void input() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		s.insert(num);
	}

	cin >> m;

	for (int i = 0; i < m; i++) {
		int num;
		cin >> num;
		if (s.find(num) != s.end()) {
			cout << 1 << " ";
			continue;
		}
		cout << 0 << " ";
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
}