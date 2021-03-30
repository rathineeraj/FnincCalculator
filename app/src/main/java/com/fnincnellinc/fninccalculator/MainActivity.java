package com.fnincnellinc.fninccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button mCalTransferFees, mCalBondCost, mCalMonthlyInstallment,
            mCalculateAll, mReset, mEmail;
    EditText mPurchasePrice, mBondCost, mBondAmtCal, mInterestRate,
            mRepaymentPeriod;
    RadioGroup mVat;
    TextView mTotalTransferCost, mTotalBondCost, mTotalAllCost;
    // TextViews for TransferCost
    TextView Trans_PurchasePrice, Trans_TransferDuty, Trans_DeedOffice,
            Trans_Conveyancer, Trans_Petties, Trans_DeedSearchFee, Trans_Fica,
            Trans_Ele, Trans_Rates, Trans_Vat, Trans_Total;
    // TextViews for Bond Cost
    TextView Bond_Amount, Bond_DeedOffice, Bond_Conveyancer, Bond_Petties,
            Bond_DeedSearchFee, Bond_Fica, Bond_Ele, Bond_Vat, Bond_Total,
            mMonthlyInstallment;

    // Variables for TrasferCost
    double TransferDuty, DeedsOfficeExaminationFee, ConveyancerTariffFee,
            PettiesSundryDisbursements, DeedsOfficeSearchFee,
            FICAComplianceFee, ElectronicDocumentGenerationFee,
            RatesClearanceCertificateCosts, VATonConveyancerTariffFee,
            TotalTransferCost, PurchasePrice;

    String sTransferDuty, sDeedsOfficeExaminationFee, sConveyancerTariffFee,
            sPettiesSundryDisbursements, sDeedsOfficeSearchFee,
            sFICAComplianceFee, sElectronicDocumentGenerationFee,
            sRatesClearanceCertificateCosts, sVATonConveyancerTariffFee,
            sTotalTransferCost, sPurchasePrice;
    // Variables for BondCost
    double BondAmount, Bond_DeedsOfficeExaminationFee,
            Bond_ConveyancerTariffFee, Bond_PettieSundryDisbursements,
            Bond_DeedsOfficeSearchFee, Bond_FICAComplianceFee,
            Bond_ElectronicDocumentGenerationFee,
            Bond_VATonConveyancerTariffFee, TotalBondCost;
    String sBondAmount, sBond_DeedsOfficeExaminationFee,
            sBond_ConveyancerTariffFee, sBond_PettieSundryDisbursements,
            sBond_DeedsOfficeSearchFee, sBond_FICAComplianceFee,
            sBond_ElectronicDocumentGenerationFee,
            sBond_VATonConveyancerTariffFee, sTotalBondCost;

    double TotalCost, Deposit;
    String sTotalCost;
    EditText mDeposit;

    int VatVendor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        try {
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            DecimalFormat df = new DecimalFormat("#,###.##");
            df.setDecimalSeparatorAlwaysShown(true);

            mEmail = findViewById(R.id.btnEmail);
            mEmail.setOnClickListener(this);
            mCalTransferFees = findViewById(R.id.btnCalTransFees);
            mPurchasePrice = findViewById(R.id.etPurchasePrice);
            mPurchasePrice.setInputType(InputType.TYPE_CLASS_NUMBER);
            mCalTransferFees.setOnClickListener(this);

            mCalBondCost = findViewById(R.id.btnCalBondCost);
            mBondCost = findViewById(R.id.etBondAmt);
            mBondCost.setInputType(InputType.TYPE_CLASS_NUMBER);
            mCalBondCost.setOnClickListener(this);


            mTotalTransferCost = findViewById(R.id.tvTotalTransferCost);
            mTotalBondCost = findViewById(R.id.tvTotalBondCost);
            mTotalAllCost = findViewById(R.id.tvTotalCost);

            // Transfer Cost Textviews
            Trans_PurchasePrice = findViewById(R.id.tvPurchasePrice);
            Trans_TransferDuty = findViewById(R.id.tvtransferDuty);
            Trans_DeedOffice = findViewById(R.id.tvExam);
            Trans_Fica = findViewById(R.id.tvFicaFee);
            Trans_Conveyancer = findViewById(R.id.tvTraiffFee);
            Trans_Petties = findViewById(R.id.tvPettiesSundry);
            Trans_DeedSearchFee = findViewById(R.id.tvDeedsSearchFee);
            Trans_Ele = findViewById(R.id.tvEleDocFee);
            Trans_Rates = findViewById(R.id.tvRatesCleCost);
            Trans_Vat = findViewById(R.id.tvVatTraiffFee);
            Trans_Total = findViewById(R.id.tvTotalTrans);
            // Bond TextViews
            Bond_Amount = findViewById(R.id.tvBondAmount);
            Bond_DeedOffice = findViewById(R.id.tvExamBond);
            Bond_Conveyancer = findViewById(R.id.tvConveyancerBond);
            Bond_DeedSearchFee = findViewById(R.id.tvDeedsSearchFeeBond);
            Bond_Petties = findViewById(R.id.tvPettiesSundryBond);
            Bond_Fica = findViewById(R.id.tvFicaFeeBond);
            Bond_Ele = findViewById(R.id.tvEleDocFeeBond);
            Bond_Vat = findViewById(R.id.tvVatBond);
            Bond_Total = findViewById(R.id.tvBondTotal);

            // Bond Calculator
            mBondAmtCal = findViewById(R.id.etBondCalAmt);
            mBondAmtCal.setInputType(InputType.TYPE_CLASS_NUMBER);
            mInterestRate = findViewById(R.id.etInterestRate);
            mInterestRate.setKeyListener(DigitsKeyListener
                    .getInstance("0123456789."));
            mInterestRate.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            mRepaymentPeriod = findViewById(R.id.etRepaymentPeriod);
            mRepaymentPeriod.setInputType(InputType.TYPE_CLASS_NUMBER);
            mMonthlyInstallment = findViewById(R.id.tvMonthly);

            mCalMonthlyInstallment = findViewById(R.id.btnInstallmentCal);
            mCalMonthlyInstallment.setOnClickListener(this);
            mInterestRate.setText(R.string.interest_rate);
            mRepaymentPeriod.setText(R.string.repayment_peroiod);

            // Calculate All
            mCalculateAll = findViewById(R.id.btnTotal);
            mCalculateAll.setOnClickListener(this);
            mDeposit = findViewById(R.id.etDeposit);
            mDeposit.setInputType(InputType.TYPE_CLASS_NUMBER);

            mReset = findViewById(R.id.btnReset);
            mReset.setOnClickListener(this);

            mVat = findViewById(R.id.rdgVat);
            mVat.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rbtnVat) {
                        VatVendor = 1;
                    } else if (checkedId == R.id.rbtnVatNot) {
                        VatVendor = 0;
                    }

                }
            });

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnEmail:

                String EmailBody = "Hi,\n\nI've Calculated Transfer and Bond Cost from FNINC Cost Calculator app."
                        + "This is very good app you can also download from google play and app store."
                        + "Here is urls : \n\nhttps://play.google.com/store/apps/details?id=com.fnincnellinc.fninccalculator&hl=en"
                        + "\n\nhttps://itunes.apple.com/in/app/fninc-cost-calculator/id1062545420?mt=8\n"
                        + "\nPurchase Price :"
                        + sPurchasePrice
                        + "\nBond Amount : "
                        + sBondAmount
                        + "\nDeposit or Cash Amount : "
                        + Deposit
                        + "\n\nTransfer Costs : "
                        + sTotalTransferCost
                        + "\nBond Costs : "
                        + sTotalBondCost
                        + "\nTotal Costs : "
                        + sTotalCost
                        + "\n\n\nTransfer Costs\nPurchase Price : "
                        + sPurchasePrice
                        + "\nTransfer Duty (non vatable) : "
                        + sTransferDuty
                        + "\nDeeds Office Examination Fee (non vatable) : "
                        + sDeedsOfficeExaminationFee
                        + "\nConveyancer Tariff Fee : "
                        + sConveyancerTariffFee
                        + "\nPetties and Sundry Disbursements : "
                        + sPettiesSundryDisbursements
                        + "\nDeeds Office Search Fee : "
                        + sDeedsOfficeSearchFee
                        + "\nFICA Compliance Fee : "
                        + sFICAComplianceFee
                        + "\nElectronic Document Generation Fee : "
                        + sElectronicDocumentGenerationFee
                        + "\nRates Clearance Certificate Costs : "
                        + sRatesClearanceCertificateCosts
                        + "\nVAT on Conveyancer Tariff Fee : "
                        + sVATonConveyancerTariffFee
                        + "\nTotal : "
                        + sTotalTransferCost
                        + "\n\n\nBond Costs\nBond Amount : "
                        + sBondAmount
                        + "\nDeeds Office Examination Fee (non vatable) : "
                        + sBond_DeedsOfficeExaminationFee
                        + "\nConveyancer Tariff Fee : "
                        + sBond_ConveyancerTariffFee
                        + "\nPetties and Sundry Disbursements : "
                        + sBond_PettieSundryDisbursements
                        + "\nDeeds Office Search Fee : "
                        + sBond_DeedsOfficeSearchFee
                        + "\nFICA Compliance Fee : "
                        + sBond_FICAComplianceFee
                        + "\nElectronic Document Generation Fee : "
                        + sBond_ElectronicDocumentGenerationFee
                        + "\nVAT on Conveyancer Tariff Fee : "
                        + sBond_VATonConveyancerTariffFee
                        + "\nTotal : "
                        + sTotalBondCost
                        + "\n\n\nBond Calculator\nBond Amount : "
                        + (mBondAmtCal.getText().toString()).replaceAll("\\s+", "")
                        + "\nInterest Rate : "
                        + (mInterestRate.getText().toString())
                        + "\nRepayment Period : "
                        + (mRepaymentPeriod.getText().toString())
                        + "\nMonthly Instalment : "
                        + mMonthlyInstallment.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");

                intent.putExtra(Intent.EXTRA_SUBJECT, "Transfer & Bond Costs");
                intent.putExtra(Intent.EXTRA_TEXT, EmailBody);
                startActivity(Intent.createChooser(intent, ""));

                break;
            case R.id.btnCalTransFees:
                if (TextUtils.isEmpty(mPurchasePrice.getText().toString())) {
                    mPurchasePrice.setError("Please Enter Purchase Price");
                    return;
                }
                CalTotalTransferFees();
                mTotalTransferCost.setText(addCommaEx(sTotalTransferCost));

                break;
            case R.id.btnCalBondCost:
                if (TextUtils.isEmpty(mBondCost.getText().toString())) {
                    mBondCost.setError("Please Enter Bond Cost");
                    return;
                }
                String tempBondAmt = (mBondCost.getText().toString()).replaceAll(
                        "\\s+", "");
                String tempBondAmt1 = (tempBondAmt).replace(",", "");
                BondAmount = Double.parseDouble(tempBondAmt1.replace(" ", ""));

                CalTotalBondCost();
                mTotalBondCost.setText(addCommaEx(sTotalBondCost));
                String msBond = String.format("%.2f", BondAmount);
                mBondAmtCal.setText(addCommaEx(msBond));
                CalculateMonthlyInstallment();

                break;
            case R.id.btnTotal:
                if (TextUtils.isEmpty(mPurchasePrice.getText().toString())) {
                    mPurchasePrice.setError("Please Enter Purchase Price");
                    return;
                }
                if (TextUtils.isEmpty(mBondCost.getText().toString())) {
                    mBondCost.setError("Please Enter Bond Cost");
                    return;
                }
                String tempBond = (mBondCost.getText().toString()).replaceAll(
                        "\\s+", "");
                String tempBond1 = (tempBond).replace(",", "");
                BondAmount = Double.parseDouble(tempBond1.replace(" ", ""));
                if (!TextUtils.isEmpty(mDeposit.getText().toString())) {
                    String tempDesposit = (mDeposit.getText().toString())
                            .replaceAll("\\s+", "");
                    String tempDeposit1 = (tempDesposit).replace(",", "");
                    Deposit = Double.parseDouble(tempDeposit1.replace(" ", ""));


                }

                CalTotalTransferFees();
                CalTotalBondCost();

                mTotalTransferCost.setText(addCommaEx(sTotalTransferCost));
                TotalCost = TotalTransferCost + TotalBondCost;
                sTotalCost = String.format("%.2f", TotalCost);
                mTotalAllCost.setText(addCommaEx(sTotalCost));
                mTotalBondCost.setText(addCommaEx(sTotalBondCost));
                String mbondAmt = String.format("%.2f", BondAmount);
                mBondAmtCal.setText(addCommaEx(mbondAmt));
                CalculateMonthlyInstallment();

                break;
            case R.id.btnInstallmentCal:
                if (TextUtils.isEmpty(mBondAmtCal.getText().toString())) {
                    mBondAmtCal.setError("Please Enter Bond Cost");
                    return;
                }
                if (TextUtils.isEmpty(mInterestRate.getText().toString())) {
                    mInterestRate.setError("Please Enter Interest Rate");
                    return;
                }
                if (TextUtils.isEmpty(mRepaymentPeriod.getText().toString())) {
                    mRepaymentPeriod.setError("Please Enter Repayment Period");
                    return;
                }

                CalculateMonthlyInstallment();
                break;
            case R.id.btnReset:

                mPurchasePrice.setText("");
                mBondCost.setText("");
                // mTotalCost.setText("");
                mBondAmtCal.setText("");
                mInterestRate.setText(R.string.interest_rate);
                mRepaymentPeriod.setText(R.string.repayment_peroiod);
                mMonthlyInstallment.setText("");
                mTotalTransferCost.setText("");
                mTotalBondCost.setText("");
                mTotalAllCost.setText("");
                // TextViews for TransferCost
                Trans_PurchasePrice.setText("");
                Trans_TransferDuty.setText("");
                Trans_DeedOffice.setText("");
                Trans_Conveyancer.setText("");
                Trans_Petties.setText("");
                Trans_DeedSearchFee.setText("");
                Trans_Fica.setText("");
                Trans_Ele.setText("");
                Trans_Rates.setText("");
                Trans_Vat.setText("");
                Trans_Total.setText("");
                // TextViews for Bond Cost
                Bond_Amount.setText("");
                Bond_DeedOffice.setText("");
                Bond_Conveyancer.setText("");
                Bond_Petties.setText("");
                Bond_DeedSearchFee.setText("");
                Bond_Fica.setText("");
                Bond_Ele.setText("");
                Bond_Vat.setText("");
                Bond_Total.setText("");

                mDeposit.setText("");
                TransferDuty = 0;
                DeedsOfficeExaminationFee = 0;
                ConveyancerTariffFee = 0;
                PettiesSundryDisbursements = 0;
                DeedsOfficeSearchFee = 0;
                FICAComplianceFee = 0;
                ElectronicDocumentGenerationFee = 0;
                RatesClearanceCertificateCosts = 0;
                VATonConveyancerTariffFee = 0;
                TotalTransferCost = 0;
                PurchasePrice = 0;
                sTransferDuty = "0";
                sDeedsOfficeExaminationFee = "0";
                sConveyancerTariffFee = "0";
                sPettiesSundryDisbursements = "0";
                sDeedsOfficeSearchFee = "0";
                sFICAComplianceFee = "0";
                sElectronicDocumentGenerationFee = "0";
                sRatesClearanceCertificateCosts = "0";
                sVATonConveyancerTariffFee = "0";
                sTotalTransferCost = "0";
                sPurchasePrice = "0";
                BondAmount = 0;
                Bond_DeedsOfficeExaminationFee = 0;
                Bond_ConveyancerTariffFee = 0;
                Bond_PettieSundryDisbursements = 0;
                Bond_DeedsOfficeSearchFee = 0;
                Bond_FICAComplianceFee = 0;
                Bond_ElectronicDocumentGenerationFee = 0;
                Bond_VATonConveyancerTariffFee = 0;
                TotalBondCost = 0;
                sBondAmount = "0";
                sBond_DeedsOfficeExaminationFee = "0";
                sBond_ConveyancerTariffFee = "0";
                sBond_PettieSundryDisbursements = "0";
                sBond_DeedsOfficeSearchFee = "0";
                sBond_FICAComplianceFee = "0";
                sBond_ElectronicDocumentGenerationFee = "0";
                sBond_VATonConveyancerTariffFee = "0";
                sTotalBondCost = "0";
                TotalCost = 0;
                Deposit = 0;
                sTotalCost = "0";
                break;

        }

    }

    private void CalTotalTransferFees() {
        String tempPP = (mPurchasePrice.getText().toString()).replaceAll(
                "\\s+", "");
        String tempPPP = (tempPP).replace(",", "");
        PurchasePrice = Double.parseDouble(tempPPP.replace(" ", ""));


        sPurchasePrice = (String.format("%.2f", PurchasePrice));

        TransferDuty = getTransferDuty(PurchasePrice);

        sTransferDuty = (String.format("%.2f", TransferDuty));

        DeedsOfficeExaminationFee = getDeedsOfficeExaminationFee(PurchasePrice);

        sDeedsOfficeExaminationFee = (String.format("%.2f",
                DeedsOfficeExaminationFee));

        ConveyancerTariffFee = getConveyancerTariffFee(PurchasePrice);

        sConveyancerTariffFee = (String.format("%.2f", ConveyancerTariffFee));

        PettiesSundryDisbursements = 1150;
        sPettiesSundryDisbursements = (String.format("%.2f",
                PettiesSundryDisbursements));

        DeedsOfficeSearchFee = 402.50;
        sDeedsOfficeSearchFee = (String.format("%.2f", DeedsOfficeSearchFee));

        FICAComplianceFee = 345;
        sFICAComplianceFee = (String.format("%.2f", FICAComplianceFee));

        ElectronicDocumentGenerationFee = 805;
        sElectronicDocumentGenerationFee = (String.format("%.2f",
                ElectronicDocumentGenerationFee));

        RatesClearanceCertificateCosts = 648.03;
        sRatesClearanceCertificateCosts = (String.format("%.2f",
                RatesClearanceCertificateCosts));

        VATonConveyancerTariffFee = GetVATonConveyancerTariffFee(ConveyancerTariffFee);

        sVATonConveyancerTariffFee = (String.format("%.2f",
                VATonConveyancerTariffFee));

        TotalTransferCost = Double.parseDouble((sTransferDuty)
                .replace(",", "."))
                + Double.parseDouble((sDeedsOfficeExaminationFee).replace(",",
                "."))
                + Double.parseDouble((sConveyancerTariffFee).replace(",", "."))
                + Double.parseDouble((sPettiesSundryDisbursements).replace(",",
                "."))
                + Double.parseDouble((sDeedsOfficeSearchFee).replace(",", "."))
                + Double.parseDouble((sFICAComplianceFee).replace(",", "."))
                + Double.parseDouble((sElectronicDocumentGenerationFee)
                .replace(",", "."))
                + Double.parseDouble((sRatesClearanceCertificateCosts).replace(
                ",", "."))
                + Double.parseDouble((sVATonConveyancerTariffFee).replace(",",
                "."));
        sTotalTransferCost = (String.format("%.2f", TotalTransferCost));

        Trans_PurchasePrice.setText(addCommaEx((sPurchasePrice)));
        Trans_TransferDuty.setText(addCommaEx(sTransferDuty));
        Trans_DeedOffice.setText(addCommaEx(sDeedsOfficeExaminationFee));
        Trans_Conveyancer.setText(addCommaEx(sConveyancerTariffFee));
        Trans_Petties.setText(addCommaEx(sPettiesSundryDisbursements));
        Trans_DeedSearchFee.setText(addCommaEx(sDeedsOfficeSearchFee));
        Trans_Fica.setText(addCommaEx(sFICAComplianceFee));
        Trans_Ele.setText(addCommaEx(sElectronicDocumentGenerationFee));
        Trans_Rates.setText(addCommaEx(sRatesClearanceCertificateCosts));
        Trans_Vat.setText(addCommaEx(sVATonConveyancerTariffFee));
        Trans_Total.setText(addCommaEx(sTotalTransferCost));

    }

    //31-05-2018
    private void CalTotalBondCost() {

        sBondAmount = (String.format("%.2f", BondAmount));

        Bond_DeedsOfficeExaminationFee = getBond_DeedsOfficeExaminationFee(BondAmount);

        sBond_DeedsOfficeExaminationFee = (String.format("%.2f",
                Bond_DeedsOfficeExaminationFee));

        Bond_ConveyancerTariffFee = getConveyancerTariffFee(BondAmount);

        sBond_ConveyancerTariffFee = (String.format("%.2f",
                Bond_ConveyancerTariffFee));

        Bond_PettieSundryDisbursements = 1150; //change 04042019
        sBond_PettieSundryDisbursements = (String.format("%.2f",
                Bond_PettieSundryDisbursements));

        Bond_DeedsOfficeSearchFee = 402.50;
        sBond_DeedsOfficeSearchFee = (String.format("%.2f",
                Bond_DeedsOfficeSearchFee));

        Bond_FICAComplianceFee = 345;
        sBond_FICAComplianceFee = (String
                .format("%.2f", Bond_FICAComplianceFee));

        Bond_ElectronicDocumentGenerationFee = 805;
        sBond_ElectronicDocumentGenerationFee = (String.format("%.2f",
                Bond_ElectronicDocumentGenerationFee));

        Bond_VATonConveyancerTariffFee = getBond_VATonConveyancerTariffFee(Bond_ConveyancerTariffFee);

        sBond_VATonConveyancerTariffFee = (String.format("%.2f",
                Bond_VATonConveyancerTariffFee));

        TotalBondCost = Double.parseDouble((sBond_DeedsOfficeExaminationFee)
                .replace(",", "."))
                + Double.parseDouble((sBond_ConveyancerTariffFee).replace(",",
                "."))
                + Double.parseDouble((sBond_PettieSundryDisbursements).replace(
                ",", "."))
                + Double.parseDouble((sBond_DeedsOfficeSearchFee).replace(",",
                "."))
                + Double.parseDouble((sBond_FICAComplianceFee)
                .replace(",", "."))
                + Double.parseDouble((sBond_ElectronicDocumentGenerationFee)
                .replace(",", "."))
                + Double.parseDouble((sBond_VATonConveyancerTariffFee).replace(
                ",", "."));

        sTotalBondCost = (String.format("%.2f", TotalBondCost));

        Bond_Amount.setText(addCommaEx(sBondAmount));
        Bond_DeedOffice.setText(addCommaEx(sBond_DeedsOfficeExaminationFee));
        Bond_Conveyancer.setText(addCommaEx(sBond_ConveyancerTariffFee));
        Bond_Petties.setText(addCommaEx(sBond_PettieSundryDisbursements));
        Bond_DeedSearchFee.setText(addCommaEx(sBond_DeedsOfficeSearchFee));
        Bond_Fica.setText(addCommaEx(sBond_FICAComplianceFee));
        Bond_Ele.setText(addCommaEx(sBond_ElectronicDocumentGenerationFee));
        Bond_Vat.setText(addCommaEx(sBond_VATonConveyancerTariffFee));
        Bond_Total.setText(addCommaEx(sTotalBondCost));

    }

    //31-05-2018 //28-02-2020
    public double getTransferDuty(double Amt) {
        double txfd = 0;
        if (VatVendor == 1) {
            // seller is a vat vendor
            return (0);
        } else if (VatVendor == 0) { // seller is a natural person
            double x;
            if (Amt <= 1000000) {
                txfd = 0;
            } else if ((Amt > 1000000) && (Amt <= 1375000)) {
                x = (Amt - 1000000) * (0.03);
                txfd = x;

            } else if ((Amt > 1375000) && (Amt <= 1925000)) {
                x = (375000) * (0.03);
                x = x + (Amt - 1375000) * (0.06);
                txfd = x;
            } else if ((Amt > 1925000) && (Amt <= 2475000)) {
                x = (375000) * (0.03);
                x = x + (550000) * (0.06);
                x = x + (Amt - 1925000) * (0.08);
                txfd = x;
            } else if ((Amt > 2475000) && (Amt <= 11000000)) {
                x = (375000) * (0.03);
                x = x + (550000) * (0.06);
                x = x + (550000) * (0.08);
                x = x + (Amt - 2475000) * (0.11);
                txfd = x;
            } else if (Amt > 11000000) {
                x = (375000) * (0.03);
                x = x + (550000) * (0.06);
                x = x + (550000) * (0.08);
                x = x + (8525000) * (0.11);
                x = x + (Amt - 11000000) * (0.13);
                txfd = x;
            }
        }
        return (txfd);
    }

    //01-04-2021
    private double getDeedsOfficeExaminationFee(double v) {
        double exam = 0;

        if (v <= 100000) {
            exam = 39;
        }
        if ((v > 100000) && (v <= 200000)) {
            exam = 86;
        }
        if ((v > 200000) && (v <= 300000)) {
            exam = 539;
        }
        if ((v > 300000) && (v <= 600000)) {
            exam = 673;
        }
        if ((v > 600000) && (v <= 800000)) {
            exam = 946;
        }
        if ((v > 800000) && (v <= 1000000)) {
            exam = 946;
        }
        if ((v > 1000000) && (v <= 2000000)) {
            exam = 1220;
        }
        if ((v > 2000000) && (v <= 4000000)) {
            exam = 1691;
        }
        if ((v > 4000000) && (v <= 6000000)) {
            exam = 2051;
        }
        if ((v > 6000000) && (v <= 8000000)) {
            exam = 2442;
        }
        if ((v > 8000000) && (v <= 10000000)) {
            exam = 2854;
        }
        if ((v > 10000000) && (v <= 15000000)) {
            exam = 3397;
        }
        if ((v > 15000000) && (v <= 20000000)) {
            exam = 4080;
        }
        if (v > 20000000) {
            exam = 4080;
        }


        return exam;
    }

    // 01-04-2021 changed
    private double getConveyancerTariffFee(double Amt) {
        double AmtTXfer1 = 1600;
        double AmtTXfer2 = 800;
        double AmtTXfer3 = 2000;

        double txfer = 0;
        if (Amt <= 100000) {
            txfer = 5200;
        }
        if ((Amt > 100000) && (Amt <= 500000)) {
            txfer = 5200;
        }
        if ((Amt > 500000) && (Amt <= 1000000)) {
            txfer = 11600;
        }
        if ((Amt > 1000000) && (Amt <= 5000000)) {
            txfer = 19600;
        }
        if (Amt > 5000000) {
            txfer = 51600;
        }

        double x;
        if ((Amt > 100000) && (Amt <= 500000)) {
            x = Math.ceil((Amt - 100000) / 50000);
            //alert(xyz);
            txfer = txfer + (x * AmtTXfer2);

        }
        if ((Amt > 500000) && (Amt <= 1000000)) {
            x = Math.ceil((Amt - 500000) / 100000);
            txfer = txfer + (x * AmtTXfer1);

        }
        if ((Amt > 1000000) && (Amt <= 5000000)) {
            x = Math.ceil((Amt - 1000000) / 200000);
            txfer = txfer + (x * AmtTXfer1);
        }

        if (Amt > 5000000) {
            x = Math.ceil((Amt - 5000000) / 500000);
            txfer = txfer + (x * AmtTXfer3);
        }


        return txfer;

    }

    // 31-05-2018
    private double GetVATonConveyancerTariffFee(double Amt) {
        double VAT_rate = 15;
        return ((Amt * (VAT_rate / 100)));

    }

    // 01-04-2020
    private double getBond_DeedsOfficeExaminationFee(double v) {
        double deeds = 0;
        if (v <= 150000) {
            deeds = 417;
        }
        if ((v > 150000) && (v <= 300000)) {
            deeds = 539;
        }
        if ((v > 300000) && (v <= 600000)) {
            deeds = 673;
        }
        if ((v > 600000) && (v <= 800000)) {
            deeds = 946;
        }
        if ((v > 800000) && (v <= 1000000)) {
            deeds = 1086;
        }
        if ((v > 1000000) && (v <= 2000000)) {
            deeds = 1220;
        }
        if ((v > 2000000) && (v <= 4000000)) {
            deeds = 1691;
        }
        if ((v > 4000000) && (v <= 6000000)) {
            deeds = 2051;
        }
        if ((v > 6000000) && (v <= 8000000)) {
            deeds = 2442;
        }
        if ((v > 8000000) && (v <= 10000000)) {
            deeds = 2854;
        }
        if ((v > 10000000) && (v <= 15000000)) {
            deeds = 3397;
        }
        if ((v > 15000000) && (v <= 20000000)) {
            deeds = 4080;
        }
        if ((v > 20000000) && (v <= 30000000)) {
            deeds = 4755;
        }
        if (v > 30000000) {
            deeds = 6794;
        }

        return deeds;
    }


    //31-05-2018
    private double getBond_VATonConveyancerTariffFee(double Amt) {
        double VAT_rate = 15;
        return (Amt * (VAT_rate / 100));

    }

    //31-05-18
    private void CalculateMonthlyInstallment() {

        double int_perc;
        double monthly_int_rate;
        double month_term;

        String tempV = (mBondAmtCal.getText().toString())
                .replaceAll("\\s+", "");
        String tempV1 = (tempV).replace(",", ".");
        double v = Double.parseDouble(tempV1.replace(" ", "")); // bond
        // amount
        String tempI = ((mInterestRate.getText().toString()).replaceAll("\\s+",
                "")).replace(",", ".");

        double i = Double.parseDouble(tempI.replace(" ", "")); // interest
        // rate
        String tempY = (mRepaymentPeriod.getText().toString()).replaceAll(
                "\\s+", "");
        double y = Double.parseDouble(tempY.replace(" ", "")); // years

        int_perc = i / 100;
        monthly_int_rate = int_perc / 12;
        month_term = 12 * y;

        double p = Math.pow((1 + monthly_int_rate), (-1 * month_term));
        double r = (v * (monthly_int_rate / (1 - p)));
        String sr = String.format("%.2f", r);

        mMonthlyInstallment.setText(addCommaEx(sr));

    }

    public String addCommaEx(String A) {
        A = A.replace(" ", "");
        if (A.length() > 9) {

            A = new StringBuilder(A).insert(A.length() - 9, " ").toString();
            A = new StringBuilder(A).insert(A.length() - 6, " ").toString();

        } else if (A.length() > 6) {
            A = new StringBuilder(A).insert(A.length() - 6, " ").toString();

        }

        return A;

    }


}