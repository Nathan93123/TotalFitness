package net.kisangan.totalfitness.ui.physicalhealth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import net.kisangan.totalfitness.R;
import net.kisangan.totalfitness.databinding.FragmentPhysicalHealthBinding;

public class PhysicalHealthFragment extends Fragment {
    private FragmentPhysicalHealthBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPhysicalHealthBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        popSpinner(root, binding.massUnitSpinner, R.array.mass_units_array);
        popSpinner(root, binding.activityLevelSpinner, R.array.physical_activity_level_array);

        return root;
    }

    private void popSpinner(View root, Spinner p, int p2) {
        ArrayAdapter<CharSequence> activity_level_adapter = ArrayAdapter.createFromResource(root.getContext(),
                p2, android.R.layout.simple_spinner_item);
        activity_level_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        p.setAdapter(activity_level_adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}