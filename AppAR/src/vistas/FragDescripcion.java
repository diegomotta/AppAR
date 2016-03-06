package vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appar.R;

public class FragDescripcion extends android.app.Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_uno, container, false);
		Bundle bundle = getArguments();
		String recuperada = bundle.getString("str");
		return view;
	}
	
}
