package com.example.vussnkrs.ui.Pagina.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vussnkrs.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;

    private Spinner prefCompraSpinner, nZapatillasSpinner;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        prefCompraSpinner = view.findViewById(R.id.pref_spinner);
        nZapatillasSpinner = view.findViewById(R.id.nzapatillas_spinner);

        ArrayAdapter<CharSequence> prefAdaptador = ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.preferenciasDeCompra,
                android.R.layout.simple_spinner_item
        );
        prefAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignamos el adaptador al primer spinner
        prefCompraSpinner.setAdapter(prefAdaptador);
        // Creamos un listener para detectar los cambios en el primer spinner
        prefCompraSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtenemos la opción seleccionada en el primer spinner
                String opcionSeleccionada = parent.getItemAtPosition(position).toString();

                // Actualizamos el segundo spinner en función de la opción seleccionada
                if (opcionSeleccionada.equals("Hombre")) {
                    ArrayAdapter<CharSequence> tallasHombreAdaptador = ArrayAdapter.createFromResource(
                            requireActivity(),
                            R.array.tallasHombre,
                            android.R.layout.simple_spinner_item
                    );
                    tallasHombreAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    nZapatillasSpinner.setAdapter(tallasHombreAdaptador);
                } else if (opcionSeleccionada.equals("Mujer")) {
                    ArrayAdapter<CharSequence> tallasMujerAdaptador = ArrayAdapter.createFromResource(
                            requireActivity(),
                            R.array.tallasMujer,
                            android.R.layout.simple_spinner_item
                    );
                    tallasMujerAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    nZapatillasSpinner.setAdapter(tallasMujerAdaptador);
                } else if (opcionSeleccionada.equals("")) {
                    ArrayAdapter<CharSequence> todasAdapatador = ArrayAdapter.createFromResource(
                            requireActivity(),
                            R.array.todasTallas,
                            android.R.layout.simple_spinner_item
                    );
                    todasAdapatador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    nZapatillasSpinner.setAdapter(todasAdapatador);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

}