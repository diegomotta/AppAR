package com.appar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentUNO extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_uno, container, false);
		Bundle bundle = getArguments();
		String recuperada = bundle.getString("str");
		TextView texto = (TextView) view.findViewById(R.id.txtDescripGeneral);
		texto.setText(recuperada);
		return view;
	}
	
}
