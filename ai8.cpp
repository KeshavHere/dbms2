#include <bits/stdc++.h>
using namespace std;

typedef long double ld;
typedef long long ll;
#define all(v) v.begin(),v.end()
#define srt(v) sort(all(v))
#define dsrt(v) sort(all(v),greater<ll>())
#define rep(i,s,e) for(i=s;i<e;i++)
#define drep(i,e,s) for(i=e;i>s;i--)
#define pb push_back
#define ppb pop_back
#define imax LONG_LONG_MAX
#define imin LONG_LONG_MIN
//ll mod=1e9+7;

/*-------------------------------------------------------------------------------------------------------------------*/
//DEBUG
#define showone(x) cout << #x << " is " << x << endl;
#define show(args...) { string _s = #args; replace(_s.begin(), _s.end(), ',', ' '); stringstream _ss(_s); istream_iterator<string> _it(_ss); err(_it, args); }

void err(istream_iterator<string> it) {}
template<typename T, typename... Args>
void err(istream_iterator<string> it, T a, Args... args) {
    cout << *it << " = " << a << endl;
    err(++it, args...);
}

unsigned countBits(unsigned int number) {return (int)log2(number)+1;}

/*-------------------------------------------------------------------------------------------------------------------*/


void desperate_optimization(int precision){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.precision(precision);
}

const ll n = 3;
ll empty_digit;
ll i,j,k,x,y;
ll input[n][n];
ll goal[n][n];

typedef struct node{
    string prev_operation;//LRUD
    int distance_travelled;
    int heuristic_value;
    int important;   //heuristic_value+ distance_travelled
    ll matrix[n][n];
} nn;

struct is_min {
bool operator()(nn p1, nn p2) {
      return p1.important > p2.important;
  }
};

priority_queue <nn,vector<nn>, is_min> frontier;
std::vector<nn> explored;


bool is_samee(ll arr1[n][n], ll arr2[n][n])
{
    ll ii,jj;
     rep(ii,0,n)
         {
         rep(jj,0,n)
         {
             if(arr1[ii][jj]!=arr2[ii][jj])
                {return false;}
         }
         }
         return true;
}

void show_arr(ll arr[n][n])
{
    rep(i,0,n)
     {
     rep(j,0,n)
     {
         cout<<arr[i][j]<<' ';
     }cout<<endl;
     }
}

void insert_frontier(nn x)
{
    vector<nn> temp;
    nn tempn;
    bool found = false;
    while(!frontier.empty() && !found)
    {
        tempn = frontier.top();
        if(is_samee(tempn.matrix,x.matrix))
            found = true;
        else
            temp.push_back(tempn);
        frontier.pop();
    }

    while(!temp.empty())
    {
        frontier.push(temp.back());
        temp.pop_back();
    }

    if(found)
    {
        if(tempn.important>x.important)
            tempn.prev_operation = x.prev_operation,tempn.important=x.important;
        frontier.push(tempn);
    }
    else{
        frontier.push(x);
    }
}

bool in_explored(nn x)
{
    cout<<"Checking in explored";
    vector<nn> temp;
    nn tempn;
    bool found = false;

    for(auto aa:explored)
    {
        if(is_samee(aa.matrix,x.matrix))
            return true;
    }
    return false;
}


nn make_node(string p,int h,int d,ll arr[n][n])
{
    nn temp;
    temp.prev_operation= p;
    temp.important = h+d;
    temp.heuristic_value= h;
    temp.distance_travelled = d;
    ll ii,jj;
    rep(ii,0,n)
    rep(jj,0,n)
    temp.matrix[ii][jj] = arr[ii][jj];
    return  temp;
}


ll priority(ll arr[n][n])
{
   ll count=0;
     rep(x,0,n)
     {
     rep(y,0,n)
     {
         rep(i,x,n)
         {
         rep(j,y,n)
         {
             if(arr[x][y]>arr[i][j])
                {count++;}
         }
         }
     }
     }
     return count;
}


ll heuristic(ll current[n][n])
{
     ll count=0;
     rep(x,0,n)
     {
     rep(y,0,n)
     {
         rep(i,0,n)
         {
         rep(j,0,n)
         {
             if(current[x][y]==goal[i][j])
                {count+=abs(i-x)+abs(y-j); i=n;j=n;}
         }
         }
     }
     }
     return count;
}


void moveL(nn intermediate)
{
    ll bb[n][n];

    ll ii,jj;
    rep(ii,0,n)
    rep(jj,0,n)
        bb[ii][jj]= intermediate.matrix[ii][jj];

    rep(ii,0,n)
    rep(jj,0,n)
    {
        if(intermediate.matrix[ii][jj]==empty_digit)
        {
            if(jj!=0)
            {
                ll cc = bb[ii][jj-1];
                bb[ii][jj-1] = bb[ii][jj];
                bb[ii][jj]= cc;
                cout<<"Can moveLeft ";
                if(!in_explored(make_node(intermediate.prev_operation+'L',heuristic(bb),intermediate.distance_travelled+1,bb)))
                    insert_frontier(make_node(intermediate.prev_operation+'L',heuristic(bb),intermediate.distance_travelled+1,bb));
                else
                    cout<<" but explored";
                cout<<endl;
            }
        }
    }
}

void moveR(nn intermediate)
{
    ll bb[n][n];

    ll ii,jj;
    rep(ii,0,n)
    rep(jj,0,n)
        bb[ii][jj]= intermediate.matrix[ii][jj];

    rep(ii,0,n)
    rep(jj,0,n)
    {
        if(intermediate.matrix[ii][jj]==empty_digit)
        {
            if(jj!=n-1)
            {
                ll cc = bb[ii][jj+1];
                bb[ii][jj+1] = bb[ii][jj];
                bb[ii][jj]= cc;
                cout<<"Can moveRYt ";
                if(!in_explored((make_node(intermediate.prev_operation+'R',heuristic(bb),intermediate.distance_travelled+1,bb))))
                    insert_frontier(make_node(intermediate.prev_operation+'R',heuristic(bb),intermediate.distance_travelled+1,bb));
                else
                    cout<<" but explored";
                cout<<endl;
            }
        }
    }
}

void moveU(nn intermediate)
{
    ll bb[n][n];

    ll ii,jj;
    rep(ii,0,n)
    rep(jj,0,n)
        bb[ii][jj]= intermediate.matrix[ii][jj];

    rep(ii,0,n)
    rep(jj,0,n)
    {
        if(intermediate.matrix[ii][jj]==empty_digit)
        {
            if(ii!=0)
            {
                ll cc = bb[ii-1][jj];
                bb[ii-1][jj] = bb[ii][jj];
                bb[ii][jj]= cc;

                cout<<"Can moveUP ";
                if(!in_explored(make_node(intermediate.prev_operation+'U',heuristic(bb),intermediate.distance_travelled+1,bb)))
                    insert_frontier(make_node(intermediate.prev_operation+'U',heuristic(bb),intermediate.distance_travelled+1,bb));
                else
                    cout<<" but explored";
                cout<<endl;
            }
        }
    }

}

void moveD(nn intermediate)
{
    ll bb[n][n];

    ll ii,jj;
    rep(ii,0,n)
    rep(jj,0,n)
        bb[ii][jj]= intermediate.matrix[ii][jj];

    rep(ii,0,n)
    rep(jj,0,n)
    {
        if(intermediate.matrix[ii][jj]==empty_digit)
        {
            if(ii!=n-1)
            {
                ll cc = bb[ii+1][jj];
                bb[ii+1][jj] = bb[ii][jj];
                bb[ii][jj]= cc;

                cout<<"Can moveDown ";
                if(!in_explored(make_node(intermediate.prev_operation+'D',heuristic(bb),intermediate.distance_travelled+1,bb)))
                    insert_frontier(make_node(intermediate.prev_operation+'D',heuristic(bb),intermediate.distance_travelled+1,bb));
                else
                    cout<<" But explored";
                cout<<endl;
            }
        }
    }

}

void show_frontier()
{
    vector<nn> temp;
    nn tempn;
    cout<<"In Frontier -> "<<endl;
    while(!frontier.empty())
    {
        tempn = frontier.top();
        cout<<"------\n";
        show_arr(tempn.matrix);
        temp.push_back(tempn);
        frontier.pop();
    }
    cout<<"Frontier ended";
    while(!temp.empty())
    {
        frontier.push(temp.back());
        temp.pop_back();
    }
}
void applyASTAR()
{
    frontier.push(make_node("X",heuristic(input),0,input));
    bool goal_found = false;
    while(!frontier.empty() && !goal_found)
    {
        nn node_to_do = frontier.top();
        frontier.pop();
        cout<<"Exploring"<<endl;
        show_arr(node_to_do.matrix);

        if(is_samee(node_to_do.matrix,goal))
        {
            node_to_do.prev_operation.erase(0, 1);
            cout<<"\nGOAL FOUND\n Operation-> "<<node_to_do.prev_operation;
            goal_found = true;
            continue;
        }
        cout<<"\nNOT GOAL\n";
        explored.pb(node_to_do);


        // Operators
//        show_frontier();
        moveL(node_to_do);
//        show_frontier();
        moveR(node_to_do);
//        show_frontier();
        moveU(node_to_do);
//        show_frontier();
        moveD(node_to_do);

//        show_frontier();
        cout<<endl<<"=====\n";
  }
}

void sol()
{
     cout<<"Enter empty-digit :"<<endl;cin>>empty_digit;
     cout<<"Enter Initial Stage :"<<endl;
     rep(i,0,n)
     rep(j,0,n)
     {
         cin>>input[i][j];
     }

     cout<<"INPUT is: "<<endl;
     show_arr(input);
     cout<<"Enter GOAL state: ";cout<<endl;
     for(int i=0;i<n;i++)
     {
         for(int j=0;j<n;j++)
         {
             cin>>goal[i][j];
         }
     }
     cout<<"Goal is: "<<endl;
     show_arr(goal);

/*
     ll even_goal[n][n] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
     ll odd_goal[n][n] =
     {{1,2,3,4},
     {12,13,14,5},
     {11,16,15,6},
     {10,9,8,7}};

     ll priority_of_input = priority(input);
     show(priority_of_input);

     if(priority_of_input&1) //==0 ? goal=even_goal:goal=odd_goal;
     {
         rep(i,0,n)rep(j,0,n)goal[i][j]=odd_goal[i][j];
     }
     else
     {
         rep(i,0,n)rep(j,0,n)goal[i][j]=even_goal[i][j];
     }
*/
     ll prev = 1000 * clock() / CLOCKS_PER_SEC;
     applyASTAR();
     cerr << "\nTime took for ALGO: " << (1000 * clock() / CLOCKS_PER_SEC)-prev << "ms\n";
}
/*
1 2 3
4 5 6
7 8 0
Enter GOAL state:
0 1 2
5 6 3
4 7 8*/

int main()
{
     desperate_optimization(10);
     sol();
     cerr << "\nTotal elapsed: " << 1000 * clock() / CLOCKS_PER_SEC << "ms\n";
     return 0;
}
