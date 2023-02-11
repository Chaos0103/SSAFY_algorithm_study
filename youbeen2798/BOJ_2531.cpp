#include <iostream>
#include <vector>
#include <set>

using namespace std;

int n; //회전 초밥 벨트에 놓인 접시의 수
int d; //초밥의 가짓수
int k; //연속해서 먹는 접시의 수
int c; //쿠폰의 수
int real_ans = 0;
int sushi[300001];
int check[3001];

void solution() {

	for (int i = 0; i < n; i++) {
		int coupon = 1; //쿠폰 아직 안 먹었으면 1
		int flag = 0; //초밥이 겹친 수
		for (int j = i; j < i + k; j++) {
			if (check[sushi[j % n]] == 1) {
				flag++;
			}
			else {
				check[sushi[j % n]] = 1;
			}
			if (sushi[j % n] == c) {
				//쿠폰이라면
				coupon = 0;
			}
		}

		memset(check, 0, sizeof(check));
		real_ans = max(real_ans, k - flag + coupon);
	}
}

void input() {

	cin >> n >> d >> k >> c;

	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		sushi[i] = num;
	}
}

int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
	cout << real_ans;
}