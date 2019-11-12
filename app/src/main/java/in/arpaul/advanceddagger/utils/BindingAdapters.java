package in.arpaul.advanceddagger.utils;

import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("app:textChangedListener")
    public static void bindTextChangeListener(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }
}
