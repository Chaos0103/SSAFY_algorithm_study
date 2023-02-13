#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

vector <int> premium(int m) { //2�� m������ �Ҽ��� ��ȯ���ִ� �Լ� 

	vector <bool> v(m + 1);

	for (int i = 2; i <= sqrt(m); i++) {
		if (v[i] == 1) {
			continue;
		}
		for (int j = 2 * i; j <= m; j += i) {
			v[j] = 1;
		}
	} //�Ҽ��� 0, �ƴϸ� 1

	vector <int> vs;
	for (int i = 2; i <= m; i++) {
		if (v[i] == 0) {  //�Ҽ��� �ƴ� �͵鸸
			vs.push_back(i); //���� vs�� ����
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
	ios_base::sync_with_stdio(0); //����� ������
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n; //���� n �Է¹ޱ�

	vector <int> vs = premium(n);

	cout << count(vs, n);

}