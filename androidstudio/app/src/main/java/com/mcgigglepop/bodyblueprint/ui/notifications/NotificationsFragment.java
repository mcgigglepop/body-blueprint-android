package com.mcgigglepop.bodyblueprint.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mcgigglepop.bodyblueprint.R;
import com.mcgigglepop.bodyblueprint.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

import helpers.DatabaseHelper;
import helpers.InputValidation;
import models.User;

public class NotificationsFragment extends Fragment {
    // layout
    private FragmentNotificationsBinding binding;

    // age
    private TextInputLayout ageTextInputLayout;
    private TextInputEditText ageTextInputEditText;

    // gender
    private TextInputLayout genderTextInputLayout;
    private AutoCompleteTextView genderAutoCompleteTextView;

    // current weight
    private TextInputLayout currentWeightTextInputLayout;
    private TextInputEditText currentWeightTextInputEditText;
    private AutoCompleteTextView currentWeightTypeAutoCompleteTextView;

    // height
    private TextInputLayout heightFeetTextInputLayout;
    private TextInputEditText heightFeetTextInputEditText;
    private TextInputLayout heightInchesTextInputLayout;
    private TextInputEditText heightInchesTextInputEditText;

    //lifestyle
    private AutoCompleteTextView lifestyleAutoCompleteTextView;

    // target weight
    private TextInputLayout targetWeightTextInputLayout;
    private TextInputEditText targetWeightTextInputEditText;
    private AutoCompleteTextView targetWeightTypeAutoCompleteTextView;

    // helpers
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initViews(root);
        initObjects();

        // save user data button
        final Button saveButton = binding.saveMaterialButton;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verify input fields and persist data to the SQLite Database
                verifyFromSQLite();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initViews(View view) {
        // age
        ageTextInputLayout = view.findViewById(R.id.ageTextInputLayout);
        ageTextInputEditText = view.findViewById(R.id.ageTextInputEditText);

        // gender
        genderTextInputLayout = view.findViewById(R.id.genderTextInputLayout);
        genderAutoCompleteTextView = view.findViewById(R.id.genderAutoCompleteTextView);

        // current weight
        currentWeightTextInputLayout = view.findViewById(R.id.currentWeightTextInputLayout);
        currentWeightTextInputEditText = view.findViewById(R.id.currentWeightTextInputEditText);
        currentWeightTypeAutoCompleteTextView = view.findViewById(R.id.currentWeightTypeAutoCompleteTextView);

        //height
        heightFeetTextInputLayout = view.findViewById(R.id.heightFeetTextInputLayout);
        heightFeetTextInputEditText = view.findViewById(R.id.heightFeetTextInputEditText);
        heightInchesTextInputLayout = view.findViewById(R.id.heightInchesTextInputLayout);
        heightInchesTextInputEditText = view.findViewById(R.id.heightInchesTextInputEditText);

        // lifestyle
        lifestyleAutoCompleteTextView = view.findViewById(R.id.lifestyleAutoCompleteTextView);

        // target weight
        targetWeightTextInputLayout = view.findViewById(R.id.targetWeightTextInputLayout);
        targetWeightTextInputEditText = view.findViewById(R.id.targetWeightTextInputEditText);
        targetWeightTypeAutoCompleteTextView = view.findViewById(R.id.targetWeightTypeAutoCompleteTextView);
    }

    private void initObjects() {
        inputValidation = new InputValidation(requireContext());
        databaseHelper = new DatabaseHelper(requireContext());
        user = new User();
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(ageTextInputEditText, ageTextInputLayout, getString(R.string.error_message_age))) {
            return;
        }
        if (!inputValidation.isTextInputLayoutFilled(genderAutoCompleteTextView, genderTextInputLayout, getString(R.string.error_message_gender))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(currentWeightTextInputEditText, currentWeightTextInputLayout, getString(R.string.error_message_current_weight))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(heightFeetTextInputEditText, heightFeetTextInputLayout, getString(R.string.error_message_height_feet))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(heightInchesTextInputEditText, heightInchesTextInputLayout, getString(R.string.error_message_height_inches))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(targetWeightTextInputEditText, targetWeightTextInputLayout, getString(R.string.error_message_target_weight))) {
            return;
        }

        // calculate daily caloric needs
        // https://www.kaged.com/blogs/nutrition/calculate-your-calories

        // logic to convert to lbs
        int weightInPounds;
        if(currentWeightTypeAutoCompleteTextView.getText().toString().trim().equals("lbs")){
            weightInPounds = Integer.parseInt(currentWeightTextInputEditText.getText().toString().trim());
        }else{
            weightInPounds = (int) (Integer.parseInt(currentWeightTextInputEditText.getText().toString().trim()) * 2.20462);
        }

        // convert height to inches
        int feetInInches = (Integer.parseInt(heightFeetTextInputEditText.getText().toString().trim()) * 12);
        int heightInInches = Integer.parseInt(heightInchesTextInputEditText.getText().toString().trim()) + feetInInches;
        int years = Integer.parseInt(ageTextInputEditText.getText().toString().trim());

        double bmr;
        if (genderAutoCompleteTextView.getText().toString().trim().equals("male")) {
            bmr = (4.53 * weightInPounds) + (15.88 * heightInInches) - (4.92 * years) + 5;
        }else {
            bmr = (4.53 * weightInPounds) + (15.88 * heightInInches) - years -16;
        }

        double dab;
        if (lifestyleAutoCompleteTextView.getText().toString().trim().equals("sedentary")) {
            dab = bmr * 1.2;
        }else if (lifestyleAutoCompleteTextView.getText().toString().trim().equals("moderate")){
            dab = bmr * 1.5;
        }else {
            dab = bmr * 1.9;
        }

        int computedBMR = (int) bmr;
        int computedDAB = (int) dab;

        Log.i("basal metabolic rate", ""+ computedBMR);
        Log.i("daily activity burn", ""+ computedDAB);


        boolean checkUser = databaseHelper.checkFirstUser();

        if (!databaseHelper.checkFirstUser()){
            //create user
//            user.setName(textInputEditTextName.getText().toString().trim());
        }else {
            Log.i("TAG", "verifyFromSQLite: update user");
        }




//        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
//            user.setName(textInputEditTextName.getText().toString().trim());
//            user.setEmail(textInputEditTextEmail.getText().toString().trim());
//            user.setPassword(textInputEditTextPassword.getText().toString().trim());
//            databaseHelper.addUser(user);
//            // Snack Bar to show success message that record saved successfully
//            new AlertDialog.Builder(requireContext())
//                    .setTitle("Error")
//                    .setMessage(R.string.success_message)
//                    .setPositiveButton("OK", null) // You can add a listener if needed
//                    .show();
//            emptyInputEditText();
//        } else {
//            // Snack Bar to show error message that record already exists
//            new AlertDialog.Builder(requireContext())
//                    .setTitle("Error")
//                    .setMessage(R.string.error_email_exists)
//                    .setPositiveButton("OK", null) // You can add a listener if needed
//                    .show();
//        }

    }
}