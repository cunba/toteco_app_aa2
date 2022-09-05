package com.svalero.toteco_app_aa2.view.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.dialog.ProductDialogContract;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.ProductType;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.domain.dto.view.ProductDialogDTO;
import com.svalero.toteco_app_aa2.presenter.dialog.ProductDialogPresenter;
import com.svalero.toteco_app_aa2.util.Utils;
import com.svalero.toteco_app_aa2.view.AddPublicationFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProductDialog extends DialogFragment implements ProductDialogContract.View,
        AdapterView.OnItemSelectedListener {

    private final AddPublicationFragment addPublicationFragment;
    private final ProductDialogPresenter presenter;
    private ProductLocal product;
    private View view;
    private List<ProductType> types;
    private ProductType typeSelected;

    public ProductDialog(AddPublicationFragment fragment, ProductLocal product) {
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
        view = inflater.inflate(R.layout.dialog_product, null);

        presenter.getTypes();

        if (product != null) {
            modifyProductTexts();
        } else {
            TextView tvTitle = view.findViewById(R.id.product_dialog_title);
            tvTitle.setText("ADD PRODUCT");
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.add_product_submit, (dialog, id) -> {
                    onPressSubmit();
                })
                .setNegativeButton(R.string.add_product_cancel, (dialog, id) ->
                        ProductDialog.this.getDialog().cancel()
                );

        return builder.create();
    }

    public void setSpinner(List<ProductType> types) {
        this.types = types;
        Spinner spinner = (Spinner) view.findViewById(R.id.product_dialog_type);

        ArrayAdapter<ProductType> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_dropdown_item, types);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        typeSelected = types.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        typeSelected = null;
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    private void modifyProductTexts() {
        Spinner spinner = view.findViewById(R.id.product_dialog_type);
        TextView tvTitle = view.findViewById(R.id.product_dialog_title);
        EditText etPrice = view.findViewById(R.id.product_dialog_price);
        EditText etPunctuation = view.findViewById(R.id.product_dialog_punctuation);

        tvTitle.setText("MODIFY PRODUCT");
        ProductType type = (ProductType) types.stream().filter(t -> t.getId() == product.getTypeId());
        typeSelected = type;
        spinner.setSelection(types.indexOf(type));
        etPrice.setText(String.valueOf(Utils.roundNumber(product.getPrice())));
        etPunctuation.setText(String.valueOf(Utils.roundNumber(product.getPunctuation())));
    }

    @Override
    public void onPressSubmit() {
        EditText etPrice = view.findViewById(R.id.product_dialog_price);
        EditText etPunctuation = view.findViewById(R.id.product_dialog_punctuation);

        String priceString = etPrice.getText().toString();
        String punctuationString = etPunctuation.getText().toString();

        ProductDialogDTO productDialogDTO = new ProductDialogDTO(typeSelected, priceString, punctuationString);
        if (product == null) {
            presenter.addProduct(productDialogDTO);
        } else {
            presenter.modifyProduct(productDialogDTO, product);
        }
    }

    @Override
    public void onSubmit(String error) {
        if (error.equals("")) {
            addPublicationFragment.refreshProductsList();
        } else {
            addPublicationFragment.showToast(error);
        }
    }
}