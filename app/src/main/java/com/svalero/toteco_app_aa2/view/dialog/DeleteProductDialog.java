package com.svalero.toteco_app_aa2.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.presenter.dialog.DeleteProductPresenter;
import com.svalero.toteco_app_aa2.view.AddPublicationFragment;


public class DeleteProductDialog extends DialogFragment {

    private final AddPublicationFragment addPublicationFragment;
    private final DeleteProductPresenter presenter;
    private Product product;

    public DeleteProductDialog(AddPublicationFragment addPublicationFragment, Product product) {
        this.addPublicationFragment = addPublicationFragment;
        presenter = new DeleteProductPresenter(this, addPublicationFragment.getContext());
        this.product = product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.delete_product_message, product.getName()))
                .setPositiveButton(R.string.delete_submit, (dialog, id) -> {
                    presenter.delete(product);
                })
                .setNegativeButton(R.string.delete_cancel, (dialog, id) ->
                    DeleteProductDialog.this.getDialog().cancel()
                );
        return builder.create();
    }
}