#include <iostream>
#include <set>
#include <vector>
#include <algorithm>
using namespace std;

int n;

vector<int> student; //�л��� ��ȣ ����
set<int> students[401];
int arr[21][21];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int anss[5] = { 0,1,10,100,1000 };

vector<pair<int,int>> small_one(int student_num) { //1�� ������ ��Ī
	
	vector<pair<int, int>> tmp; //�����ϴ� �л��� ���� ������ ĭ�� ���� �ڸ���

	int max_cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int cnt = 0;
			if (arr[i][j] == 0) { //ĭ�� ����ִٸ�
				for (int k = 0; k < 4; k++) {
					//������ ĭ
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (0 <= nx && nx < n && 0 <= ny && ny < n) { //������ ĭ�� ���� ���� �����Ѵٸ�
						int num = arr[nx][ny]; //�ش� �ڸ��� �ɾ��ִ� �л� Ȥ�� 0
						if (students[student_num].find(num) != students[student_num].end()) {
							//������ ĭ�� �����ϴ� �л��� �ִٸ�
							cnt++;
						}
					}
				}
				if (cnt > max_cnt) { //���� ������ ĭ�� �����ϴ� �л��� ���� ���ٸ�
					tmp.clear();
					tmp.push_back({ i,j });
					max_cnt = cnt;
				}
				else if (cnt == max_cnt) { //������ ĭ�� �����ϴ� �л��� �ִ� ����ŭ �ִٸ�
					tmp.push_back({ i,j });
				}
			}
			
		}
	}

	return tmp;
}

vector<pair<int,int>> small_two(vector<pair<int, int>> tmp) {

	int max_cnt = 0;

	vector<pair<int, int>> return_tmp; //��ĭ�� ���� ���� �ڸ��� ����
	for (int i = 0; i < tmp.size(); i++) {
		int row = tmp[i].first; //��
		int col = tmp[i].second; //��
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] == 0) {
				cnt++;
			}
		}
		if (cnt > max_cnt) { //�ֺ��� ��ĭ�� ���� ���� ĭ�� ã�Ҵٸ�
			return_tmp.clear();
			return_tmp.push_back({ row, col });
			max_cnt = cnt;
		}
		else if (cnt == max_cnt) { //��ĭ�� ���ݱ��� ã�� ��ĭ ����ŭ�̶��
			return_tmp.push_back({ row, col });
		}
	}

	return return_tmp;
}

void score() { //�����ű��
	
	int ans = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int student_num = arr[i][j];
			int tmp = 0;
			for (int k = 0; k < 4; k++) { //������ ĭ
				int nx = i + dx[k];
				int ny = j + dy[k];
				if (0 <= nx && nx < n && 0 <= ny && ny < n) { //���� �����
					int num = arr[nx][ny]; //������ ĭ�� �ɾ��ִ� �л� ��ȣ
					if (students[student_num].find(num) != students[student_num].end()) { //���� �����ϴ� �л��̶��
						//���⼭ ���!
						//set���� �� ���� - set�� �˻��� ������ ������ : �ð� ���⵵ O(1) 
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
		int student_num = student[i]; //�л� ��ȣ
		vector<pair<int, int>> tmp = small_one(student_num); 
		if (tmp.size() == 1) {
			arr[tmp[0].first][tmp[0].second] = student_num;
			continue;
		}
		//1�� �����ϴ� ĭ�� ���� ���̸�
		tmp = small_two(tmp);
		//2���� �����ϴ� ĭ�� ���� ���̵�, �� ���̵� �����Ͽ� 0��° �ε����� ������ �ǹǷ�
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
		student.push_back(student_num); //�л��� ��ȣ ������� ����
		for (int j = 0; j < 4; j++) {
			int num;
			cin >> num;
			students[student_num].insert(num); //�л����� �����ϴ� �л��� ����
		}
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input(); //�Է�
	solution(); //Ǯ��
}