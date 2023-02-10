#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

vector <int> premium(int m) { //2와 m사이의 소수를 반환해주는 함수 

	vector <bool> v(m + 1);

	for (int i = 2; i <= sqrt(m); i++) {
		if (v[i] == 1) {
			continue;
		}
		for (int j = 2 * i; j <= m; j += i) {
			v[j] = 1;
		}
	} //소수면 0, 아니면 1

	vector <int> vs;
	for (int i = 2; i <= m; i++) {
		if (v[i] == 0) {  //소수가 아닌 것들만
			vs.push_back(i); //벡터 vs에 저장
		}
	}
	return vs;
}


int count(vector <int> vs, int n) {

	int sum = 0;
	int ptrA = -1;
	int ptrB = -1;
	int count = 0;

	while (1) {
		if (sum < n) {
			if (ptrB + 1 == vs.size())
				break;
			ptrB++;
			sum += vs[ptrB];

			if (sum == n) {
				count++;
			}
		}
		else {
			if (ptrA + 1 == vs.size())
				break;

			ptrA++;
			sum -= vs[ptrA];

			if (sum == n) {
				count++;
			}
		}
	}

	return count;
}




int main() {
	ios_base::sync_with_stdio(0); //입출력 빠르게
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n; //정수 n 입력받기

	vector <int> vs = premium(n);

	cout << count(vs, n);

}