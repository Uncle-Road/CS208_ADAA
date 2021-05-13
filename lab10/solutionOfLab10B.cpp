#include <bits/stdc++.h>

using namespace std;

struct Graph {
    int from;
    int to;
    long long weight;
};

int parentList[200020];
long long degree[200020];
long long ans[200020];
Graph edgesList[200020];
int q_arr[200020];

bool compareTO(Graph e1, Graph e2);

int getParent(int a);

void getDegree(int x, int y);

void MST(int max, int n);

void printAns(int maxQue, int n, int m);

int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n - 1; i++) {
        scanf("%d%d%lld", &edgesList[i].from, &edgesList[i].to, &edgesList[i].weight);
    }
    int maxQue = 0;
    for (int i = 0; i < m; i++) {
        scanf("%d", &q_arr[i]);
        maxQue = (maxQue < q_arr[i]) ? q_arr[i] : maxQue;
    }
    printAns(maxQue, n, m);
    return 0;
}

bool compareTO(Graph e1, Graph e2) {
    if (e1.weight < e2.weight)
        return true;
    else
        return false;
}

int getParent(int a) {
    if (a == parentList[a]) return parentList[a];
    parentList[a] = getParent(parentList[a]);
    return parentList[a];
}

void getDegree(int x, int y) {
    int x1 = getParent(x);
    int y1 = getParent(y);
    parentList[x1] = y1;
    degree[y1] += degree[x1];
}

void MST(int max, int n) {
    ans[0] = 0;
    ans[1] = 0;
    for (int i = 1; i <= n; i++) {
        parentList[i] = i;
        degree[i] = 1;
    }
    int q = 1;
    int i = 1;
    while (q <= max) {
        while (i < n ) {
            if (edgesList[i].weight <= q) {

                int src = edgesList[i].from;
                int dest = edgesList[i].to;

                ans[q] += degree[getParent(src)] * degree[getParent(dest)];
                getDegree(src, dest);
                i++;
            } else{
                break;
            }
        }
        long long tmp = ans[q];
        q++;
        ans[q] = tmp;
    }
}

void printAns(int maxQue, int n, int m) {
    sort(edgesList, edgesList + n, compareTO);
    MST(maxQue, n);
    for (int i = 0; i < m; i++) {
        cout << ans[q_arr[i]] << " ";
    }
}
