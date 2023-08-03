package com.cassio.userapi.util;

public class CpfUtil {
	public static boolean validarCPF(String cpf) {
        // Remova caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifique se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifique se todos os dígitos são iguais (uma sequência de CPF inválida)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != cpf.charAt(9) - '0') {
            return false;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != cpf.charAt(10) - '0') {
            return false;
        }

        // Se passar por todas as verificações, o CPF é válido
        return true;
    }
}
