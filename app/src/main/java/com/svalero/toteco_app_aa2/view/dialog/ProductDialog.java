package com.svalero.toteco_app_aa2.view.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.dialog.ProductDialogContract;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.dto.ProductDialogDTO;
import com.svalero.toteco_app_aa2.presenter.dialog.ProductDialogPresenter;
import com.svalero.toteco_app_aa2.util.Utils;
import com.svalero.toteco_app_aa2.view.AddPublicationFragment;

public class ProductDialog extends DialogFragment implements ProductDialogContract.View {

    private final AddPublicationFragment addPublicationFragment;
    private final ProductDialogPresenter presenter;
    private Product product;

    public ProductDialog(AddPublicationFragment fragment, Product product) {
        this.product = product;
        addPublicationFragment = fragment;
        presenter = new ProductDialogPresenter(this, fragment.getContext());
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product, null);

        if (product != null) {
            modifyProductTexts(view);
        } else {
            TextView tvTitle = view.findViewById(R.id.product_dialog_title);
            tvTitle.setText("ADD PRODUCT");
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.add_product_submit, (dialog, id) -> {
                    onPressSubmit(view);
                })
                .setNegativeButton(R.string.add_product_cancel, (dialog, id) ->
                        ProductDialog.this.getDialog().cancel()
                );

        return builder.create();
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    private void modifyProductTexts(View view) {
        TextView tvTitle = view.findViewById(R.id.product_dialog_title);
        EditText etName = view.findViewById(R.id.product_dialog_name);
        EditText etPrice = view.findViewById(R.id.product_dialog_price);
        EditText etPunctuation = view.findViewById(R.id.product_dialog_punctuation);

        tvTitle.setText("MODIFY PRODUCT");
        etName.setText(product.getName());
        etPrice.setText(String.valueOf(Utils.roundNumber(product.getPrice())));
        etPunctuation.setText(String.valueOf(Utils.roundNumber(product.getPunctuation())));
    }

    @Override
    public void onPressSubmit(View view) {
        EditText etName = view.findViewById(R.id.product_dialog_name);
        EditText etPrice = view.findViewById(R.id.product_dialog_price);
        EditText etPunctuation = view.findViewById(R.id.product_dialog_punctuation);

        String name = etName.getText().toString();
        String priceString = etPrice.getText().toString();
        String punctuationString = etPunctuation.getText().toString();

        String error;
        ProductDialogDTO productDialogDTO = new ProductDialogDTO(name, priceString, punctuationString);
        if (product == null) {
            error = presenter.addProduct(productDialogDTO);
        } else {
            error = presenter.modifyProduct(productDialogDTO, product);
        }

        if (error.equals("")) {
            addPublicationFragment.refreshProductsList();
            addPublicationFragment.makeSummary();
        } else {
            addPublicationFragment.showToast(error);
        }
    }
}