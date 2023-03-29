#include <iostream>
#include <cstdio>
#include <climits>

#define MAX 500002

using namespace std;

int totalBottom[MAX];
int totalTop[MAX];
int total[MAX];
int bottom[MAX];
int top[MAX];

int main()
{
    int n, h;
    int min = INT_MAX;
    int count = 1;

    cin >> n >> h;

    for (int i = 0; i < n / 2; i++)
    {
        int bVal, tVal;
        cin >> bVal >> tVal;

        top[tVal]++;
        bottom[bVal]++;
    }

    for (int i = h; i >= 1; i--)
    {
        totalBottom[i] = bottom[i] + totalBottom[i + 1];
        totalTop[i] = top[i] + totalTop[i + 1];
    }

    for (int i = 1; i <= h; i++)
    {
        total[i] = totalBottom[i] + totalTop[h - i + 1];

        if (total[i] <= min)
            total[i] == min ? count++ : count = 1, min = total[i];
    }

    cout << min << " " << count;
    return 0;
}