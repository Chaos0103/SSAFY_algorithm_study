#include <iostream>
#include <vector>
using namespace std;

int n; //도시의 개수
int m; //도시들의 수

int arr[1001];
int parent[1001];

vector<int> travel_city;

int get_parent(int num) {
	if (num == parent[num]) {
		return num;
	}

	return parent[num] = get_parent(parent[num]);
}
void union_find(int x, int y) {

	x = get_parent(x);
	y = get_parent(y);

	if (x == y) {
		return;
	}

	if (x > y) {
		parent[x] = y;
		return;
	}
	
	parent[y] = x;
}
void input() {
	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		parent[i] = i;
	}

	for (int i = 1; i <= n; i++) {
		int num;
		for (int j = 1; j <= n; j++) {
			cin >> num;

			if (num == 1) {
				union_find(i, j);
			}
		}
	}

	for (int i = 0; i < m; i++) {
		int num;
		cin >> num;
		travel_city.push_back(num);
	}
}

void solution() {
	
	int tmp_num = get_parent(travel_city[0]);

	for (int i = 1; i < travel_city.size(); i++) {
		if (get_parent(travel_city[i]) != tmp_num) {
			cout << "NO";
			exit(0);
		}
	}

	cout << "YES";
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}