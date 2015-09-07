package returnt.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.UndoAdapter;
import com.nhaarman.listviewanimations.util.Insertable;
import com.nhaarman.listviewanimations.util.Swappable;

import java.util.ArrayList;

import returnt.models.News;
import returnt.semplnews.R;

public class ItemAdapterNews extends BaseAdapter implements UndoAdapter, Insertable, Swappable {

    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<News> objects;
    private String description;

    public ItemAdapterNews(Context context, ArrayList<News> products) {

        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        News news = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        ((TextView) view.findViewById(R.id.tvDescr)).setText(Html.fromHtml(news.getTitle()));
        if(news.getPublished() != null)((TextView) view.findViewById(R.id.tvPrice)).setText(Html.fromHtml(news.getPublished()).subSequence(11, 16));
        description = news.getDescription();
        if(news.getPublished() != null) view.findViewById(R.id.backNews).setBackgroundResource(R.drawable.ic_arrow_right);

        return view;
    }

    // товар по позиции
    public News getProduct(int position) {
        return ((News) getItem(position));
    }

    public boolean hasStableIds(){
        return true;
    }

    @NonNull
    @Override
    public View getUndoView(int i, View view, @NonNull ViewGroup viewGroup) {
        // используем созданные, но не используемые view
        View view1 = view;
        if (view1 == null) {
            view1 = lInflater.inflate(R.layout.item, viewGroup, false);
        }

        News news = getProduct(i);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        //((TextView) view1.findViewById(R.id.tvDescr)).setText("11111");
        if(news.getPublished() != null)((TextView) view1.findViewById(R.id.tvPrice)).setText(Html.fromHtml(news.getPublished()).subSequence(11, 16));
        return view1;
    }

    @NonNull
    @Override
    public View getUndoClickView(@NonNull View view) {

        TextView textDesc = (TextView) view.findViewById(R.id.tvDescr);
        textDesc.setText(Html.fromHtml(description));
        textDesc.setTextSize(10);
        view.findViewById(R.id.backNews).setBackgroundResource(R.drawable.ic_arrow_left);
        view.findViewById(R.id.rootItemLayout).setBackgroundColor(Color.YELLOW);
        return view;
    }

    @Override
    public void add(int i, @NonNull Object o) {

    }

    @Override
    public void swapItems(int i, int i1) {

    }

}
