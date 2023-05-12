package com.example.vussnkrs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinnerAdapter extends ArrayAdapter<CharSequence> {

    public SpinnerAdapter(Context context, int resource, CharSequence[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (position == 0) {
            // si es la primera posición, se devuelve una vista vacía para que se oculte
            return new View(getContext());
        } else {
            // en caso contrario, se llama al método de la superclase
            return super.getDropDownView(position, null, parent);
        }
    }
}
