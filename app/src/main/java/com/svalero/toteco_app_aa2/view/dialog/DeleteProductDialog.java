package com.svalero.toteco_app_aa2.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.presenter.dialog.DeleteProductPresenter;
import com.svalero.toteco_app_aa2.view.AddPublicationFragment;


public class DeleteProductDialog extends DialogFragment {

    private final DeleteProductPresenter presenter;
    private ProductLocal productLocal;

    public DeleteProductDialog(AddPublicationFragment addPublicationFragment, ProductLocal productLocal) {
        presenter = new DeleteProductPresenter(this, addPublicationFragment.getContext());
        this.productLocal = productLocal;
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
//        builder.setMessage(getString(R.string.delete_product_message, productLocal.getName()))
        builder.setMessage(getString(R.string.delete_product_message))
                .setPositiveButton(R.string.delete_submit, (dialog, id) -> {
                    presenter.delete(productLocal);
                })
                .setNegativeButton(R.string.delete_cancel, (dialog, id) ->
                    DeleteProductDialog.this.getDialog().cancel()
                );
        return builder.create();
    }
}