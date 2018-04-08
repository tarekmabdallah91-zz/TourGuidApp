package com.example.tarek.tourguidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.image_main)
    ImageView mainImage;
    @BindView(R.id.list_view_main)
    ListView listCategories;

    private ArrayList<CategoryItem> categories;
    private String currentCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainImage.setImageResource(R.drawable.egy);

        setCategories();
        buildRecycleView();
    }

    public void buildRecycleView() {
        CategoryItemAdapter adapter = new CategoryItemAdapter(this, categories);
        listCategories.setAdapter(adapter);
        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentCategory = categories.get(position).getCategoryName();
                sendIntentToLocationItemsActivity();
            }
        });
    }

    @OnClick(R.id.invite_icon)
    public void inviteOnClick() {
        sendIntentToInvitationActivity();
    }

    @OnClick(R.id.image_main)
    public void mainImageOnClick() {
        currentCategory = getString(R.string.country);
        sendIntentToLocationItemsActivity();
    }

    /**
     * to send intent to invitationActivity
     */
    public void sendIntentToInvitationActivity() {
        Intent sendIntentToInvitationActivity = new Intent(MainActivity.this, InvitationActivity.class);
        startActivity(sendIntentToInvitationActivity);
    }

    /**
     * to send intent to NavBarMainActivity
     */
    public void sendIntentToLocationItemsActivity() {
        final String CURRENT_CATEGORY = "currentCategory";
        Intent sendIntentToLocationItemsActivity = new Intent(MainActivity.this, NavBarMainActivity.class);
        sendIntentToLocationItemsActivity.putExtra(CURRENT_CATEGORY, currentCategory);
        startActivity(sendIntentToLocationItemsActivity);
    }

    public void setCategories() {
        categories = new ArrayList<>();
        categories.add(new CategoryItem(getString(R.string.natural_places), R.drawable.icons8_field_48));
        categories.add(new CategoryItem(getString(R.string.historical_places), R.drawable.icons8_pyramids));
        categories.add(new CategoryItem(getString(R.string.museums), R.drawable.icons8_parliament_48));
        categories.add(new CategoryItem(getString(R.string.restaurants), R.drawable.icons8_restaurant_building_48));
        categories.add(new CategoryItem(getString(R.string.islamics), R.drawable.icons8_mosque_48));
        categories.add(new CategoryItem(getString(R.string.copts), R.drawable.icons8_cathedral_48));
        categories.add(new CategoryItem(getString(R.string.beaches), R.drawable.icons8_beach_48));
    }

}
