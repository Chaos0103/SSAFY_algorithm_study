#include <iostream>

using namespace std;

string s1, s2;
int dp[1001][1001];
void input() {
	cin >> s1 >> s2;
}

void solution() {
	s1 = " " + s1;
	s2 = " " + s2;

	for (int i = 1; i < s1.size(); i++) {
		for (int j = 1; j < s2.size(); j++) {
			if (s1[i] == s2[j]) {
				dp[i][j] = dp[i - 1][j - 1] + 1;
			}
			else {
		//		cout << "i: " << i << " j: " << j << "\n";
				dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
	}


	cout << dp[s1.size() - 1][s2.size() - 1];
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}