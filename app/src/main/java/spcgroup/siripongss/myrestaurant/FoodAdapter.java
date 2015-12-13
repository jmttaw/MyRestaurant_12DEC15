package spcgroup.siripongss.myrestaurant;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by siripong.ss on 13/12/2558.
 */
public class FoodAdapter extends BaseAdapter {
    //Explict
    private Context objContext;
    private String[] foodString,priceString, sourceString;

    public FoodAdapter(Context objContext, String[] foodString, String[] sourceString, String[] priceString) {
        this.objContext = objContext;
        this.foodString = foodString;
        this.priceString = priceString;
        this.sourceString = sourceString;
    }//Constructor

    @Override
    public int getCount() {
        return foodString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.food_listview, viewGroup, false);

        //For Food
        TextView foodTextView = (TextView) objView1.findViewById(R.id.textView3);
        foodTextView.setText(foodString[i]);
        //for price
        TextView periceTextView = (TextView) objView1.findViewById(R.id.textView4);
        periceTextView.setText(priceString[i]);

        //For icon
        ImageView iconImageView = (ImageView) objView1.findViewById(R.id.imageView2);
        Picasso.with(objContext)
                .load(sourceString[i])
                .resize(120,120)
                .into(iconImageView);

        return objView1;
    }
}//Main Class
