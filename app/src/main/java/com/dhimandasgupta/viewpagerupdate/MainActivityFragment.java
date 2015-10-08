package com.dhimandasgupta.viewpagerupdate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String MODEL_TAG = "model";

    public static MainActivityFragment newInstance(final Model model) {
        final MainActivityFragment fragment = new MainActivityFragment();

        final Bundle args = new Bundle();
        args.putParcelable(MODEL_TAG, model);

        fragment.setArguments(args);

        return fragment;
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView textView = (TextView) view.findViewById(R.id.fragment_main_text_view);
        final View fullView = view.findViewById(R.id.fragment_main_view);

        final Model model = getArguments().getParcelable(MODEL_TAG);

        textView.setText(model.mText);
        fullView.setBackgroundColor(model.mColor);

        if (model.mShowLayout) {
            textView.setVisibility(View.GONE);
            fullView.setVisibility(View.VISIBLE);
        } else {
            fullView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
