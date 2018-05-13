package com.ngagerrard.security;

import com.ngagerrard.Constants;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Utils {
    public static String getToken(String username, String key) {
        //gen attribute username from token
        Date date = new Date();
        String jws = Jwts.builder()
                .setIssuer("Stormpath")
                .setSubject("msilverman")
                .claim("name", username)
                .claim("scope", "user")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + Constants.TIME_TOKEN_EXPIRE)) //set thời gian hết hạn của token
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(key)
                )
                .compact();
        return jws;
    }
    public static Date getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        return date;
    }
}
