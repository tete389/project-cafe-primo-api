package com.example.cafebackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    private final EmployeeService employeeService;

    public TokenService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Algorithm algorithm(){
        return Algorithm.HMAC256(secret);
    }

    ///public String tokenize(UserProfile userProfile)
    public String tokenize(String userProfile){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        Date expiresAt = calendar.getTime();

        if (userProfile.equals("Admin")){
            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("principal",userProfile)
                    .withClaim("role","ADMIN")
                    .withExpiresAt(expiresAt)
                    .sign(algorithm());
        }
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal",userProfile)
                .withClaim("role","USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }
    public DecodedJWT verify(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();
            return verifier.verify(token);

        }catch (Exception e){
            return null;
        }
    }


//    public UserProfile checkTokenUser() throws BaseException {
//        Optional<String> optUser = SecurityUtil.getCurrentUserId();
//        if (optUser.isEmpty()) {
//            throw UserException.unAuthorized();
//        }
//        String userId = optUser.get();
//        Optional<UserProfile> byIdUser = userProfileService.findUserProfileById(userId);
//        if (byIdUser.isEmpty()) {
//            throw UserException.userNotFound();
//        }
//        return byIdUser.get();
//    }
//
//    public Boolean checkAdmin() throws BaseException{
//        Optional<String> optUser = SecurityUtil.getCurrentUserId();
//        if (optUser.isEmpty()) {
//            throw UserException.accessDenied();
//        }
//        return optUser.get().equals("Admin");
//    }
}
