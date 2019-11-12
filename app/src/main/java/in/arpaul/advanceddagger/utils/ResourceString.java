package in.arpaul.advanceddagger.utils;

import android.content.Context;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public abstract class ResourceString {
    public abstract String format(Context context);

    public static class IdResourceString extends ResourceString {
        private int id;
        public IdResourceString(int id) {
            this.id = id;
        }

        @Override
        public String format(Context context) {
            return context.getString(id);
        }

    }

    public static class TextResourceString extends ResourceString {
        private String text;
        public TextResourceString(String text) {
            this.text = text;
        }

        @Override
        public String format(Context context) {
            return text;
        }
    }
    public static class FormatResourceString extends ResourceString {
        private int id;
        private ArrayList<String> values;
        public FormatResourceString(int id, ArrayList<String> values) {
            this.id = id;
            this.values = values;
        }

        @Override
        public String format(Context context) {
            return context.getString(id, values);
        }
    }
}
