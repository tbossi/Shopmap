package tbossi.shopmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShopActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Shop currentShop;
    private TextView shopName;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Intent intent = getIntent();
        Bundle shopData = intent.getExtras();
        currentShop = new Shop(shopData.getString("name"), shopData.getDouble("lat"),shopData.getDouble("lon"));

        shopName = (TextView) findViewById(R.id.shopName);
        shopName.setText(currentShop.getName());

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(currentShop.getLat(), currentShop.getLon())).title(currentShop.getName()));
    }
}
