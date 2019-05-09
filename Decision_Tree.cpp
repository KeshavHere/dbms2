#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

using namespace __gnu_pbds;
using namespace std;

typedef long double ld;
typedef long long ll;
typedef tree<ll,null_type,less<ll>,rb_tree_tag,tree_order_statistics_node_update> ordered_set;
#define TEST ll t;cin>>t;for(ll wtf =1;wtf<t+1;wtf++)
#define all(v) v.begin(),v.end()
#define srt(v) sort(all(v))
#define dsrt(v) sort(all(v),greater<ll>())
#define rep(i,s,e) for(i=s;i<e;i++)
#define drep(i,s,e) for(i=e;i>=s;i--)
#define pb push_back
#define ppb pop_back
#define imax LONG_LONG_MAX
#define imin LONG_LONG_MIN
#define PB push_back
#define PPB pop_back


std::vector<string> fm,co,bs,gr,sc;

double my_log(double x)
{
    return x==0 ? 0: log2(x);
}

double Entropy(string x,std::vector<string> attrib)
{
    double xcount = 0, xy = 0, xn = 0;
    for (int i = 0; i < attrib.size(); i++)
    {
        if (attrib[i]==x)
        {
            xcount += 1;    
            if(sc[i]==" TRUE") 
                {xy+=1;}
            else 
                {xn+=1;}
        }
    }
    double entropy = 0;
    entropy += ((-1)*(xy/xcount)*my_log(xy/xcount));
    entropy += ((-1)*(xn/xcount)*my_log(xn/xcount));
    // cout<<endl<<"For "<<x<<' '<<xy<<' '<<xn<<' '<<xcount<<entropy;
    return entropy; 
}

double Gain(double Hi,vector<string> attrib)
{
    double sum = 0;
    set<string> unique(all(attrib));
    for(string x : unique)
    {
        double a = count(all(attrib), x), b = attrib.size();
        sum += (a/b)*Entropy(x,attrib);
    }
    // cout<<"\nHi"<<Hi<<'-'<<"Sum : "<<sum;
    return Hi-sum;
}

double Gain(std::vector<string> sc)
{
    double sum = 0;
    set<string> unique(all(sc));
    for(string x : unique)
    {
        double a = count(all(sc), x), b = sc.size();
        sum += (-1*(a/b)*my_log(a/b));
    }
    // cout<<"\nSum : "<<sum<<endl;
    return sum;    
}

void making_tree()
{
    double Hi = Gain(sc);
    cout<<endl;
    cout<<"Country_of_origin"<<Gain(Hi,co);
    cout<<endl;
    cout<<"Big_star"<<Gain(Hi,bs);
    cout<<endl;
    cout<<"Genre"<<Gain(Hi,gr);
    cout<<endl;

}
void sol()  
{
    ifstream ip("C:\\Users\\Keshav\\Desktop\\College\\Ai STech lab\\input.csv");

  if(!ip.is_open()) std::cout << "ERROR: File Open" << '\n';

  bool header = true;
  // please hard code column names here
  string Film, Country_of_origin , Big_star , Genre , Success;
  while(ip.good()){

    getline(ip,Film,',');
    getline(ip,Country_of_origin,',');
    getline(ip,Big_star,',');
    getline(ip,Genre,',');
    getline(ip,Success,'\n');
    if(!header)
    {
        fm.pb(Film);
        co.pb(Country_of_origin);
        bs.pb(Big_star);
        gr.pb(Genre);
        sc.pb(Success);

        std::cout << "Film: "<<Film << '\n';
        std::cout << "Country_of_origin: "<<Country_of_origin << '\n';
        std::cout << "Big_star: "<<Big_star << '\n';
        std::cout << "Genre: "<<Genre << '\n';
        std::cout << "Success: "<<Success << '\n';
        std::cout << "-------------------" << '\n';
    }
    else
    {
        header = false;
    }
  }

  ip.close();

  making_tree();
}
 
int main() 
{
   sol(); //make it void	
     cerr << "\nTime elapsed: " << 1000 * clock() / CLOCKS_PER_SEC << "ms\n";
     // your code goes here
     return 0;
} 




