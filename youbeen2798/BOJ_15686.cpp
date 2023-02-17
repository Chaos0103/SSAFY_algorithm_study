#include <iostream>
#include <vector> 
using namespace std;

int n, m;
int arr[51][51];

vector<pair<int, int>> chickens;
vector<pair<int, int>> homes;

long min_dist = 99999999;

void input() {
	//0은 빈칸, 1은 집, 2는 치킨집

	cin >> n >> m; //크기와 개수

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 2) { //치킨 집이라면
				chickens.push_back({ i,j });
			}
			else if (arr[i][j] == 1) {
				homes.push_back({ i, j });
			}
		}
	}
}

void dist(vector<pair<int, int>> chickens) {

	int total_dist = 0;

	for (int i = 0; i < homes.size(); i++) {
		int tmp = 999999;
		for (int j = 0; j < chickens.size(); j++) {
			tmp = min(tmp, abs(homes[i].first - chickens[j].first) + abs(homes[i].second - chickens[j].second));
		}
		//		cout << "tmp: " << tmp << "\n";
		total_dist += tmp;
	}

	//	cout << "##total_dist: " << total_dist << "\n";
	//	cout << "##min_dist: " << min_dist << "\n";

	if (total_dist <= min_dist) {
		min_dist = total_dist;
	}

}
void combination(vector <pair<int, int>> arr, vector<pair<int, int>> comb, int r, int index, int depth) {

	if (r == 0) {
		//치킨집을 골라줌
//		cout << "----------" << "\n";
//		for (int i = 0; i < comb.size(); i++) {
//			cout << "( " << comb[i].first << ", " << comb[i].second  << ")" << "\n";
//		}
		dist(comb);
	}
	else if (depth == arr.size()) {
		return;
	}
	else {
		comb[index] = arr[depth];
		combination(arr, comb, r - 1, index + 1, depth + 1);

		combination(arr, comb, r, index, depth + 1);
	}
}

void solution() {
	//	cout << "chicken.size: " << chickens.size() << "\n";

	vector<pair<int, int>> comb(m);
	combination(chickens, comb, m, 0, 0);
	//	for (int i = 1; i <= chickens.size(); i++) {
	//		vector<pair<int, int>> comb(i);
	//		cout << "#################" << "\n";
	//		combination(chickens, comb, i, 0, 0);
	//	}
	cout << min_dist;
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}