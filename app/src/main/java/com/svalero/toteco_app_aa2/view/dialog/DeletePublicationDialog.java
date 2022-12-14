package com.svalero.toteco_app_aa2.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.dialog.DeletePublicationContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.presenter.dialog.DeletePublicationPresenter;
import com.svalero.toteco_app_aa2.view.HomeFragment;

public class DeletePublicationDialog extends DialogFragment implements DeletePublicationContract.View {

    private final HomeFragment homeFragment;
    private final DeletePublicationPresenter presenter;
    private Publication publication;

    public DeletePublicationDialog(HomeFragment homeFragment, Publication publication) {
        this.homeFragment = homeFragment;
        this.publication = publication;
        presenter = new DeletePublicationPresenter(this, homeFragment.getContext());
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
        builder.setMessage(R.string.delete_publication_message)
                .setPositiveButton(R.string.delete_submit, (dialog, id) -> {
                    presenter.delete(publication);
                })
                .setNegativeButton(R.string.delete_cancel, (dialog, id) ->
                        DeletePublicationDialog.this.getDialog().cancel()
                );
        return builder.create();
    }

    @Override
    public void onDelete(String message) {
        homeFragment.showToast(message);
        homeFragment.refreshPublications();
    }
}
