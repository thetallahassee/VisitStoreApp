package com.visitapp.visitstoreapp.menuPrincipalGenerico.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.huxq17.swipecardsview.SwipeCardsView;
import com.visitapp.visitstoreapp.R;
import com.visitapp.visitstoreapp.menuPrincipalGenerico.adapter.CardAdapter;
import com.visitapp.visitstoreapp.menuPrincipalGenerico.model.Model;
import com.visitapp.visitstoreapp.sistema.controllers.productos.ProductoController;
import com.visitapp.visitstoreapp.sistema.domain.productos.Producto;
import com.visitapp.visitstoreapp.sistema.interfaces.OnGetDataListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPrincipal extends Fragment {

    TextView textView;
    private SwipeCardsView swipeCardsView;
    private List<Model> modelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_principal, container, false);
        //return inflater.inflate(R.layout.fragment_fragment_principal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*textView = view.findViewById(R.id.textoFragment1);
        textView.setText("BIENVENIDO A FRAGMENT PRINCIPAL");*/

        swipeCardsView = view.findViewById(R.id.swipeCardsView);
        swipeCardsView.retainLastCard(false);
        swipeCardsView.enableSwipe(true);
        getData();

        super.onViewCreated(view, savedInstanceState);
    }

    private void getData() {
        /*modelList.add(new Model("Camisa", "https://i2.linio.com/p/e7ad556f25504c845047bd9a43e511ef-product.jpg"));
        modelList.add(new Model("Pantalon", "https://media.wuerth.com/stmedia/modyf/shop/900px/1370434.jpg"));
        modelList.add(new Model("Zapatos", "https://cdn.ferragamo.com/wcsstore/FerragamoCatalogAssetStore/images/products/551035/551035_00.png"));*/

        ProductoController productoController = new ProductoController();
        productoController.getList(new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                List<Producto> productoControllerList = new ArrayList<>();
                for(DataSnapshot item : data.getChildren()){
                    Producto producto = item.getValue(Producto.class);
                    productoControllerList.add(producto);
                }

                CardAdapter cardAdapter = new CardAdapter(productoControllerList, getActivity());
                swipeCardsView.setAdapter(cardAdapter);
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });

        /*CardAdapter cardAdapter = new CardAdapter(modelList, getActivity());
        swipeCardsView.setAdapter(cardAdapter);*/

    }


}
