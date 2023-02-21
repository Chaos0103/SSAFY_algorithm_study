#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m;
int parent[100001];

int ans = 0;

struct info {
	int home1;
	int home2;
	int dist;

	bool operator < (const info& d) const {
		return dist < d.dist;
	}
};

vector<info> v;
vector<int> dist_ans;

int get_parent(int num) {

	if (num == parent[num]) {
		return num;
	}

	return parent[num] = get_parent(parent[num]);
}
void union_find(int num1, int num2, int dist) {

	num1 = get_parent(num1); //1
	num2 = get_parent(num2); //5

	if (num1 == num2) {
		return;
	}
	else if (num1 < num2) { //num2의 부모가 더 크다면
		parent[num2] = num1;
	}
	else if (num2 < num1) { //num1의 부모가 더 크다면
		parent[num1] = num2;
	}

	dist_ans.push_back(dist);
}
void input() {
	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		parent[i] = i;
	}

	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		v.push_back({ a,b,c });
	}

	sort(v.begin(), v.end());

	for (int i = 0; i < m; i++) {
		union_find(v[i].home1, v[i].home2, v[i].dist);
	}

	for (int i = 0; i < dist_ans.size() - 1; i++) {
		ans += dist_ans[i];
	}
	cout << ans;
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
}

