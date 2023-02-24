#include <iostream>
#include <set>
#include <vector>
#include <algorithm>
using namespace std;

int n;

vector<int> student; //학생들 번호 저장
set<int> students[401];
int arr[21][21];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int anss[5] = { 0,1,10,100,1000 };

vector<pair<int,int>> small_one(int student_num) { //1번 조건을 지칭
	
	vector<pair<int, int>> tmp; //좋아하는 학생이 가장 인접한 칸에 많은 자리들

	int max_cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int cnt = 0;
			if (arr[i][j] == 0) { //칸이 비어있다면
				for (int k = 0; k < 4; k++) {
					//인접한 칸
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (0 <= nx && nx < n && 0 <= ny && ny < n) { //인접한 칸이 격자 내에 존재한다면
						int num = arr[nx][ny]; //해당 자리에 앉아있는 학생 혹은 0
						if (students[student_num].find(num) != students[student_num].end()) {
							//인접한 칸에 좋아하는 학생이 있다면
							cnt++;
						}
					}
				}
				if (cnt > max_cnt) { //만약 인접한 칸에 좋아하는 학생이 제일 많다면
					tmp.clear();
					tmp.push_back({ i,j });
					max_cnt = cnt;
				}
				else if (cnt == max_cnt) { //인접한 칸에 좋아하는 학생이 최대 수만큼 있다면
					tmp.push_back({ i,j });
				}
			}
			
		}
	}

	return tmp;
}

vector<pair<int,int>> small_two(vector<pair<int, int>> tmp) {

	int max_cnt = 0;

	vector<pair<int, int>> return_tmp; //빈칸이 가장 많은 자리들 리턴
	for (int i = 0; i < tmp.size(); i++) {
		int row = tmp[i].first; //행
		int col = tmp[i].second; //렬
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] == 0) {
				cnt++;
			}
		}
		if (cnt > max_cnt) { //주변에 빈칸이 제일 많은 칸을 찾았다면
			return_tmp.clear();
			return_tmp.push_back({ row, col });
			max_cnt = cnt;
		}
		else if (cnt == max_cnt) { //빈칸이 지금까지 찾은 빈칸 수만큼이라면
			return_tmp.push_back({ row, col });
		}
	}

	return return_tmp;
}

void score() { //점수매기기
	
	int ans = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int student_num = arr[i][j];
			int tmp = 0;
			for (int k = 0; k < 4; k++) { //인접한 칸
				int nx = i + dx[k];
				int ny = j + dy[k];
				if (0 <= nx && nx < n && 0 <= ny && ny < n) { //격자 내라면
					int num = arr[nx][ny]; //인접한 칸에 앉아있는 학생 번호
					if (students[student_num].find(num) != students[student_num].end()) { //내가 좋아하는 학생이라면
						//여기서 잠깐!
						//set으로 한 이유 - set이 검색이 빠르기 때문임 : 시간 복잡도 O(1) 
						tmp++;
					}
				}
			}
			ans += anss[tmp];
		}
	}
	cout << ans;
}

void solution() {

	for (int i = 0; i < student.size(); i++) {
		int student_num = student[i]; //학생 번호
		vector<pair<int, int>> tmp = small_one(student_num); 
		if (tmp.size() == 1) {
			arr[tmp[0].first][tmp[0].second] = student_num;
			continue;
		}
		//1을 만족하는 칸이 여러 개이면
		tmp = small_two(tmp);
		//2번을 만족하는 칸이 여러 개이든, 한 개이든 정렬하여 0번째 인덱스를 뽑으면 되므로
		sort(tmp.begin(), tmp.end());
		arr[tmp[0].first][tmp[0].second] = student_num;
	}
	score();
}

void input() {
	cin >> n;

	for (int i = 1; i <= n * n; i++) {
		int student_num;
		cin >> student_num;
		student.push_back(student_num); //학생들 번호 순서대로 저장
		for (int j = 0; j < 4; j++) {
			int num;
			cin >> num;
			students[student_num].insert(num); //학생들이 좋아하는 학생들 저장
		}
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input(); //입력
	solution(); //풀이
}