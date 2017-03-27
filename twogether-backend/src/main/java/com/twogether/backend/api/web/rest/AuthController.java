package com.twogether.backend.api.web.rest;

import com.twogether.backend.api.domain.auth.AuthResponse;
import com.twogether.backend.api.domain.auth.UserLogin;
import com.twogether.backend.api.domain.user.Person;
import com.twogether.backend.api.repository.user.PersonRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@Api(value = "Ces méthodes sont utilisables sans authentification, pour se logger, créer des comptes et changer son mot de passe")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String JWT_KEY_ROLES = "roles";

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @ApiOperation(value = "Méthode pour s'authentifier à l'API avec l'email et le mot de passe (en clair) => HTTPS")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Succès, l'utlisateur/mot de passe est valide"),
            @ApiResponse(code = 401, message = "Echec, l'utlisateur et/ou mot de passe ne sont pas valides") })
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> login(@RequestBody UserLogin login) throws ServletException {
        // On vérifie si les données envoyées par le client sont correctes
        if (!login.isValidated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Person> person = personRepository.findByLogin(login.login);

        if (!person.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!passwordEncoder.matches(login.password, person.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Génération du Json Web Token
        AuthResponse loginResponse = new AuthResponse(Jwts
                .builder()
                .setSubject(person.get().getEmail())
                .claim(JWT_KEY_ROLES, person.get().getAppRoleType())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey).compact());

        return ResponseEntity.ok(loginResponse);
    }
}