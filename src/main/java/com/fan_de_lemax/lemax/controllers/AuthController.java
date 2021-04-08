package com.fan_de_lemax.lemax.controllers;

import static org.springframework.http.HttpStatus.OK;

import com.fan_de_lemax.lemax.auth.UserDetailsImpl;
import com.fan_de_lemax.lemax.auth.UserDetailsServiceImpl;
import com.fan_de_lemax.lemax.forms.LoginForm;
import com.fan_de_lemax.lemax.forms.RegisterForm;
import com.fan_de_lemax.lemax.jwt.JwtResponse;
import com.fan_de_lemax.lemax.jwt.JwtUtils;
import com.fan_de_lemax.lemax.models.entities.Role;
import com.fan_de_lemax.lemax.models.entities.User;
import com.fan_de_lemax.lemax.models.repositories.RoleRepository;
import com.fan_de_lemax.lemax.models.repositories.UserRepository;
import com.fan_de_lemax.lemax.models.services.UserService;
import com.fan_de_lemax.lemax.security.EnumRole;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api")
public class AuthController {

//   private UserService userService;
//   private UserDetailsServiceImpl userDetailsServiceImpl;
//   private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//   private String password;
   @Autowired
   AuthenticationManager authenticationManager;

   @Autowired
   RoleRepository roleRepository;

   @Autowired
   PasswordEncoder passwordEncoder;

   @Autowired
   JwtUtils jwtUtils;

   @Autowired
   UserService userService;


   @PostMapping(path="/register")
   public ResponseEntity<String> register(@Valid @RequestBody RegisterForm registerForm){
      //Je vérifie si l'email existe déjà
      if(userService.getUserByUsername(registerForm.getEmail()).isPresent()){
         return new ResponseEntity<String>("Cet email existe déjà dans nos bases", HttpStatus.BAD_REQUEST);
      }

      if(registerForm != null){
         System.out.println(registerForm);
         try{
            //Setting a role
            Role role = roleRepository.findByRoleName(EnumRole.USER).orElseThrow(()-> new RuntimeException("Error: this role does not exists"));
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);

            //encoding password
            String password = registerForm.getPassword();
            String encodedPassword = passwordEncoder.encode(password);

            //Setting the user
            User user = new User();
            user.setUsername(registerForm.getEmail());
            user.setPassword(encodedPassword);
            user.setPseudo(registerForm.getPseudo());
            user.setRoles(roles);

            userService.saveOrUpdateUser(user);
            return new ResponseEntity<String>("L'utilisateur a bien été enregistré!", OK);

         } catch(Exception e){
            System.out.println(e.getMessage());
         }
               return new ResponseEntity<String>("Instantiation failed",HttpStatus.BAD_REQUEST );
         } else {

         return new ResponseEntity<String>("le formulaire est vide", HttpStatus.BAD_REQUEST);
      }

   }

   @PostMapping(path="/login")
   public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm){

//      if(loginForm !=null){
//         System.out.println(loginForm);
//     //Je vérifie que le user existe dans la DB
//         User userInDb = userService.getUserByUsername(loginForm.getEmail())
//             .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "le user " + loginForm.getEmail() + "n'existe pas dans la base de donnée"));
//         System.out.println(userInDb);
//         //Je vérifie que les 2 passwords correspondent
//         String passwordRequest = loginForm.getPassword();
//         String passwordInDb = userInDb.getPassword();
//         boolean passwordMatches = passwordEncoder.matches(passwordRequest, passwordInDb);
//
//         if(passwordRequest == null || !passwordMatches){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Les deux mots de passe ne correspondent pas");
//         }

         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = jwtUtils.generateJwtToken(authentication);
      Object principal = authentication.getPrincipal();
      System.out.println("user récupéré " + principal.toString());
         String finalToken = "Bearer " + jwt;
         UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
         List<String> roles = userDetails.getAuthorities().stream()
             .map(item -> item.getAuthority())
            .collect(Collectors.toList());

         return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

//      } else {
//         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "aucun utilisateur trouvé");
//      }

   }

}
