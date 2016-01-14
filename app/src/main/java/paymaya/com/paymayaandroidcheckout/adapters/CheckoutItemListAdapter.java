package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.SampleApplication;
import paymaya.com.paymayaandroidcheckout.models.ItemModel;
import paymaya.com.paymayaandroidcheckout.utils.ListItem;
import paymaya.com.paymayaandroidcheckout.utils.Utils;
import paymaya.com.paymayaandroidcheckout.widgets.CheckoutItemListViewHolder;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemListAdapter extends BaseAdapter {
    private CheckoutItemListAdapterListener mCheckoutItemListAdapterListener;
    private List<ItemModel> mItemModels;
    private Context mContext;

    public interface CheckoutItemListAdapterListener {
        void onBuyItemClick(int position);
    }

    public CheckoutItemListAdapter(Context context, CheckoutItemListAdapterListener
            checkoutItemListAdapterListener, List<ItemModel> itemModels) {
        mContext = context;
        mCheckoutItemListAdapterListener = checkoutItemListAdapterListener;
        mItemModels = itemModels;
    }

    @Override
    public int getCount() {
        return mItemModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItemModels.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        CheckoutItemListViewHolder holder;
        final ItemModel itemModel = mItemModels.get(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.checkout_adapter_item_list, parent, false);
            holder = new CheckoutItemListViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CheckoutItemListViewHolder) view.getTag();
        }
        Log.d("@Adapter", "image = " + itemModel.getThumbNails());
        Utils.loadImage(mContext, itemModel.getThumbNails(), holder.getImageViewItem());
        holder.getTextViewItemName().setText(itemModel.getItemName());
        holder.getTextViewItemPrice().setText("PHP " + itemModel.getItemPrice());

        holder.setOnBuyItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckoutItemListAdapterListener.onBuyItemClick(position);
            }
        });
        return view;
    }
}