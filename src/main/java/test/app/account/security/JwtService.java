package test.app.account.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.app.account.entity.User;

@Service
public class JwtService {

  @Value("${token.signing.key}")
  private String jwtSigningKey;


  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    return generateToken(claims, user);
  }

  /**
   * Извлечение данных из токена
   *
   * @param token           токен
   * @param claimsResolvers функция извлечения данных
   * @param <T>             тип данных
   * @return данные
   */
  private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }

  private String generateToken(Map<String, Object> extraClaims, User user) {
    return Jwts.builder().setClaims(extraClaims).setSubject(user.getName())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
  }

  /**
   * Проверка токена на просроченность
   *
   * @param token токен
   * @return true, если токен просрочен
   */
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
   * Извлечение даты истечения токена
   *
   * @param token токен
   * @return дата истечения
   */
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /**
   * Извлечение всех данных из токена
   *
   * @param token токен
   * @return данные
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
        .getBody();
  }

  /**
   * Получение ключа для подписи токена
   *
   * @return ключ
   */
  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public boolean isTokenValid(String token, User user) {
    final String userId = extractUserId(token);
    return (userId.equals(user.getId().toString())) && !isTokenExpired(token);
  }

  public String extractUserId(String token) {
    return extractClaim(token, Claims::getSubject);
  }
}