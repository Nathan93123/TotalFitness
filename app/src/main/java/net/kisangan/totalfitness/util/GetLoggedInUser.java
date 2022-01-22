package net.kisangan.totalfitness.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.kisangan.totalfitness.ui.login.LoginActivity;

public class GetLoggedInUser extends ActivityResultContract<Void, String> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Void nothing) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public String parseResult(int resultCode, @Nullable Intent result) {
        if (resultCode != Activity.RESULT_OK || result == null) {
            return null;
        }
        return result.getStringExtra("user");
    }
}
