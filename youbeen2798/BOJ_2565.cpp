#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>

using namespace std;
struct node {
	int from;
	int to;
};

struct node line[101];
int a[101];

bool compare(struct node a, struct node b) {
	if (a.from == b.from) {
		return a.to < b.to;
	}
	else {
		return a.from < b.from;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		a[i] = 1;
		cin >> line[i].from >> line[i].to;
	}
	sort(line, line + n, compare);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (line[j].to < line[i].to) {
				a[i] = max(a[i], a[j] + 1);
			}
		}
	}
	int answer = *max_element(a, a + n);
	cout << n - answer << '\n';

	return 0;
}