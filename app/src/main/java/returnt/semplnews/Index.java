package returnt.semplnews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.SimpleSwipeUndoAdapter;

import java.util.ArrayList;

import returnt.adapter.ItemAdapterNews;
import returnt.libraryjava.SpliceString;
import returnt.models.News;
import returnt.xmlParser.ParsXML;

public class Index extends AppCompatActivity {

    private ArrayList<News> products = new ArrayList<News>();
    private ItemAdapterNews boxAdapter;
    private DynamicListView dynamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // initialize listView
        dynamList = (DynamicListView) findViewById(R.id.listView);

        setListNews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void refresh(MenuItem item) {
        Toast.makeText(this, "Обновляем...", Toast.LENGTH_SHORT).show();

        products = new ArrayList<>();
        setListNews();
        boxAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Обновлено...", Toast.LENGTH_SHORT).show();
    }

    private void setListNews(){

        // create adapter
        fillData();
        boxAdapter = new ItemAdapterNews(this, products);

        SwingBottomInAnimationAdapter animationAdapter = new SwingBottomInAnimationAdapter(boxAdapter);

        // свайп и замена айтема на другой
        SimpleSwipeUndoAdapter animationAdapter1 = new SimpleSwipeUndoAdapter(animationAdapter, this,
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Log.d("position", ""+position);
                        }
                    }
                }
        );

        animationAdapter.setAbsListView(dynamList);
        dynamList.setAdapter(animationAdapter1);
        dynamList.enableSimpleSwipeUndo();

        // click item list
        dynamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getApplicationContext(), BrowserActivity.class)
                        .putExtra("url", products.get(position).getUrl().toString())
                        .putExtra("tile", products.get(position).getTitle().toString()));
            }
        });
    }

    // generate items adapter
    private void fillData() {

        SpliceString spl = new SpliceString();
        ParsXML xmlParser = new ParsXML();

        for (int i = 0; i <= xmlParser.getTitle().size()-1; i++) {
            String urlTemp = xmlParser.getLinks().get(i).toString().substring(42);
            String urlTempCut = urlTemp.substring(0, urlTemp.indexOf("&"));
            String url = spl.getSpliceString(spl.getSpliceString(urlTempCut, "%3F", "?"), "%3D", "=");
            products.add(new News(xmlParser.getTitle().get(i).toString(), xmlParser.getDescription().get(i).toString(), url, xmlParser.getPublished().get(i).toString(), "dwwerwer", "dwerwerwe", 5, false));
        }
    }
}