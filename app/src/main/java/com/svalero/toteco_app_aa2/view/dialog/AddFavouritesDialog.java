package com.svalero.toteco_app_aa2.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.dialog.AddFavouritesContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.presenter.dialog.AddFavouritesPresenter;
import com.svalero.toteco_app_aa2.view.HomeFragment;

public class AddFavouritesDialog extends DialogFragment implements AddFavouritesContract.View {
    private final AddFavouritesPresenter presenter;
    private Publication publication;

    public AddFavouritesDialog(HomeFragment homeFragment, Publication publication) {
        presenter = new AddFavouritesPresenter(this, homeFragment.getContext());
        this.publication = publication;
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
        builder.setMessage(R.string.add_favourite_message)
                .setPositiveButton(R.string.add_favourite, (dialog, id) -> {
                    presenter.addFavourite(publication);
                })
                .setNegativeButton(R.string.delete_cancel, (dialog, id) ->
                        AddFavouritesDialog.this.getDialog().cancel()
                );
        return builder.create();
    }
}
