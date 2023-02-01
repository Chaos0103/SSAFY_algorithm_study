#include <iostream>
#include <queue>
using namespace std;

using namespace std;

int n, k;
bool visited[100001];


void solution() {
	queue<pair<int,int>> q;
	q.push({ n,0 });

	visited[n] = true;

	while (!q.empty()) {
		int a = q.front().first;
		int cnt = q.front().second;

	//	cout << "a: " << a << "\n";
		if (a == k) {
			cout <<  cnt << "\n";
			break;
		}
		q.pop();

		if (!visited[a - 1] && a -1 >= 0 && a - 1 <= 100000) {
			q.push({ a - 1, cnt + 1 });
			visited[a - 1] = true;
		}
		if (!visited[a + 1] && a + 1 >= 0 && a + 1 <= 100000) {
			q.push({ a + 1, cnt + 1 });
			visited[a + 1] = true;
		}
		if (!visited[2 * a] && 2 * a >= 0 && 2 * a <= 100000) {
			q.push({ 2 * a, cnt + 1 });
			visited[2 * a] = true;
		}
	}
}

void input() {
	cin >> n >> k;
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}