package com.supperbit.authentication.auth.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, String> auth_data = new HashMap<>();
        auth_data.put("id", "791435265");
        auth_data.put("first_name", "Dai");
        auth_data.put("last_name", "Vuong");
        auth_data.put("photo_url", "https://t.me/i/userpic/320/WArpktuY8AwtknSWTtNWdLKvlahOFFKduW7cEiVqBR4.jpg");
        auth_data.put("auth_date", "1700212965");
        auth_data.put("hash", "265021f4e090a08e0ad4feb933ef6080d53b0b3e6845f0c113775703d2c86c51");

        try {
            checkTelegramAuthorization(auth_data);
            System.out.println("Data is valid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        byte[] hmacSecret = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, botTokenData).hmac(botToken);
//
//        String calculatedHash = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, hmacSecret).hmacHex(dataCheckString);
    }

    public static void checkTelegramAuthorization(Map<String, String> auth_data) throws Exception {
        String check_hash = auth_data.get("hash");
//        auth_data.remove("hash");

        String[] data_check_arr = auth_data.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .toArray(String[]::new);
        Arrays.sort(data_check_arr);

        String data_check_string = String.join("&", data_check_arr);
        String secret_key = "6983372509:AAFyrlgHqb_FLL7qHfnkoeItn1KqPwv9vxs";
        String hash = hmacSha256(data_check_string, secret_key);

        if (!hash.equals(check_hash)) {
            throw new Exception("Data is NOT from Telegram");
        }

        if ((System.currentTimeMillis() / 1000 - Long.parseLong(auth_data.get("auth_date"))) > 86400) {
            throw new Exception("Data is outdated");
        }

    }

    public static String hmacSha256(String data, String secretKey) throws Exception {
        javax.crypto.Mac sha256_HMAC = javax.crypto.Mac.getInstance("HmacSHA256");
        javax.crypto.spec.SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] hash = sha256_HMAC.doFinal(data.getBytes());
        StringBuilder result = new StringBuilder();
        for (byte b : hash) {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }
}


