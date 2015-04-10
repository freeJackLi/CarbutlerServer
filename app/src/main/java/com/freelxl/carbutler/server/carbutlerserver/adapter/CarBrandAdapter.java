package com.freelxl.carbutler.server.carbutlerserver.adapter;

import android.content.Context;
import android.view.View;

import com.freelxl.baselibrary.listview.CommonAdapter;
import com.freelxl.baselibrary.listview.ViewHolder;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.domain.QueryAllBrand;

import java.util.List;

/**
 * Created by root-pc on 2015/4/10.
 */
public class CarBrandAdapter extends CommonAdapter<QueryAllBrand.Data> {

    public CarBrandAdapter(Context context, List<QueryAllBrand.Data> mDatas) {
        super(context, mDatas, R.layout.item_car_brand);
    }

    @Override
    public void convert(ViewHolder holder, QueryAllBrand.Data item) {

    }

    @Override
    public void convert(ViewHolder holder, QueryAllBrand.Data item, int position) {
        super.convert(holder, item, position);
        if (position == 0) {
            holder.setVisibility(R.id.tv_indexes, View.VISIBLE);
            if (item.brandIsHot.equals("" + 1)) {
                holder.setText(R.id.tv_indexes, "热门");
            } else {
                holder.setText(R.id.tv_indexes, item.brandEname);
            }
        } else {
            if (item.brandIsHot.equals("" + 1)) {
                holder.setVisibility(R.id.tv_indexes, View.GONE);
            } else {
                QueryAllBrand.Data lastCarBrand = super.getDatas().get(position - 1);
                if (lastCarBrand.brandEname.equals(
                        item.brandEname)) {
                    holder.setVisibility(R.id.tv_indexes, View.GONE);
                } else {
                    holder.setVisibility(R.id.tv_indexes, View.VISIBLE);
                    holder.setText(R.id.tv_indexes, item.brandEname.toUpperCase());
                }
            }
        }
        holder.setText(R.id.tv_car_brand_name, item.brandName);
    }
}
