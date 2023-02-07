#include <iostream>
#include <set>
using namespace std;

int n;
set<int> arr1;
int m;
set<int> arr2;

void solution() {

}
void input() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		arr1.insert(num);
	}

	cin >> m;

	for (int i = 0; i < m; i++) {
		int num;
		cin >> num;
		if (arr1.find(num) != arr1.end()) {
			//원소가 있으면
			cout << 1 << "\n";
			continue; 
		}
		cout << 0 << "\n";
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
}