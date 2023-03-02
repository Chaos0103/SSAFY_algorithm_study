#include <iostream>
#include <vector>

using namespace std;

int fire_ball_size_num = 0;

struct fireball {
	int mi; //파이어볼 질량
	int si; //파이어볼 속력
	int di; //파이어볼 방향
};
int n, m, k;

vector<fireball> arr[51][51];
vector<fireball> tmp_arr[51][51];

int dx[8] = { -1,-1,0,1,1,1,0,-1 };
int dy[8] = { 0,1,1,1,0,-1,-1,-1 };

pair<int, int> check_over_n(int new_ri, int new_ci) {
	if (new_ri > n) {
		new_ri %= n;
		if (new_ri == 0) {
			new_ri = n;
		}
	}
	else if (new_ri == 0) {
		new_ri = n;
	}
	else if (new_ri < 0) {
		new_ri = n - abs(new_ri) % n;
	}

	if (new_ci > n) {
		new_ci %= n;
		if (new_ci == 0) {
			new_ci = n;
		}
	}
	else if (new_ci == 0) {
		new_ci = n;
	}
	else if (new_ci < 0) {
		new_ci = n - abs(new_ci) % n;
	}

	return { new_ri, new_ci };
}

void real_move_fireball(int ri, int ci) {
	//위치 (ri, ci)의 파이어볼을 si 속력으로 di만큼 이동시키기
	vector<fireball> tmp_fireball = arr[ri][ci];

	for (int i = 0; i < tmp_fireball.size(); i++) {
		int mi = tmp_fireball[i].mi;
		int si = tmp_fireball[i].si;
		int di = tmp_fireball[i].di;

		int new_ri = ri + si * dx[di];
		int new_ci = ci + si * dy[di];

		pair<int, int> p = check_over_n(new_ri, new_ci);
		tmp_arr[p.first][p.second].push_back({ mi, si, di });
	}
	
}
void move_fireballs() {
	//파이어볼 이동시키기
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			tmp_arr[i][j].clear();
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (arr[i][j].size() > 0) {
				//만약 파이어볼이 존재한다면
				real_move_fireball(i, j);
				arr[i][j].clear(); //원래 있던 파이어볼 제거
				//(i,j) 위치에 있는 fireball 이동시키기
			}
		}
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (tmp_arr[i][j].size() > 0) {
				arr[i][j].insert(arr[i][j].end(), tmp_arr[i][j].begin(), tmp_arr[i][j].end());
			}
		}
	}
}

void over_two_fireball(int row, int col) {

	int total_weight = 0;
	int total_speed = 0;
	bool all_odd = true;
	bool all_even = true;

	vector<fireball> v = arr[row][col]; //특정 행에 있는 파이어볼 모음
	arr[row][col].clear();

	for (int i = 0; i < v.size(); i++) {
		total_weight += v[i].mi; //질량 더하기
		total_speed += v[i].si; //속도 더하기

		if (v[i].di % 2 == 0) {
			//파이어볼 방향이 짝수라면
			all_odd = false; //모두 홀수인건 false
		}
		else if (v[i].di % 2 != 0) {
			//파이어볼 방향이 홀수라면
			all_even = false; //모두 짝수인건 false 
		}
	}

	int weight = total_weight / 5; //질량은 질량합 / 5
	int speed = total_speed / v.size(); //속력의 합 / 파이어볼 개수

	if (weight == 0) { //만약 질량이 0인 파이어볼이라면 소멸
		return; 
	}
	if (all_odd || all_even) { 
		//합쳐지는 파이어볼 방향이 모두 홀수이거나 모두 짝수이면
		for (int i = 0; i < 4; i++) {
			arr[row][col].push_back({ weight, speed, i * 2 });
		}
	}
	else {
		for (int i = 0; i < 4; i++) {
			arr[row][col].push_back({ weight, speed, i * 2 + 1 });
		}
	}
}

void after_move() {
	//이동이 모두 끝난뒤

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			//만약 2개 이상의 파이어볼이 있는 칸이라면
			if (arr[i][j].size() >= 2) {
				over_two_fireball(i,j);
			}
		}
	}
}

int get_weight_sum() {
	//남아있는 파이어볼 질량 합 구하기

	int weight = 0;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (arr[i][j].size() > 0) {
				for (int k = 0; k < arr[i][j].size(); k++) {
					weight += arr[i][j][k].mi;
				}
			}
		}
	}

	return weight;
}
void solution() {

	for (int i = 0; i < k; i++) {
		move_fireballs();
		after_move();
	}
	
	cout << get_weight_sum();
}
void input() {
	cin >> n >> m >> k;

	for (int i = 0; i < m; i++) {
		int ri; //파이어볼 행 위치
		int ci; //파이어볼 열 위치
		int mi; //질량
		int si; //속력
		int di; //방향
		cin >> ri >> ci >> mi >> si >> di;
		arr[ri][ci].push_back({ mi,si,di });
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}