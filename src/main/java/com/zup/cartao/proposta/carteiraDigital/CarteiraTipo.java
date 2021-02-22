package com.zup.cartao.proposta.carteiraDigital;

public enum CarteiraTipo {

    PAYPAL, SAMSUNGPAY;

    public String converter() {
        if (this.equals(PAYPAL)) {
            return "PayPal";
        }
        return "Samsung Pay";
    }
}
