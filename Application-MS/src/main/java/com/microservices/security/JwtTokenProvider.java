package com.microservices.security;


import com.microservices.entities.User;
import com.microservices.entities.UserRole;
import com.microservices.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

  private String jwtSecret = "ik8rMbBpISVsx/uUZOQsmcw0nGUq3EVu8+ctzbM2Sv3z6KpkICLiBqmBQjgu7MC25a/q1ZB9TYzMddQYEYNajw=="; // Changez ceci avec votre propre clé secrète
  private long jwtExpirationInMs = 3600000;

  public String generateToken(String email, UserRole role) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    return Jwts.builder()
      .setSubject(email)
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .claim("roles", Collections.singletonList(role.name()))  // Store role as a list
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  // Extraire les authorities à partir du token
  public List<SimpleGrantedAuthority> getAuthorities(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody();

    // Récupérer les rôles (par exemple, sous le nom "roles")
    List<String> roles = claims.get("roles", List.class);

    // Vérifier si les rôles sont présents dans le token
    if (roles == null || roles.isEmpty()) {
      // Si aucun rôle n'est trouvé, on pourrait lever une exception ou retourner une liste vide
      // Par exemple, en levant une exception
      throw new IllegalArgumentException("Roles not found in the token");
      // Ou, pour un comportement sans exception, retourner une liste vide ou un rôle générique
      // return Collections.emptyList();
    }
System.out.println(roles);
    // Si des rôles sont présents, les retourner comme une liste de SimpleGrantedAuthority
    return roles.stream()
      .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
      .collect(Collectors.toList());
  }


}
