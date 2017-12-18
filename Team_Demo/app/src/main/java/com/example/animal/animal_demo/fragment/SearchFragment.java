package com.example.animal.animal_demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.animal.animal_demo.R;

/**
 * Created by kevin on 2017/8/31.
 */

public class SearchFragment extends Fragment {
    private SearchView mySearchView;
    private ListView myListView;
    private PopupWindow pop;
    //final private String[] myStrings={"骆驼","斑马","熊猫","鹦鹉","狐狸","河马","孔雀","驴","羊","犀牛","鳄鱼","乌龟","狮子","大象","猴子","松鼠","兔子","长颈鹿"};
    final private String[] myStrings={"ssss","aaaa","ssss"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = View.inflate(getActivity(),R.layout.search_fragment,null);
        mySearchView=(SearchView)view.findViewById(R.id.search_text);
        myListView=(ListView)view.findViewById(R.id.search_list);
        mySearchView.setSubmitButtonEnabled(true);
        myListView.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.search_list,myStrings));
        myListView.setTextFilterEnabled(true);
        pop=new PopupWindow(myListView,mySearchView.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.showAsDropDown(mySearchView);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(),"您输入的是:"+query,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    myListView.clearTextFilter();
                }else{
                    myListView.setFilterText(newText);
                }
                return true;
            }
        });
        return view;
    }

    public void setMyFragment(){
        View view = View.inflate(getActivity(),R.layout.search_fragment,null);
       // mySearchView=(SearchView)view.findViewById(R.id.search_text);

      //  mySearchView.setSubmitButtonEnabled(true);

    }
}
