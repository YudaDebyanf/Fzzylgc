package com.faza.project.fuzzylogicexample.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.faza.project.fuzzylogicexample.FuzzyLogicExample;
import com.faza.project.fuzzylogicexample.R;

/**
 * Dibuat oleh Faza Zulfika Permana Putra
 */

public class MainActivity extends AppCompatActivity {

    public Button btnNext;
    public EditText etDemandMin, etDemandMax, etStockMin, etStockMax, etProductionMin, etProductionMax;
    public TextInputLayout tilDemandMin, tilDemandMax, tilStockMin, tilStockMax, tilProductionMin, tilProductionMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDemandMin = (EditText) findViewById(R.id.et_demand_min);
        etDemandMax = (EditText) findViewById(R.id.et_demand_max);
        etStockMin = (EditText) findViewById(R.id.et_stock_min);
        etStockMax = (EditText) findViewById(R.id.et_stock_max);
        etProductionMin = (EditText) findViewById(R.id.et_production_min);
        etProductionMax = (EditText) findViewById(R.id.et_production_max);

        tilDemandMin = (TextInputLayout) findViewById(R.id.til_demand_min);
        tilDemandMax = (TextInputLayout) findViewById(R.id.til_demand_max);
        tilStockMin = (TextInputLayout) findViewById(R.id.til_stock_min);
        tilStockMax = (TextInputLayout) findViewById(R.id.til_stock_max);
        tilProductionMin = (TextInputLayout) findViewById(R.id.til_production_min);
        tilProductionMax = (TextInputLayout) findViewById(R.id.til_production_max);

        etDemandMin.addTextChangedListener(new InputChangeListener(tilDemandMin));
        etDemandMax.addTextChangedListener(new InputChangeListener(tilDemandMax));
        etStockMin.addTextChangedListener(new InputChangeListener(tilStockMin));
        etStockMax.addTextChangedListener(new InputChangeListener(tilStockMax));
        etProductionMin.addTextChangedListener(new InputChangeListener(tilProductionMin));
        etProductionMax.addTextChangedListener(new InputChangeListener(tilProductionMax));

        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new NextClickListener());

        clearInput();
    }

    private void checkInput(TextInputLayout textInputLayout, String s) {
        if (s.equals("")) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Input tidak dapat kosong...");

            checkError();

            return;
        } else
            textInputLayout.setErrorEnabled(false);

        Integer inputInt = Integer.parseInt(s);

        if (inputInt < 0) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Input tidak dapat minus...");

            checkError();

            return;
        } else
            textInputLayout.setErrorEnabled(false);

        checkError();
    }

    private void checkError() {
        boolean demandMin = tilDemandMin.isErrorEnabled();
        boolean demandMax = tilDemandMax.isErrorEnabled();
        boolean stockMin = tilStockMin.isErrorEnabled();
        boolean stockMax = tilStockMax.isErrorEnabled();
        boolean productionMin = tilProductionMin.isErrorEnabled();
        boolean productionMax = tilProductionMax.isErrorEnabled();

        int color;

        if (demandMin || demandMax || stockMin || stockMax || productionMin || productionMax) {
            color = ContextCompat.getColor(this, R.color.colorGray);
            btnNext.setEnabled(false);
        } else {
            color = ContextCompat.getColor(this, R.color.colorPrimary);
            btnNext.setEnabled(true);
        }

        btnNext.setBackgroundColor(color);
    }

    private void clearInput() {
        etDemandMin.setText("");
        etDemandMax.setText("");
        etStockMin.setText("");
        etStockMax.setText("");
        etProductionMin.setText("");
        etProductionMax.setText("");
    }

    private class InputChangeListener implements TextWatcher {

        private TextInputLayout textInputLayout;

        private InputChangeListener(TextInputLayout textInputLayout) {
            this.textInputLayout = textInputLayout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String string = s.toString();
            checkInput(textInputLayout, string);
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Do nothin
        }
    }

    private class NextClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String demandMin = etDemandMin.getText().toString();
            String demandMax = etDemandMax.getText().toString();
            String stockMin = etStockMin.getText().toString();
            String stockMax = etStockMax.getText().toString();
            String productionMin = etProductionMin.getText().toString();
            String productionMax = etProductionMax.getText().toString();

            Integer demandMinInt = Integer.parseInt(demandMin);
            Integer demandMaxInt = Integer.parseInt(demandMax);
            Integer stockMinInt = Integer.parseInt(stockMin);
            Integer stockMaxInt = Integer.parseInt(stockMax);
            Integer productionMinInt = Integer.parseInt(productionMin);
            Integer productionMaxInt = Integer.parseInt(productionMax);

            clearInput();

            FuzzyLogicExample.setDemandSets(demandMinInt, demandMaxInt);
            FuzzyLogicExample.setStockSets(stockMinInt, stockMaxInt);

            FuzzyLogicExample.setProductionMin(productionMinInt);
            FuzzyLogicExample.setProductionMax(productionMaxInt);

            Intent intent = new Intent(MainActivity.this, ProductionActivity.class);
            startActivity(intent);
        }
    }
}
