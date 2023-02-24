#include <iostream>

using namespace std;

int n, m;
int arr[501][501];
int real_ans = 0;

pair<int, int> dxdy[19][4] = {
	{{0,0}, {0,1}, {0,2}, {0,3}}, //1
	{{0,0}, {1,0}, {2,0}, {3,0}}, //2
	{{0,0}, {1,0}, {0,1}, {1,1}}, //3
	{{0,0}, {1,0}, {2,0}, {2,1}}, //4
	{{0,0}, {0,1}, {0,2}, {-1,2}}, //5
	{{0,0}, {1,0}, {1,1}, {1,2}}, //6
	{{0,0}, {0,1}, {0,2}, {1,2}}, //7
	{{0,0}, {0,1}, {-1,1}, {-2,1}}, //8
	{{0,0}, {0,1}, {0,2}, {1,0}}, //9
	{{0,0}, {1,0}, {1,1}, {2,1}}, //10
	{{0,0}, {1,0}, {0,1}, {-1,1}}, //11
	{{0,0}, {0,1}, {-1,1}, {-1,2}}, //12
	{{0,0}, {0,1}, {1,1}, {1,2}}, //13
	{{0,0}, {1,0}, {1,1}, {2,0}}, //14
	{{0,0}, {0,1}, {0,2}, {1,1}}, //15
	{{0,0}, {0,1}, {-1,1}, {1,1}}, //16
	{{0,0}, {0,1}, {-1,1}, {0,2}}, //17
	{{0,0}, {0,1}, {1,1}, {2,1}}, //18
	{{0,0}, {0,1}, {1,0}, {2,0}}

};

int mini_solution(int x, int y) {

//	cout << "##########" << "\n";

//	cout << "x: " << x << " y: " << y << "\n"; 
	int tmp_ans = 0;

	for (int i = 0; i < 19; i++) {
		bool possible = true;
		int mini_tmp_ans = 0;
//		cout << "/////////////" << "\n";

		for (int j = 0; j < 4; j++) {

	//		cout << "dx: " << dxdy[i][j].first;
	//		cout << "dy: " << dxdy[i][j].second;

			int nx = x + dxdy[i][j].first;
			int ny = y + dxdy[i][j].second;

	//		cout << "nx: " << nx << " ny: " << ny << "\n";
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
	//			cout << "##nx: " << nx << " ny: " << ny << "\n";
				mini_tmp_ans += arr[nx][ny];
			}
			else {
				possible = false;
			}
		}
	//	cout << "mini_tmp_ans: " << mini_tmp_ans << "\n";
		if (possible) {
			tmp_ans = max(tmp_ans, mini_tmp_ans);
		}
	}

	return tmp_ans;
}
void solution() {

	//mini_solution(0, 4);

	 
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
	//		int num = mini_solution(i, j);
	//		cout << "i: " << i << " j: " << j << " num: " << num << "\n";
			real_ans = max(real_ans, mini_solution(i, j));
		}
	}
	cout << real_ans;
	
}

void input() {
	cin >> n >> m; //세로 크기와 가로 크기

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}
