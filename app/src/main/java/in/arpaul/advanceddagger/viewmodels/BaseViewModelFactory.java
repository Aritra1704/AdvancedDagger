package in.arpaul.advanceddagger.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BaseViewModelFactory implements ViewModelProvider.Factory {
    private final BaseVM baseVm;

    public BaseViewModelFactory(BaseVM baseVm) {
        this.baseVm = baseVm;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel;
        if (modelClass == BaseVM.class) {
            viewModel = baseVm;
        }
        else {
            throw new RuntimeException("unsupported view model class: " + modelClass);
        }

        return (T) viewModel;
    }
}
