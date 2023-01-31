#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int l, c;
vector<char> arr;
set<string> ans;

void input() {
	
	cin >> l >> c;

	for (int i = 0; i < c; i++) {
		char c;
		cin >> c;
		arr.push_back(c);
	}
}

bool check_moum(char c) {
	if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
		return true;
	}
	return false;
}
void Combination(vector<char> arr, vector<char> comb, int r, int idx, int depth) {
	if (r == 0) {
		sort(comb.begin(), comb.end());
		
		string st = "";
		int moum_num = 0;
		int zaum_num = 0;
		for (int i = 0; i < comb.size(); i++) {
			if (check_moum(comb[i])) {
				moum_num++;
			}
			else {
				zaum_num++;
			}
			st += comb[i];
		}
		if (moum_num >= 1 && zaum_num >= 2) {
			ans.insert(st);
		}
	}
	else if (depth == arr.size()) {
		return;
	}
	else {
		comb[idx] = arr[depth];
		Combination(arr, comb, r - 1, idx + 1, depth + 1);

		Combination(arr, comb, r, idx, depth + 1);
	}
}
void solution() {
	vector<char> comb(l);
	Combination(arr, comb, l, 0, 0);

	for (auto s : ans) {
		cout << s << "\n";
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}
