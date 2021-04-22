package com.example.curbsidethairestaurant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.curbsidethairestaurant.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    DatabaseHelper db;
    ArrayList<FoodList> bkls = new ArrayList<>();
    RecyclerView recyclerBook;
    FoodAdapter foodAdapter;

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    public void getFoods( String foodName) {
        List<FoodList> dailyTransactionsList = null;
        dailyTransactionsList = db.getBooksByIDorName(foodName);
        bkls.clear();
        for (FoodList bb : dailyTransactionsList) {
            bkls.add(new FoodList(
                    bb.getName(),
                    bb.getDescription(),
                    bb.getPrice()
            ));
        }
        if (!bkls.isEmpty()) {
            foodAdapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
//        }
        db = new DatabaseHelper(getActivity());
        db.insertBookDetails("Basil Beef Sesame Salad","Spicy Angus beef and sweet basil on top of fresh spring mix, red cabbage, \n" +
                "carrot, cucumber and tomatoes; served with sesame vinaigrette","$9.95");
        db.insertBookDetails("Curbside Rice","Stir-fried rice with onions, red bell peppers, peas and carrots, garnished \n" +
                "with red cabbage, cucumbers, scallions, and fried garlic","$6.50");
        db.insertBookDetails("Garlic Pepper Pork","Marinated pork stir-fried with fresh garlic and pepper; served with steamed \n" +
                "Jasmine rice, red cabbage, carrot, cucumbers, scallions, and fried garlic","$8.50");
        db.insertBookDetails("Pad Thai","Stir-fried rice noodles with bean sprouts and chives, garnished with red \n" +
                "cabbage, carrot, scallions, lime, and crushed peanuts","$7.50");
        db.insertBookDetails("Check Out","CheckOut..","$7.50");

        if(mItem.content.equals("Basil Beef Sesame Salad"))
        {
            rootView = inflater.inflate(R.layout.foods,container,false);
            recyclerBook = rootView.findViewById(R.id.recyclerViewId);
            recyclerBook.setLayoutManager(new LinearLayoutManager(getContext()));
            foodAdapter = new FoodAdapter(bkls);
            recyclerBook.setAdapter(foodAdapter);
            getFoods("Basil Beef Sesame Salad");
        }
        if(mItem.content.equals("Curbside Rice"))
        {
            rootView = inflater.inflate(R.layout.foods,container,false);
            recyclerBook = rootView.findViewById(R.id.recyclerViewId);
            recyclerBook.setLayoutManager(new LinearLayoutManager(getContext()));
            foodAdapter = new FoodAdapter(bkls);
            recyclerBook.setAdapter(foodAdapter);
            getFoods("Curbside Rice");
        }
        if(mItem.content.equals("Garlic Pepper Pork"))
        {
            rootView = inflater.inflate(R.layout.foods,container,false);
            recyclerBook = rootView.findViewById(R.id.recyclerViewId);
            recyclerBook.setLayoutManager(new LinearLayoutManager(getContext()));
            foodAdapter = new FoodAdapter(bkls);
            recyclerBook.setAdapter(foodAdapter);
            getFoods("Garlic Pepper Pork");
        }
        if(mItem.content.equals("Pad Thai")) {
            rootView = inflater.inflate(R.layout.foods, container, false);
            recyclerBook = rootView.findViewById(R.id.recyclerViewId);
            recyclerBook.setLayoutManager(new LinearLayoutManager(getContext()));
            foodAdapter = new FoodAdapter(bkls);
            recyclerBook.setAdapter(foodAdapter);
            getFoods("Pad Thai");
        }

        if(mItem.content.equals("Check Out")) {
            rootView = inflater.inflate(R.layout.foods, container, false);
            recyclerBook = rootView.findViewById(R.id.recyclerViewId);
            recyclerBook.setLayoutManager(new LinearLayoutManager(getContext()));
            foodAdapter = new FoodAdapter(bkls);
            recyclerBook.setAdapter(foodAdapter);
            getFoods("Check Out");
        }
        return rootView;
    }

}