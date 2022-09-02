package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.dialog.ProductDialogContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.dto.ProductDialogDTO;

public class ProductDialogModel implements ProductDialogContract.Model {

    private final AppDatabase db;
    private final Context context;

    public ProductDialogModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        this.context = context;
    }

    @Override
    public String addProduct(ProductDialogDTO productDialogDTO) {
        if (productDialogDTO.getName().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPrice().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPunctuation().equals("")) {
            return context.getString(R.string.error_field_empty);
        }

        float price = Float.parseFloat(productDialogDTO.getPrice());
        float punctuation = Float.parseFloat(productDialogDTO.getPunctuation());

        if (price < 0.0 || punctuation < 0.0) {
            return context.getString(R.string.add_product_error_price_punctuation);
        }
        if (punctuation > 5.0) {
            return context.getString(R.string.add_product_error_punctuation);
        }

        Product newProduct = new Product(productDialogDTO.getName(), price, punctuation, 1);
        db.productDao().insert(newProduct);
        return "";
    }

    @Override
    public String modifyProduct(ProductDialogDTO productDialogDTO, Product product) {
        if (productDialogDTO.getName().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPrice().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPunctuation().equals("")) {
            return context.getString(R.string.error_field_empty);
        }

        float price = Float.parseFloat(productDialogDTO.getPrice());
        float punctuation = Float.parseFloat(productDialogDTO.getPunctuation());

        if (price < 0.0 || punctuation < 0.0) {
            return context.getString(R.string.add_product_error_price_punctuation);
        }
        if (punctuation > 5.0) {
            return context.getString(R.string.add_product_error_punctuation);
        }

        product.setName(productDialogDTO.getName());
        product.setPunctuation(punctuation);
        product.setPrice(price);
        db.productDao().update(product);
        return "";
    }
}
