#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int n, m; //���� �Ѻ��� ũ��, ������ ����
int arr[21][21]; //����
int visited[21][21];
int score = 0;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int cnt = 0;

pair<pair<int, int>, pair<int, int>> bfs(int x, int y) {
	//���� ��� x,y && ������� ������, ������ ���

	memset(visited, false, sizeof(visited));

	queue <pair<int, int>> q;
	q.push({ x,y });
	int num = arr[x][y]; //��� ����
	visited[x][y] = cnt;

	int muzigae_num = 0;
	int total_num = 1;

	vector<pair<int, int>> general_blocks; //�Ϲ� ��� ����
	general_blocks.push_back({ x,y });

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < n && visited[nx][ny] != cnt) {

				if (arr[nx][ny] == num) { //�Ϲ� ����̸�
					q.push({ nx,ny });
					general_blocks.push_back({ nx,ny }); //�Ϲ� ��� ������ �߰�
					total_num++; //��ü ����
					visited[nx][ny] = cnt;
				}
				else if (arr[nx][ny] == 0) { //������ ����̸�
					q.push({ nx,ny });
					visited[nx][ny] = cnt;
					muzigae_num++; //������ ����
					total_num++; //��ü ����
				}
			}
		}
	}

	sort(general_blocks.begin(), general_blocks.end());

	pair<int, int> gizun_block = general_blocks[0]; //���� ���

	return { gizun_block, { total_num, muzigae_num } };
}

void bfs2(int x, int y) {

	queue<pair<int, int>> q;

	q.push({ x,y });
	visited[x][y] = true;
	int num = arr[x][y];
	arr[x][y] = -2;
	int tmp_num = 1;

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
				if (arr[nx][ny] == num || arr[nx][ny] == 0) {
					arr[nx][ny] = -2; //����
					q.push({ nx,ny });
					visited[nx][ny] = true;
					tmp_num++;
				}
			}
		}
	}

	score += tmp_num * tmp_num;

}

void print() {

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}

}
void find_biggest_block_group() {
	//ũ�Ⱑ ���� ū ��� �׷��� ã�´�.

	memset(visited, false, sizeof(visited));

	int x;
	int y;
	pair<int, int> gizun_block = { -1,-1 };
	int total_num = -1;
	int muzigae_num = -1;

	cnt = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (1 <= arr[i][j] && arr[i][j] <= m && visited[i][j] == 0) {
				//�湮���� ����, �Ϲ� ���
				cnt++;
				pair<pair<int, int>, pair<int, int>> p = bfs(i, j);
				pair<int, int> tmp_gizun_block = p.first; //���� ���
				int tmp_total_num = p.second.first; //��ä ����
				int tmp_muzigae_num = p.second.second; //������ ����
				if (tmp_total_num == 1) { //���� ������ 1�����
					continue;
				}
				if (total_num == -1 || total_num < tmp_total_num) {
					//��ü ��� ������ �۰ų�
					gizun_block = tmp_gizun_block;
					total_num = tmp_total_num;
					muzigae_num = tmp_muzigae_num;
					x = i;
					y = j;
				}
				else if (total_num == tmp_total_num) {
					//��ü ��� ������ ���ٸ�
					if (tmp_muzigae_num > muzigae_num) {
						gizun_block = tmp_gizun_block;
						total_num = tmp_total_num;
						muzigae_num = tmp_muzigae_num;
						x = i;
						y = j;
					}
					else if (tmp_muzigae_num == muzigae_num) {
						//������ ������ ���ٸ�
						if (tmp_gizun_block > gizun_block) {
							gizun_block = tmp_gizun_block;
							total_num = tmp_total_num;
							muzigae_num = tmp_muzigae_num;
							x = i;
							y = j;
						}
					}
				}
			}
		}
	}

	if (total_num == -1) {
		cout << score;
		exit(0);
	}
	memset(visited, false, sizeof(visited));
	bfs2(x, y);
}

pair<int, int> down(int x, int y) {

	pair<int, int> start = { x,y };

	for (int i = x + 1; i < n; i++) {
		if (arr[i][y] != -2) { //������ ��� Ȥ�� �Ϲ� ��� Ȥ�� ������ ����� �ִٸ�
			return start;
		}
		start = { i,y };
	}
	return start;
}
void gravity() {
	for (int j = 0; j < n; j++) {
		for (int i = n - 1; i >= 0; i--) {
			int num = arr[i][j];
			if (num == -1 || num == -2) { //������ ����̸�
				continue;
			}
			pair<int, int> next_position = down(i, j);
			arr[i][j] = -2; //��ĭó��
			arr[next_position.first][next_position.second] = num; //���� ä���
		}
	}
}

void rotate() {

	int arr2[100][100];

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			arr2[i][j] = arr[j][abs(n - 1 - i)];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			arr[i][j] = arr2[i][j];
		}
	}
}
void solution() {
	//���������: -1
	//���������: 0
	//���� ��� : ������ ����� �ƴ� ��� �߿��� ���� ��ȣ�� ���� �۰�, ���� ��ȣ�� ���� ���� ���

	while (true) {
		find_biggest_block_group();
		gravity();
		rotate(); //�ݽð� �������� ȸ��
		gravity(); //�ٽ� �߷�
	}

}
void input() {
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
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