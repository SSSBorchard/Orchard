package com.sssb.orchard;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Shreya on 5/20/18.
 */

public class Util {

    public static String get(String url) throws Exception {

        URL obj = new URL(url);
        Scanner s = new Scanner(obj.openStream());

        String res = "";
        while (s.hasNextLine()) {
            res += s.nextLine() + '\n';
        }

        return res;


    }

}
