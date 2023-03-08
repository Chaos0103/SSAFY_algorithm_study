#include <iostream>
#include <set>
#include <algorithm>

using namespace std;

int N; //�巡�� Ŀ�� ����
set<pair<int, int>> tmp;
set<pair<int, int>> points;
int dx[4] = { 0, 1, 0, 1};
int dy[4] = { 0, 0, 1, 1};
int start_x;
int start_y;
int ans = 0;

pair<int, int> make_zero_generation(pair<int, int> tmp, int d) {

	int x = tmp.first;
	int y = tmp.second;

	if (d == 0) { //x��ǥ�� �����ϴ� �����̸�
		return { x + 1, y };
	}
	else if (d == 1) { //y��ǥ�� �����ϴ� �����̸�
		return { x, y - 1 };
	}
	else if (d == 2) { //x��ǥ�� �����ϴ� �����̸�
		return { x - 1, y };
	}
	else if (d == 3) { //y��ǥ�� �����ϴ� �����̸�
		return { x, y + 1 };
	}
}

pair<int,int> make_generation(pair<int,int> end_point) { //{0,-2}
	set<pair<int, int>> real_tmp;
	pair<int, int> new_end_point;
	int diff = 0;
	for (auto i : tmp) {
		if (i.first == end_point.first && i.second == end_point.second) {
			continue;
		}

		//�Ÿ� ����
		int x_diff = end_point.first - i.first; //0
		int y_diff = end_point.second - i.second; //-1

		pair<int, int> new_point; //���ο� ��ǥ
		new_point.first = end_point.first + y_diff;
		new_point.second = end_point.second - x_diff;
				
		real_tmp.insert(new_point);
	}
	for (auto i : real_tmp) {
		tmp.insert({ i.first, i.second });
	}

	int tmp_x_diff = end_point.first - start_x; //0
	new_end_point.first = end_point.first + end_point.second - start_y;
	new_end_point.second = end_point.second + start_x - end_point.first;

	return new_end_point;
}

void check() {

	for (auto i : points) {
		int cnt = 0;
		for (int j = 0; j < 4; j++) {
			int nx = i.first + dx[j];
			int ny = i.second + dy[j];
			if (points.find({ nx,ny }) != points.end() && 0 <= nx && nx <= 100 && 0 <= ny && ny <= 100) {
				cnt++;
			}
		}
		if (cnt == 4) {
			ans++;
		}
	}
	cout << ans;
	
}

void move() {
	for (auto i : tmp) {
		points.insert({ i.first, i.second });
	}
}
void solution(int start_x, int start_y, int d, int g) {
	//start_x : ���� ��, start_y : ���� ��, int d: ����, int g: ����
	tmp.clear();
	pair<int, int> end_point; //����
	tmp.insert({ start_x, start_y }); //{4,2}

	//0����
	end_point = make_zero_generation({start_x, start_y}, d);
	tmp.insert(end_point); //1���� �߰�

	for (int i = 1; i <= g; i++) { //2���� �̻��̸�
		end_point = make_generation(end_point);
	}
	move();


}
void input() {
	cin >> N; //�巡�� Ŀ�� ����

	for (int i = 0; i < N; i++) {
		int d;
		int g;
		cin >> start_x >> start_y >> d >> g; //x�� y�� ���� ��, d�� ����, g�� ����
		solution(start_x, start_y, d, g); //3 3 0 1
	}
	check();
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
}