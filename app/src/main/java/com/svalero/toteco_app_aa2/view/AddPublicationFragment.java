package com.svalero.toteco_app_aa2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;
import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.AddPublicationContract;
import com.svalero.toteco_app_aa2.databinding.FragmentAddPublicationBinding;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationDTO;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationSummaryDTO;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.presenter.AddPublicationPresenter;
import com.svalero.toteco_app_aa2.util.ImageAdapter;
import com.svalero.toteco_app_aa2.util.Utils;
import com.svalero.toteco_app_aa2.view.dialog.AddEstablishmentDialog;
import com.svalero.toteco_app_aa2.view.dialog.DeleteProductDialog;
import com.svalero.toteco_app_aa2.view.dialog.ProductDialog;

import java.util.ArrayList;
import java.util.List;

public class AddPublicationFragment extends Fragment implements AddPublicationContract.View,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private FragmentAddPublicationBinding binding;
    private AddPublicationPresenter presenter;
    private List<ProductLocal> productLocals;
    private ArrayAdapter<ProductLocal> productsAdapter;
    private EstablishmentLocal establishment;
    private final int SELECT_PICTURE_RESULT = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAddPublicationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter = new AddPublicationPresenter(this);
        initializeProducts();
        loadProducts();

        Button addProductButton = root.findViewById(R.id.add_product_button);
        addProductButton.setOnClickListener(view -> onPressAddProduct());

        ImageButton addImageButton = root.findViewById(R.id.add_publication_image);
        addImageButton.setOnClickListener(view -> onPressAddImage());

        TextView addEstablishment = root.findViewById(R.id.add_publication_establishment_name);
        addEstablishment.setOnClickListener(view -> onPressAddEstablishment());

        Button submit = root.findViewById(R.id.add_publication_button2);
        submit.setOnClickListener(view -> onPressSubmit());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        setEstablishment();
        refreshProductsList();
        makeSummary();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void initializeProducts() {
        productLocals = new ArrayList<>();
        ListView lvProducts = binding.getRoot().findViewById(R.id.add_publication_product_list);
        productsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, productLocals);
        lvProducts.setAdapter(productsAdapter);
        lvProducts.setOnItemClickListener(this);
        lvProducts.setOnItemLongClickListener(this);
    }

    @Override
    public void setEstablishment() {
        TextView tvEstablishmentName = binding.getRoot().findViewById(R.id.add_publication_establishment_name);
        TextView tvEstablishmentPunctuation = binding.getRoot().findViewById(R.id.add_publication_establishment_punctuation);

        establishment = presenter.getEstablishment();

        if (establishment == null || establishment.getName().equals("")) {
            tvEstablishmentName.setText(R.string.add_publication_establishment_add);
            tvEstablishmentPunctuation.setText(R.string.add_publication_establishment_punctuation);
            return;
        }

        tvEstablishmentName.setText(establishment.getName());
        double price = Utils.roundNumber(establishment.getPunctuation());
        String sPrice = String.valueOf(price);
        tvEstablishmentPunctuation.setText(getString(R.string.add_publication_establishment_punctuation_print, sPrice));
    }

    @Override
    public void refreshProductsList() {
        loadProducts();
        productsAdapter.notifyDataSetChanged();
    }

    @Override
    public void makeSummary() {
        if (establishment != null) {
            AddPublicationSummaryDTO summary = presenter.makeSummary(establishment.getPunctuation());

            TextView tvTotalPrice = binding.getRoot().findViewById(R.id.add_publication_total_price);
            tvTotalPrice.setText(getString(R.string.add_publication_total_price,
                    String.valueOf(summary.getTotalPrice())));

            TextView tvTotalPunctuation = binding.getRoot().findViewById(R.id.add_publication_total_punctuation);
            tvTotalPunctuation.setText(getString(R.string.add_publication_total_punctuation,
                    String.valueOf(summary.getTotalPunctuation())));
        }
    }

    @Override
    public void onPressAddProduct() {
        showError(" ");
        DialogFragment newFragment = new ProductDialog(this, null);
        newFragment.show(getParentFragmentManager(), "add");
    }

    @Override
    public void onPressAddEstablishment() {
        showError("");
        DialogFragment fragment = new AddEstablishmentDialog(this);
        fragment.show(getParentFragmentManager(), "add establishment");
    }

    @Override
    public void onPressAddImage() {
        TextView tvError = binding.getRoot().findViewById(R.id.add_publication_error);
        tvError.setText("");
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE_RESULT);
    }

    @Override
    public void onPressSubmit() {
        TextView tvEstablishmentName = binding.getRoot().findViewById(R.id.add_publication_establishment_name);
        TextView tvEstablishmentPunctuation = binding.getRoot().findViewById(R.id.add_publication_establishment_punctuation);
        ImageView ivPublication = binding.getRoot().findViewById(R.id.add_publication_image);

        String establishmentName = tvEstablishmentName.toString();
        String establishmentPunctuationString = tvEstablishmentPunctuation.toString();

        if (establishmentName.equals("") || establishmentPunctuationString.equals("")) {
            showError(getString(R.string.error_establishment_empty));
            return;
        }
        if (productLocals.isEmpty()) {
            showError(getString(R.string.error_products_empty));
            return;
        }
        if (ivPublication.getDrawable() == null) {
            showError(getString(R.string.error_empty_image));
            return;
        }

        String publicationImage = ImageAdapter.fromImageViewToString(ivPublication);

        AddPublicationDTO addPublicationDTO = new AddPublicationDTO(
                establishment,
                establishmentPunctuationString,
                publicationImage
        );

        presenter.onPressSubmit(addPublicationDTO);

        showToast(getString(R.string.publication_created));

        tvEstablishmentName.setText(R.string.add_publication_establishment_add);
        tvEstablishmentPunctuation.setText(R.string.add_publication_establishment_punctuation);
        showError("");
        productLocals.clear();

        establishment = presenter.clearEstablishmentAux();

        Navigation.findNavController(getView()).navigate(R.id.nav_home);
        establishment = presenter.clearEstablishmentAux();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == SELECT_PICTURE_RESULT) && (resultCode == getActivity().RESULT_OK)
                && (data != null)) {
            Picasso.get().load(data.getData()).noPlaceholder().centerCrop().fit()
                    .into((ImageView) binding.getRoot().findViewById(R.id.add_publication_image));

        }
    }

    @Override
    public void loadProducts() {
        productLocals.clear();
        productLocals.addAll(presenter.loadProducts());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ProductLocal productLocal = productLocals.get(i);
        DialogFragment newFragment = new ProductDialog(this, productLocal);
        newFragment.show(getParentFragmentManager(), "modify");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        ProductLocal productLocal = productLocals.get(i);
        DialogFragment newFragment = new DeleteProductDialog(this, productLocal);
        newFragment.show(getParentFragmentManager(), "delete");
        return true;
    }

    @Override
    public void showError(String message) {
        TextView tvError = binding.getRoot().findViewById(R.id.add_publication_error);
        tvError.setText(message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}