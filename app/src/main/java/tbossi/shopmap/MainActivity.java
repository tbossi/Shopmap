package tbossi.shopmap;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private List<Shop> shopList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.shopListView);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        setShopList();

        setListView();
        setMapView();
    }

    private void setListView() {
        List<String> shopNames = new ArrayList<String>();

        for (Shop s: shopList) {
            shopNames.add(s.getName());
        }

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shopNames);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                Bundle shopData = new Bundle(3);
                shopData.putString("name",shopList.get(position).getName());
                shopData.putDouble("lat",shopList.get(position).getLat());
                shopData.putDouble("lon",shopList.get(position).getLon());
                intent.putExtras(shopData);
                startActivity(intent);
            }
        });
    }

    private void setShopList() {
        shopList = new ArrayList<Shop>();

        ShopsProvider shopsProvider = new ShopsProvider();
        Cursor cursor = shopsProvider.query(null,null,null,null,null,null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Shop s = new Shop(cursor.getString(cursor.getColumnIndex("name")),
                              cursor.getDouble(cursor.getColumnIndex("lat")),
                              cursor.getDouble(cursor.getColumnIndex("lon")));
            shopList.add(s);
        }
    }

    private void setMapView() {
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        for (Shop s: shopList) {
            map.addMarker(new MarkerOptions().position(new LatLng(s.getLat(), s.getLon())).title(s.getName()));
        }
    }


}
