package com.example.vussnkrs.ui.Pagina.cesta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vussnkrs.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class CestaFragment extends Fragment {
        private static final int REQUEST_CODE_PAYMENT = 1;
        private PayPalConfiguration config;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            config = new PayPalConfiguration()
                    .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                    .clientId("AUPEciDTB6EytXqKrfspsUJbY5o2RbLxZg0jimzMATfrPMApAsN4eVBp0xW62bALA5OOIiPyb8AlZvoy");

            Intent intent = new Intent(getActivity(), PayPalService.class);
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
            getActivity().startService(intent);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_cesta, container, false);

            Button paypalButton = view.findViewById(R.id.btn_pagar);
            paypalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PayPalPayment payment = new PayPalPayment(new BigDecimal("0.01"), "EUR", "Descripción del pago", PayPalPayment.PAYMENT_INTENT_SALE);

                    Intent intent = new Intent(getActivity(), PaymentActivity.class);
                    intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                    intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                    startActivityForResult(intent, REQUEST_CODE_PAYMENT);
                }
            });

            return view;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_PAYMENT) {
                if (resultCode == Activity.RESULT_OK) {
                    PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                    if (confirm != null) {
                        String paymentId = confirm.getProofOfPayment().getPaymentId();
                        // Procesa el ID de pago según tus necesidades
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // El usuario canceló el proceso de pago
                } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                    // Datos de pago inválidos o faltantes
                }
            }
        }

        @Override
        public void onDestroy() {
            getActivity().stopService(new Intent(getActivity(), PayPalService.class));
            super.onDestroy();
        }


}